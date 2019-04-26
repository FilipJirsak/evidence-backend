package net.czela.backend.evidence.data.db;

import io.micronaut.context.annotation.Factory;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;

/**
 * @author Filip Jirsák
 */
@Factory
public class MySQLFactory {
	@Singleton
	public Jdbi jdbi(@Named("netadmin") DataSource dataSource) {
		return Jdbi.create(dataSource);
	}
}
