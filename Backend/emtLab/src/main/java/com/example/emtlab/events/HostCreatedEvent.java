package com.example.emtlab.events;

import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.domain.Host;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostCreatedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public HostCreatedEvent(Host source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public HostCreatedEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}

