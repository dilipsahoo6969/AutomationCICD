package rahulshettyacademy.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class DataReader {

	public static List<HashMap<String, String>> getJsonData(String fileName) throws IOException {

		//this reads JSON to String
        InputStream is = DataReader.class
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (is == null) {
            throw new RuntimeException("JSON file not found: " + fileName);
        }

        //This convert string to Map using Jackson Databinder
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(is,
                new TypeReference<List<HashMap<String, String>>>() {});
    }
}

/*
 Here readvalue will read the string and convert it in HashMap
 */
