package com.week9.taskmanagerapp.controller;

import com.week9.taskmanagerapp.model.Person;
import com.week9.taskmanagerapp.model.TaskModel;
import com.week9.taskmanagerapp.service.serviceImpl.PersonServiceImpl;
import com.week9.taskmanagerapp.service.serviceImpl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PersonController {

    private final PersonServiceImpl personService;
    private final TaskServiceImpl taskService;

    @Autowired
    public PersonController(PersonServiceImpl personService, TaskServiceImpl taskService) {
        this.personService = personService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String showHomePage(HttpServletRequest request, @ModelAttribute("person") Person person, Model model){

        HttpSession session = request.getSession(false);

        if(session != null){
            List<TaskModel> list = taskService.getAllTasks();
            model.addAttribute("taskList", list);


            String fullName = (String) session.getAttribute("fullName");
            Long posterId = (Long) session.getAttribute("personId");
            model.addAttribute("fullName", fullName);
            model.addAttribute("posterId", posterId);
            return "homePage";
        }
        return "index";
    }

    @PostMapping("/signup")
    public String addPersons(Model model, @ModelAttribute("person") Person person){
        if(person != null){
            model.addAttribute("mess", "true");
            personService.savePersons(person);
        }
        model.addAttribute("mess", "false");
        return "index";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model, @RequestParam("email") String email, @RequestParam("password") String password){

        Person person = personService.getUserByEmail(email);

        if(person == null){
            model.addAttribute("message", "false");
            return "index";
        }

        if(email.equals(person.getEmail()) && password.equals(person.getPassword())){
            HttpSession session = request.getSession();
            session.setAttribute("personId", personService.getUserByEmail(email).getPersonId());
            session.setAttribute("fullName", personService.getUserByEmail(email).getFirstName() + " " + personService.getUserByEmail(email).getFirstName());
            System.out.println(personService.getUserByEmail(email).getPersonId());
            return "redirect:/";
        }
        model.addAttribute("message", "false");
        return "index";
    }
}
