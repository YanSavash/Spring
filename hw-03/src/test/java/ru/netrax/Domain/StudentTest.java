package ru.netrax.Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Проверка класса Student")
public class StudentTest {
    private Student student;

    @BeforeEach
    void setStudent() {
        student = new Student("Yan", "Savash");
    }

    @Test
    @DisplayName("Правильно создается объект")
    void shouldHaveCorrectConstructor() {
        assertEquals("Yan", student.getName());
        assertEquals("Savash", student.getSurname());
    }

    @Test
    @DisplayName("Правильно возвращает имя")
    void shouldCorrectGetFirstName() {
        assertEquals("Yan", student.getName());
    }

    @Test
    @DisplayName("Правильно возвращает фамилию")
    void shouldCorrectGetLastName() {
        assertEquals("Savash", student.getSurname());
    }
}