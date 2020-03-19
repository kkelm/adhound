package com.paypal.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class BillingCyclesItem{

	@JsonProperty("sequence")
	private int sequence;

	@JsonProperty("tenure_type")
	private String tenureType;

	@JsonProperty("total_cycles")
	private int totalCycles;

	@JsonProperty("pricing_scheme")
	private PricingScheme pricingScheme;

	@JsonProperty("frequency")
	private Frequency frequency;

	public void setSequence(int sequence){
		this.sequence = sequence;
	}

	public int getSequence(){
		return sequence;
	}

	public void setTenureType(String tenureType){
		this.tenureType = tenureType;
	}

	public String getTenureType(){
		return tenureType;
	}

	public void setTotalCycles(int totalCycles){
		this.totalCycles = totalCycles;
	}

	public int getTotalCycles(){
		return totalCycles;
	}

	public void setPricingScheme(PricingScheme pricingScheme){
		this.pricingScheme = pricingScheme;
	}

	public PricingScheme getPricingScheme(){
		return pricingScheme;
	}

	public void setFrequency(Frequency frequency){
		this.frequency = frequency;
	}

	public Frequency getFrequency(){
		return frequency;
	}

	@Override
 	public String toString(){
		return 
			"BillingCyclesItem{" + 
			"sequence = '" + sequence + '\'' + 
			",tenure_type = '" + tenureType + '\'' + 
			",total_cycles = '" + totalCycles + '\'' + 
			",pricing_scheme = '" + pricingScheme + '\'' + 
			",frequency = '" + frequency + '\'' + 
			"}";
		}
}