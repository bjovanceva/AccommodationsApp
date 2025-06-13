package com.example.emtlab.repository;

import com.example.emtlab.model.domain.Host;
import com.example.emtlab.model.projections.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {

    List<HostProjection> findAllBy();
}
