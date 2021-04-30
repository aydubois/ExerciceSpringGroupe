package com.alik.alik.controller;

import com.alik.alik.entity.Customer;
import com.alik.alik.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public String index(Model model){
        List<Customer> customers = service.getCustomers();
        model.addAttribute("customers",customers);
        model.addAttribute("newCustomer",new Customer());
        return "index";
    }

    @DeleteMapping (path = "{id}")
    public String delete(@PathVariable("id") int id){
        service.deleteCustomer(id);
        return "redirect:/view";
    }

    @PostMapping()
    public String add(@ModelAttribute Customer customer) {
        service.addCustomer(customer);
        return "redirect:/view";
    }
    @PutMapping(path = "{id}")
    public String edit(@PathVariable int id, @ModelAttribute Customer customer) {
        service.editCustomer(id,customer);
        return "redirect:/view/"+id;
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model){
        Customer customer = service.getCustomerById(id);
        model.addAttribute("customer",customer);
        return "show";
    }

}
