package com.web.convert;

import com.web.dto.ItemsDtoIn;
import com.web.entity.Items;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemsConvert {

    @Autowired
    private ModelMapper modelMapper;

    // chuyen du lieu tu entity sang dto
    public ItemsDtoIn ItemsToDto(Items Items) {
        ItemsDtoIn ItemsDtoIn = new ItemsDtoIn();
        ItemsDtoIn = modelMapper.map(Items, ItemsDtoIn.class);
        return ItemsDtoIn;
    }

    // chuyen tu dto sang entity
    public Items ItemsToEntity(ItemsDtoIn ItemsDtoIn){
        Items Items = new Items();
        Items = modelMapper.map(ItemsDtoIn, Items.class);
        return Items;
    }
}
