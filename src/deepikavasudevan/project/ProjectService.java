package deepikavasudevan.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractMap;


public class ProjectService {
    static String fileName = "projects.txt";
    static Logger logger = LogManager.getLogger("ProjectService");

    public AbstractMap.SimpleEntry<Boolean, String> create(String requestContents) {
        try {
            JSONObject requestJson = new JSONObject(requestContents);
            //Contains more than the number of expected fields
            if (requestJson.length() != 9) {
                String message = "More number of fields found in the request";
                logger.error(message);
                return new AbstractMap.SimpleEntry<>(false, message);
            }

            if (!isAValidField(requestJson, "id")) return new AbstractMap.SimpleEntry<>(false, "Request does not contain ID");
            if (!isAValidField(requestJson, "projectName")) return new AbstractMap.SimpleEntry<>(false, "Request does not contain projectName");
            if (!isAValidField(requestJson, "creationDate")) return new AbstractMap.SimpleEntry<>(false, "Request does not contain creationDate");
            if (!isAValidField(requestJson, "expiryDate")) return new AbstractMap.SimpleEntry<>(false, "Request does not contain expiryDate");
            if (!isAValidField(requestJson, "enabled")) return new AbstractMap.SimpleEntry<>(false, "Request does not contain enabled");
            if (!isAValidField(requestJson, "targetCountries")) return new AbstractMap.SimpleEntry<>(false, "Request does not contain targetCountries");
            if (!isAValidField(requestJson, "projectCost")) return new AbstractMap.SimpleEntry<>(false, "Request does not contain projectCost");
            if (!isAValidField(requestJson, "projectUrl")) return new AbstractMap.SimpleEntry<>(false, "Request does not contain projectUrl");
            if (!isAValidField(requestJson, "targetKeys")) return new AbstractMap.SimpleEntry<>(false, "Request does not contain targetKeys");

        } catch (JSONException exception) {
            String message = "The request contains malformed JSON";
            logger.error(message);
            return new AbstractMap.SimpleEntry<>(false, message);
        }

//        try {
//            logger.info("Writing content from request to the file");
//            FileWriter writer = new FileWriter(fileName, true);
//            BufferedWriter bufferedWriter = new BufferedWriter(writer);
//            bufferedWriter.write(requestContents + ",");
//            bufferedWriter.newLine();
//            bufferedWriter.flush();
//            bufferedWriter.close();
//            return true;
//        } catch (IOException exception) {
//            logger.error("Error in writing contents to the file");
//            return false;
//        }
        return new AbstractMap.SimpleEntry<>(true, "Campaign is successfully created");
    }

    private boolean isAValidField(JSONObject requestJson, String field) {
        if (requestJson.isNull(field)) {
            logger.error("Request does not contain " + field);
            return false;
        }
        return true;
    }
}
