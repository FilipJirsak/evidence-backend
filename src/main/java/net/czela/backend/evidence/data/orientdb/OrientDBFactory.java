package net.czela.backend.evidence.data.orientdb;

import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;
import org.dom4j.DocumentException;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Filip Jirsák
 */
@Factory
public class OrientDBFactory {
  private final List<OrientDB> servers = new LinkedList<>();

/*
	@Bean(preDestroy = "close")
	@EachBean(OrientDBConfiguration.class)
	public OrientDB orientDB(OrientDBConfiguration configuration) throws IOException, DocumentException {
		OrientDB orientDB = new OrientDB(configuration.getUrl(), OrientDBConfig.defaultConfig());
		servers.add(orientDB);
		return orientDB;
	}
*/

  @Bean(preDestroy = "close")
  @EachBean(OrientDBConfiguration.class)
  public ODatabasePool orientDBPool(OrientDBConfiguration configuration) throws IOException, DocumentException {
    OrientDB orientDB = new OrientDB(configuration.getUrl(), OrientDBConfig.defaultConfig());
    servers.add(orientDB);

    return new ODatabasePool(orientDB, configuration.getDatabase(), configuration.getUsername(), configuration.getPassword());
  }

  @PreDestroy
  public void close() {
    servers.forEach(OrientDB::close);
  }

}
