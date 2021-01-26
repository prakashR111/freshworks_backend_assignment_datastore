package keyDataValueDataStore;

public class Value {
	private int time_to_live;
	private String time_of_creation;
	public Value()
	{
		
	}
	public Value(int time_to_live,String time_of_creation) {
		super();
		this.time_to_live = time_to_live;
		this.time_of_creation = time_of_creation;
	}
	public int getTime_to_live() {
		return time_to_live;
	}
	public void setTime_to_live(int time_to_live) {
		this.time_to_live = time_to_live;
	}
	public String getTime_of_creation() {
		return time_of_creation;
	}
	public void setTime_of_creation(String time_of_creation) {
		this.time_of_creation = time_of_creation;
	}
}
