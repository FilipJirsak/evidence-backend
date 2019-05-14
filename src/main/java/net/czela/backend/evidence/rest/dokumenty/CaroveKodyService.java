package net.czela.backend.evidence.rest.dokumenty;

import javax.inject.Singleton;
import java.util.regex.Pattern;

/**
 * Služby pro práci s čárovými kódy.
 *
 * Čárový kód má formát {@code CNNNNNNNNNKK}, kde:
 * <dl>
 *     <dt>C</dt>
 *     <dd>Jeden znak – velké písmeno anglické abecedy. Určuje typ čárového kódu (způsob použití).</dd>
 *     <dt>N</dt>
 *     <dd>Číslice 0–9 – celkem devět číslic označujících pořadí čárového kódu.</dd>
 *     <dt>K</dt>
 *     <dd>Číslice 0–9 – dvě číslice – kontrolní součet.</dd>
 * </dl>
 *
 * <h3>Výpočet kontrolního součtu</h3>
 * Hodnota znaku {@code C} se převede na číslo v rozsahu 10–27 (A=10, B=11 atd.) a uvede se v dekadickém zápisu, za ní se v dekadickém zápisu připojí pořadové číslo
 * čárového kódu (v dekadickém zápisu uvozeném nulami tak, aby mělo 9 znaků). Výsledné jedenáctimístné se vydělí 97. Zbytek po dělení
 * se odečte od hodnoty 98. Výsledné číslo (kontrolní součet) se připojí za čárový kód – pokud je menší než 10, doplní se zleva nulou.
 * @author Filip Jirsák
 */
@Singleton
public class CaroveKodyService {
	private static final Pattern RE_CAROVY_KOD = Pattern.compile("[A-Z]\\d{11}");
	private static final int MAX_NUMBER = 999_999_999;
	public String generuj(char prefix, int ordinalNumber) {
		int checkDigit = computeCheckDigit(prefix, ordinalNumber);
		return String.format("%1$c%2$09d%3$02d", prefix, ordinalNumber, checkDigit);
	}

	public boolean validuj(String carovyKod) {
		try {
			validujSChybou(carovyKod);
			return true;
		} catch (RuntimeException ex) {
			return false;
		}
	}

	public void validujSChybou(String carovyKod) {
		if (carovyKod == null || carovyKod.isEmpty()) {
			throw new IllegalArgumentException("Čárový kód nesmí být prázdný");
		}
		if (carovyKod.length() == 12) {
			throw new IllegalArgumentException("Čárový kód musí mít 12 znaků.");
		}
		if (!RE_CAROVY_KOD.matcher(carovyKod).matches()) {
			throw new IllegalArgumentException("Čárový kód musí odpovídat vzoru CNNNNNNNNNNN.");
		}
		int number = Integer.parseInt(carovyKod.substring(1));
		int ordinalNumber = number / 100;
		int checkDigit = number % 100;
		if (checkDigit != computeCheckDigit(carovyKod.charAt(0), ordinalNumber)) {
			throw new IllegalArgumentException("Kontrolní součet čárového kódu neosuhlasí.");
		}
	}

	private int computeCheckDigit(char prefix, int ordinalNumber) {
		if (prefix < 'A' || prefix > 'Z') {
			throw new IllegalArgumentException(String.format("Znak typu čárového kódu '%c' je mimo povolený rozsah A–Z.", prefix));
		}
		if (ordinalNumber <= 0 || ordinalNumber > MAX_NUMBER) {
			throw new IllegalArgumentException(String.format("Pořadové číslo čárového kódu %d je mimo povolený rozsah %d–%d.", ordinalNumber, 0, MAX_NUMBER));
		}
		long number = (1_000_000_000L * (prefix  - 55)) + ordinalNumber;
		long checkDigit = 98 - (number % 97);
		assert checkDigit >= 0 && checkDigit <= 99;
		return (int) checkDigit;
	}
}
