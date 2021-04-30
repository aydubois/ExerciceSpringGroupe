package org.aydbs.projectitems.controller;


import io.swagger.annotations.Api;
import org.aydbs.projectitems.dto.ItemDTO;
import org.aydbs.projectitems.entity.Item;
import org.aydbs.projectitems.exception.ItemNotFoundException;
import org.aydbs.projectitems.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="items API", produces = "", consumes="" , tags="Items", protocols="GET, POST, PATCH, DELETE")
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAll() {
        return itemService.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Item> get(@PathVariable("id") Long id) {
        try {
            return itemService.get(id);
        }catch (ItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping(path = "/order/{id}")
    public ResponseEntity<List<Item>> getByOrderId(@PathVariable("id") Long idOrder) {
        try {
            return itemService.getByOrderId(idOrder);
        }catch (ItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Item> add(@RequestBody ItemDTO item) {
        try {
            return itemService.add(item, 0L); //TODO : modifier orderID
        }catch (ItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        return itemService.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Item> replaceItem(@RequestBody ItemDTO newItem, @PathVariable Long id){
        try {
            return itemService.patch(newItem,id);
        }catch (ItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}