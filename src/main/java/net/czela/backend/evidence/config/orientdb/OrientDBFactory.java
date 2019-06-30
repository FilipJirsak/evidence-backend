package net.czela.backend.evidence.config.orientdb;

import com.orientechnologies.orient.core.db.*;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;
import jodd.io.StreamUtil;
import jodd.util.ClassLoaderUtil;
import jodd.util.ThreadUtil;
import org.dom4j.DocumentException;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
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
    OrientDBConfig orientDBConfig = OrientDBConfig.builder()
            .build();
    OrientDB orientDB = new OrientDB(configuration.getUrl(), configuration.getUsername(), configuration.getPassword(), orientDBConfig);
    servers.add(orientDB);

    OrientDBConfiguration.DatabaseConfiguration databaseConfiguration = configuration.getDatabase();
    boolean created = orientDB.createIfNotExists(databaseConfiguration.getName(), databaseConfiguration.getType());
    if (created) {
      try (ODatabaseSession session = orientDB.open(databaseConfiguration.getName(), configuration.getUsername(), configuration.getPassword())) {
        try (OResultSet resultSet = session.command(String.format("CREATE USER `%s` IDENTIFIED BY `%s` ROLE `writer`", databaseConfiguration.getUsername(), databaseConfiguration.getPassword()))) {
        }

        for (String scriptName : databaseConfiguration.getScripts()) {
          try (InputStream inputStream = ClassLoaderUtil.getResourceAsStream("orientdb/" + scriptName)) {
            String script = new String(StreamUtil.readChars(inputStream));
            session.execute("sql", script);
          }
        }
      }
    }
    return new ODatabasePool(orientDB, databaseConfiguration.getName(), databaseConfiguration.getUsername(), databaseConfiguration.getPassword());
  }

  @PreDestroy
  public void close() {
    servers.forEach(OrientDB::close);
  }

}
