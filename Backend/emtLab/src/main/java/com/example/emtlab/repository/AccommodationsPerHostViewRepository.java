package com.example.emtlab.repository;

import com.example.emtlab.model.views.AccommodationsPerHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccommodationsPerHostViewRepository extends JpaRepository<AccommodationsPerHostView, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.acc_per_host", nativeQuery = true)
    void refreshMaterializedView();
}
