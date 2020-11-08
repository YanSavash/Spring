package ru.netrax.Service;

public interface IOService {
    String getStudentAnswer(String message);
    void showMessageForStudent(String message);
    String getStudentNameOrSurname(String message);
}
