package io.github.iza.todoapp.logic;

import io.github.iza.todoapp.model.TaskGroup;
import io.github.iza.todoapp.model.TaskGroupRepository;
import io.github.iza.todoapp.model.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskGroupServiceTest {

    @Test
    @DisplayName("should throw IllegalStateException when undone tasks")
    void toggleGroup_undoneTasksExists_throwIllegalStateException() {
        //given
        var mockTaskRepository = taskRepositoryReturning(true);
        //system under test
        var toTest = new TaskGroupService(null, mockTaskRepository);
        //when
        var exception = catchThrowable(() -> toTest.toggleGroup(0));
        //then
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("as undone tasks");
    }

    @Test
    @DisplayName("should throw IllegalArgumentException when no group")
    void toggleGroup_taskGroupNotFound_throwIllegalArgumentException() {
        //given
        var mockTaskRepository = taskRepositoryReturning(false);
        //and
        var mockGroupRepository = mock(TaskGroupRepository.class);
        when(mockGroupRepository.findById(anyInt())).thenReturn(Optional.empty());
        //system under test
        var toTest = new TaskGroupService(mockGroupRepository, mockTaskRepository);
        //when
        var exception = catchThrowable(() -> toTest.toggleGroup(0));
        //then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("with given id not");
    }

    @Test
    @DisplayName("should change status group")
    void toggleGroup_changeStatusGroup() {
        //given
        var mockRepository = mock(TaskRepository.class);
        when(mockRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(false);
        //and
        var taskGroup = new TaskGroup();
        taskGroup.setDone(false);
        //and
        var mockGroupRepository = mock(TaskGroupRepository.class);
        when(mockGroupRepository.findById(anyInt())).thenReturn(Optional.of(taskGroup));
        //system under test
        var toTest = new TaskGroupService(mockGroupRepository, mockRepository);
        //when
        toTest.toggleGroup(0);
        //then
        assertThat(taskGroup.isDone()).isEqualTo(true);
    }

    private TaskRepository taskRepositoryReturning(final boolean result) {
        var mockTaskRepository = mock(TaskRepository.class);
        when(mockTaskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(result);
        return  mockTaskRepository;
    }
}