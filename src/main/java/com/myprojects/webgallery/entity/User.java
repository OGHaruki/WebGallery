package com.myprojects.webgallery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "UserData", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ImageData> images;
}
