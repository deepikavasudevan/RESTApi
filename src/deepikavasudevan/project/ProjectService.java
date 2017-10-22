package deepikavasudevan.project;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


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
        String response = "[";
        try {
            ObjectMapper object = new ObjectMapper();
            TypeReference<List<Project>> mapType = new TypeReference<List<Project>>() {
            };
            List<Project> projects = object.readValue(contents, mapType);
            ProjectParser projectParser = new ProjectParser(projects);

            object.enable(SerializationFeature.INDENT_OUTPUT);
            boolean addResponse = true;
            for (Project project : projects) {
                if (queries.containsKey("projectid")) {
                    if (Arrays.asList(queries.get("projectid")).contains(project.id)) {
                        addResponse = true;
                    } else {
                        addResponse = false;
                    }
                }

                if (queries.containsKey("country")) {
                    if (!Collections.disjoint(Arrays.asList(queries.get("country")), Arrays.asList(project.targetCountries))) {
                        addResponse = true;
                    } else {
                        addResponse = false;
                    }
                }

                if (queries.containsKey("number")) {
                    for (TargetKeys target : project.targetKeys) {
                        if (Arrays.asList(queries.get("number")).contains(target.number)) {
                            addResponse = true;
                        } else {
                            addResponse = false;
                        }
                    }
                }

                if (queries.containsKey("keyword")) {
                    for (TargetKeys target : project.targetKeys) {
                        if (Arrays.asList(queries.get("keyword")).contains(target.keyword)) {
                            addResponse = true;
                        } else {
                            addResponse = false;
                        }
                    }
                }

                if (addResponse)
                    response += object.writeValueAsString(project) + ",";

            }
        } catch (JsonGenerationException exception) {
            logger.error(exception.getMessage());
            return new AbstractMap.SimpleEntry<>(false, "Request is invalid. Please try again.");
        } catch (JsonMappingException exception) {
            logger.error(exception.getMessage());
            return new AbstractMap.SimpleEntry<>(false, "Request is invalid. Please try again.");
        }

        if (response.endsWith(",")) {
            response = response.substring(0, response.length() - 1) + "]";
        }

        if (response.trim().endsWith("[")) {
            return new AbstractMap.SimpleEntry<>(false, "No project matches this search");
        }

        return new AbstractMap.SimpleEntry<>(true, response);
    }
}
