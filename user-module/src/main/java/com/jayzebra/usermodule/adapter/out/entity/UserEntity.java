package com.jayzebra.usermodule.adapter.out.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "users") // Good practice to explicitly name the table
public class UserEntity {

    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String profileImageUrl;

}
