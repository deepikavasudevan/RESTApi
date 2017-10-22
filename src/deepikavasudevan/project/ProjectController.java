package deepikavasudevan.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.AbstractMap;
import java.util.HashMap;

public class ProjectController {
    static Logger logger = LogManager.getLogger("ProjectController");


    public static Route createProject = (Request request, Response response) -> {
        ProjectService service = new ProjectService();
        AbstractMap.SimpleEntry<Boolean, String> result = service.create(request.body());
        if (result.getKey()) {
            logger.info(result.getValue());
            response.status(200);
            response.type("application/json");
            return result.getValue();
        } else {
            logger.error(result.getValue());
            response.status(400);
            response.body(result.getValue());
            return result.getValue();
        }
    };

    public static Route requestProject = (Request request, Response response) -> {
        ProjectService service = new ProjectService();
        AbstractMap.SimpleEntry<Boolean, String> result = service.get(request.queryMap().toMap());
        if (result.getKey()) {
            logger.info(result.getValue());
            response.status(200);
            response.type("application/json");
            return result.getValue();
        } else {
            logger.error(result.getValue());
            response.status(404);
            response.body(result.getValue());
            return result.getValue();
        }
    };
}