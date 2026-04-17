package com.fixit.tasks.application.port.out;

import com.fixit.tasks.domain.enums.TaskStatus;
import com.fixit.tasks.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskPersistencePort extends ICrudPersistencePort<Task, Long> {
    long countUrgentTasksByTechnicianId(Long technicianId);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByTechnicianId(Long technicianId);
    Task save(Task entity);
    Optional<Task> findById(ID id);
    List<Task> findAll();
    void deleteById(ID id);
}