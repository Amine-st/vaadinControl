package com.example.application.data.entity;

import org.dom4j.tree.AbstractEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;


public class TimeAndPicker extends AbstractEntity {
    

    private int id ;

    @NotNull
    private int jour;


    private String name ;
    private LocalTime lundi = LocalTime.of(12,0);
    private LocalTime mardi=LocalTime.of(12,0);
    private LocalTime mercredi=LocalTime.of(12,0);
    private LocalTime jeudi=LocalTime.of(12,0);
    private LocalTime vendredi=LocalTime.of(12,0);
    private LocalTime samedi=LocalTime.of(12,0);
    private LocalTime dimanche=LocalTime.of(12,0);
    private LocalTime lundiE=LocalTime.of(12,0);
    private LocalTime mardiE=LocalTime.of(12,0);
    private LocalTime mercrediE=LocalTime.of(12,0);
    private LocalTime jeudiE=LocalTime.of(12,0);
    private LocalTime vendrediE=LocalTime.of(12,0);
    private LocalTime samediE=LocalTime.of(12,0);
    private LocalTime dimancheE=LocalTime.of(12,0);


    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalTime getLundi() {
        return lundi;
    }
    public void setLundi(LocalTime lundi) {
        this.lundi = lundi;
    }
    public LocalTime getMardi() { return mardi; }
    public void setMardi(LocalTime mardi) { this.mardi = mardi; }
    public LocalTime getMercredi() { return mercredi; }
    public void setMercredi(LocalTime mercredi) { this.mercredi = mercredi; }
    public LocalTime getJeudi() { return jeudi; }
    public void setJeudi(LocalTime jeudi) { this.jeudi = jeudi; }
    public LocalTime getVendredi() { return vendredi; }
    public void setVendredi(LocalTime vendredi) { this.vendredi = vendredi; }
    public LocalTime getSamedi() { return samedi; }
    public void setSamedi(LocalTime samedi) { this.samedi = samedi; }
    public LocalTime getDimanche() { return dimanche; }
    public void setDimanche(LocalTime dimanche) { this.dimanche = dimanche; }
    public LocalTime getLundiE() { return lundiE; }
    public void setLundiE(LocalTime lundiE) { this.lundiE = lundiE; }
    public LocalTime getMardiE() { return mardiE; }
    public void setMardiE(LocalTime mardiE) { this.mardiE = mardiE; }
    public LocalTime getMercrediE() { return mercrediE; }
    public void setMercrediE(LocalTime mercrediE) { this.mercrediE = mercrediE; }
    public LocalTime getJeudiE() { return jeudiE; }
    public void setJeudiE(LocalTime jeudiE) { this.jeudiE = jeudiE; }
    public LocalTime getVendrediE() { return vendrediE; }
    public void setVendrediE(LocalTime vendrediE) { this.vendrediE = vendrediE; }
    public LocalTime getSamediE() { return samediE; }
    public void setSamediE(LocalTime samediE) { this.samediE = samediE; }
    public LocalTime getDimancheE() { return dimancheE; }
    public void setDimancheE(LocalTime dimancheE) { this.dimancheE = dimancheE; }
}
