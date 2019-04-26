package net.czela.backend.evidence.ucetnictvi.flexibee;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import org.dom4j.Document;

import javax.annotation.Nullable;
import javax.inject.Singleton;
import javax.validation.constraints.Null;

/**
 * @author Filip Jirsák
 */
@Singleton
@Client(FlexibeeConfiguration.SERVICE_ID)
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
interface FlexibeeClient {

	@Get("/faktura-prijata")
	Document fakturaPrijata(@QueryValue @Nullable String detail, @Nullable @QueryValue Integer start, @Nullable @QueryValue Integer limit, @Nullable @QueryValue("add-row-count") Boolean addRowCount);

	@Get("/faktura-prijata/({query})")
	Document fakturaPrijata(String query, @QueryValue @Nullable String detail, @QueryValue @Nullable Integer start, @QueryValue @Nullable Integer limit, @Nullable @QueryValue("add-row-count") Boolean addRowCount);

	@Post("/faktura-prijata/get")
	Document fakturaPrijata(@Body Document query, @QueryValue @Nullable String detail, @QueryValue @Nullable String includes);
}
