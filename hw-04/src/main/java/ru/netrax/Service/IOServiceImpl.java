package ru.netrax.Service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

@Component
public class IOServiceImpl implements IOService {

    private final BufferedReader reader;
    private final PrintStream printStream;

    public IOServiceImpl() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.printStream = new PrintStream(System.out);
    }

    @Override
    public String getStudentAnswer(String message) {
        while (true) {
            try {
                showMessageForStudent(message);
                return reader.readLine();
            } catch (IOException e) {
                showMessageForStudent("Error! Try again!");
            }
        }
    }

    @Override
    public void showMessageForStudent(String message) {
        printStream.println(message);
    }

    @Override
    public String getStudentNameOrSurname(String message) {
        while (true) {
            try {
                showMessageForStudent(message);
                return reader.readLine();
            } catch (IOException e) {
                showMessageForStudent("Error! Try again!");
            }
        }
    }
}