package com.deploytrack.backend.controller;

import com.deploytrack.backend.model.DeployLog;
import com.deploytrack.backend.service.DeployLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/deploys")
public class DeployLogController {

    private final DeployLogService service;

    public DeployLogController(DeployLogService service) {
        this.service = service;
    }

    // Endpoint POST /deploys
    // Recebe JSON de um novo deploy e salva no banco
    @PostMapping
    public ResponseEntity<DeployLog> create(@RequestBody DeployLog deploy) {
        return ResponseEntity.ok(service.createDeploy(deploy));
    }

    // Endpoint GET /deploys
    // Permite listagem completa ou filtrada com query params
    @GetMapping
    public ResponseEntity<List<DeployLog>> findAll(
            @RequestParam(required = false) String application,
            @RequestParam(required = false) String version,
            @RequestParam(required = false) String environment,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String status
    ) {
        return ResponseEntity.ok(
                service.getDeploysFiltered(application, version, environment, author, status)
        );
    }

    //Busca um unico DeployLog por ID
    @GetMapping("/{id}")
    public ResponseEntity<DeployLog> findById(@PathVariable Long id){
        DeployLog deploy = service.getById(id);
        return ResponseEntity.ok(deploy);
    }

    //PUT/ deploys/{id} - Atualiza um deploy existente
    @PutMapping("/{id}")
    public ResponseEntity<DeployLog> updateDeploy(@PathVariable Long id, @RequestBody DeployLog updatedDeploy) {
        return service.update(id, updatedDeploy)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    //Delete /deploy/{id} - Deleta um deply por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteDeploy(id);
        return ResponseEntity.noContent().build();
    }

    //Permite alterar apenas alguns campos.
    @PatchMapping("/{id}")
    public ResponseEntity<DeployLog> patchStatus(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        return ResponseEntity.ok(service.updatePartial(id, updates));
    }


}
