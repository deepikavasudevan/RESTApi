package deepikavasudevan.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import deepikavasudevan.project.JSONModels.Project;
import deepikavasudevan.project.JSONModels.ProjectDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProjectService {
    final int numberOfFields = 9;
    static String fileName = "projects.txt";
    FilesOperator filesOperator = new FilesOperator(fileName);
    ArrayList<String> projectIds = new ArrayList<>();
    static Logger logger = LogManager.getLogger("ProjectService");
    ObjectMapper object = new ObjectMapper();

    public AbstractMap.SimpleEntry<Boolean, String> create(String requestContents) {
        /*Checks if inputted JSON is valid*/
        try {
            AbstractMap.SimpleEntry<Boolean, String> result = validateJSONContents(requestContents);

            if (result != null) return result;
        } catch (JSONException exception) {
            logger.error(exception.getMessage());
            return new AbstractMap.SimpleEntry<>(false, exception.getMessage());
        }

        /*Writes to the file*/
        logger.info("Writing content from request to the file");
        try {
            filesOperator.writeToFile(requestContents);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new AbstractMap.SimpleEntry<>(false, "Could not write to the file");
        }
        return new AbstractMap.SimpleEntry<>(true, "Campaign is successfully created");
    }

    private AbstractMap.SimpleEntry<Boolean, String> validateJSONContents(String requestContents) throws JSONException {
        JSONObject requestJson = new JSONObject(requestContents);
        //Checks if it contains more than the number of expected fields
        if (requestJson.length() != numberOfFields) {
            String message = "Number of fields present is not equal to number of expected fields";
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

        try {
            Project project = object.readValue(requestContents, Project.class);
            String contents = filesOperator.getContentsFromFile();
            List<Project> projects = getProjects(contents);
            for (Project p : projects) {
                if (p.getId().equals(project.getId())) {
                    return new AbstractMap.SimpleEntry<>(false, "Project ID cannot be the same");
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    /*Checks whether the file contains a null value or not*/
    private boolean isAValidField(JSONObject requestJson, String field) {
        if (requestJson.isNull(field)) {
            return false;
        }
        return true;
    }

    public AbstractMap.SimpleEntry<Boolean, String> get(Map<String, String[]> queries) {
        String contents = "";
        List<Project> projects;

        /*Read from the file*/
        try {
            contents = filesOperator.getContentsFromFile();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        /*Convert the file contents to JSON Objects*/
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

        /*Check if project satisfies conditions*/
        ProjectDTO selectedProject = projectParser.getMatchingProjects(projects);

        if (selectedProject != null) {
            String response;
            try {
                response = object.writeValueAsString(selectedProject);
            } catch (IOException e) {
                logger.error(e.getMessage());
                return new AbstractMap.SimpleEntry<>(false, "Could not fetch the correct response.");
            }

            return new AbstractMap.SimpleEntry<>(true, response);
        } else {
            String response = "No projects found that matchToSearchCriteria this criteria";
            return new AbstractMap.SimpleEntry<>(false, response);
        }
    }

    private List<Project> getProjects(String contents) throws IOException {
        TypeReference<List<Project>> mapType = new TypeReference<List<Project>>() {
        };
        return object.readValue(contents, mapType);
    }
}
