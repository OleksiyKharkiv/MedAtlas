package com.example.medatlas.repository;
import com.example.medatlas.model.InstanceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstanceDataRepository extends JpaRepository<InstanceData, UUID> {
}