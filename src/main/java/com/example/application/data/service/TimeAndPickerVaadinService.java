package com.example.application.data.service;

import com.example.application.data.entity.TimeAndPickerVaadin;
import com.example.application.data.repository.TimeAndPickerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;

@Service
public class TimeAndPickerVaadinService  extends CrudService<TimeAndPickerVaadin,Integer>{

    public static final int TimeAndPickerVaadinS_COUNT_LIMIT = 1000;



    public static class LimitReached extends RuntimeException {
    }

    private final TimeAndPickerRepository TimeAndPickerVaadinRepository;
    @Override
    protected JpaRepository<TimeAndPickerVaadin, Integer> getRepository() {
        return TimeAndPickerVaadinRepository;
    }
    public TimeAndPickerVaadinService(TimeAndPickerRepository TimeAndPickerVaadinRepository) {
        this.TimeAndPickerVaadinRepository = TimeAndPickerVaadinRepository;
    }

    public List<TimeAndPickerVaadin> findAll() {
        return TimeAndPickerVaadinRepository.findAll();
    }

    public int countAll() {
        return (int) TimeAndPickerVaadinRepository.count();
    }

    public List<TimeAndPickerVaadin> findByNameContainingIgnoreCase(String name) {
        return TimeAndPickerVaadinRepository.findByNameContainingIgnoreCase(name);
    }

    public TimeAndPickerVaadin save(TimeAndPickerVaadin TimeAndPickerVaadin) {


        return TimeAndPickerVaadinRepository.save(TimeAndPickerVaadin);
    }

    public void delete(TimeAndPickerVaadin TimeAndPickerVaadin) {
        TimeAndPickerVaadinRepository.delete(TimeAndPickerVaadin);
    }

}
