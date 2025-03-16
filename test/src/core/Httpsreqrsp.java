package core;

import java.util.HashMap;

public class Httpsreqrsp {
	String endpoint, request, httpMethod, response;
	int responseCode;
	HashMap <String, String> addtlprops = new HashMap<>();
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public HashMap<String, String> getAddtlprops() {
		return addtlprops;
	}
	public void setAddtlprops(HashMap<String, String> addtlprops) {
		this.addtlprops = addtlprops;
	} 
	

}
