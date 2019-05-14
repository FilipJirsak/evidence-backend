package net.czela.backend.evidence.rest.dokumenty;

import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OVertex;

import javax.inject.Singleton;

/**
 * @author Filip
 */
@Singleton
public class SouborService {
	private final ODatabaseSession session;

	public SouborService(ODatabaseSession session) {
		this.session = session;
	}

	public SouborUUID vytvoritSoubor() {
		try (ODatabase database = session.begin()) {
			final OVertex vertex = session.newVertex("Soubor");
			session.commit();
			return new SouborUUID(vertex.getProperty("uuid"));
		}
	}
}
