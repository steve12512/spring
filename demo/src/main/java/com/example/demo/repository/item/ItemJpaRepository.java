package com.example.demo.repository.item;

import com.example.demo.domain.Item;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface ItemJpaRepository extends JpaRepository<Item,Integer>, ItemRepository{

}