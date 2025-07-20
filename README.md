# 📚 CRUD Biblioteca - API REST em Java

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-green.svg)](https://spring.io/projects/spring-boot)

Um sistema backend para gerenciamento de biblioteca desenvolvido em Java com Spring Boot, implementando operações CRUD completas.

## Funcionalidades

- ✅ CRUD completo para biblioteca
- ✅ Validações com mensagens personalizadas
- ✅ Tratamento global de exceções
- ✅ Documentação da API com OpenAPI/Swagger

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA + Hibernate
- Bean Validation (JSR-380)
- Lombok
- MySQL
- Swagger 
- Maven
- Docker

## 📂 Estrutura do Projeto
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── biblioteca/
│   │           ├── controller/    # Controladores REST
│   │           ├── dto/           # Objetos de Transferência de Dados
│   │           ├── exception/     # Tratamento de exceções
│   │           ├── model/         # Entidades JPA
│   │           ├── repository/    # Repositórios Spring Data
│   │           ├── service/       # Lógica de negócio
│   │           └── BibliotecaApplication.java  # Classe principal
│   └── resources/
│       ├── static/
│       ├── templates/
│       └── application.properties # Configurações
├── test/                          # Testes unitários e de integração (a desenvolver)
```
## 📘 Endpoints

Documentação disponível via Swagger: 
http://localhost:8080/swagger-ui.html

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/VictorPedrotti/CRUD-Biblioteca-Backend-Java.git
   cd CRUD-Biblioteca-Backend-Java

2. Configure o banco de dados no arquivo application.properties

3. Execute o projeto:
   ```bash
   ./mvnw spring-boot:run
