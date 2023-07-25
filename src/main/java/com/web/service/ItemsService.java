package com.web.service;

import com.web.dto.ItemsDtoIn;

import java.util.List;

public interface ItemsService {
    ItemsDtoIn getItems(Long idItems);

    List<ItemsDtoIn> getAllItems();

    ItemsDtoIn saveItems(ItemsDtoIn itemsDtoIn);

}
