package deepikavasudevan.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import deepikavasudevan.project.JSONModels.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;


public class ProjectService {
    static String fileName = "projects.txt";
    FilesOperator filesOperator = new FilesOperator(fileName);
    static Logger logger = LogManager.getLogger("ProjectService");

    public AbstractMap.SimpleEntry<Boolean, String> create(String requestContents) {
        /*checks if inputted JSON is valid*/
        try {
            JSONObject requestJson = new JSONObject(requestContents);
            AbstractMap.SimpleEntry<Boolean, String> result = validateJSONContents(requestJson);
            if (result != null) return result;
        } catch (JSONException exception) {
            logger.error(exception.getMessage());
            return new AbstractMap.SimpleEntry<>(false, exception.getMessage());
        }

        /*Writes to the file*/
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

    private AbstractMap.SimpleEntry<Boolean, String> validateJSONContents(JSONObject requestJson) {
        //Checks if it contains more than the number of expected fields
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

    public AbstractMap.SimpleEntry<Boolean, String> get(Map<String, String[]> queries) throws IOException {
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

        List<Project> projects;
        String response = "[";
        try {
            projects = getProjects(contents);
        } catch (IOException e) {
            String message = "Error in parsing contents in file to JSON";
            logger.error(message);
            return new AbstractMap.SimpleEntry<>(false, message);
        }

        ObjectMapper object = new ObjectMapper();
        ProjectParser projectParser = new ProjectParser(queries);
        object.enable(SerializationFeature.INDENT_OUTPUT);

        for (Project project : projects) {
            boolean addResponse = projectParser.match(project);
            if (addResponse)
                response += object.writeValueAsString(project) + ",";
        }

        /*Append with ] to print out valid JSON*/
        if (response.endsWith(",")) {
            response = response.substring(0, response.length() - 1) + "]";
        }

        /*No project has been matched and added to response*/
        if (response.trim().endsWith("[")) {
            return new AbstractMap.SimpleEntry<>(false, "No project matches this search");
        }

        return new AbstractMap.SimpleEntry<>(true, response);
    }

    private List<Project> getProjects(String contents) throws IOException {
        ObjectMapper object = new ObjectMapper();
        TypeReference<List<Project>> mapType = new TypeReference<List<Project>>() {
        };
        return object.readValue(contents, mapType);
    }

}
