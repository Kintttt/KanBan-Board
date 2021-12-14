package com.week9.taskmanagerapp.service.serviceImpl;

import com.week9.taskmanagerapp.model.TaskModel;
import com.week9.taskmanagerapp.respository.TaskRepository;
import com.week9.taskmanagerapp.service.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskServices {

    private final TaskRepository taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    public void addTask(TaskModel task){
        taskRepo.save(task);
    }

    public List<TaskModel> getAllTasks(){
        List<TaskModel> taskList = taskRepo.findAll();
        return taskList;
    }

    public TaskModel getTaskById(Long id){
       return taskRepo.findById(id).get();
    }
}

