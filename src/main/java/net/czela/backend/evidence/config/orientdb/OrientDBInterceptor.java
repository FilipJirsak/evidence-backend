package net.czela.backend.evidence.config.orientdb;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import net.czela.backend.evidence.data.orientdb.OrientDB;

import javax.inject.Singleton;

/**
 * @author Filip Jirsák
 */
@Singleton
public class OrientDBInterceptor implements MethodInterceptor<Object, Object> {
  private final OrientDBSourceImpl orientDBSource;

  public OrientDBInterceptor(OrientDBSourceImpl orientDBSource) {
    this.orientDBSource = orientDBSource;
  }

  @Override
  public Object intercept(MethodInvocationContext<Object, Object> context) {
    boolean readOnly = context.getValue(OrientDB.class, "readOnly", Boolean.class).get();
    boolean started = orientDBSource.startSession();
    ODatabaseSession databaseSession = null;
    try {
      databaseSession = orientDBSource.getDatabaseSession();
      Object result = context.proceed();
      if (!readOnly) {
        databaseSession.commit();
      }
      return result;
    } catch (RuntimeException e) {
      if (!readOnly && databaseSession != null) {
        databaseSession.rollback();
      }
      throw e;
    } finally {
      if (started) {
        orientDBSource.endSession();
      }
    }
  }
}
