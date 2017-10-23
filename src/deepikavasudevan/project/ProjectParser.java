package deepikavasudevan.project;

import deepikavasudevan.project.JSONModels.Project;
import deepikavasudevan.project.JSONModels.ProjectDTO;
import deepikavasudevan.project.JSONModels.TargetKeys;

import java.util.*;

public class ProjectParser {
    private Map<String, String[]> queries;

    public ProjectParser(Map<String, String[]> queries) {
        this.queries = queries;
    }

    public ProjectDTO getMatchingProjects(List<Project> projects) {
        List<Project> addedProjects = new ArrayList<>();

        if (queries.isEmpty()) {
            Project maxProject = getMaximumCostProject(projects);
            return maxProject.toProjectDTO();
        } else if (queries.containsKey("projectid")) {
            Project project = getProjectWithId(queries.get("projectId"), projects);
            if (project != null)
                return project.toProjectDTO();
        } else {
            for (Project project : projects) {
                boolean addResponse = match(project);
                if (addResponse)
                    addedProjects.add(project);
            }
            Project maxProject = getMaximumCostProject(addedProjects);
            return maxProject.toProjectDTO();
        }
        return null;
    }

    private Project getProjectWithId(String[] projectIds, List<Project> projects) {
        for (Project project : projects) {
            if (Arrays.asList(projectIds).contains(project.getId())) {
                return project;
            }
        }
        return null;
    }

    private Project getMaximumCostProject(List<Project> projects) {
        Project maxProject = projects.get(0);
        for (Project project : projects) {
            if (project.getProjectCost() > maxProject.getProjectCost()) {
                maxProject = project;
            }
        }
        return maxProject;
    }

    public boolean match(Project project) {
        boolean addResponse = true;

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
