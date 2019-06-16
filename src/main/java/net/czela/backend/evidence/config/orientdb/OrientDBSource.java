package net.czela.backend.evidence.config.orientdb;

import com.orientechnologies.orient.core.db.ODatabaseSession;

/**
 * @author Filip Jirsák
 */
public interface OrientDBSource {
  ODatabaseSession getDatabaseSession();
}
