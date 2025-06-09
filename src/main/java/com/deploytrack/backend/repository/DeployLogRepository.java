package com.deploytrack.backend.repository;

import com.deploytrack.backend.model.DeployLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repositório JPA para acessar dados do DeployLog no banco de dados
public interface DeployLogRepository extends JpaRepository<DeployLog, Long> {

    // Métodos de busca por filtros individuais
    List<DeployLog> findByApplication(String application);
    List<DeployLog> findByVersion(String version);
    List<DeployLog> findByEnvironment(String environment);
    List<DeployLog> findByAuthor(String author);
    List<DeployLog> findByStatus(String status);

    // Busca combinada com todos os campos (usado para filtro completo)
    List<DeployLog> findByApplicationAndVersionAndEnvironmentAndAuthorAndStatus(
            String application, String version, String environment, String author, String status
    );
}
