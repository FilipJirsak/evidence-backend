package net.czela.backend.evidence.rest.soubor;

/**
 * @author Filip
 */
public class NovaFaktura {
	private String souborUUID;
	private int kod;

	public NovaFaktura(String souborUUID, int kod) {
		this.souborUUID = souborUUID;
		this.kod = kod;
	}

	public String getSouborUUID() {
		return souborUUID;
	}

	public void setSouborUUID(String souborUUID) {
		this.souborUUID = souborUUID;
	}

	public int getKod() {
		return kod;
	}

	public void setKod(int kod) {
		this.kod = kod;
	}
}
