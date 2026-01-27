package com.jayzebra.usermodule.domain.dto;

import java.util.List;

// Wrapper for returning a list of users, allowing for future pagination fields.
public class UserListDto {
    private List<UserDto> users;

    public UserListDto(List<UserDto> users) {
        this.users = users;
    }

    // Getter and Setter
    public List<UserDto> getUsers() { return users; }
    public void setUsers(List<UserDto> users) { this.users = users; }
}

