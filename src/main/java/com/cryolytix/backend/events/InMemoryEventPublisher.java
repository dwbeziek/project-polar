package com.cryolytix.backend.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class InMemoryEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public InMemoryEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishEvent(Object event) {

    }
}
