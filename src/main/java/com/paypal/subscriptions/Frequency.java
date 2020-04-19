package com.paypal.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Frequency{

	@JsonProperty("interval_count")
	private int intervalCount;

	@JsonProperty("interval_unit")
	private String intervalUnit;

	public void setIntervalCount(int intervalCount){
		this.intervalCount = intervalCount;
	}

	public int getIntervalCount(){
		return intervalCount;
	}

	public void setIntervalUnit(String intervalUnit){
		this.intervalUnit = intervalUnit;
	}

	public String getIntervalUnit(){
		return intervalUnit;
	}

	@Override
 	public String toString(){
		return 
			"Frequency{" + 
			"interval_count = '" + intervalCount + '\'' + 
			",interval_unit = '" + intervalUnit + '\'' + 
			"}";
		}
}