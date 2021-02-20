package ru.netrax.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.netrax.service.ProductDepartmentService;
import ru.netrax.service.TenderDepartmentService;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {

    private final TenderDepartmentService tenderDepartmentService;
    private final ProductDepartmentService productDepartmentService;

    @Bean
    public QueueChannel clientChannel() {
        return MessageChannels.queue(5).get();
    }

    @Bean
    public PublishSubscribeChannel engineerChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller(){
        return Pollers
                .fixedRate(3000)
                .get();
    }

    @Bean
    public IntegrationFlow tenderWorkFlow() {
        return IntegrationFlows.from("clientChannel")
                .handle(tenderDepartmentService, "getNewTender")
                .handle(productDepartmentService, "manageTaskList")
                .channel("engineerChannel")
                .get();
    }
}
