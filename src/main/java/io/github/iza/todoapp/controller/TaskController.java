package io.github.iza.todoapp.controller;

import io.github.iza.todoapp.model.Task;
import io.github.iza.todoapp.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RepositoryRestController
public class TaskController {

    public static final Logger logger = LoggerFactory.getLogger(TaskRepository.class);
    private final TaskRepository repository;

    public TaskController(final TaskRepository repository) {
        this.repository = repository;
    }
    ResponseEntity<List<Task>> readAllTasks() {
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(repository.findAll());
    }
}
