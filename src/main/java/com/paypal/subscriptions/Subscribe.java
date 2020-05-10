package com.paypal.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;
/**
 * PayPal object based off of their API.
 * @author kkelm
 *
 */
@Generated("com.robohorse.robopojogenerator")
public class Subscribe {

	@JsonProperty("create_time")
	private String createTime;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private String id;

	@JsonProperty("status")
	private String status;

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return createTime;
	}

	public void setLinks(List<LinksItem> links){
		this.links = links;
	}
	// Links contain this link to redirect the user to the PayPal page to complete the registration process
	/*
	"links": [
        {
            "href": "https://www.sandbox.paypal.com/webapps/billing/subscriptions?ba_token=BA-2K386648TN4498151",
            "rel": "approve",
            "method": "GET"
        }
     ]
	 */
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

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"create_time = '" + createTime + '\'' + 
			",links = '" + links + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}