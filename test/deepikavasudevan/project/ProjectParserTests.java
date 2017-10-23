package deepikavasudevan.project;

import deepikavasudevan.project.JSONModels.Project;
import deepikavasudevan.project.JSONModels.ProjectDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectParserTests {
    ProjectParser projectParser;

    @Test
    public void getProjectWithMaximumCostWhenNoQueriesAreProvided() {
        Project project1 = new Project();
        project1.setId("1111");
        project1.setProjectName("Project1");
        project1.setProjectCost(111.555);
        project1.setProjectUrl("www.unit.com");
        Project project2 = new Project();
        project2.setId("1211");
        project2.setProjectName("Project2");
        project2.setProjectCost(880.6);
        project2.setProjectUrl("www.unity3d.com");
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);

        projectParser = new ProjectParser(new HashMap<>());

        ProjectDTO project = projectParser.getMatchingProjects(projects);

        Assert.assertEquals(project.getProjectCost(), 880.6, 0.01);
        Assert.assertEquals(project.getProjectName(), "Project2");
    }
}
