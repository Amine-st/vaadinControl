package com.example.application.data.service;

import com.example.application.data.entity.Attendance;
import com.example.application.data.repository.AttendanceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;

@Service
public class AttendanceService extends CrudService<Attendance, Integer>{

    public static final int AttendanceS_COUNT_LIMIT = 1000;



    public static class LimitReached extends RuntimeException {
    }

    private final AttendanceRepository AttendanceRepository;
    @Override
    protected JpaRepository<Attendance, Integer> getRepository() {
        return AttendanceRepository;
    }
    public AttendanceService(AttendanceRepository AttendanceRepository) {
        this.AttendanceRepository = AttendanceRepository;
    }

    public List<Attendance> findAll() {
        return AttendanceRepository.findAll();
    }

    public int countAll() {
        return (int) AttendanceRepository.count();
    }


    public Attendance save(Attendance Attendance) {
        if (countAll() >= AttendanceS_COUNT_LIMIT) {
            throw new LimitReached();
        }

        return AttendanceRepository.save(Attendance);
    }

    public void delete(Attendance Attendance) {
        AttendanceRepository.delete(Attendance);
    }
}
