package deepikavasudevan.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static spark.Spark.*;

/*starting application which contains the port
on which the application runs and its routes */
public class ProjectApplication {

    static Logger logger = LogManager.getLogger("ProjectApplication");
    public static void main(String args[]) {
        logger.info("Server running on Port 8800");
        port(8800);

        post("/createProject", ProjectController.createProject);
        get("/requestProject", ProjectController.requestProject);
    }
}
