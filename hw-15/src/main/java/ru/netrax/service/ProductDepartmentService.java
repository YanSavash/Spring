package ru.netrax.service;

import org.springframework.stereotype.Service;
import ru.netrax.domain.Sale;
import ru.netrax.domain.SoftwareEngineer;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDepartmentService {
    private List<SoftwareEngineer> listOfEngineers = new ArrayList<>();

    public List<SoftwareEngineer> manageTaskList(Sale sale) {
        System.out.println("Получение нового заказа в отделе менеджеров");
        sale.getOrderList().values().stream().parallel().forEach(list -> {
            list.forEach(task -> {
                listOfEngineers.add(new SoftwareEngineer(task));
            });
        });
        System.out.println("Отдел менеджеров раздал новые задачи");
        return listOfEngineers;
    }
}
