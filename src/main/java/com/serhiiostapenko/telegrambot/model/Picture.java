package com.serhiiostapenko.telegrambot.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name", nullable = false, length = 200)
    String attachName;

    @Column(name = "caption", length = 500)
    String caption;

    @Column(name = "is_checked", columnDefinition = "boolean default false")
    Boolean isChecked;
}
