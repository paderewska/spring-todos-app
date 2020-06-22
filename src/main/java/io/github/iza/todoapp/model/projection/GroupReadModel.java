package io.github.iza.todoapp.model.projection;

import io.github.iza.todoapp.model.Task;
import io.github.iza.todoapp.model.TaskGroup;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupReadModel {

    private String description;
    private LocalDateTime lastTaskDeadline;
    private Set<GroupTaskReadModel> tasks;

    public GroupReadModel(TaskGroup source) {
        description = source.getDescription();
        source.getTasks().stream()
                .map(Task::getDeadline)
                .max(LocalDateTime::compareTo)
                .ifPresent(date -> lastTaskDeadline = date);
        tasks = source.getTasks().stream().map(GroupTaskReadModel::new).collect(Collectors.toSet());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLastTaskDeadline() {
        return lastTaskDeadline;
    }

    public void setLastTaskDeadline(LocalDateTime lastTaskDeadline) {
        this.lastTaskDeadline = lastTaskDeadline;
    }

    public Set<GroupTaskReadModel> getTasks() {
        return tasks;
    }

    public void setTasks(Set<GroupTaskReadModel> tasks) {
        this.tasks = tasks;
    }
}
