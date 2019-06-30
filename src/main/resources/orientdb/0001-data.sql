CREATE VERTEX ZpusobUhrady SET nazev = 'Uhrazeno členem', kod = 'PARAGON';
CREATE VERTEX ZpusobUhrady SET nazev = 'Uhradit předem', kod = 'PROFORMA';
CREATE VERTEX ZpusobUhrady SET nazev = 'Uhradit po dodání', kod = 'FAKTURA';

CREATE VERTEX Organ SET nazev = 'Rada', kod = 'RADA';
CREATE VERTEX Organ SET nazev = 'Komise infrastruktury', kod = 'KI', nadrizenyOrgan = (SELECT FROM Organ WHERE kod = 'RADA');
CREATE VERTEX Organ SET nazev = 'Kontrolní komise', kod = 'KK';