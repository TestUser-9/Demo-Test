package Framework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONFileReader {

	public Map<String, String> readWriteJSON(String jsonFilePath) throws FileNotFoundException {

		FileReader reader = new FileReader(jsonFilePath);
		JsonParser JSON=new JsonParser();
		JsonObject jsonObject = (JsonObject) JSON.parse(reader);
		Map<String, String> dataMap = new Gson().fromJson(jsonObject.toString(), Map.class);
		return dataMap;
	}

} 
