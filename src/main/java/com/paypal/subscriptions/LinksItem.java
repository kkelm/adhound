package com.paypal.subscriptions;
/**
 * PayPal object based off of their API.
 * @author kkelm
 *
 */
public class LinksItem{
	private String method;
	private String rel;
	private String href;
	private String encType;

	public void setMethod(String method){
		this.method = method;
	}

	public String getMethod(){
		return method;
	}

	public void setRel(String rel){
		this.rel = rel;
	}

	public String getRel(){
		return rel;
	}

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}

	public void setEncType(String encType){
		this.encType = encType;
	}

	public String getEncType(){
		return encType;
	}

	@Override
 	public String toString(){
		return 
			"LinksItem{" + 
			"method = '" + method + '\'' + 
			",rel = '" + rel + '\'' + 
			",href = '" + href + '\'' + 
			"}";
		}
}
