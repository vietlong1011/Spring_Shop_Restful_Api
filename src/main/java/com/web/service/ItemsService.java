package com.web.service;

import com.web.dto.ItemsDtoIn;
import com.web.dto.UserDtoIn;
import com.web.entity.Cart;
import com.web.entity.Items;

import java.util.List;

public interface ItemsService {
    ItemsDtoIn getItems(Long idItems);

    List<ItemsDtoIn> getAllItems() ;

    //CRUD
    ItemsDtoIn saveItems(ItemsDtoIn itemsDtoIn);

    ItemsDtoIn deleteItemsById(Long idItems);

    ItemsDtoIn updateItemsById(ItemsDtoIn itemsDtoIn);
}
