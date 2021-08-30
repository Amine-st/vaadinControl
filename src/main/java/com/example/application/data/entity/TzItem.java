package com.example.application.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TzItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id ;
    int HStart;
    int MStart = 0;
    int HEnd;
    int MEnd = 0;


    public TzItem() {
    }

    public TzItem( int HStart, int MStart, int HEnd, int MEnd) {
        this.HStart = HStart;
        this.MStart = MStart;
        this.HEnd = HEnd;
        this.MEnd = MEnd;
    }



    public int getHStart() {
        return HStart;
    }

    public void setHStart(int HStart) {
        this.HStart = HStart;
    }

    public int getMStart() {
        return MStart;
    }

    public void setMStart(int MStart) {
        this.MStart = MStart;
    }

    public int getHEnd() {
        return HEnd;
    }

    public void setHEnd(int HEnd) {
        this.HEnd = HEnd;
    }

    public int getMEnd() {
        return MEnd;
    }

    public void setMEnd(int MEnd) {
        this.MEnd = MEnd;
    }
}
