package net.czela.backend.evidence.domain.akce;

import net.czela.backend.evidence.domain.netadmin.Uzivatel;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

/**
 * @author Filip
 */
public interface AkceDao {
  @SqlQuery("SELECT * FROM akce.v_akce ORDER BY id")
  List<Akce> seznam();

  @SqlQuery("SELECT DISTINCT v_akce.* FROM akce.v_akce JOIN akce.akce_role ON (v_akce.id = akce_role.akce_id) WHERE akce_role.uzivatel_id = ? ORDER BY v_akce.id")
  List<Akce> seznamUzivatele(int uzivatelId);

  @SqlQuery("SELECT * FROM akce.v_akce  WHERE id = ?")
  Akce detail(int id);

  @SqlQuery("SELECT * FROM akce.pridel WHERE akce_id = ?")
  List<Pridel> pridely(int akceId);

  @SqlQuery("SELECT uzivatel_id FROM akce.akce_role WHERE akce_id = ? AND role_id = ?")
  List<Integer> role(int akceId, int roleId);
}
