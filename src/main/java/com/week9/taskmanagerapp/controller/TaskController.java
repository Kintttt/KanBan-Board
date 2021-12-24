package com.week9.taskmanagerapp.controller;


import com.week9.taskmanagerapp.model.TaskModel;
import com.week9.taskmanagerapp.service.serviceImpl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        String personNames = (String) session.getAttribute("fullName");
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

    //logout method
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("got here");
        return "index";
    }

    @PostMapping("/edit/{taskId}")
    public String editTask(HttpServletRequest request, @PathVariable String taskId, Model model, @RequestParam("newTaskDesc") String taskDesc, @RequestParam("newTaskTitle") String newTaskTitle){
        HttpSession session = request.getSession(false);
        session.setAttribute("taskId", Long.parseLong(taskId));
        session.setAttribute("taskService", taskService);
        Long personId = (Long) session.getAttribute("personId");

        TaskModel taskModel = taskService.getTaskById(Long.parseLong(taskId));
        //long posterId = taskModel.getPosterId();
        System.out.println(taskId);
        System.out.println(taskModel.getTaskDescription() +" "+ taskModel.getTaskId() +" "+ taskModel.getTaskStatus());

        //if(taskId.equals(posterId)){
            model.addAttribute("taskService", taskService);
            taskModel.setTaskDescription(taskDesc);
            taskModel.setTaskTitle(newTaskTitle);
            taskService.addTask(taskModel);
            System.out.println();

//            return "redirect:/";
//        }
        return "redirect:/";
    }

    @RequestMapping(path = "/delete/{taskId}/{posterId}")
    public String removePost(HttpServletRequest request, @PathVariable String posterId, @PathVariable String taskId, Model model){
        HttpSession session = request.getSession(false);
        Long personId = (Long) session.getAttribute("personId");

        taskService.deletePost(Long.parseLong(taskId));
        model.addAttribute("taskService", taskService);
        System.out.println("deleted " + taskId);
        return "redirect:/";

    }




}
