package com.example.project.repository;

import com.example.project.model.Developer;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {

    long getLastAddedDeveloper();
}
