package com.jayzebra.usermodule.domain.service;

import com.jayzebra.usermodule.adapter.out.entity.SessionEventEntity;
import com.jayzebra.usermodule.domain.port.in.UserSessionUseCase;
import com.jayzebra.usermodule.domain.port.out.SessionEventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserSessionService implements UserSessionUseCase {

    private final SessionEventRepositoryPort sessionEventRepositoryPort;

    @Override
    public void recordLogin(String userId) {
        createAndSaveEvent(userId, SessionEventEntity.EventType.LOGIN);
    }

    @Override
    public void recordLogout(String userId) {
        createAndSaveEvent(userId, SessionEventEntity.EventType.LOGOUT);
    }

    private void createAndSaveEvent(String userId, SessionEventEntity.EventType eventType) {
        SessionEventEntity event = new SessionEventEntity();
        event.setUserId(userId);
        event.setEventType(eventType);
        event.setTimestamp(Instant.now());
        sessionEventRepositoryPort.save(event);
    }
}
