package keyDataValueDataStore;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Deleting extends Creating{
	
	
	public String DeletingData(String key,String filename) throws FileNotFoundException, IOException, ParseException 
	{
		if(ExistingOrNot(key, filename))
		{
			List<String> arr = new ArrayList<>();
			try (FileInputStream fis = new FileInputStream(filename)) 
			{
				JsonFactory jsonfactory = new JsonFactory();
				JsonParser jsonparser = jsonfactory.createParser(fis);
				jsonparser.setCodec(new ObjectMapper());
				jsonparser.nextToken();
				
				
				while (jsonparser.hasCurrentToken()) 
				{
					Data data = jsonparser.readValueAs(Data.class);
					jsonparser.nextToken();
					if (!data.getKey().equals(key))
						arr.add(new ObjectMapper().writeValueAsString(data));
				}
				new FileOutputStream(filename).close();
				for (int i = 0; i < arr.size();i++)
					 writeintofile(arr.get(i), filename);
	
			}
			return "deleted successfully";
		}
		return "The key is not found.";
	}
	
	
	
		//writing the  data into file
		public void writeintofile(String data,String filename) {
				try {
					BufferedWriter bfw = new BufferedWriter(new FileWriter(filename, true)); 
					bfw.write(data);
					//System.out.println("data recorded");
					bfw.close(); 
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			}

}
