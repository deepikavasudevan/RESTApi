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
    static Logger logger = LogManager.getLogger("ProjectService");

    public AbstractMap.SimpleEntry<Boolean, String> create(String requestContents) {
        try {
            JSONObject requestJson = new JSONObject(requestContents);
            AbstractMap.SimpleEntry<Boolean, String> result = validateJSONContents(requestJson);
            if (result != null) return result;
        } catch (JSONException exception) {
            String message = "The request contains malformed JSON";
            logger.error(message);
            return new AbstractMap.SimpleEntry<>(false, message);
        }

        try {
            logger.info("Writing content from request to the file");
            writeToFile(requestContents);
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

    private void writeToFile(String requestContents) throws IOException {
        FileWriter writer = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(requestContents + ",");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private boolean isAValidField(JSONObject requestJson, String field) {
        if (requestJson.isNull(field)) {
            return false;
        }
        return true;
    }

    public AbstractMap.SimpleEntry<Boolean, String> get(HashMap<String, String> queries) {
        String line, contents = "[";
        try {
            File file = new File(fileName);
            FileInputStream stream = new FileInputStream(file);
            DataInputStream data = new DataInputStream(stream);
            InputStreamReader bream = new InputStreamReader(data);
            BufferedReader reader = new BufferedReader(bream);

            while ((line = reader.readLine()) != null) {
                contents += line;
            }

            if (contents.endsWith(",")) {
                contents = contents.substring(0, contents.length() - 1) + "]";
            }

            data.close();
            bream.close();
            stream.close();

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
        JSONObject project;
        try {
            JSONObject jsonContents = new JSONObject(contents);
            JSONArray projectArray = jsonContents.getJSONArray("project");

            for (int i = 0; i < projectArray.length(); i++) {
                project = projectArray.getJSONObject(i);
                if (queries.containsKey("id") && !project.isNull("id")) {
                    if (queries.get("id") == project.get("id")) {
                        addResponse = true;
                    } else {
                        addResponse = false;
                    }
                }

                if (queries.containsKey("targetCountries") && !project.isNull("targetCountries")) {
                    if (queries.get("targetCountries") == project.get("targetCountries")) {
                        addResponse = true;
                    } else {
                        addResponse = false;
                    }
                }

                if (queries.containsKey("number") || queries.containsKey("keyword") && !project.isNull("targetKeys")) {
                    JSONArray targetKeys = project.getJSONArray("targetKeys");
                    JSONObject targetKey;
                    for (int j = 0; j < targetKeys.length(); j++) {
                        targetKey = targetKeys.getJSONObject(j);

                        if (queries.containsKey("number") && !targetKey.isNull("number")) {
                            if (queries.get("number") == project.get("number")) {
                                addResponse = true;
                            } else {
                                addResponse = false;
                            }
                        }

                        if (queries.containsKey("keyword") && !targetKey.isNull("keyword")) {
                            if (queries.get("keyword") == project.get("keyword")) {
                                addResponse = true;
                            } else {
                                addResponse = false;
                            }
                        }
                    }
                }

                if (addResponse)
                    response += project.toString();

            }
            response += "]";
        } catch (JSONException e) {
            String message = "The JSON in the file could not be parsed";
            logger.error(message);
            return new AbstractMap.SimpleEntry<>(false, message);
        }

        return new AbstractMap.SimpleEntry<>(true, response);
    }
}
