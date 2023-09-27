package com.centric.bench.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centric.bench.model.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Long>{

}
