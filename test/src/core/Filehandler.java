package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Filehandler {


	public static String readfile(String filename) throws Exception{
		String filecontent="", out="";
		BufferedReader filebr = new BufferedReader(new FileReader(filename));
		while ((out = filebr.readLine())!=null){
			filecontent = filecontent+out;
			filecontent = filecontent.replaceAll("\\s", "");
		}
		//System.out.println(filecontent);
		filebr.close();
		return filecontent;
	}
	public static void writefile(String filename,String filecontent) throws Exception{
		Files.write(Paths.get("target/"+filename), filecontent.getBytes(), StandardOpenOption.CREATE);

	}
	public static void createoutputfile() {
		try {
			File file = new File("target/testresult.csv");
			new File("target").mkdir();
			file.createNewFile();
			writeresultfile("Test Case No,Test Case Description,Test Result");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void writeresultfile(String linetowrite) throws Exception{
		Files.write(Paths.get("target/testresult.csv"), (linetowrite+"\n").getBytes(), StandardOpenOption.APPEND);
	}
}
