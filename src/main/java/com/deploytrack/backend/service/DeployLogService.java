package com.deploytrack.backend.service;

import com.deploytrack.backend.model.DeployLog;
import com.deploytrack.backend.repository.DeployLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DeployLogService {

    private final DeployLogRepository repository;

    public DeployLogService(DeployLogRepository repository) {
        this.repository = repository;
    }

    // Cria um novo registro de deploy com timestamp atual e status padrão SUCCESS (caso não informado)
    public DeployLog createDeploy(DeployLog deploy) {
        deploy.setTimestamp(LocalDateTime.now());
        if (deploy.getStatus() == null) {
            deploy.setStatus("SUCCESS");
        }
        return repository.save(deploy);
    }

    // Retorna todos os registros de deploy salvos
    public List<DeployLog> getAllDeploys() {
        return repository.findAll();
    }


    // Buscar um único DeployLog por ID.
    public DeployLog getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deploy não encontrado"));
    }

    // Retorna os deploys com um status específico (ex: SUCCESS, ERROR, ROLLBACK)
    public List<DeployLog> getDeployByStatus(String status) {
        return repository.findByStatus(status);
    }

    // Filtra deploys por qualquer combinação de: app, versão, ambiente, autor e status
    public List<DeployLog> getDeploysFiltered(String application, String version, String environment, String author, String status) {

            List<DeployLog> allDeploys = repository.findAll();

            return allDeploys.stream()
                    .filter(d -> application == null || d.getApplication().equalsIgnoreCase(application))
                    .filter(d -> version == null || d.getVersion().equalsIgnoreCase(version))
                    .filter(d -> environment == null || d.getEnvironment().equalsIgnoreCase(environment))
                    .filter(d -> author == null || d.getAuthor().equalsIgnoreCase(author))
                    .filter(d-> status == null || d.getStatus().equalsIgnoreCase(status))
                    .toList();

    }

    // Atualiza um deploy existente (por ID)
    public DeployLog updateDeploy(Long id, DeployLog newData) {
        DeployLog existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deploy não encontrado"));

        existing.setApplication((newData.getApplication()));
        existing.setVersion(newData.getVersion());
        existing.setEnvironment(newData.getEnvironment());
        existing.setAuthor(newData.getAuthor());
        existing.setStatus(newData.getStatus());
        existing.setTimestamp(LocalDateTime.now());

        return repository.save(existing);
    }


    //Deleta um deploy por ID
    public void deleteDeploy(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Deploy não encontrado com o ID: " + id);

        }
        repository.deleteById(id);
    }


    public DeployLog updatePartial(Long id, Map<String, String> updates) {
        DeployLog deploy = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deploy não encontrado"));
        if (updates.containsKey("status")) {
            deploy.setStatus(updates.get("status"));
        }
        return repository.save(deploy);
    }

}
