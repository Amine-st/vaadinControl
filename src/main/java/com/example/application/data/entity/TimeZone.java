package com.example.application.data.entity;

import org.dom4j.tree.AbstractEntity;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

@Entity
public class TimeZone extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    @Column(insertable = false, updatable = false)
    private LocalTime lundiTime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime mardiTime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime mercrediTime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime jeudiTime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime vendrediTime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime samediTime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime dimancheTime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime lundiETime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime mardiETime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime mercrediETime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime jeudiETime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime vendrediETime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime samediETime = LocalTime.of(12, 0);
    @Column(insertable = false, updatable = false)
    private LocalTime dimancheETime = LocalTime.of(12, 0);
    @Column(name = "timezone1")
    @Lazy
    private Integer timezone1;
    @Column(name = "timezone2")
    private Integer timezone2;
    @Column(name = "timezone3")
    private Integer timezone3;

    private String lundi;
    private String mardi;
    private String mercredi;
    private String jeudi;
    private String vendredi;
    private String samedi;
    private String dimanche;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private List<TzItem> timezone;

    public int getId() {
        return id;
    }

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getLundiTime() {
        return lundiTime;
    }

    public void setLundiTime(LocalTime lundiTime) {
        this.lundiTime = lundiTime;
    }

    public LocalTime getMardiTime() {
        return mardiTime;
    }

    public void setMardiTime(LocalTime mardiTime) {
        this.mardiTime = mardiTime;
    }

    public LocalTime getMercrediTime() {
        return mercrediTime;
    }

    public void setMercrediTime(LocalTime mercrediTime) {
        this.mercrediTime = mercrediTime;
    }

    public LocalTime getJeudiTime() {
        return jeudiTime;
    }

    public void setJeudiTime(LocalTime jeudiTime) {
        this.jeudiTime = jeudiTime;
    }

    public LocalTime getVendrediTime() {
        return vendrediTime;
    }

    public void setVendrediTime(LocalTime vendrediTime) {
        this.vendrediTime = vendrediTime;
    }

    public LocalTime getSamediTime() {
        return samediTime;
    }

    public void setSamediTime(LocalTime samediTime) {
        this.samediTime = samediTime;
    }

    public LocalTime getDimancheTime() {
        return dimancheTime;
    }

    public void setDimancheTime(LocalTime dimancheTime) {
        this.dimancheTime = dimancheTime;
    }

    public LocalTime getLundiETime() {
        return lundiETime;
    }

    public void setLundiETime(LocalTime lundiETime) {
        this.lundiETime = lundiETime;
    }

    public LocalTime getMardiETime() {
        return mardiETime;
    }

    public void setMardiETime(LocalTime mardiETime) {
        this.mardiETime = mardiETime;
    }

    public LocalTime getMercrediETime() {
        return mercrediETime;
    }

    public void setMercrediETime(LocalTime mercrediETime) {
        this.mercrediETime = mercrediETime;
    }

    public LocalTime getJeudiETime() {
        return jeudiETime;
    }

    public void setJeudiETime(LocalTime jeudiETime) {
        this.jeudiETime = jeudiETime;
    }

    public LocalTime getVendrediETime() {
        return vendrediETime;
    }

    public void setVendrediETime(LocalTime vendrediETime) {
        this.vendrediETime = vendrediETime;
    }

    public LocalTime getSamediETime() {
        return samediETime;
    }

    public void setSamediETime(LocalTime samediETime) {
        this.samediETime = samediETime;
    }

    public LocalTime getDimancheETime() {
        return dimancheETime;
    }

    public void setDimancheETime(LocalTime dimancheETime) {
        this.dimancheETime = dimancheETime;
    }

    public String getLundi() {
        return lundi;
    }

    public void setLundi(String lundi) {
        this.lundi = lundi;
    }

    public String getMardi() {
        return mardi;
    }

    public void setMardi(String mardi) {
        this.mardi = mardi;
    }

    public String getMercredi() {
        return mercredi;
    }

    public void setMercredi(String mercredi) {
        this.mercredi = mercredi;
    }

    public String getJeudi() {
        return jeudi;
    }

    public void setJeudi(String jeudi) {
        this.jeudi = jeudi;
    }

    public String getVendredi() {
        return vendredi;
    }

    public void setVendredi(String vendredi) {
        this.vendredi = vendredi;
    }

    public String getSamedi() {
        return samedi;
    }

    public void setSamedi(String samedi) {
        this.samedi = samedi;
    }

    public String getDimanche() {
        return dimanche;
    }

    public void setDimanche(String dimanche) {
        this.dimanche = dimanche;
    }

    public List<TzItem> getTimezone() {
        return timezone;
    }

    public void setTimezone(List<TzItem> timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return
                name;
    }
}
