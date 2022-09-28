package rikkei.academy.demo7.service;

import rikkei.academy.demo7.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    void save(Student student);

    Student findById(int id);

    void deleteById(int id);
}
