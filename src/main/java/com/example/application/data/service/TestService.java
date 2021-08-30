package com.example.application.data.service;

import com.example.application.data.entity.Test;
import com.example.application.data.repository.TestRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;

@Service
public class TestService extends CrudService<Test, Integer>{

    public static final int TestS_COUNT_LIMIT = 1000;



    public static class LimitReached extends RuntimeException {
    }

    private final TestRepository TestRepository;
    @Override
    protected JpaRepository<Test, Integer> getRepository() {
        return TestRepository;
    }
    public TestService(TestRepository TestRepository) {
        this.TestRepository = TestRepository;
    }

    public List<Test> findAll() {
        return TestRepository.findAll();
    }

    public int countAll() {
        return (int) TestRepository.count();
    }

    public List<Test> findByNameContainingIgnoreCase(String name) {
        return TestRepository.findByNameContainingIgnoreCase(name);
    }

    public Test save(Test Test) {
        if (countAll() >= TestS_COUNT_LIMIT) {
            throw new LimitReached();
        }

        return TestRepository.save(Test);
    }

    public void delete(Test Test) {
        TestRepository.delete(Test);
    }
}
