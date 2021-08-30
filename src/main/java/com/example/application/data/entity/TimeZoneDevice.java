package com.example.application.data.entity;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.util.List;


public class TimeZoneDevice extends AbstractEntity {


    int id;
    String name;

    @ElementCollection(fetch = FetchType.EAGER,targetClass=int.class)
    List<List<Integer>> items;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<List<Integer>> getItems() {
        return items;
    }

    public void setItems(List<List<Integer>> items) {
        this.items = items;
    }
}
