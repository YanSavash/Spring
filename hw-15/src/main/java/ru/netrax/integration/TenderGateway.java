package ru.netrax.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.netrax.domain.Client;
import ru.netrax.domain.SoftwareEngineer;

import java.util.List;

@MessagingGateway
public interface TenderGateway {

    @Gateway(requestChannel = "clientChannel", replyChannel = "engineerChannel")
    List<SoftwareEngineer> process(Client client);
}
