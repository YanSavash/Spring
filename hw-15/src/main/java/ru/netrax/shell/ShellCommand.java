package ru.netrax.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.netrax.domain.Client;
import ru.netrax.domain.SoftwareEngineer;
import ru.netrax.integration.TenderGateway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@ShellComponent
@AllArgsConstructor
public class ShellCommand {
    private final static List<String> orderList = new ArrayList(Arrays.asList("order1", "order2", "order3"));
    private final TenderGateway tenderGateway;

    @ShellMethod("begin")
    public void begin() throws InterruptedException {
        List<SoftwareEngineer> softwareEngineers = new ArrayList<>();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Список инженеров на начало работы: ");
        softwareEngineers.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------");
        ForkJoinPool pool = ForkJoinPool.commonPool();
        while (softwareEngineers.size() < 50) {
            Thread.sleep(1000);
            pool.execute(() -> {
                Client client = new Client("", orderList);
                softwareEngineers.addAll(tenderGateway.process(client));
            });
        }
        Thread.sleep(2000);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Список инженеров после получения заказов: ");
        softwareEngineers.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
