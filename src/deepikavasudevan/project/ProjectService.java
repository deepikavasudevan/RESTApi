package deepikavasudevan.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class ProjectService {
    static String fileName = "projects.txt";
    static Logger logger = LogManager.getLogger("ProjectService");

    public boolean create(String requestContents) {
        try {
            JSONObject requestJson = new JSONObject(requestContents);
            //Contains more than the number of expected fields
            if(requestJson.length() > 9) {
                logger.error("More number of fields found in the request");
                return false;
            }
        } catch (JSONException exception) {
            logger.error("The request contains malformed JSON");
            return false;
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
        return true;
    }
}
