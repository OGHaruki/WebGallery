package com.myprojects.webgallery.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TokenData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    @Id
    private Long id;
    private String token;
    private boolean expired;
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
