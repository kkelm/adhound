package com.paypal.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
/**
 * PayPal object based off of their API.
 * @author kkelm
 *
 */
@Generated("com.robohorse.robopojogenerator")
public class SetupFee{

	@JsonProperty("value")
	private String value;

	@JsonProperty("currency_code")
	private String currencyCode;

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return currencyCode;
	}

	@Override
 	public String toString(){
		return 
			"SetupFee{" + 
			"value = '" + value + '\'' + 
			",currency_code = '" + currencyCode + '\'' + 
			"}";
		}
}