package cieloecommerce.sdk.ecommerce.request;

import cieloecommerce.sdk.Environment;
import cieloecommerce.sdk.Merchant;
import cieloecommerce.sdk.ecommerce.CardToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * <p>
 *     Call method to request the card registered by token
 * </p>
 */
public class QueryCardTokenRequest extends AbstractSaleRequest<String, CardToken> {
	public QueryCardTokenRequest(Merchant merchant, Environment environment) {
		super(merchant, environment);
	}

	@Override
	public CardToken execute(String param) throws IOException, CieloRequestException {
		String url = environment.getApiQueryURL() + "1/card/" + param;

		HttpGet request = new HttpGet(url);
		HttpResponse response = sendRequest(request);

		return readResponse(response, CardToken.class);
	}
}