package com.example.guitar.repository;

import com.example.guitar.entity.TrackApp;
import
org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepo extends JpaRepository<TrackApp, Long> {

}
