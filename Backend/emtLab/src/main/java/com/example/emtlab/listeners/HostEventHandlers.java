package com.example.emtlab.listeners;

import com.example.emtlab.events.HostCreatedEvent;
import com.example.emtlab.service.domain.AccommodationService;
import com.example.emtlab.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {

    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService=hostService;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent event) {
        this.hostService.refreshMaterializedView();
    }
}
