package com.serhiiostapenko.telegrambot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "text", nullable = false, length = 1000)
    String text;

    @Column(name = "is_checked", columnDefinition = "boolean default false")
    Boolean isChecked;
}
