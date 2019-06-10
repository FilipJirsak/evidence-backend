//vertices
CREATE CLASS Uzivatel EXTENDS V;
CREATE CLASS Soubor EXTENDS V;
CREATE CLASS Faktura EXTENDS V;
CREATE CLASS CarovyKod EXTENDS V;
CREATE CLASS Objednavka EXTENDS V;
CREATE CLASS FormatovanyText EXTENDS V;
CREATE CLASS Akce EXTENDS V;
CREATE CLASS Pridel EXTENDS V;
CREATE CLASS ZpusobUhrady EXTENDS V;

//edges
CREATE CLASS Vlastnik EXTENDS E;

//vertex properties
CREATE PROPERTY Uzivatel.id INTEGER (MANDATORY TRUE, NOTNULL TRUE);
CREATE PROPERTY Uzivatel.login STRING (MANDATORY TRUE, NOTNULL TRUE);
CREATE PROPERTY Uzivatel.jmeno STRING (MANDATORY TRUE);
CREATE PROPERTY Uzivatel.prijmeni STRING (MANDATORY TRUE);
CREATE PROPERTY Uzivatel.prezdivka STRING (MANDATORY TRUE);
CREATE PROPERTY Uzivatel.email STRING (MANDATORY TRUE);
CREATE PROPERTY Uzivatel.variabilni_symbol INTEGER;
CREATE INDEX Uzivatel.id UNIQUE;
CREATE INDEX Uzivatel.login UNIQUE;
CREATE INDEX Uzivatel.variabilni_symbol UNIQUE;
//CREATE INDEX Uzivatel.email UNIQUE;

CREATE PROPERTY Soubor.uuid STRING (MANDATORY TRUE, NOTNULL TRUE, DEFAULT "uuid()", READONLY TRUE);
CREATE PROPERTY Soubor.mediaType STRING (MANDATORY TRUE, NOTNULL FALSE);
CREATE PROPERTY Soubor.name STRING (MANDATORY TRUE, NOTNULL FALSE);
CREATE PROPERTY Soubor.popis STRING (MANDATORY FALSE);
CREATE PROPERTY Soubor.netadmin STRING (MANDATORY FALSE);
CREATE INDEX Soubor.uuid UNIQUE;

CREATE PROPERTY CarovyKod.hodnota STRING (MANDATORY TRUE);
CREATE INDEX CarovyKod.hodnota UNIQUE;

CREATE PROPERTY Akce.nazev STRING (MANDATORY TRUE);
CREATE PROPERTY Akce.popis EMBEDDED FormatovanyText (MANDATORY TRUE);
CREATE PROPERTY Akce.schvalena DATE (MANDATORY TRUE);
CREATE PROPERTY Akce.odkaz STRING (MANDATORY TRUE);
CREATE PROPERTY Akce.netadmin INTEGER (MANDATORY FALSE);

CREATE PROPERTY Pridel.akce LINK Akce (MANDATORY TRUE, NOTNULL TRUE);
CREATE PROPERTY Pridel.popis EMBEDDED FormatovanyText (MANDATORY TRUE);
CREATE PROPERTY Pridel.schvalen DATE (MANDATORY TRUE);
CREATE PROPERTY Pridel.odkaz STRING (MANDATORY TRUE);

CREATE PROPERTY FormatovanyText.text STRING (MANDATORY TRUE);
CREATE PROPERTY FormatovanyText.format STRING (MANDATORY TRUE);

CREATE PROPERTY Objednavka.popis STRING (MANDATORY TRUE);
CREATE PROPERTY Objednavka.castka DECIMAL (MANDATORY TRUE);
CREATE PROPERTY Objednavka.datumObjednani DATE (MANDATORY TRUE);
CREATE PROPERTY Objednavka.datumSplatnosti DATE;
CREATE PROPERTY Objednavka.zpusobUhrady LINK ZpusobUhrady (MANDATORY TRUE);
CREATE PROPERTY Objednavka.uhrazenoKym LINK Uzivatel;
CREATE PROPERTY Objednavka.dodavatel STRING;
CREATE PROPERTY Objednavka.ico STRING;
CREATE PROPERTY Objednavka.cisloUctu STRING;
CREATE PROPERTY Objednavka.variabilniSymbol STRING;
CREATE PROPERTY Objednavka.poznamka EMBEDDED FormatovanyText;

CREATE PROPERTY ZpusobUhrady.kod BYTE (MANDATORY TRUE, NOTNULL TRUE);
CREATE PROPERTY ZpusobUhrady.nazev STRING (MANDATORY TRUE, NOTNULL TRUE);
CREATE INDEX ZpusobUhrady.kod UNIQUE;

//edge properties
CREATE PROPERTY Vlastnik.out LINK Uzivatel (MANDATORY TRUE, NOTNULL TRUE);

//data
INSERT INTO ZpusobUhrady (kod, nazev) VALUES (1, 'Uhrazeno členem');
INSERT INTO ZpusobUhrady (kod, nazev) VALUES (2, 'Uhradit předem');
INSERT INTO ZpusobUhrady (kod, nazev) VALUES (3, 'Uhradit po dodání');