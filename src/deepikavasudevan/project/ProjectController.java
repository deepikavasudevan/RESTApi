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
        HashMap<String, String> queries = new HashMap<>();
        String projectId = request.queryParams("projectid");
        String country = request.queryParams("country");
        String number = request.queryParams("number");
        String keyword = request.queryParams("keyword");

        //Checks if the parameter exists
        if ((projectId == null || projectId.length() == 0)
                && (country == null || country.length() == 0)
                && (number == null || number.length() == 0)
                && (keyword == null || keyword.length() == 0)) {
            response.status(400);
            String message = "Request must have at least one search parameter";
            response.body(message);
            return message;
        }

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