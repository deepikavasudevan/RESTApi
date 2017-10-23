package deepikavasudevan.project.JSONModels;

public class ProjectDTO {
    String projectName;
    double projectCost;
    String projectUrl;

    public ProjectDTO(String projectName, double projectCost, String projectUrl) {
        this.projectName = projectName;
        this.projectCost = projectCost;
        this.projectUrl = projectUrl;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(double projectCost) {
        this.projectCost = projectCost;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }
}
