package cieloecommerce.sdk.ecommerce;

import cieloecommerce.sdk.Merchant;
import cieloecommerce.sdk.ecommerce.request.*;
import org.apache.http.client.HttpClient;

import java.io.IOException;

/**
 * The Cielo Ecommerce SDK front-end;
 */
public class CieloEcommerce {
	private final Merchant merchant;
	private final Environment environment;
	private HttpClient httpClient;

	/**
	 * Create an instance of CieloEcommerce choosing the environment where the
	 * requests will be send
	 *
	 * @param merchant
	 *            The merchant credentials
	 * @param environment
	 *            The environment: {@link Environment#PRODUCTION} or
	 *            {@link Environment#SANDBOX}
	 */
	public CieloEcommerce(Merchant merchant, Environment environment) {
		this.merchant = merchant;
		this.environment = environment;
	}

	/**
	 * Create an instance of CieloEcommerce to work on production environment
	 *
	 * @param merchant
	 *            The merchant credentials
	 */
	public CieloEcommerce(Merchant merchant) {
		this(merchant, Environment.PRODUCTION);
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * Send the Sale to be created and return the Sale with tid and the status
	 * returned by Cielo.
	 *
	 * @param sale
	 *            The preconfigured Sale
	 * @return The Sale with authorization, tid, etc. returned by Cielo.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public Sale createSale(Sale sale) throws IOException, CieloRequestException {
		CreateSaleRequest createSaleRequest = new CreateSaleRequest(merchant, environment);

		createSaleRequest.setHttpClient(httpClient);

		sale = createSaleRequest.execute(sale);

		return sale;
	}

	/**
	 * Create a card token to be stored on store
	 * 
	 * @param cardToken
	 *            The credit card data
	 * @return The card token
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public CardToken createCardToken(CardToken cardToken) throws IOException, CieloRequestException {
		CreateCardTokenRequest createCardTokenRequest = new CreateCardTokenRequest(merchant, environment);

		createCardTokenRequest.setHttpClient(httpClient);

		cardToken = createCardTokenRequest.execute(cardToken);

		return cardToken;
	}

	/**
	 * Query a {@link CardToken} on Cielo by card token
	 *
	 * @param cardToken
	 *            The cardToken to be queried
	 * @return The {@link CardToken} with tokenized card number, holder name, and expiration date
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public CardToken queryCardToken(String cardToken) throws IOException, CieloRequestException {
		QueryCardTokenRequest queryCardTokenRequest = new QueryCardTokenRequest(merchant, environment);

		queryCardTokenRequest.setHttpClient(httpClient);

		return queryCardTokenRequest.execute(cardToken);
	}

	/**
	 * Query a Sale on Cielo by paymentId
	 *
	 * @param paymentId
	 *            The paymentId to be queried
	 * @return The Sale with authorization, tid, etc. returned by Cielo.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public Sale querySale(String paymentId) throws IOException, CieloRequestException {
		QuerySaleRequest querySaleRequest = new QuerySaleRequest(merchant, environment);

		querySaleRequest.setHttpClient(httpClient);

		Sale sale = querySaleRequest.execute(paymentId);

		return sale;
	}

	/**
	 * Cancel a Sale on Cielo by paymentId and speficying the amount
	 *
	 * @param paymentId
	 *            The paymentId to be queried
	 * @param amount
	 *            Order value in cents
	 * @return The Sale with authorization, tid, etc. returned by Cielo.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public Sale cancelSale(String paymentId, Integer amount) throws IOException, CieloRequestException {
		UpdateSaleRequest updateSaleRequest = new UpdateSaleRequest("void", merchant, environment);

		updateSaleRequest.setHttpClient(httpClient);
		updateSaleRequest.setAmount(amount);

		Sale sale = updateSaleRequest.execute(paymentId);

		return sale;
	}

	/**
	 * Cancel a Sale on Cielo by paymentId
	 *
	 * @param paymentId
	 *            The paymentId to be queried
	 * @return The Sale with authorization, tid, etc. returned by Cielo.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public Sale cancelSale(String paymentId) throws IOException, CieloRequestException {
		return cancelSale(paymentId, null);
	}

	/**
	 * Capture a Sale on Cielo by paymentId and specifying the amount and the
	 * serviceTaxAmount
	 *
	 * @param paymentId
	 *            The paymentId to be captured
	 * @param amount
	 *            Amount of the authorization to be captured
	 * @param serviceTaxAmount
	 *            Amount of the authorization should be destined for the service
	 *            charge
	 * @return The Sale with authorization, tid, etc. returned by Cielo.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public Sale captureSale(String paymentId, Integer amount, Integer serviceTaxAmount)
			throws IOException, CieloRequestException {
		UpdateSaleRequest updateSaleRequest = new UpdateSaleRequest("capture", merchant, environment);

		updateSaleRequest.setHttpClient(httpClient);
		updateSaleRequest.setAmount(amount);
		updateSaleRequest.setServiceTaxAmount(serviceTaxAmount);

		Sale sale = updateSaleRequest.execute(paymentId);

		return sale;
	}

	/**
	 * Capture a Sale on Cielo by paymentId and specifying the amount
	 *
	 * @param paymentId
	 *            The paymentId to be captured
	 * @param amount
	 *            Amount of the authorization to be captured
	 * @return The Sale with authorization, tid, etc. returned by Cielo.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public Sale captureSale(String paymentId, Integer amount) throws IOException, CieloRequestException {
		return captureSale(paymentId, amount, null);
	}

	/**
	 * Capture a Sale on Cielo by paymentId
	 *
	 * @param paymentId
	 *            The paymentId to be captured
	 * @return The Sale with authorization, tid, etc. returned by Cielo.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public Sale captureSale(String paymentId) throws IOException, CieloRequestException {
		return captureSale(paymentId, null, null);
	}
}
