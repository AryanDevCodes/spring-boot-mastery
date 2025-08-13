package org.practice.bootpro8multiprofiles.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true,nullable=false)
    private String userName;
    @Column(unique=true,nullable=false)
    private String email;
    @Column(unique=true,nullable=false)
    private String profile;


}

