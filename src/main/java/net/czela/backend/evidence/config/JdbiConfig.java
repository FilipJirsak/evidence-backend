package net.czela.backend.evidence.config;

import io.micronaut.context.annotation.Factory;
import net.czela.backend.evidence.domain.akce.Akce;
import net.czela.backend.evidence.domain.akce.Pridel;
import net.czela.backend.evidence.domain.netadmin.Uzivatel;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.core.mapper.reflect.internal.PojoMapperFactory;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.inject.Singleton;
import javax.sql.DataSource;

/**
 * @author Filip
 */
@Factory
class JdbiConfig {

  @Singleton
  Jdbi jdbi(DataSource dataSource) {
    Jdbi jdbi = Jdbi.create(dataSource);
    jdbi.installPlugin(new SqlObjectPlugin());
    jdbi.registerRowMapper(BeanMapper.factory(Uzivatel.class));
    jdbi.registerRowMapper(BeanMapper.factory(Akce.class));
    jdbi.registerRowMapper(BeanMapper.factory(Pridel.class));
    return jdbi;
  }
}
