package io.github.iza.todoapp.logic;

import io.github.iza.todoapp.TaskConfigurationProperties;
import io.github.iza.todoapp.model.*;
import io.github.iza.todoapp.model.projection.GroupReadModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private TaskGroupRepository taskGroupRepository;
    private TaskConfigurationProperties myProp;

    public ProjectService(final ProjectRepository projectRepository, final TaskGroupRepository taskGroupRepository, final TaskConfigurationProperties myProp) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.myProp = myProp;
    }

    public List<Project> readAll() {
        return projectRepository.findAll();
    }

    public void save(final Project project) {
        projectRepository.save(project);
    }

    public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {

        if(!myProp.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
            TaskGroup result = projectRepository.findById(projectId)
                    .map(project -> {
                        var targetGroup = new TaskGroup();
                        targetGroup.setDescription(project.getDescription());
                        targetGroup.setTasks(project.getSteps().stream()
                                .map(step -> new Task(
                                        step.getDescription(),
                                        deadline.plusDays(step.getDaysToDeadline())))
                                .collect(Collectors.toSet()));
                        return targetGroup;
                    }).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
            return new GroupReadModel(result);
        }
}
