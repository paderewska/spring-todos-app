package io.github.iza.todoapp.adapter;

import io.github.iza.todoapp.model.Task;
import io.github.iza.todoapp.model.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> {

    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from tasks where id=:id")
    boolean existsById(@Param("id")Integer id);

    @Override
    boolean existsByDoneIsFalseAnAndGroup_Id(Integer id);

}
