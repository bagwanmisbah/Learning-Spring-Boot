package com.misbah.authorsapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length=100)
    private String name;
    private Integer age;

}