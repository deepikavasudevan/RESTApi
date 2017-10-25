package deepikavasudevan.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import deepikavasudevan.project.JSONModels.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.AbstractMap;

public class ProjectController {
    static Logger logger = LogManager.getLogger("ProjectController");
    static ObjectMapper object = new ObjectMapper();

    public static Route createProject = (Request request, Response response) -> {
        object.enable(SerializationFeature.INDENT_OUTPUT);
        ProjectService service = new ProjectService();
        AbstractMap.SimpleEntry<Boolean, String> result = service.create(request.body());
        if (result.getKey()) {
            logger.info(result.getValue());
            response.status(200);
            response.type("application/json");
            return object.writeValueAsString(new ErrorMessage(result.getValue()));
        } else {
            logger.error(result.getValue());
            response.status(400);
            response.body(result.getValue());
            response.type("application/json");
            return result.getValue();
        }
    };

    public static Route requestProject = (Request request, Response response) -> {
        object.enable(SerializationFeature.INDENT_OUTPUT);
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
            response.type("application/json");
            return object.writeValueAsString(new ErrorMessage(result.getValue()));
        }
    };
}