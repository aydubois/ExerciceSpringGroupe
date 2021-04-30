package com.order.order.controller;

import com.order.order.NotFoundException;
import com.order.order.entity.Order;
import com.order.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ViewController {
    private final OrderService orderService;

    public ViewController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/index")
    public String index(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("order", new Order());
        return "index";
    }

    @GetMapping("/index/{id}")
    public String indexById(Model model,@PathVariable long id) {
        model.addAttribute("orders", orderService.findOrdersByUserId(id));
        model.addAttribute("order", new Order());
        return "index";
    }

    @GetMapping(path ="/show/{id}")
    public String show(Model model, @PathVariable int id) {
        model.addAttribute("order", orderService.findOrderById(id));
        return "view-by-id";
    }

    @GetMapping("/addOrder")
    public String showAddOrder(Model model){
        model.addAttribute("Order",new Order());
        return "addOrder";
    }

    @PostMapping("create")
    public String create(Model model, @ModelAttribute("Order") Order newOrder) {
        orderService.addOrder(newOrder);
        return "redirect:/index";
    }

    @DeleteMapping(path = "/delete/{id}")
    public String delete(@PathVariable int id) throws NotFoundException {
        Order order = orderService.findOrderById(id);
        orderService.removeOrder(order);
        return "redirect:/index";
    }

    @RequestMapping(path = "/showItems/{id}")
    public RedirectView showItems(@PathVariable int id, Model model) throws NotFoundException{
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8083/index/"+id);
        return redirectView;
    }
}
