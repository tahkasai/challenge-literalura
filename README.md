# Challenge Literalura | ONE 📚

O Literalura é uma aplicação de linha de comando desenvolvida como parte do programa **Oracle Next Education (ONE)** em parceria com a **Alura**. O projeto consulta a [API Gutendex](https://gutendex.com) — um catálogo com informações sobre mais de 70 mil livros do Project Gutenberg (biblioteca online gratuita) — e persiste os dados em um banco de dados relacional, permitindo buscas e listagens locais.

## Funcionalidades

O sistema oferece um menu interativo via terminal com as seguintes opções:

| Opção | Descrição |
|-------|-----------|
| **1** | Buscar livro pelo título (consulta a API e salva no banco) |
| **2** | Listar todos os livros registrados no banco de dados |
| **3** | Listar todos os autores registrados e seus respectivos livros |
| **4** | Listar autores vivos em um determinado ano |
| **5** | Listar livros por idioma (es, en, fr, pt) |
| **0** | Sair da aplicação |

## Tecnologias utilizadas

* **Java 21**
* **Spring Boot 4.0.3**
* **Spring Data JPA** — mapeamento objeto-relacional e persistência
* **MySQL** — banco de dados relacional
* [**API Gutendex**](https://gutendex.com) — fonte de dados de livros
* [**Jackson 2.15.2**](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind) — desserialização de JSON

## Pré-requisitos

* Java 21+
* Maven
* MySQL 8+

## Configuração

1. Crie o banco de dados no MySQL:
   ```sql
   CREATE DATABASE literalura;
   ```

2. Configure as credenciais no arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/literalura?useSSL=false&serverTimezone=UTC
   spring.datasource.username=SEU_USUARIO
   spring.datasource.password=SUA_SENHA
   ```

## Como executar

```bash
# Clone o repositório
git clone https://github.com/tahkasai/challenge-literalura.git
cd challenge-literalura

# Compile e execute
./mvnw spring-boot:run
```

## Estrutura do projeto

```
src/main/java/br/com/alura/literalura/
├── Controller/        # Cliente HTTP para consumo da API
├── Model/
│   ├── Autor/         # Entidade e DTO de Autor
│   └── Livro/         # Entidade e DTO de Livro
├── Principal/         # Menu interativo e lógica principal
├── Repository/        # Repositórios JPA (AutorRepository, LivroRepository)
└── Service/           # Conversão e desserialização de dados JSON
```

## Emblema do Challenge 
<img width="210" height="210" alt="Badge-Literalura" src="https://github.com/user-attachments/assets/56d2bf22-cf82-40f7-b8bd-2e5c9c3e891d" />
