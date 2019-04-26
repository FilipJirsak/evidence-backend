package net.czela.backend.evidence.data.db;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Filip Jirsák
 */
@Factory
public class OrientDBFactory {
	private final List<OrientDB> servers = new LinkedList<>();

	@Singleton
	@Bean(preDestroy = "close")
	@EachBean(OrientDBConfiguration.class)
	public ODatabaseSession orientDB(OrientDBConfiguration configuration) {
		OrientDB orientDB = new OrientDB(configuration.getUrl(), OrientDBConfig.defaultConfig());
		servers.add(orientDB);
		return orientDB.open(configuration.getDatabase(), configuration.getUsername(), configuration.getPassword());
	}

	@PreDestroy
	public void close() {
		servers.forEach(OrientDB::close);
	}

}
