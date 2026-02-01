# 🔥 BrasaSplit API

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)
![Security](https://img.shields.io/badge/Spring_Security-JWT-red?style=for-the-badge&logo=spring-security&logoColor=white)

> **O fim da dor de cabeça na hora de cobrar o churrasco.** 🥩🍺

O **BrasaSplit** é uma API desenvolvida para gerenciar eventos, calcular despesas e dividir os custos de forma justa entre os participantes. Chega de planilha no Excel ou cobrar o amigo "que esqueceu o PIX".

---

## 🚀 Tecnologias & Arquitetura

O projeto foi construído seguindo as melhores práticas de mercado, focando em **Clean Code**, **SOLID** e **Segurança**.

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3
* **Banco de Dados:** MongoDB (NoSQL)
* **Autenticação:** Spring Security + JWT (Stateless)
* **Documentação:** OpenAPI 3 (Swagger UI)
* **Utilitários:** Lombok, Jakarta Validation

### 🏗️ Padrões de Projeto Adotados
* **DTO Pattern:** Separação estrita entre Entidades de Banco e Objetos de Transferência (Request/Response).
* **Mapper Pattern:** Camada dedicada para conversão de dados, mantendo Controllers e Services limpos.
* **Repository Pattern:** Abstração da camada de dados.
* **Global Exception Handling:** Tratamento centralizado de erros com respostas HTTP semânticas (400, 401, 404, 409).
* **Auditing:** Controle automático de `createdAt` e `updatedAt` via classe base.

---

## 🛠️ Funcionalidades (Status)

| Recurso | Status | Descrição |
| :--- | :---: | :--- |
| **Autenticação** | ✅ | Login e Registro com JWT e Senha Criptografada (BCrypt). |
| **Gestão de Eventos** | 🚧 | Criar eventos vinculados ao usuário logado. |
| **Listagem Segura** | 🚧 | Usuário vê apenas os *seus* eventos. |
| **Participantes** | 🚧 | Adicionar participantes ao churrasco. |
| **Despesas** | 🚧 | Adicionar itens (Carne, Bebida, Carvão). |
| **Cálculo de Rateio** | 🚧 | Lógica de divisão por consumo (quem bebe x quem não bebe). |

---

## 🔌 API Endpoints

A documentação completa pode ser acessada via Swagger após rodar a aplicação:
👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

### Principais Rotas

#### Auth 🔐
* `POST /api/auth/register` - Cria novo usuário (valida email único).
* `POST /api/auth/authenticate` - Login (retorna Bearer Token).

#### Eventos 📅
* `POST /api/eventos` - Cria um churrasco.
* `GET /api/eventos` - Lista meus churrascos.
* `POST /api/eventos/{id}/participantes` - Adiciona galera no churrasco.

---

## 📂 Estrutura de Pastas

```text
com.brasasplit.demo
├── config       # Configurações (Security, Swagger, Beans)
├── controller   # Camada REST (Entrada de dados)
├── domain       # Entidades do Banco (MongoDB)
├── dto          # Objetos de Transferência (Records)
├── mapper       # Conversores (Entity <-> DTO)
├── repository   # Acesso ao Banco
├── service      # Regras de Negócio
└── util         # Constantes e Utilitários
