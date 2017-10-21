package deepikavasudevan.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.AbstractMap;
import java.util.HashMap;


public class ProjectService {
    static String fileName = "projects.txt";
    FilesOperator filesOperator = new FilesOperator(fileName);
    static Logger logger = LogManager.getLogger("ProjectService");

    public AbstractMap.SimpleEntry<Boolean, String> create(String requestContents) {
        try {
            JSONObject requestJson = new JSONObject(requestContents);
            AbstractMap.SimpleEntry<Boolean, String> result = validateJSONContents(requestJson);
            if (result != null) return result;
        } catch (JSONException exception) {
            logger.error(exception.getMessage());
            return new AbstractMap.SimpleEntry<>(false, exception.getMessage());
        }

        try {
            logger.info("Writing content from request to the file");
            filesOperator.writeToFile(requestContents);
            return new AbstractMap.SimpleEntry<>(true, "Campaign is successfully created");
        } catch (IOException exception) {
            String message = "Error in writing contents to the file";
            logger.error(message);
            return new AbstractMap.SimpleEntry<>(false, message);
        }
    }

    //Checks if the contents of the request is valid
    private AbstractMap.SimpleEntry<Boolean, String> validateJSONContents(JSONObject requestJson) {
        //Contains more than the number of expected fields
        if (requestJson.length() != 9) {
            String message = "More number of fields found in the request";
            logger.error(message);
            return new AbstractMap.SimpleEntry<>(false, message);
        }

        if (!isAValidField(requestJson, "id"))
            return new AbstractMap.SimpleEntry<>(false, "Request does not contain ID");
        if (!isAValidField(requestJson, "projectName"))
            return new AbstractMap.SimpleEntry<>(false, "Request does not contain projectName");
        if (!isAValidField(requestJson, "creationDate"))
            return new AbstractMap.SimpleEntry<>(false, "Request does not contain creationDate");
        if (!isAValidField(requestJson, "expiryDate"))
            return new AbstractMap.SimpleEntry<>(false, "Request does not contain expiryDate");
        if (!isAValidField(requestJson, "enabled"))
            return new AbstractMap.SimpleEntry<>(false, "Request does not contain enabled");
        if (!isAValidField(requestJson, "targetCountries"))
            return new AbstractMap.SimpleEntry<>(false, "Request does not contain targetCountries");
        if (!isAValidField(requestJson, "projectCost"))
            return new AbstractMap.SimpleEntry<>(false, "Request does not contain projectCost");
        if (!isAValidField(requestJson, "projectUrl"))
            return new AbstractMap.SimpleEntry<>(false, "Request does not contain projectUrl");
        if (!isAValidField(requestJson, "targetKeys"))
            return new AbstractMap.SimpleEntry<>(false, "Request does not contain targetKeys");
        return null;
    }

    private boolean isAValidField(JSONObject requestJson, String field) {
        if (requestJson.isNull(field)) {
            return false;
        }
        return true;
    }

    public AbstractMap.SimpleEntry<Boolean, String> get(HashMap<String, String> queries) {
        String contents;
        try {
            contents = filesOperator.getContentsFromFile();
        } catch (FileNotFoundException exception) {
            String message = "Error in reading contents from the file";
            logger.error(message);
            return new AbstractMap.SimpleEntry<>(false, message);
        } catch (IOException e) {
            String message = "Error in reading contents from the file";
            logger.error(message);
            return new AbstractMap.SimpleEntry<>(false, message);
        }

        String response = "[";
        boolean addResponse = true;

        return new AbstractMap.SimpleEntry<>(true, response);
    }
}
