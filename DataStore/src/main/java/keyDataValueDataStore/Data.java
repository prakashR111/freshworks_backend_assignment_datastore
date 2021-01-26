package keyDataValueDataStore;

public class Data {
	
	@Override
	public String toString() {
		return "Data [key=" + key + ", value=" + value + "]";
	}
	private String key;
	private Value value;
	public Data(String key, Value value) {
		super();
		this.key = key;
		this.value = value;
	}
	public Data() {
		super();
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}

}
