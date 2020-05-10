package com.paypal.subscriptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Generated;
/**
 * PayPal object based off of their API.
 * @author kkelm
 *
 */
@Generated("com.robohorse.robopojogenerator")
public class Plan {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@JsonProperty("quantity_supported")
	private boolean quantitySupported;

	@JsonProperty("update_time")
	private String updateTime;

	@JsonProperty("create_time")
	private String createTime;

	@JsonProperty("payment_preferences")
	private PaymentPreferences paymentPreferences;

	@JsonProperty("product_id")
	private String productId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("billing_cycles")
	private List<BillingCyclesItem> billingCycles;

	@JsonProperty("description")
	private String description;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;

	@JsonProperty("status")
	private String status;

	@JsonProperty("usage_type")
	private String usageType;



	public void setQuantitySupported(boolean quantitySupported){
		this.quantitySupported = quantitySupported;
	}

	public boolean isQuantitySupported(){
		return quantitySupported;
	}

	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	public String getUpdateTime(){
		return updateTime;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return createTime;
	}

	public void setPaymentPreferences(PaymentPreferences paymentPreferences){
		this.paymentPreferences = paymentPreferences;
	}

	public PaymentPreferences getPaymentPreferences(){
		return paymentPreferences;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setBillingCycles(List<BillingCyclesItem> billingCycles) {

		this.billingCycles = billingCycles;
	}

	public List<BillingCyclesItem> getBillingCycles(){
		return billingCycles;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setLinks(List<LinksItem> links) { this.links = links; }

	public List<LinksItem> getLinks(){
		return links;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setUsageType(String usageType){
		this.usageType = usageType;
	}

	public String getUsageType(){
		return usageType;
	}

	@Override
 	public String toString(){
		return 
			"Plan{" +
			"quantity_supported = '" + quantitySupported + '\'' +
			",update_time = '" + updateTime + '\'' +
			",create_time = '" + createTime + '\'' +
			",payment_preferences = '" + paymentPreferences + '\'' +
			",product_id = '" + productId + '\'' +
			",name = '" + name + '\'' +
			",billing_cycles = '" + billingCycles + '\'' +
			",description = '" + description + '\'' +
			",links = '" + links + '\'' +
			",id = '" + id + '\'' +
			",status = '" + status + '\'' +
			",usageType = '" + usageType + '\'' +
			"}";
		}


}