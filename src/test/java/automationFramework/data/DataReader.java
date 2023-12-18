package automationFramework.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String,String>> getJSONDataToMap() throws IOException {
        //Step 1 is to read the string as shown below, need to type filetype encoding now at end
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "src/test/java/automationFramework/data/PurchaseOrder.json"), StandardCharsets.UTF_8);

    //Next step is to create object mapper i.e string to hashmap - Jackson Bind dependency
        ObjectMapper mapper = new ObjectMapper();
      List<HashMap<String,String>> data = mapper.readValue(jsonContent , new TypeReference<List<HashMap<String,String>>>() {
      });
      return data;
      }
    }

