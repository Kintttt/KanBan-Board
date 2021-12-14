package com.week9.taskmanagerapp.controller;


import com.week9.taskmanagerapp.model.TaskModel;
import com.week9.taskmanagerapp.service.serviceImpl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(path = "/completed/{taskId}")
    public String markCompleted(@PathVariable String taskId, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        Long id = Long.parseLong(taskId);

        TaskModel taskModel = taskService.getTaskById(id);
        taskModel.setTaskStatus("Completed");
        taskService.addTask(taskModel);

        return "redirect:/";
    }


    @RequestMapping(path = "/inprogress/{taskId}")
    public String markInProgress(@PathVariable String taskId, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        Long id = Long.parseLong(taskId);

        TaskModel taskModel = taskService.getTaskById(id);
        taskModel.setTaskStatus("Inprogress");
        taskService.addTask(taskModel);

        return "redirect:/";
    }

    @RequestMapping(path = "/backlogs/{taskId}")
    public String markBacklogs(@PathVariable String taskId, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        Long id = Long.parseLong(taskId);

        TaskModel taskModel = taskService.getTaskById(id);
        taskModel.setTaskStatus("Backlogs");
        taskService.addTask(taskModel);

        return "redirect:/";
    }


}
