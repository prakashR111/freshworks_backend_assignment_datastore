package keyDataValueDataStore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Creating {

			//To create a new file
			void createnewfile(String filename) {
				try {
					File obj = new File(filename);
					if (obj.createNewFile()) {
						System.out.println("File created Successfully: " + obj.getName());
					} else {
						System.out.println("The given file already exists.");
					}
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			}

			//Creating a Json object
			public String createobject(Data data,String filename) throws CustomException,JsonGenerationException, JsonMappingException, IOException, ParseException, org.json.simple.parser.ParseException {
				
				
				String temp="{\"key\":\""+data.getKey()+"\",\"value\":{\"time_to_live\":"+data.getValue().getTime_to_live()+",\"time_of_creation\":\""+data.getValue().getTime_of_creation()+"\"}}";
				//System.out.println(temp);
				JSONParser parser = new JSONParser();  
				JSONObject json = (JSONObject) parser.parse(temp); 
				//System.out.println(json.size());
				//checking  whether the  json object is greater than 16KB
				try
				{
				if(json.size()>16*1000)
					throw new Exception();
				}
				catch(Exception ex)
				{
					System.out.println("JsonObject should not be greater than 16Kb");
					return "";
				}
				//checking whether the file size is greater than 1GB
				if(filename.length() > 1024 * 1024 * 1024)
					throw new CustomException("file size should be less than 1GB");
				try
				{
				if(ExistingOrNot(data.getKey(),filename))
					throw new CustomException("Object already exists");
				
				// writing the data into file
				String content = new ObjectMapper().writeValueAsString(data);
				writeintofile(content, filename);
				return "object created";
				}
				catch(Exception ex)
				{
					System.out.println("object already exists");
				}
				return "object created ";
				
	
				
			  }
		
		
				//writing data into the file
				public void writeintofile(String data,String filename) {
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(filename, true)); 
						out.write(data);
						System.out.println("object created and data recorded Successfully");
						out.close(); 
					} catch (IOException e) {
						System.out.println("An error occurred.");
						e.printStackTrace();
					}
				}
				
				
				//Checking whether the object is present in the file
				public boolean ExistingOrNot(String key,String filename) throws ParseException, IOException
				{
					try (FileInputStream fis = new FileInputStream(filename)) {

						JsonFactory jsonfactory = new JsonFactory();
						JsonParser jsonparser = jsonfactory.createParser(fis);
						jsonparser.setCodec(new ObjectMapper());
						jsonparser.nextToken();
						while (jsonparser.hasCurrentToken()) {
							Data data = jsonparser.readValueAs(Data.class);
							jsonparser.nextToken();
							if (data.getKey().equals(key))
								return true;
						}
					}
					return false;
				}

}
