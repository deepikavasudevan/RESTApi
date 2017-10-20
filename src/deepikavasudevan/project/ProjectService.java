package deepikavasudevan.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProjectService {
    static String fileName = "projects.txt";
    static Logger logger = LogManager.getLogger("ProjectController");

    public boolean create(String requestContents) {
        try {
            JSONObject requestJson = new JSONObject(requestContents);
        } catch (JSONException exception) {
            logger.error("The request contains malformed JSON");
            return false;
        }

        try {
            logger.info("Writing content from request to the file");
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(requestContents + ",");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
            return true;

        } catch (IOException exception) {
            logger.error("Error in writing contents to the file");
            return false;
        }
    }
}
