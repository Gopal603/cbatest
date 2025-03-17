package core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Httpclient {
	
	public static Httpsreqrsp sendRequest(Httpsreqrsp reqrsp){
		String inputLine, output="";
		try {
		URL endpoint =  new URL(reqrsp.getEndpoint());	
		HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection(); //open connection url
		connection.setRequestProperty("accept", "application/json");
		connection.setRequestProperty("api-key", "1742199175882");
        connection.setRequestMethod(reqrsp.getHttpMethod()); // Set the HTTP method
		connection.setDoOutput(true);
		if(reqrsp.getRequest()!=null) { //if there is no request body

			if(reqrsp.endpoint.contains("upload")) {
				String boundary = Long.toHexString(System.currentTimeMillis());
				connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
				PrintWriter writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
				writer.append("--"+boundary).append("\r\n");
				writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"test.jpg\"").append("\r\n");
				writer.append("\r\n").flush();
				writer.write(reqrsp.getRequest());
				writer.append("\r\n").flush();
				writer.append("--"+boundary+"--").append("\r\n").flush();
			}
			else {
				connection.setRequestProperty("Content-Type", "application/json");
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream()); //write request
				out.write(reqrsp.getRequest());
				out.flush();
				out.close();
			}
			
		}
		InputStream is; //read response
		try {
			is = connection.getInputStream();
		}
		catch(Exception e) {
			is = connection.getErrorStream();
		}
		if(is != null) {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			while ((inputLine = br.readLine())!=null){
				output = output+inputLine;
			}
			//closing resources
			is.close();
			br.close();
		}
		reqrsp.setResponseCode(connection.getResponseCode());
		reqrsp.setResponse(output);
		connection.disconnect();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return reqrsp;
		
	}

}
