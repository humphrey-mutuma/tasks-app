package com.example.tasks.service;

import com.example.tasks.model.Task;
import com.example.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

//    get all task
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
//    get task by id

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

//    create task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
//    update task
    public Task updateTask(Long id, Task updatedTask){
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setRiskLevel(updatedTask.getRiskLevel());
                    task.setDueDate(updatedTask.getDueDate());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new RuntimeException("Task not found with id"+ id));
    }
//    delete task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
