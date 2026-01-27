package com.jayzebra.usermodule.adapter.out.repository;


import com.jayzebra.usermodule.adapter.out.entity.SessionEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionEventRepository extends JpaRepository<SessionEventEntity, String> {
}

