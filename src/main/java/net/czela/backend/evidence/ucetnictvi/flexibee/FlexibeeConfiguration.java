package net.czela.backend.evidence.ucetnictvi.flexibee;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;

/**
 * @author Filip Jirsák
 */
@ConfigurationProperties(FlexibeeConfiguration.PREFIX)
@Requires(property = FlexibeeConfiguration.PREFIX)
public class FlexibeeConfiguration {
	public static final String SERVICE_ID = "flexibee";
	public static final String PREFIX = "flexibee";

	private String url;
	private String username;
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
