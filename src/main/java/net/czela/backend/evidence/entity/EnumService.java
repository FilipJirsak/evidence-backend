package net.czela.backend.evidence.entity;

import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.record.impl.ODocument;
import net.czela.backend.evidence.database.OrientDB;
import net.czela.backend.evidence.database.OrientDBService;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Filip
 */
@Singleton
public class EnumService {
  private final OrientDBService orientdb;
  private final Map<ORID, Organ> organByRID = new HashMap<>();
  private final Map<String, Organ> organByKod = new HashMap<>();

  public EnumService(OrientDBService orientdb) {
    this.orientdb = orientdb;
  }

  @PostConstruct
  void init() {
    orientdb.inSession(this::initialize);
  }

  private void initialize() {
    orientdb.readClass(Organ.class, this::readOrgan);
  }

  public Organ organByKod(String kod) {
    return organByKod.get(kod);
  }

  public Organ organByRID(ORID rid) {
    return organByRID.get(rid);
  }

  public Organ organByRID(String rid) {
    return organByRID(new ORecordId(rid));
  }

  private void readOrgan(ODocument document) {
    ORID rid = document.getIdentity();
    int version = document.getVersion();
    String kod = document.field("kod");
    String nazev = document.field("nazev");
    Organ organ = new Organ(kod, nazev, rid, version);
    organByRID.put(rid, organ);
    organByKod.put(kod, organ);
  }
}
