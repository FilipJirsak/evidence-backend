package net.czela.backend.evidence.ucetnictvi.flexibee;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;

/**
 * @author Filip Jirsák
 */
@Filter()
@Requires(property = FlexibeeConfiguration.PREFIX + ".username")
@Requires(property = FlexibeeConfiguration.PREFIX + ".password")
public class FlexibeeAuthFilter implements HttpClientFilter {
	private final FlexibeeConfiguration configuration;

	public FlexibeeAuthFilter(FlexibeeConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
		return chain.proceed(request.basicAuth(configuration.getUsername(), configuration.getPassword()));
	}
}
