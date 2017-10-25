package deepikavasudevan.project;

import deepikavasudevan.project.JSONModels.Project;
import deepikavasudevan.project.JSONModels.ProjectDTO;
import deepikavasudevan.project.JSONModels.TargetKeys;

import java.util.*;

public class ProjectParser {
    private Map<String, String[]> queries;
    private final String projectid = "projectid";

    public ProjectParser(Map<String, String[]> queries) {
        this.queries = queries;
    }

    public ProjectDTO getMatchingProjects(List<Project> projects) {
        List<Project> addedProjects = new ArrayList<>();
        Project selectedProject;

        if (queries.isEmpty()) {
            selectedProject = getMaximumCostProject(projects);
        } else {
            if (queries.containsKey(projectid)) {
                selectedProject = getProjectWithId(queries.get(projectid), projects);
            } else {
                for (Project project : projects) {
                    boolean addResponse = match(project);
                    if (addResponse)
                        addedProjects.add(project);
                }
                selectedProject = getMaximumCostProject(addedProjects);
            }
        }

        if (selectedProject != null && selectedProject.isEnabled())
            return selectedProject.toProjectDTO();
        else
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
        boolean addResponse = false;

        if (queries.containsKey("country")) {
            if (!Collections.disjoint(Arrays.asList(queries.get("country")), Arrays.asList(project.getTargetCountries()))) {
                addResponse = true;
            }
        }

        if (queries.containsKey("number")) {
            for (TargetKeys target : project.getTargetKeys()) {
                if (Arrays.asList(queries.get("number")).contains(target.getNumber())) {
                    addResponse = true;
                }
            }
        }

        if (queries.containsKey("keyword")) {
            for (TargetKeys target : project.getTargetKeys()) {
                if (Arrays.asList(queries.get("keyword")).contains(target.getKeyword())) {
                    addResponse = true;
                }
            }
        }
        return addResponse;
    }
}
