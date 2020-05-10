package com.paypal.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
/**
 * PayPal object based off of their API.
 * @author kkelm
 *
 */
@Generated("com.robohorse.robopojogenerator")
public class PaymentPreferences{

	@JsonProperty("setup_fee")
	private SetupFee setupFee;

	@JsonProperty("setup_fee_failure_action")
	private String setupFeeFailureAction;

	@JsonProperty("auto_bill_outstanding")
	private boolean autoBillOutstanding;

	@JsonProperty("payment_failure_threshold")
	private int paymentFailureThreshold;

	@JsonProperty("service_type")
	private String serviceType;

	public void setSetupFee(SetupFee setupFee){
		this.setupFee = setupFee;
	}

	public SetupFee getSetupFee(){
		return setupFee;
	}

	public void setSetupFeeFailureAction(String setupFeeFailureAction){
		this.setupFeeFailureAction = setupFeeFailureAction;
	}

	public String getSetupFeeFailureAction(){
		return setupFeeFailureAction;
	}

	public void setAutoBillOutstanding(boolean autoBillOutstanding){
		this.autoBillOutstanding = autoBillOutstanding;
	}

	public boolean isAutoBillOutstanding(){
		return autoBillOutstanding;
	}

	public void setPaymentFailureThreshold(int paymentFailureThreshold){
		this.paymentFailureThreshold = paymentFailureThreshold;
	}

	public int getPaymentFailureThreshold(){
		return paymentFailureThreshold;
	}

	public void setServiceType(String serviceType){ this.serviceType = serviceType; }

	public String getServiceType(){
		return serviceType;
	}

	@Override
 	public String toString(){
		return 
			"PaymentPreferences{" + 
			"setup_fee = '" + setupFee + '\'' + 
			",setup_fee_failure_action = '" + setupFeeFailureAction + '\'' + 
			",auto_bill_outstanding = '" + autoBillOutstanding + '\'' + 
			",payment_failure_threshold = '" + paymentFailureThreshold + '\'' + 
			"}";
		}
}