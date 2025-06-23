package projects.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private Integer projectId;
    private String projectName;
    private BigDecimal estimatedHours;
    private BigDecimal actualHours;
    private Integer difficulty;
    private String notes;
    private List<Material> materials = new ArrayList<>();
    private List<Step> steps = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(BigDecimal estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public BigDecimal getActualHours() {
        return actualHours;
    }

    public void setActualHours(BigDecimal actualHours) {
        this.actualHours = actualHours;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID=").append(projectId).append("\n");
        sb.append("name=").append(projectName).append("\n");
        sb.append("estimatedHours=").append(estimatedHours != null ? estimatedHours : "null").append("\n");
        sb.append("actualHours=").append(actualHours != null ? actualHours : "null").append("\n");
        sb.append("difficulty=").append(difficulty != null ? difficulty : "null").append("\n");
        sb.append("notes=").append(notes != null ? notes : "null").append("\n");
        sb.append("Materials:\n");
        for (Material m : materials) {
            sb.append("  ID=").append(m.getMaterialId())
              .append(", materialName=").append(m.getMaterialName())
              .append(", numRequired=").append(m.getNumRequired())
              .append(", cost=").append(m.getCost() != null ? m.getCost() : "null").append("\n");
        }
        sb.append("Steps:\n");
        for (Step s : steps) {
            sb.append("  ID=").append(s.getStepId())
              .append(", stepText=").append(s.getStepText()).append("\n");
        }
        sb.append("Categories:\n");
        for (Category c : categories) {
            sb.append("  ID=").append(c.getCategoryId())
              .append(", categoryName=").append(c.getCategoryName()).append("\n");
        }
        return sb.toString();
    }
}