package com.example.demo.repository.item;

import com.example.demo.domain.Item;

public interface ItemRepository{

    Item findByName(String name);
    Item save(Item item);

    Item findById(Long id);
}