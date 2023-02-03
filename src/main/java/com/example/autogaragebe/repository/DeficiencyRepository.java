package com.example.autogaragebe.repository;

import com.example.autogaragebe.model.Deficiency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeficiencyRepository extends JpaRepository<Deficiency, Long> {


}
