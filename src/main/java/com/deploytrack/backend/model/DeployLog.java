package com.deploytrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class DeployLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID único do deploy (chave primária)

    private String application;  // Nome da aplicação

    private String version;      // Versão da aplicação

    private String environment;  // Ambiente de deploy (ex: dev, staging, prod)

    private String author;       // Responsável pelo deploy

    private String status;       // Status atual: SUCCESS, ERROR ou ROLLBACK

    private LocalDateTime timestamp; // Data/hora do deploy
}
