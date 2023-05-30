package com.web.service.impl;

import com.web.convert.ItemsConvert;
import com.web.dto.DtoIn.ItemsDtoIn;
import com.web.entity.Items;
import com.web.repository.ItemsRepository;
import com.web.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsImpl implements ItemsService {

    @Autowired
    private ItemsConvert ItemsConvert;
    @Autowired
    private ItemsRepository ItemsRepository;

    // lay ra Items theo idItems
    @Override
    public ItemsDtoIn getItems(Long idItems) {
        Items Items = ItemsRepository.findById(idItems).get();
        ItemsDtoIn ItemsDtoIn = ItemsConvert.ItemsToDto(Items);
        return ItemsDtoIn;
    }

    // lay ra toan bo danh sach Items
    @Override
    public List<ItemsDtoIn> getAllItems() {
        List<Items> ItemsList =  ItemsRepository.findAll(Sort.by("nameItems").ascending());
        List<ItemsDtoIn> ItemsDtoInList = new ArrayList<>();
        for (Items Items : ItemsList){
            ItemsDtoIn ItemsDtoIn = new ItemsDtoIn();
            ItemsDtoIn =   ItemsConvert.ItemsToDto(Items);
            ItemsDtoInList.add(ItemsDtoIn);
        }
        return ItemsDtoInList;
    }

    @Override
    public ItemsDtoIn saveItems(ItemsDtoIn ItemsDtoIn) {
        Items Items = new Items();
        Items = ItemsConvert.ItemsToEntity(ItemsDtoIn);
        Items = ItemsRepository.save(Items);
        return ItemsConvert.ItemsToDto(Items);
    }

    @Override
    public ItemsDtoIn deleteItemsById(Long idItems) {
        Items Items = ItemsRepository.findById(idItems).orElseThrow();
        if (Items.getIdItems() == idItems) {
            ItemsRepository.deleteById(Items.getIdItems());
        }
        return ItemsConvert.ItemsToDto(Items);
    }

    @Override
    public ItemsDtoIn updateItemsById(ItemsDtoIn ItemsDtoIn) {
        Items Items = new Items();
        Items.setIdItems(ItemsDtoIn.getIdItems());
        Items = ItemsConvert.ItemsToEntity(ItemsDtoIn);
        Items = ItemsRepository.save(Items);
        return ItemsConvert.ItemsToDto(Items);
    }
}

