package net.czela.backend.evidence.data.db;

import com.orientechnologies.orient.core.db.ODatabaseSession;

import javax.inject.Singleton;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Filip
 */
@Singleton
public class OrientDBService {
	private final ODatabaseSession session;

	public OrientDBService(ODatabaseSession session) {
		this.session = session;
	}

	public void withSession(Consumer<ODatabaseSession> action) {
		session.begin();
		try {
			action.accept(session);
			session.commit();
		} catch (RuntimeException e) {
			session.rollback();
			throw e;
		}
	}

	public <T> T inSession(Function<ODatabaseSession, T> function) {
		session.begin();
		try {
			T result = function.apply(session);
			session.commit();
			return result;
		} catch (RuntimeException e) {
			session.rollback();
			throw e;
		}
	}
}
