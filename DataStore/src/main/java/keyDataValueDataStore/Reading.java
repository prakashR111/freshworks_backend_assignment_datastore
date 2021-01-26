package keyDataValueDataStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Reading extends Deleting{
	
	//To Read the data of the key
	public String ReadingData(String key,String filename) throws JsonParseException, JsonMappingException, IOException, CustomException, ParseException
	{ 
		if(checkfile(filename))
		{
			try (FileInputStream fis = new FileInputStream(filename)) 
			{
				JsonFactory jsonfactory = new JsonFactory();
				JsonParser jsonparser = jsonfactory.createParser(fis);
				jsonparser.setCodec(new ObjectMapper());
				jsonparser.nextToken();
				int flag=0;
				while (jsonparser.hasCurrentToken()) 
				{
					Data data = jsonparser.readValueAs(Data.class);
					jsonparser.nextToken();
					
					if(data.getKey().equals(key))
					{
					
					String giventime=data.getValue().getTime_of_creation()+"";
					LocalDateTime  createdtime= LocalDateTime.parse(giventime);
					LocalDateTime newtime=createdtime.plusSeconds(data.getValue().getTime_to_live());
					LocalDateTime current= LocalDateTime.now();
					
					
					//checking the expiry of the key
					 boolean datetimecheck = current.isBefore(newtime);
					 
					 
					 //System.out.println(createdtime+"\n"+newtime+"\n"+current+" "+datetimecheck);
					
					
						if(datetimecheck)
						{
						String temp="{\"key\":\""+data.getKey()+"\",\"value\":{\"time_to_live\":"+data.getValue().getTime_to_live()+",\"time_of_creation\":\""+data.getValue().getTime_of_creation()+"\"}}";
						flag=1;
						return temp;
						}
						else
						{
							DeletingData(key, filename);
							return "The time-to-live has been over and the Key is expired and deleted";
							
						}
					}
					
					
				}
				if(flag==0)
				{
					return "The key does not exists or the key is wrong";
				}
			}
		}
		return "file does not exists...please create a file ";
		
	}
	
	
	//for checking whether the file is present or not
	public boolean checkfile(String filename)
	{
		File obj = new File(filename);
		if(obj.exists())
		{
			return true;
		}
		else
			return false;
	}

}
