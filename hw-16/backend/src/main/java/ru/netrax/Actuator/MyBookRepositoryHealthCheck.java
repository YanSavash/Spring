package ru.netrax.Actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.netrax.Model.Book;
import ru.netrax.Repository.BookRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyBookRepositoryHealthCheck implements HealthIndicator {

    private final BookRepository bookRepository;

    @Override
    public Health health() {
        try {
            List<Book> bookList = bookRepository.findAll();
            return Health.up()
                    .status(Status.UP)
                    .withDetail("indicator", "Book repository is working")
                    .build();
        }
        catch (Exception e) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("indicator", "Book repository is not working")
                    .build();
        }
    }
}
