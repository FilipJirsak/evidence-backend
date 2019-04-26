package net.czela.backend.evidence.ucetnictvi.flexibee;

import org.dom4j.Document;
import org.dom4j.Element;

import javax.inject.Singleton;
import java.util.List;

/**
 * @author Filip Jirsák
 */
@Singleton
public class FlexibeeFaktury {
	private final FlexibeeClient flexibee;

	public FlexibeeFaktury(FlexibeeClient flexibee) {
		this.flexibee = flexibee;
	}

	public Document prijate(FlexibeePage page) {
		return flexibee.fakturaPrijata(null, page.getStart(), page.getLimit(), page.getAddRowCount());
	}

	public Document prijate(String query, FlexibeePage page) {
		return flexibee.fakturaPrijata(query, null, page.getStart(), page.getLimit(), page.getAddRowCount());
	}

	public Document prijate(List<String> seznamId) {
		Document query = Flexibee.createXml();
		Element root = query.getRootElement();
		for (String id : seznamId) {
			root.addElement("id").setText(id);
		}
		return flexibee.fakturaPrijata(
				query,
				Flexibee.customDetail("kod", "stavUhrK", "datSplat", "zbyvaUhradit", "nazFirmy", "ic", "popis", "buc", "smerKod(kod)", "varSym", "konSym", "specSym", "bankovniUcet(buc)"),
				"/faktura-prijata/smerKod,/faktura-prijata/bankovniUcet");
	}

	public Document prijateNeuhrazene(FlexibeePage page) {
		return prijate("stavUhrK is null", page);
	}
}
