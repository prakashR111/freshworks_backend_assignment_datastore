package keyDataValueDataStore;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class HomeController {
	
	
		public static void main(String args[]) throws JsonGenerationException, JsonMappingException, CustomException, IOException, ParseException, org.json.simple.parser.ParseException
		{
			int choice;
			Scanner scanner=new Scanner(System.in);
			do {
				System.out.println("\nWelcome to the Key-Value DataStore");
				System.out.println("1.Create a new file or to create a new object in existing file");
				System.out.println("2.Read the contents in a file ");
				System.out.println("3.Delete a object in file ");
				System.out.println("4.Exit");
				System.out.println("Please Enter Your Choice:\n");
				
				choice=scanner.nextInt();
				
				
				if(choice==1)
				{
					Creating hc=new Creating();
					System.out.println("Enter the file name with extension:");
					String name=scanner.next();
					hc.createnewfile(name);
					String key;
					System.out.println("Enter the key:");
					key=scanner.next();
					try
					{
					if(key.length()>32)
						throw new CustomException("The key should not exceed 32 characters");
					}
					catch(Exception ex) {
					       System.out.println("Enter key within 32 characters:\n");
					       key=scanner.next();
					}
					System.out.println("Enter the value(Time to Live in seconds):");
					int value1=scanner.nextInt();
					//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
					//String value2=dtf.format(now)+"";
					String value2=now+"";
					//System.out.println(value2);
					//System.out.println(now.plusSeconds(value1)+" ");
					Value v=new Value(value1,value2);
					Data d=new Data(key,v);
					hc.createobject(d,name);
				}
				
				if(choice==2)
				{
					Reading rd=new Reading();

					System.out.println("Enter the file name with extension to read:");
					String name=scanner.next();
					if(rd.checkfile(name))
					{
						System.out.println("Enter the key to read:");
						String key=scanner.next();
						try
						{
							System.out.println(rd.ReadingData(key, name));
						}
						catch(Exception ex)
						{
							System.out.println("The key is not found");
						}
					}
					else
					{
						System.out.println("file does not exists...please create a file ");
					}
				}
				if(choice==3)
				{
					Reading rd=new Reading();
					System.out.println("Enter the file name with extension to delete:");
					String name=scanner.next();
					if(rd.checkfile(name))
					{
						System.out.println("Enter the key to delete:");
						String key=scanner.next();
						Deleting dd=new Deleting();
						System.out.println(dd.DeletingData(key,name));
					}
					else
					{
						System.out.println("file does not exists...please create a file ");
					}
					
				}
				if(choice==4)
				{

					System.out.println("Exited Successfully");
				}
				if(choice>4)
				{
					System.out.println("please Enter a valid choice.");
				}
			}while(choice!=4);
			scanner.close();
	
			
		}

		
		
		
}
