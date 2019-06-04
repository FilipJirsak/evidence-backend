//vertexes
CREATE CLASS VUzivatel EXTENDS V;
CREATE CLASS VSoubor EXTENDS V;
CREATE CLASS VFaktura EXTENDS V;
CREATE CLASS VCarovyKod EXTENDS V;
CREATE CLASS VAkce EXTENDS V;
CREATE CLASS VUctenka EXTENDS V;
CREATE CLASS VObjednavka EXTENDS V;
CREATE CLASS VText EXTENDS V;

//edges
CREATE CLASS ESoubor EXTENDS E;
CREATE CLASS EVlastnik EXTENDS E;
CREATE CLASS ECarovyKod EXTENDS E;

//vertex properties
CREATE PROPERTY VUzivatel.id INTEGER (MANDATORY TRUE, NOTNULL TRUE);
CREATE INDEX VUzivatel.id UNIQUE;

CREATE PROPERTY VUzivatel.login STRING (MANDATORY TRUE, NOTNULL TRUE);
CREATE INDEX VUzivatel.login UNIQUE;

CREATE PROPERTY VUzivatel.jmeno STRING (MANDATORY TRUE);
CREATE PROPERTY VUzivatel.prijmeni STRING (MANDATORY TRUE);
CREATE PROPERTY VUzivatel.prezdivka STRING (MANDATORY TRUE);

CREATE PROPERTY VUzivatel.email STRING (MANDATORY TRUE, NOTNULL TRUE);
CREATE INDEX VUzivatel.email UNIQUE;

CREATE PROPERTY VUzivatel.variabilni_symbol INTEGER;
CREATE INDEX VUzivatel.variabilni_symbol UNIQUE;

CREATE PROPERTY VSoubor.uuid STRING (MANDATORY TRUE, NOTNULL TRUE, DEFAULT "uuid()", READONLY TRUE);
CREATE PROPERTY VSoubor.mediaType STRING (MANDATORY TRUE, NOTNULL FALSE);
CREATE PROPERTY VSoubor.name STRING (MANDATORY TRUE, NOTNULL FALSE);
CREATE PROPERTY VSoubor.popis STRING (MANDATORY FALSE);
CREATE PROPERTY VSoubor.netadmin STRING (MANDATORY FALSE);
CREATE INDEX VSoubor.uuid UNIQUE;

CREATE PROPERTY VFaktura.uuid STRING (MANDATORY TRUE, NOTNULL TRUE, DEFAULT "uuid()", READONLY TRUE);
CREATE INDEX VFaktura.uuid UNIQUE;

CREATE PROPERTY VFaktura.flexibee_id LONG (MANDATORY TRUE, NOTNULL FALSE);
CREATE INDEX VFaktura.flexibee_id NOTUNIQUE;

CREATE PROPERTY VCarovyKod.hodnota STRING (MANDATORY TRUE);
CREATE INDEX VCarovyKod.hodnota UNIQUE;

CREATE PROPERTY VAkce.uuid STRING (MANDATORY TRUE, NOTNULL TRUE, DEFAULT "uuid()", READONLY TRUE);
CREATE INDEX VAkce.uuid UNIQUE;
CREATE PROPERTY VAkce.nazev STRING (MANDATORY TRUE);
CREATE PROPERTY VAkce.popis EMBEDDED VText (MANDATORY FALSE);
CREATE PROPERTY VAkce.schvalena DATE (MANDATORY FALSE);
CREATE PROPERTY VAkce.odkaz STRING (MANDATORY FALSE);
CREATE PROPERTY VAkce.netadmin INTEGER (MANDATORY FALSE);

CREATE PROPERTY VText.text STRING (MANDATORY TRUE);
CREATE PROPERTY VText.typ STRING (MANDATORY TRUE);

CREATE PROPERTY VUctenka.uuid STRING (MANDATORY TRUE, NOTNULL TRUE, DEFAULT "uuid()", READONLY TRUE);
CREATE INDEX VUctenka.uuid UNIQUE;

CREATE PROPERTY VObjednavka.uuid STRING (MANDATORY TRUE, NOTNULL TRUE, DEFAULT "uuid()", READONLY TRUE);
CREATE INDEX VObjednavka.uuid UNIQUE;

//edge properties
CREATE PROPERTY ESoubor.out LINK VSoubor (MANDATORY TRUE, NOTNULL TRUE);
CREATE PROPERTY EVlastnik.out LINK VUzivatel (MANDATORY TRUE, NOTNULL TRUE);
CREATE PROPERTY ECarovyKod.out LINK VCarovyKod (MANDATORY TRUE, NOTNULL TRUE);

CREATE PROPERTY EVlastnik.timestamp DATETIME(MANDATORY FALSE, NOTNULL TRUE, DEFAULT "sysdate()");
