package ru.netrax.Dao;

import org.springframework.stereotype.Component;
import ru.netrax.Domain.Student;

@Component
public class StudentDaoImpl implements StudentDao {
    @Override
    public Student newStudent(String name, String surname) {
        return new Student(name, surname);
    }
}
