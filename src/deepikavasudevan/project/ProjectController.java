package deepikavasudevan.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Request;
import spark.Response;
import spark.Route;

public class ProjectController {
    static Logger logger = LogManager.getLogger("ProjectController");


    public static Route createProject = (Request request, Response response) -> {
        ProjectService service = new ProjectService();
        if(service.create(request.body())) {
            String message = "Campaign is successfully created";
            logger.info(message);
            response.status(200);
            response.type("application/json");
            return message;
        } else {
            logger.error("Error in writing to the file");
            response.status(400);
            response.body("Error in writing to the file");
            return response.body();
        }
    };

    public static Route requestProject = (Request request, Response response) -> {
        return "Hello there";
    };
}