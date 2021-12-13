package com.week9.taskmanagerapp.controller;

import com.week9.taskmanagerapp.model.Person;
import com.week9.taskmanagerapp.service.serviceImpl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PersonController {

    private final PersonServiceImpl personService;

    @Autowired
    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
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
            return "homePage";
        }
        model.addAttribute("message", "false");
        return "index";
    }
}
