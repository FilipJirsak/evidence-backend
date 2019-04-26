package net.czela.backend.evidence.ucetnictvi.flexibee;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author Filip Jirsák
 */
public class Flexibee {
	public static final String DETAIL_ID = "id";
	public static final String DETAIL_SUMMARY = "summary";
	public static final String DETAIL_FULL = "full";
	public static final String DETAIL_CUSTOM = "custom:";

	public static Document createXml() {
		Element root = DocumentHelper.createElement("winstrom");
		return DocumentHelper.createDocument(root);
	}

	public static String customDetail(String... polozky) {
		StringBuilder builder = new StringBuilder(DETAIL_CUSTOM);
		boolean first = true;
		for(String polozka : polozky) {
			if (first) {
				first = false;
			} else {
				builder.append(',');
			}
			builder.append(polozka);
		}
		return builder.toString();
	}
}
