package cieloecommerce.sdk.ecommerce.request;

import cieloecommerce.sdk.Environment;
import cieloecommerce.sdk.Merchant;
import cieloecommerce.sdk.ecommerce.CardToken;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

public class CreateCardTokenRequest extends AbstractSaleRequest<CardToken, CardToken> {
	public CreateCardTokenRequest(Merchant merchant, Environment environment) {
		super(merchant, environment);
	}

	@Override
	public CardToken execute(CardToken param) throws IOException, CieloRequestException {
		String url = environment.getApiUrl() + "1/card/";
		HttpPost request = new HttpPost(url);

		request.setEntity(new StringEntity(new GsonBuilder().create().toJson(param)));

		HttpResponse response = sendRequest(request);

		return readResponse(response, CardToken.class);
	}
}