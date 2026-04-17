package com.fixit.tasks.application.port.in;

import com.fixit.tasks.domain.model.AutoAssignSummary;
import com.fixit.tasks.domain.model.Task;

import java.util.List;


public interface ITaskServicePort<Task, Long> {

    Task assignUrgentTask(Long taskId);
    Task updateTask(Long id, Task task);
    AutoAssignSummary autoAssignAllUrgentTasks();
    void processWaitingTasks();
    void startTask(Long taskId);
    void completeTask(Long taskId);
    Task create(Task entity);
    List<Task> getAll();
    Task getById(ID id);
    void delete(ID id);
}