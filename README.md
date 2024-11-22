![logo_neuro](https://github.com/user-attachments/assets/aadfbe27-bd6d-49e8-991c-c5230d2fe1c8)

# Gerenciamento de Projetos e Competências

## Descrição

Este repositório contém um projeto de gerenciamento de usuários, seus projetos, competências e habilidades, desenvolvido com **Spring Boot** para a API Rest.

O sistema permite a criação e gerenciamento de usuários e suas respectivas competências, projetos e certificações. Ele está configurado para garantir que as informações sejam armazenadas de forma eficiente em um banco de dados **Supabase**.

Além disso, vale ressaltar que é umpara fins acadêmicos, feito em parceria com a equipe da Neurotech.

### Funcionalidades

- **Cadastro de usuários e suas competências**
- **Gerenciamento de projetos** com alocação de membros

## Tecnologias Usadas

### Backend (API Rest)

- **Java 21**
- **Spring Boot**
- **JPA** para persistência de dados
- **Supabase** como banco de dados

## Instruções de Instalação

### Backend

1. Clone o repositório:

   ```bash
   git clone https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git
   ```

2. Execute o comando

   ```bash
   ./mvnw spring-boot:run
   ```

3. Acesse a API através da Porta

   ```
   http://localhost:8081/
   ```

## Estrutura do Repositório

src/main/java/personal/neuro/neuroAPI

```bash
.
└── src
   └── main
       ├── java
       │     └── personal
       │            └── neuro
       │                  └── neuroAPI
       │                        ├── configs
       │                        ├── controllers
       │                        ├── dtos
       |                        ├── entites
       |                        ├── repositries
       |                        ├── services
       |                        └── NeuroApiApplication.java
       ├── pom.xml
       └── application.properties
```
