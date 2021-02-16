package ru.netrax.service;

import org.springframework.stereotype.Service;
import ru.netrax.domain.Client;
import ru.netrax.domain.Sale;

@Service
public class TenderDepartmentService {

    public Sale getNewTender(Client client) {
        System.out.println("Получение нового заказа в отделе продаж");
        Sale sale = new Sale(client.getName(), client.getOrderList());
        System.out.println("Отдел продаж оформил новый заказ");
        return sale;
    }
}
