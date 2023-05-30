package com.web.controller;

import com.web.dto.DtoIn.ItemsDtoIn;
import com.web.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemsController {

    @Autowired
    public ItemsService itemsService;

    @GetMapping("/Items")
    public List<ItemsDtoIn> getItems(){
        return itemsService.getAllItems();
    }

    @GetMapping("/Items/{idItems}")
    private ResponseEntity getItemsById(@PathVariable("idItems") Long idItems){
         ItemsDtoIn itemsDtoIn = itemsService.getItems(idItems);
        if (idItems == null)
            return (ResponseEntity<String>) ResponseEntity.notFound();
        return ResponseEntity.ok(itemsDtoIn);
    }

    @PostMapping("/Items")
    public ItemsDtoIn saveOrder(@RequestBody ItemsDtoIn itemsDtoIn){
        itemsService.saveItems(itemsDtoIn);
        return itemsDtoIn;
    }
}
