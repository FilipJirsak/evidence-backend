package net.czela.backend.evidence.domain.netadmin;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

/**
 * @author Filip
 */
public interface UzivatelDao {
  @SqlQuery("SELECT * FROM netadmin.uzivatel WHERE id = ?")
  Uzivatel uzivatelById(int id);

  @SqlQuery("SELECT * FROM netadmin.uzivatel WHERE username = ?")
  Uzivatel uzivatelByUsername(String username);

  @SqlQuery("SELECT id FROM netadmin.uzivatel WHERE username = ?")
  int idByUsername(String username);

  @SqlQuery("SELECT * FROM netadmin.uzivatel ORDER BY prijmeni, jmeno, id")
  List<Uzivatel> seznamUzivatelu();
}
