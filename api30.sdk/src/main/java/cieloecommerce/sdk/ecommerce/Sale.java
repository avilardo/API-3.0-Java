package cieloecommerce.sdk.ecommerce;

import com.google.gson.annotations.SerializedName;

public class Sale {
	@SerializedName("MerchantOrderId")
	private String merchantOrderId;

	@SerializedName("Customer")
	private Customer customer;

	@SerializedName("Payment")
	private Payment payment;

	@SerializedName("Status")
	private Integer status;

	@SerializedName("ReasonCode")
	private Integer reasonCode;

	@SerializedName("ReasonMessage")
	private String reasonMessage;

	@SerializedName("ProviderReturnCode")
	private Integer providerReturnCode;

	@SerializedName("ProviderReturnMessage")
	private String providerReturnMessage;

	@SerializedName("ReturnCode")
	private Integer returnCode;

	@SerializedName("ReturnMessage")
	private String returnMessage;

	@SerializedName("Links")
	private Object links[];

	public Sale(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Customer customer(String name) {
		setCustomer(new Customer(name));

		return getCustomer();
	}

	public Payment payment(Integer amount, Integer installments) {
		setPayment(new Payment(amount, installments));

		return getPayment();
	}

	public Payment payment(Integer amount) {
		return payment(amount, 1);
	}

	public Customer getCustomer() {
		return customer;
	}

	public Sale setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public Sale setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
		return this;
	}

	public Payment getPayment() {
		return payment;
	}

	public Sale setPayment(Payment payment) {
		this.payment = payment;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonMessage() {
		return reasonMessage;
	}

	public void setReasonMessage(String reasonMessage) {
		this.reasonMessage = reasonMessage;
	}

	public Integer getProviderReturnCode() {
		return providerReturnCode;
	}

	public void setProviderReturnCode(Integer providerReturnCode) {
		this.providerReturnCode = providerReturnCode;
	}

	public String getProviderReturnMessage() {
		return providerReturnMessage;
	}

	public void setProviderReturnMessage(String providerReturnMessage) {
		this.providerReturnMessage = providerReturnMessage;
	}

	public Integer getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public Object[] getLinks() {
		return links;
	}

	public void setLinks(Object[] links) {
		this.links = links;
	}
}