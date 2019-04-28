CREATE CLASS Uzivatel EXTENDS V;
CREATE CLASS Soubor EXTENDS V;
CREATE CLASS Dokument EXTENDS V;
CREATE CLASS Faktura EXTENDS V;

CREATE CLASS FakturaDokument EXTENDS E;
CREATE CLASS FakturaVlastnik EXTENDS E;
CREATE CLASS DokumentSoubor EXTENDS E;
CREATE CLASS DokumentSouborHlavni EXTENDS E;

CREATE PROPERTY Uzivatel.id INTEGER (MANDATORY TRUE, NOTNULL TRUE);
CREATE INDEX Uzivatel.id UNIQUE;

CREATE PROPERTY Uzivatel.login STRING (MANDATORY TRUE, NOTNULL TRUE);
CREATE INDEX Uzivatel.login UNIQUE;

CREATE PROPERTY Uzivatel.jmeno STRING (MANDATORY TRUE);
CREATE PROPERTY Uzivatel.prijmeni STRING (MANDATORY TRUE);
CREATE PROPERTY Uzivatel.prezdivka STRING (MANDATORY TRUE);

CREATE PROPERTY Uzivatel.email STRING (MANDATORY TRUE, NOTNULL TRUE);
CREATE INDEX Uzivatel.email UNIQUE;

CREATE PROPERTY Uzivatel.variabilni_symbol INTEGER;
CREATE INDEX Uzivatel.variabilni_symbol UNIQUE;

CREATE PROPERTY Soubor.uuid STRING (MANDATORY TRUE, NOTNULL TRUE, DEFAULT "uuid()", READONLY TRUE);
CREATE INDEX Soubor.uuid UNIQUE;

CREATE PROPERTY Faktura.kod STRING (MANDATORY TRUE, NOTNULL TRUE);
CREATE INDEX Faktura.kod UNIQUE;

CREATE PROPERTY Faktura.flexibee_id LONG (MANDATORY TRUE, NOTNULL FALSE);
CREATE INDEX Faktura.flexibee_id UNIQUE;

CREATE PROPERTY FakturaDokument.in LINK Dokument (MANDATORY TRUE, NOTNULL TRUE);
CREATE PROPERTY FakturaDokument.out LINK Faktura (MANDATORY TRUE, NOTNULL TRUE);

CREATE PROPERTY FakturaVlastnik.in LINK Uzivatel (MANDATORY TRUE, NOTNULL TRUE);
CREATE PROPERTY FakturaVlastnik.out LINK Faktura (MANDATORY TRUE, NOTNULL TRUE);

CREATE PROPERTY DokumentSoubor.in LINK Soubor (MANDATORY TRUE, NOTNULL TRUE);
CREATE PROPERTY DokumentSoubor.out LINK Dokument (MANDATORY TRUE, NOTNULL TRUE);

CREATE PROPERTY DokumentSouborHlavni.in LINK Soubor (MANDATORY TRUE, NOTNULL TRUE);
CREATE PROPERTY DokumentSouborHlavni.out LINK Dokument (MANDATORY TRUE, NOTNULL TRUE);
CREATE INDEX DokumentSOuborHlavni_unique ON DokumentSouborHlavni(out, in) UNIQUE;