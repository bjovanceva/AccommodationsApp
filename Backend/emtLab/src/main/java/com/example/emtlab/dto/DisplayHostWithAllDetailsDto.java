package com.example.emtlab.dto;

import com.example.emtlab.model.domain.Country;
import com.example.emtlab.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostWithAllDetailsDto (Long id, String name, String surname, Country country) {

    public static DisplayHostWithAllDetailsDto from(Host host){
        return new DisplayHostWithAllDetailsDto(host.getId(), host.getName(), host.getSurname(), host.getCountry());
    }

    public static List<DisplayHostWithAllDetailsDto> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostWithAllDetailsDto::from).collect(Collectors.toList());
    }

    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }

}

