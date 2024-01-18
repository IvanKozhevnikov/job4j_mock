package ru.checkdev.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;
import ru.checkdev.notification.domain.Notify;

@Service
@Slf4j
public class Consumer {

    private final TemplateService templateService;

    public Consumer(TemplateService templateService) {
        this.templateService = templateService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.notify}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumerNotify(Notify notify) {
        templateService.send(notify);
        log.debug(notify.toString());
    }
}
