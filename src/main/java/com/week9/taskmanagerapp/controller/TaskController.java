package com.week9.taskmanagerapp.controller;


import com.week9.taskmanagerapp.model.TaskModel;
import com.week9.taskmanagerapp.service.serviceImpl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TaskController {

    private final TaskServiceImpl taskService;

    @Autowired
    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/newTask")
    public String saveTask(HttpServletRequest request, @ModelAttribute("task") TaskModel task, Model model){
        HttpSession session = request.getSession(false);

        Long personId = (Long) session.getAttribute("personId");
        String personNames = (String) session.getAttribute("fullname");
        String status = "Backlogs";

        System.out.println(task.getTaskDescription());
        System.out.println(task.getTaskTitle());
        System.out.println(task.getTaskTag());

        TaskModel newTask = new TaskModel(task.getTaskTitle(), task.getTaskDescription(),task.getTaskTag(), status, personId, personNames);
        taskService.addTask(newTask);
        return "redirect:/";
    }


}
