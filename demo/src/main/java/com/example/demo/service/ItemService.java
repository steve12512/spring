package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.dto.requests.item_requests.CreateItemRequest;
import com.example.demo.exception.item.ItemAlreadyExistsException;
import com.example.demo.repository.item.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemService{
    ItemRepository repository;

    public Item createItem(CreateItemRequest request){
        String name = request.name();
        if (repository.findByName(name) != null) throw new ItemAlreadyExistsException("Item with id: " + request.name() + " already exists");
        Item item = new Item(request.name(),request.price(),request.info());
        repository.save(item);
        return item;
    }

    public Item getItem(int id){
        Item item = repository.findById(id);
        return item;
    }


}

