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

    public ProjectDTO toProjectDTO() {
        return new ProjectDTO(projectName, projectCost, projectUrl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (enabled != project.enabled) return false;
        if (Double.compare(project.projectCost, projectCost) != 0) return false;
        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (projectName != null ? !projectName.equals(project.projectName) : project.projectName != null) return false;
        if (creationDate != null ? !creationDate.equals(project.creationDate) : project.creationDate != null)
            return false;
        if (expiryDate != null ? !expiryDate.equals(project.expiryDate) : project.expiryDate != null) return false;
        if (targetCountries != null ? !targetCountries.equals(project.targetCountries) : project.targetCountries != null)
            return false;
        if (projectUrl != null ? !projectUrl.equals(project.projectUrl) : project.projectUrl != null) return false;
        return targetKeys != null ? targetKeys.equals(project.targetKeys) : project.targetKeys == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (targetCountries != null ? targetCountries.hashCode() : 0);
        temp = Double.doubleToLongBits(projectCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (projectUrl != null ? projectUrl.hashCode() : 0);
        result = 31 * result + (targetKeys != null ? targetKeys.hashCode() : 0);
        return result;
    }
}
