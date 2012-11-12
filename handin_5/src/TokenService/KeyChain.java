package TokenService;
import java.util.HashMap;

public class KeyChain{
	private HashMap<String, String> keys = new HashMap<String, String>();

	public KeyChain(){
		keys.put("hilde",  	"9013ffb9d4b2808cf4f2c293830e5094");
		keys.put("rao",    	"97d176faea4e98a33611cd4df57b4391");
		keys.put("naiz",   	"92e93438776f62570efc22de5d700637");
		keys.put("rahr",   	"6c4a19bfbdf448170b7e79c5cced38e4");
		keys.put("jesan",  	"410e01ffba2d0c65f460303d7299744d");
		keys.put("sidi",	"8be9a88806c1a86350c177ed566ef30b");
	}

	public String getKey(String name) {
		return keys.get(name);
	}

	public void addKey(String name, String key) {
		keys.put(name, key);
	}
}
