package net.czela.backend.evidence.data.db;

import io.micronaut.context.annotation.EachProperty;

/**
 * @author Filip Jirsák
 */
//@EachProperty("orientdb")
public class OrientDBConfiguration {
	private String url;
	private String database;
	private String username;
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
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
}
