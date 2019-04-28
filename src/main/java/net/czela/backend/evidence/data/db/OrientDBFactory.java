package net.czela.backend.evidence.data.db;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OType;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;
import jodd.util.ClassLoaderUtil;
import jodd.util.ThreadUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
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

	@Singleton
	@Bean(preDestroy = "close")
	@EachBean(OrientDBConfiguration.class)
	public ODatabaseSession orientDB(OrientDBConfiguration configuration) throws IOException, DocumentException {
		OrientDB orientDB = new OrientDB(configuration.getUrl(), OrientDBConfig.defaultConfig());
		servers.add(orientDB);
		return orientDB.open(configuration.getDatabase(), configuration.getUsername(), configuration.getPassword());
	}

	@PreDestroy
	public void close() {
		servers.forEach(OrientDB::close);
	}

}
