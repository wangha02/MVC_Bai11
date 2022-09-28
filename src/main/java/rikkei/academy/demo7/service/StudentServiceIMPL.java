package rikkei.academy.demo7.service;

import rikkei.academy.demo7.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceIMPL implements IStudentService {

    public static List<Student> studentList = new ArrayList<>();

    static {
        studentList.add(new Student(1, "Tung", 31));
        studentList.add(new Student(2, "Phu", 31));
        studentList.add(new Student(3, "Dat", 31));

    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public void save(Student student) {
        studentList.add(student);
    }

    @Override
    public Student findById(int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                return studentList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (id == studentList.get(i).getId()) {
                studentList.remove(i);
                break;
            }
        }

    }
}
