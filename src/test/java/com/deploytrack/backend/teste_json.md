Aqui estão vários exemplos de JSONs seguindo a estrutura do projeto **DeployTrack**, com diferentes combinações de Status
### ✅ `SUCCESS`

```json
{
  "application": "Sistema de Vendas",
  "version": "1.0.0",
  "environment": "prod",
  "author": "ana.silva",
  "status": "SUCCESS"
}
```

```json
{
  "application": "Portal RH",
  "version": "2.3.4",
  "environment": "homolog",
  "author": "carlos.santos",
  "status": "SUCCESS"
}
```

```json
{
  "application": "Financeiro API",
  "version": "0.9.1",
  "environment": "dev",
  "author": "juliana.moraes",
  "status": "SUCCESS"
}
```

---

### ❌ `ERROR`

```json
{
  "application": "Sistema de Vendas",
  "version": "1.0.1",
  "environment": "prod",
  "author": "ana.silva",
  "status": "ERROR"
}
```

```json
{
  "application": "Portal RH",
  "version": "2.3.5",
  "environment": "dev",
  "author": "carlos.santos",
  "status": "ERROR"
}
```

```json
{
  "application": "Controle de Estoque",
  "version": "3.1.2",
  "environment": "homolog",
  "author": "fernando.oliveira",
  "status": "ERROR"
}
```

---

### 🔁 `ROLLBACK`

```json
{
  "application": "Financeiro API",
  "version": "0.9.2",
  "environment": "prod",
  "author": "juliana.moraes",
  "status": "ROLLBACK"
}
```

```json
{
  "application": "Portal RH",
  "version": "2.3.6",
  "environment": "homolog",
  "author": "carlos.santos",
  "status": "ROLLBACK"
}
```

```json
{
  "application": "Controle de Estoque",
  "version": "3.1.3",
  "environment": "dev",
  "author": "fernando.oliveira",
  "status": "ROLLBACK"
}
```

---

