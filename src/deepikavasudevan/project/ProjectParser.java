package deepikavasudevan.project;

import deepikavasudevan.project.JSONModels.Project;
import deepikavasudevan.project.JSONModels.TargetKeys;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class ProjectParser {
    private Map<String, String[]> queries;

    public ProjectParser(Map<String, String[]> queries) {
        this.queries = queries;
        if(queries.isEmpty()) {
            queries.put("projectCost", null);
        }
    }

    public boolean match(Project project) {
        boolean addResponse = true;
        if (queries.containsKey("projectid")) {
            if (Arrays.asList(queries.get("projectid")).contains(project.getId())) {
                addResponse = true;
            } else {
                addResponse = false;
            }
            return addResponse;
        }

        if (queries.containsKey("country")) {
            if (!Collections.disjoint(Arrays.asList(queries.get("country")), Arrays.asList(project.getTargetCountries()))) {
                addResponse = true;
            } else {
                addResponse = false;
            }
        }

        if (queries.containsKey("number")) {
            for (TargetKeys target : project.getTargetKeys()) {
                if (Arrays.asList(queries.get("number")).contains(target.getNumber())) {
                    addResponse = true;
                } else {
                    addResponse = false;
                }
            }
        }

        if (queries.containsKey("keyword")) {
            for (TargetKeys target : project.getTargetKeys()) {
                if (Arrays.asList(queries.get("keyword")).contains(target.getKeyword())) {
                    addResponse = true;
                } else {
                    addResponse = false;
                }
            }
        }
        return addResponse;
    }
}
