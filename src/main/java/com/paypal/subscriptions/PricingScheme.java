package com.paypal.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PricingScheme{

	@JsonProperty("update_time")
	private String updateTime;

	@JsonProperty("create_time")
	private String createTime;

	@JsonProperty("fixed_price")
	private FixedPrice fixedPrice;

	@JsonProperty("version")
	private int version;

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

	public void setFixedPrice(FixedPrice fixedPrice){
		this.fixedPrice = fixedPrice;
	}

	public FixedPrice getFixedPrice(){
		return fixedPrice;
	}

	public void setVersion(int version){
		this.version = version;
	}

	public int getVersion(){
		return version;
	}

	@Override
 	public String toString(){
		return 
			"PricingScheme{" + 
			"update_time = '" + updateTime + '\'' + 
			",create_time = '" + createTime + '\'' + 
			",fixed_price = '" + fixedPrice + '\'' + 
			",version = '" + version + '\'' + 
			"}";
		}
}