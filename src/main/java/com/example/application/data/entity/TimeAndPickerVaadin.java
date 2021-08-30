package com.example.application.data.entity;

import lombok.Data;
import org.dom4j.tree.AbstractEntity;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class TimeAndPickerVaadin extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private List<TzItem> timezone;

    @Column(name = "timezone1")
    @Lazy
    private Integer timezone1;
    @Column(name = "timezone2")
    private Integer timezone2;
    @Column(name = "timezone3")
    private Integer timezone3;



    public void setId(int id) {
        this.id = id;
    }


    public Integer getTimezone1() {
        return timezone1;
    }

    public void setTimezone1(Integer timezone1) {
        this.timezone1 = timezone1;
    }

    public Integer getTimezone2() {
        return timezone2;
    }

    public void setTimezone2(Integer timezone2) {
        this.timezone2 = timezone2;
    }

    public Integer getTimezone3() {
        return timezone3;
    }

    public void setTimezone3(Integer timezone3) {
        this.timezone3 = timezone3;
    }

    public List<TzItem> getTimezone() {
        return timezone;
    }

    public void setTimezone(List<TzItem> timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


}
