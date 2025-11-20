# Command Processing API

Este projeto é uma API REST desenvolvida para receber e processar comandos de centrais de alarme de forma assíncrona e extensível.

## Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3** (Web, JDBC)
* **H2 Database** (Banco em memória)
* **Maven**
* **JUnit 5 & Mockito** (Testes)

## Arquitetura e Decisões de Design

A aplicação segue uma arquitetura em camadas clássica (Controller, Service, Repository), mas com foco em extensibilidade e baixo acoplamento, utilizando princípios **SOLID**:

1.  **Strategy Pattern**: Utilizado para resolver o processamento de comandos.
    * Existe uma interface `CommandProcessor` e implementações concretas (`ArmCommandProcessor`, `DisarmCommandProcessor`).
    * Isso elimina a necessidade de grandes blocos `if/else` e permite adicionar novos comandos (ex: "PANIC") criando apenas uma nova classe, sem alterar o código existente (Open/Closed Principle).
2.  **Spring JDBC**: A persistência é feita via `JdbcTemplate` para controle direto das queries e isolamento da camada de dados.
3.  **Camadas**:
    * `Controller`: Apenas recebe e valida a requisição HTTP.
    * `Service`: Contém a regra de negócio e roteamento do comando.
    * `Repository`: Responsável exclusivamente por interagir com o banco de dados.

## Como Executar

### Pré-requisitos
* Java 17 ou superior
* Maven
  
### Passos
1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/LarissaBSouza/CommandProcessing-API.git](https://github.com/LarissaBSouza/CommandProcessing-API.git)
    ```
2.  **Execute a aplicação:**
    No terminal (dentro da pasta do projeto), rode:
    ```bash
    ./mvnw spring-boot:run
    ```
3.  **Acesse:**
    A API estará rodando em `http://localhost:8080`.

---

## Como Usar a API

A API possui um único endpoint unificado para recepção de comandos. O tipo de ação é definido pelo campo `type` no JSON.

**Endpoint:** `POST /api/commands`

### Exemplo 1: Armar o Alarme (ARM)
Use este JSON para ativar o monitoramento de um dispositivo.

```json
{
  "type": "ARM",
  "deviceId": "sensor-janela-01",
  "payload": {
    "zona": "sala-estar",
    "usuario": "admin"
  }
}

```

### Exemplo 2: Desarmar o Alarme (DISARM)
Use este JSON para desativar o monitoramento de um dispositivo.

```json
{
  "type": "DISARM",
  "deviceId": "sensor-janela-01",
  "payload": {
    "senha": "1234",
    "motivo": "limpeza"
  }
}

```

Este projeto foi desenvolvido para um desafio prático, com fins de aprendizado.
