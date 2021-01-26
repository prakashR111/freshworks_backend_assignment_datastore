package keyDataValueDataStore;

public class CustomException extends Exception{
	
	private String message;
	CustomException(String str)
	{
		this.message=str;
		System.out.println(toString());
	}
	@Override
	public String toString() {
		return message;
	}
	

}