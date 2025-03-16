package core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Httpclient {
	
	public static Httpsreqrsp sendRequest(Httpsreqrsp reqrsp){
		String inputLine, output="";
		try {
		URL endpoint =  new URL(reqrsp.getEndpoint());	
		HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection(); //open connection url
		connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod(reqrsp.getHttpMethod()); // Set the HTTP method
		connection.setDoOutput(true);
		if(!reqrsp.getHttpMethod().equals("GET")) { //if request method is GET then no request body
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream()); //write request
			out.write(reqrsp.getRequest());
			out.flush();
			out.close();
		}
		InputStream is; //read response
		try {
			is = connection.getInputStream();
		}
		catch(Exception e) {
			is = connection.getErrorStream();
		}
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		while ((inputLine = br.readLine())!=null){
			output = output+inputLine;
		}
		reqrsp.setResponseCode(connection.getResponseCode());
		reqrsp.setResponse(output);
		//closing resources
		is.close();
		br.close();
		connection.disconnect();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return reqrsp;
		
	}

}
