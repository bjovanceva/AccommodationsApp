package com.example.emtlab.model.domain;

import com.example.emtlab.model.enumerations.TemporaryListStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TemporaryReservationList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Accommodation> accommodations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @Enumerated(EnumType.STRING)
    private TemporaryListStatus temporaryListStatus;

    public TemporaryReservationList() {}

    public TemporaryReservationList(User user) {
        this.user=user;
        this.accommodations=new ArrayList<>();
        this.temporaryListStatus=TemporaryListStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public User getUser() {
        return user;
    }

    public TemporaryListStatus getTemporaryListStatus() {
        return temporaryListStatus;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTemporaryListStatus(TemporaryListStatus temporaryListStatus) {
        this.temporaryListStatus = temporaryListStatus;
    }
}
