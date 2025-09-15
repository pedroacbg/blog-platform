<div id="top">

<!-- HEADER STYLE: CLASSIC -->
<div align="center">


# BLOG-PLATFORM

<em>Potencializando Conteúdo, Inspirando Conexões, Acelerando Crescimento</em>

<!-- BADGES -->
<img src="https://img.shields.io/github/last-commit/pedroacbg/blog-platform?style=flat&logo=git&logoColor=white&color=0080ff" alt="last-commit">
<img src="https://img.shields.io/github/languages/top/pedroacbg/blog-platform?style=flat&color=0080ff" alt="repo-top-language">
<img src="https://img.shields.io/github/languages/count/pedroacbg/blog-platform?style=flat&color=0080ff" alt="repo-language-count">

<em>Desenvolvido com as seguintes tecnologias:</em>

<img src="https://img.shields.io/badge/Spring-000000.svg?style=flat&logo=Spring&logoColor=white" alt="Spring">
<img src="https://img.shields.io/badge/Docker-2496ED.svg?style=flat&logo=Docker&logoColor=white" alt="Docker">
<img src="https://img.shields.io/badge/XML-005FAD.svg?style=flat&logo=XML&logoColor=white" alt="XML">
<img src="https://img.shields.io/badge/GitHub%20Actions-2088FF.svg?style=flat&logo=GitHub-Actions&logoColor=white" alt="GitHub%20Actions">

</div>
<br>

---

## Table of Contents

- [Visão Geral](#overview)
- [Inicializando](#getting-started)
    - [Pré-requisitos](#prerequisites)
    - [Instalação](#installation)
    - [Como Usar](#usage)

---

## Visão Geral

Este projeto é uma plataforma de blog moderna, baseada em Spring Boot, projetada para gerenciamento de conteúdo escalável, seguro e sustentável. Ela utiliza a conteinerização com o Docker para otimizar a implantação e integra recursos de segurança abrangentes, incluindo autenticação e autorização JWT. A plataforma também oferece documentação automática de APIs por meio do OpenAPI, garantindo uma comunicação clara para desenvolvedores e consumidores. Construída com uma arquitetura modular, ela suporta fluxos de trabalho de conteúdo eficientes, testes robustos e integração/implantação contínua, tornando-se uma base ideal para construir e escalar um sistema de blog profissional.

Este projeto simplifica o desenvolvimento e a implantação de um aplicativo de blog completo. Os principais recursos incluem:

- 🐳 **Implantação em contêiner:** usa Docker e Docker Compose para ambientes consistentes e escaláveis.
- 🔒 **Autenticação Segura:** Implementa segurança baseada em JWT para login de usuário e controle de acesso.
- 📜 **Documentação da API:** gera automaticamente documentos abrangentes da API com configuração OpenAPI.
- ⚙️ **Arquitetura modular:** Separação clara de modelos de domínio, serviços e controladores para manutenção.
- 🚀 **Integração CI/CD:** automatiza fluxos de trabalho de criação, teste e implantação para iteração rápida.

---

## Inicializando

### Pré-requisitos

Este projeto requer as seguintes dependências:

- **Linguagem de Programação:** Java
- **Gerenciador de Dependências:** Maven
- **Container Runtime:** Docker

### Instalação

Faça o build do código fonte e instale as dependências:

1. **Clone o repositório:**

    ```sh
    ❯ git clone https://github.com/pedroacbg/blog-platform
    ```

2. **Navegue até o diretório do projeto:**

    ```sh
    ❯ cd blog-platform
    ```

3. **Instale as dependências:**

**Usando [docker](https://www.docker.com/):**

```sh
❯ docker build -t pedroacbg/blog-platform .
```
**Usando [maven](https://maven.apache.org/):**

```sh
❯ mvn install
```

### Como Usar

Execute o projeto com:

**Usando [docker](https://www.docker.com/):**

```sh
docker run -it {image_name}
```
**Usando [maven](https://maven.apache.org/):**

```sh
mvn exec:java
```

---

<div align="left"><a href="#top">⬆ Voltar ao Início</a></div>

---
