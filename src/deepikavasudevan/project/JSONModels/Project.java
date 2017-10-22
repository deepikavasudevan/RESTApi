package deepikavasudevan.project.JSONModels;

import java.util.List;

public class Project {
    String id;
    String projectName;
    String creationDate;
    String expiryDate;
    boolean enabled;
    List<String> targetCountries;
    double projectCost;
    String projectUrl;
    List<TargetKeys> targetKeys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getTargetCountries() {
        return targetCountries;
    }

    public void setTargetCountries(List<String> targetCountries) {
        this.targetCountries = targetCountries;
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

    public List<TargetKeys> getTargetKeys() {
        return targetKeys;
    }

    public void setTargetKeys(List<TargetKeys> targetKeys) {
        this.targetKeys = targetKeys;
    }
}
