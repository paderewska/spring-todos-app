package io.github.iza.todoapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table
@Entity(name = "project_steps")
public class ProjectStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Project step's description must be not empty")
    private String description;
    private Long daysToDeadline;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public Long getDaysToDeadline() {
        return daysToDeadline;
    }

    void setDaysToDeadline(Long daysToDeadline) {
        this.daysToDeadline = daysToDeadline;
    }

    public Project getProject() {
        return project;
    }

    void setProject(Project project) {
        this.project = project;
    }
}
