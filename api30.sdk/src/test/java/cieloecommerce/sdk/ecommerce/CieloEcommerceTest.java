package cieloecommerce.sdk.ecommerce;

import cieloecommerce.sdk.Merchant;
import cieloecommerce.sdk.ecommerce.request.CieloError;
import cieloecommerce.sdk.ecommerce.request.CieloRequestException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by marciocamurati on 31/01/17.
 */
public class CieloEcommerceTest {

    private Merchant merchant;

    private Environment environment;

    private static String paymentToRefund;
    
    private final Integer amount = 1 * 100; // R$ 1.00

    @Before
    public void setUp() throws Exception {
        merchant = new Merchant("4d019d78-aaf2-487c-b6d6-8d24b152ac46", "NSNFSDYCDQCZSORWJUXGUAEYYZTIJYZLQHLHMCTH");
        environment = Environment.SANDBOX;
    }

    /**
     * <p>
     *     Debit card payment method test
     * </p>
     */
    @Test
    public void testWithDebitCard() {
        Sale sale = new Sale("1");

        Customer customer = sale.customer("Nonononno");

        Assert.assertNotNull(customer);

        Payment payment = sale.payment(amount);
        payment.setReturnUrl("http://requestb.in/1ezw2rt1");

        Assert.assertNotNull(payment);

        payment.debitCard("123", "Master")
                .setExpirationDate("05/2018")
                .setCardNumber("5453010000066167")
                .setHolder("Nonononno");

        payment.setSoftDescriptor("Zona Azul");

        try {
            sale = new CieloEcommerce(merchant, environment).createSale(sale);

            Assert.assertNotNull(sale);

            Assert.assertSame(0, sale.getPayment().getStatus());

            Assert.assertEquals("1", sale.getPayment().getReturnCode());
            
            String paymentId = sale.getPayment().getPaymentId();

            Assert.assertNotNull(paymentId);
        } catch (CieloRequestException e) {
            CieloError error = e.getError();
            Assert.fail();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    /**
     * <p>
     *     Credit card payment method test
     * </p>
     */
    @Test
    public void testWithCreditCard() {
        Sale sale = new Sale("1");

        Customer customer = sale.customer("Nonononno");

        Assert.assertNotNull(customer);

        Payment payment = sale.payment(amount);

        Assert.assertNotNull(payment);

        payment.creditCard("123", "Diners")
                .setExpirationDate("05/2017")
                .setCardNumber("36490102462661")
                .setHolder("Nonononno");

        payment.setSoftDescriptor("CAD");

        try {
            sale = new CieloEcommerce(merchant, environment).createSale(sale);

            Assert.assertNotNull(sale);

            String paymentId = sale.getPayment().getPaymentId();

            paymentToRefund = paymentId;
            
            Assert.assertNotNull(paymentId);

            sale = new CieloEcommerce(merchant, environment).captureSale(paymentId, amount, 0);

            Assert.assertSame(0, sale.getReasonCode());
        } catch (CieloRequestException e) {
            CieloError error = e.getError();
            Assert.fail();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    /**
     * <p>
     *     Tokenizer a card
     * </p>
     */
    @Test
    public void testTokenizer() {
        String cardToken = "ea85965a-b215-4d2b-98ed-2a4a1239d50f";

        try {
            CardToken card = new CieloEcommerce(merchant, environment).queryCardToken(cardToken);

            Assert.assertEquals("COMPRADOR T CIELO", card.getHolder());
        } catch (CieloRequestException e) {
            CieloError error = e.getError();
            Assert.fail();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    /**
     * <p>
     *     Refund with payment id
     * </p>
     */
    @Test
    public void testRefund() {
        try {
            Sale sale = new CieloEcommerce(merchant, environment).cancelSale(paymentToRefund, amount);

            Assert.assertSame(0, sale.getReasonCode());
        } catch (CieloRequestException e) {
            CieloError error = e.getError();
            Assert.fail();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
