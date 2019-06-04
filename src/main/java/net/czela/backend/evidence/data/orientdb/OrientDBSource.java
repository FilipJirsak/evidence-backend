package net.czela.backend.evidence.data.orientdb;

import com.orientechnologies.orient.core.db.ODatabaseSession;

/**
 * @author Filip Jirsák
 */
public interface OrientDBSource {
  ODatabaseSession getDatabaseSession();
}
