package com.example.emtlab.dto;

import com.example.emtlab.model.domain.Country;
import com.example.emtlab.model.domain.Host;

import java.util.List;

public record CreateHostDto(String name, String surname, Long country) {

    public static CreateHostDto from(Host host){
        return new CreateHostDto(host.getName(), host.getSurname(), host.getCountry().getId());

    }

    public Host toHost(Country country){
        return new Host(name, surname, country);
    }

    public List<CreateHostDto> from(List<Host> hosts){
        return hosts.stream().map(CreateHostDto::from).toList();
    }
}
