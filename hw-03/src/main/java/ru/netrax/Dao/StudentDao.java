package ru.netrax.Dao;

import ru.netrax.Domain.Student;

public interface StudentDao {
    Student newStudent(String name, String surname);
}
