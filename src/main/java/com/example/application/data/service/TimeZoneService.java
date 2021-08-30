package com.example.application.data.service;

import com.example.application.data.entity.TimeZone;
import com.example.application.data.repository.TimeZoneRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;

@Service
public class TimeZoneService extends CrudService<TimeZone,Integer>{

    public static final int TimeAndPickerVaadinS_COUNT_LIMIT = 1000;



    public static class LimitReached extends RuntimeException {
    }

    private final TimeZoneRepository timeZone;
    @Override
    protected JpaRepository<TimeZone, Integer> getRepository() {
        return timeZone;
    }
    public TimeZoneService(TimeZoneRepository timeZone) {
        this.timeZone = timeZone;
    }

    public List<TimeZone> findAll() {
        return timeZone.findAll();
    }

    public int countAll() {
        return (int) timeZone.count();
    }

    public List<TimeZone> findByNameContainingIgnoreCase(String name) {
        return timeZone.findByNameContainingIgnoreCase(name);
    }

    public TimeZone save(TimeZone time) {


        return timeZone.save(time);
    }

    public void delete(TimeZone time) {
        timeZone.delete(time);
    }

}
