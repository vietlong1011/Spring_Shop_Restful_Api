package com.web.convert;

import com.web.dto.ItemsDtoIn;
import com.web.entity.Items;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ItemsConvert {

    @Autowired
    private ModelMapper modelMapper;

    // chuyen du lieu tu entity sang dto
    public ItemsDtoIn itemsToDto(Items items) {
        ItemsDtoIn itemsDtoIn ;
        itemsDtoIn = modelMapper.map(items, ItemsDtoIn.class);
        return itemsDtoIn;
    }

    // chuyen tu dto sang entity
    public Items itemsToEntity(ItemsDtoIn itemsDtoIn){
        Items items ;
        items = modelMapper.map(itemsDtoIn, Items.class);
        items.setCreateDate(new Date());
        return items;
    }
}
