package com.web.controller;

import com.web.dto.ItemsDtoIn;
import com.web.entity.Items;
import com.web.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemsController {

    @Autowired
    public ItemsService itemsService;

    @GetMapping("/items")
    public List<ItemsDtoIn> getItems() {
        return itemsService.getAllItems();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<?> getItemsById(@PathVariable("id") Long idItems) {
        ItemsDtoIn itemsDtoIn = itemsService.getItems(idItems);
        if (idItems == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No value present");
        return ResponseEntity.ok(itemsDtoIn);
    }

    @PostMapping("/items")
    public ItemsDtoIn saveOrder(@RequestBody ItemsDtoIn itemsDtoIn) {
        itemsService.saveItems(itemsDtoIn);
        return itemsDtoIn;
    }

    @PutMapping("/items")
    public String update(@RequestBody ItemsDtoIn items){
        itemsService.updateItemsById(items);
        return "OKE";
    }
}
