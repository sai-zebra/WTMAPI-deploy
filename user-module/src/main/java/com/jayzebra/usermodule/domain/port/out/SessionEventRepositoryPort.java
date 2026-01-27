package com.jayzebra.usermodule.domain.port.out;


import com.jayzebra.usermodule.adapter.out.entity.SessionEventEntity;
import org.springframework.modulith.NamedInterface;

@NamedInterface
public interface SessionEventRepositoryPort {
    void save(SessionEventEntity event);
}
