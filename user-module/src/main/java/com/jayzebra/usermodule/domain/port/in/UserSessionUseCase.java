package com.jayzebra.usermodule.domain.port.in;


public interface UserSessionUseCase {
    void recordLogin(String userId);
    void recordLogout(String userId);
}

