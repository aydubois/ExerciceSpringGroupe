package org.aydbs.projectitems.controller;


import org.aydbs.projectitems.dto.ItemDTO;
import org.aydbs.projectitems.entity.Item;
import org.aydbs.projectitems.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ViewController {
    private final ItemService itemService;

    public ViewController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping(path = "/index")
    public String index( Model model){
        model.addAttribute("regions", itemService.findAllRegions());
        model.addAttribute("items", itemService.getAll().getBody());
        model.addAttribute("item", new ItemDTO());
        return "allItems";
    }
    @GetMapping(path = "/index/{id}")
    public String indexByOrder( Model model, @PathVariable Long id){
        model.addAttribute("regions", itemService.findAllRegions());
        model.addAttribute("items", itemService.getByOrderId(id).getBody());
        model.addAttribute("item", new ItemDTO());
        model.addAttribute("idOrder",id );

        return "allItems";
    }
    @GetMapping(path = "/item/{id}/{idOrder}")
    public String viewItemWithOrder( Model model, @PathVariable Long id, @PathVariable Long idOrder){
        model.addAttribute("regions", itemService.findAllRegions());
        model.addAttribute("item", itemService.get(id).getBody());
        model.addAttribute("idOrder",idOrder );

        return "oneItem";
    }

    @GetMapping(path = "/item/{id}")
    public String viewItem( Model model, @PathVariable Long id){
        model.addAttribute("regions", itemService.findAllRegions());
        model.addAttribute("item", itemService.get(id).getBody());
        return "oneItem";
    }
    @RequestMapping(path = "/delete/{id}/{idOrder}")
    public String deleteWithOrder (@PathVariable Long id, @PathVariable Long idOrder){
        itemService.delete(id);
        return "redirect:/index/"+idOrder;
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(@PathVariable Long id){
        itemService.delete(id);
        return "redirect:/index";
    }

    @PatchMapping(path = "/patch/{id}")
    public String patch(@ModelAttribute Item item, @PathVariable Long id){
        itemService.patchItem(item, id);
        return "redirect:/item/{id}";
    }
    @PostMapping(path="/add")
    public String create(@ModelAttribute ItemDTO item){
        itemService.add(item, 0L); //TODO : modifier orderId
        return "redirect:/index";
    }
    @PostMapping(path="/add/{idOrder}")
    public String createWithOrder(@ModelAttribute ItemDTO item, @PathVariable Long idOrder){
        itemService.add(item, idOrder);
        return "redirect:/index/"+idOrder;
    }

    @RequestMapping(path = "/test")
    public RedirectView testredirect(){
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8083/index");
        return redirectView;
    }

}
