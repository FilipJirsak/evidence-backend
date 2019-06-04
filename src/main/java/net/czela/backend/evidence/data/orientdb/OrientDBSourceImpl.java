package net.czela.backend.evidence.data.orientdb;

import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.ODatabaseSession;

import javax.inject.Singleton;

/**
 * @author Filip Jirsák
 */
@Singleton
public class OrientDBSourceImpl implements OrientDBSource {
  private final ThreadLocal<ODatabaseSession> threadLocalSession = new ThreadLocal<>();
  private final ODatabasePool databasePool;

  public OrientDBSourceImpl(ODatabasePool databasePool) {
    this.databasePool = databasePool;
  }

  @Override
  public ODatabaseSession getDatabaseSession() {
    return threadLocalSession.get();
  }

  public boolean startSession() {
    if (threadLocalSession.get() != null) {
      return false;
    }
    ODatabaseSession databaseSession = databasePool.acquire();
    threadLocalSession.set(databaseSession);
    return true;
  }

  public void endSession() {
    ODatabaseSession databaseSession = threadLocalSession.get();
    assert databaseSession != null;
    threadLocalSession.remove();
    databaseSession.close();
  }
}
