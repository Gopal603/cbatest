package test;

import java.io.BufferedReader;
import java.io.FileReader;

import core.Filehandler;
import core.Httpclient;
import core.Httpsreqrsp;

public class testCBA {

	public static void main(String[] args) throws Exception{
		String line="", splitBy=",";
		//InputStream is = testCBA.class.getResourceAsStream("/resources/datadrivensheet.csv");
		//InputStreamReader isr = new InputStreamReader(is);
		//BufferedReader br = new BufferedReader(isr);
		Filehandler.createoutputfile();
		BufferedReader br = new BufferedReader(new FileReader("./resources/datadrivensheet.csv"));
		 while((line=br.readLine())!=null) {
			String[] details = line.split(splitBy);
			if(line.contains("TestCaseNo")) continue;
			//System.out.println(line);
			Httpsreqrsp testreq = new Httpsreqrsp();
			testreq.setEndpoint(details[3]);
			testreq.setHttpMethod(details[2]);
			String expresponse = Filehandler.readfile("./resources/expectedResponse/"+details[5]);
			testreq = Httpclient.sendRequest(testreq);
			int exprespcode = Integer.parseInt(details[6]);
			Filehandler.writefile("actualrsp_tstcase_"+details[0]+".json", testreq.getResponse());
			//System.out.println(testreq.getResponse().replaceAll("\\s", ""));
			if(expresponse.equals(testreq.getResponse().replaceAll("\\s", "")) && testreq.getResponseCode()==exprespcode) {
				System.out.println("Test case No "+ details[0]+" "+details[1]+" is PASSED");
				Filehandler.writeresultfile("Test case No "+ details[0]+","+details[1]+",PASSED");
			}
			else {
				System.out.println("Test case No "+ details[0]+" "+details[1]+" is FAILED");
			    Filehandler.writeresultfile("Test case No "+ details[0]+","+details[1]+",FAILED");
			}
				
		}
		br.close();
	
	}

}
