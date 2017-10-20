package deepikavasudevan.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProjectController {
    static Logger logger = LogManager.getLogger("ProjectController");

    static String fileName = "projects.txt";
    static String path = "/home/deepika/Documents/RESTApi/content";

    public static Route createProject = (Request request, Response response) -> {
        File file = new File(path, fileName);
        if (!file.isFile() && !file.createNewFile()) {
            logger.error("\"" + fileName + "/" + path + "\" File does not exist or cannot be created");
            response.status(404);
            response.body("\"" + fileName + "/" + path + "\" File does not exist or cannot be created");
            return response;
        }

        try {
            new JSONObject(request.body());
        }
        catch(JSONException exception) {

        }

        try {
            logger.info("Writing content from request to the file");
            FileWriter writer = new FileWriter(path + "/" + fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(request.body());
        } catch (IOException exception) {
            logger.error("Error in writing to the file");
            response.status(400);
            response.body("Error in writing to the file");
            return response;
        }

        logger.info("Campaign has been successfully created");
        response.status(200);
        response.type("application/json");
        response.body("Campaign is successfully created");
        return response;
    };

    public static Route requestProject = (Request request, Response response) -> {

        return response;
    };
}