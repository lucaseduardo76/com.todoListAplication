# TODO List API

Esta é uma API para gerenciar uma lista de tarefas (TODO list), desenvolvida com **Spring Boot** e que utiliza autenticação JWT (JSON Web Token).

## Tecnologias Utilizadas

- **Java 17*
- **Spring Boot 3.3.6**
    - Spring Web
    - Spring Data JPA
    - Spring Security
    - Spring Validation
- **MySQL**
- **JWT (JSON Web Token)**
- **Lombok**
- **MapStruct**

## Funcionalidades

- **Autenticação e Autorização**:
    - Login de usuários com geração de token JWT.
    - Rotas protegidas que exigem autenticação.

- **Gestão de Usuários**:
    - Cadastro de novos usuários.
    - Atualização e exclusão de perfis.

- **Gestão de Tarefas**:
    - Criar, listar, atualizar e excluir tarefas.
    - Definir status (pendente, em progresso, concluída).

## Estrutura da API

### Endpoints Principais

#### Autenticação

- **POST /api/auth/login**  
  Autentica o usuário e retorna o token JWT.

  **Exemplo de Requisição**:
  ```json
  {
    "email": "email",
    "password": "senha123"
  }
