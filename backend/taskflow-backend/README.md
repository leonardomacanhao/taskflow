# 🚧 TaskFlow - Sistema de Gerenciamento de Tarefas (Kanban)

> **Status:** 🏗️ Em Desenvolvimento (Work in Progress)  
> **Backend:** ✅ Concluído (Fase 1) | **Frontend:** ⏳ Planejado

## 📖 Sobre o Projeto
O **TaskFlow** é uma aplicação full-stack para gestão de tarefas baseada na metodologia **Kanban**. O objetivo é oferecer uma interface visual (quadros e colunas) para organização de atividades, com foco em segurança, performance e uma experiência de usuário moderna (Dark Mode).

Este repositório contém a evolução incremental do projeto. Atualmente, a **camada de Backend** está estruturada e funcional.

---

## 🚀 Estado Atual do Desenvolvimento

### ✅ O que já está pronto:
*   **Backend (Java/Spring Boot 3):**
    *   Estrutura base com Maven e PostgreSQL.
    *   Arquitetura em camadas (Controller, Service, Repository).
    *   Padrão de DTOs e Mappers para desacoplamento de dados.
    *   CRUD completo de Usuários com validações.
    *   **Autenticação JWT (Stateless):** Login e Registro de usuários.
    *   Segurança configurada com Spring Security 6.
    *   Tratamento Global de Exceções (ErrorHandler padronizado).

### ⏳ Próximos Passos (Roadmap):
*   [ ] Modelagem das entidades de Domínio (`Board`, `List`, `Card`).
*   [ ] Lógica de movimentação de cards (Drag-and-Drop support no backend).
*   [ ] Desenvolvimento do Frontend em **Angular 17+**.
*   [ ] Integração visual com Angular Material e Drag & Drop CDK.
*   [ ] Dockerização e Deploy.

---

## 🛠️ Tecnologias Utilizadas

| Camada | Tecnologias |
| :--- | :--- |
| **Backend** | Java 17, Spring Boot 3, Spring Security, Hibernate/JPA |
| **Banco** | PostgreSQL 15 |
| **Segurança** | JWT (jjwt), BCrypt |
| **Build** | Maven |
| **Frontend (Futuro)** | Angular 17+, TypeScript, SCSS |

---

## 📋 Estrutura do Projeto
