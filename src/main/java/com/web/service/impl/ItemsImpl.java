package com.web.service.impl;

import com.web.convert.ItemsConvert;
import com.web.dto.ItemsDtoIn;
import com.web.entity.Items;
import com.web.repository.ItemsRepository;
import com.web.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemsImpl implements ItemsService {

    @Autowired
    private ItemsConvert itemsConvert;

    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public ItemsDtoIn getItems(Long idItems) {
        Optional<Items> optionalItems = itemsRepository.findById(idItems);
        if (optionalItems.isPresent()) {
            Items items = optionalItems.get();
            return itemsConvert.itemsToDto(items);
        }
        return  null;
    }

    @Override
    public List<ItemsDtoIn> getAllItems() {
        List<Items> itemsList = itemsRepository.findAll();
        List<ItemsDtoIn> itemsDtoInList = new ArrayList<>();
        for (Items items : itemsList) {
            ItemsDtoIn itemsDtoIn = itemsConvert.itemsToDto(items);
            itemsDtoInList.add(itemsDtoIn);
        }
        return itemsDtoInList;
    }

    @Override
    public ItemsDtoIn saveItems(ItemsDtoIn itemsDtoIn) {
        Items items = itemsConvert.itemsToEntity(itemsDtoIn);
        items = itemsRepository.save(items);
        return itemsConvert.itemsToDto(items);
    }


}

