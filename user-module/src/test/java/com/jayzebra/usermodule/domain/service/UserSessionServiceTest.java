package com.jayzebra.usermodule.domain.service;


import com.jayzebra.usermodule.adapter.out.entity.SessionEventEntity;
import com.jayzebra.usermodule.domain.port.out.SessionEventRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserSessionServiceTest {

    @Mock
    private SessionEventRepositoryPort sessionEventRepositoryPort; // Mock the dependency

    @InjectMocks
    private UserSessionService userSessionService; // Inject the mock into our service

    @Test
    void recordLogin_shouldSaveLoginEvent() {
        // Arrange
        String userId = "user-login-test";

        // Act
        userSessionService.recordLogin(userId);

        // Assert
        // Capture the entity that was passed to the save method
        ArgumentCaptor<SessionEventEntity> eventCaptor = ArgumentCaptor.forClass(SessionEventEntity.class);
        verify(sessionEventRepositoryPort).save(eventCaptor.capture());

        SessionEventEntity capturedEvent = eventCaptor.getValue();

        // Verify the state of the captured entity
        assertEquals(userId, capturedEvent.getUserId());
        assertEquals(SessionEventEntity.EventType.LOGIN, capturedEvent.getEventType());
        assertNotNull(capturedEvent.getTimestamp());
    }

    @Test
    void recordLogout_shouldSaveLogoutEvent() {
        // Arrange
        String userId = "user-logout-test";

        // Act
        userSessionService.recordLogout(userId);

        // Assert
        // Capture the entity that was passed to the save method
        ArgumentCaptor<SessionEventEntity> eventCaptor = ArgumentCaptor.forClass(SessionEventEntity.class);
        verify(sessionEventRepositoryPort).save(eventCaptor.capture());

        SessionEventEntity capturedEvent = eventCaptor.getValue();

        // Verify the state of the captured entity
        assertEquals(userId, capturedEvent.getUserId());
        assertEquals(SessionEventEntity.EventType.LOGOUT, capturedEvent.getEventType());
        assertNotNull(capturedEvent.getTimestamp());
    }
}

