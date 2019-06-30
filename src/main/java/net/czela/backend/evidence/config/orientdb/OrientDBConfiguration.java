package net.czela.backend.evidence.config.orientdb;

import com.orientechnologies.orient.core.db.ODatabaseType;
import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.EachProperty;

import java.util.List;

/**
 * @author Filip Jirsák
 */
@EachProperty("orientdb")
public class OrientDBConfiguration {
	private String url;
	private DatabaseConfiguration database = new DatabaseConfiguration();
	private String username;
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DatabaseConfiguration getDatabase() {
		return database;
	}

	public void setDatabase(DatabaseConfiguration database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ConfigurationProperties("database")
	public static class DatabaseConfiguration {
		private String name;
		private String username;
		private String password;
		private ODatabaseType type;
		private List<String> scripts;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public ODatabaseType getType() {
			return type;
		}

		public void setType(ODatabaseType type) {
			this.type = type;
		}

		public List<String> getScripts() {
			return scripts;
		}

		public void setScripts(List<String> scripts) {
			this.scripts = scripts;
		}
	}

}
