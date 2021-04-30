package org.abr.audreybr.controller;

import org.abr.audreybr.entity.User;
import org.abr.audreybr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private UserService service;

    @GetMapping
    public String index(Model model){
        List<User> users = service.getAll();
        model.addAttribute("users",users);
        model.addAttribute("newUser",new User());
        return "register";
    }

    @DeleteMapping(path = "{id}")
    public String delete(@PathVariable("id") long id){
        service.deleteUser(id);
        return "redirect:/view";
    }

    @PostMapping()
    public String add(@ModelAttribute User user, Model model) {
        service.create(user);
        model.addAttribute("newUser", new User());
        return "login";
    }

    @PostMapping(path = "login")
    public RedirectView login(@ModelAttribute User user) {
        User authUser = service.login(user.getName());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8081/index/"+authUser.getId());
        return redirectView;
    }
    @GetMapping(path = "login")
    public String loginView(@ModelAttribute User user,Model model) {
        model.addAttribute("newUser", new User());
        return "login";
    }

    @PutMapping(path = "{id}")
    public String edit(@PathVariable long id, @ModelAttribute User user) {
        service.editUser(id,user);
        return "redirect:/view/"+id;
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model){
        User user = service.getUser(id);
        model.addAttribute("user",user);
        return "view-by-id";
    }

}