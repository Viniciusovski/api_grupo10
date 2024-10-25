# Desafio Generation - Grupo 10

API para gerenciamento de sala de aula, onde funcionários autenticados podem cadastrar, consultar e gerenciar turmas e alunos.

## Autores

- [@Bruno Oliveira](https://github.com/brunocaoliveira)
- [@Cauã Sebastian](https://github.com/cauasebastian)
- [@Vinicius Bacelar](https://github.com/Viniciusovski)
- [@Wesley Bueno](https://github.com/WesleyBueno)

---

## Stack Utilizada

**Back-end:** Java 21, Spring Boot (com HATEOAS), Spring Security, Java JWT  
**Banco de Dados:** PostgreSQL 16  
**Deploy:** Docker, Render  
**Documentação:** Swagger  
**Caching:** Spring Cache para otimizar consultas recorrentes e reduzir o tempo de resposta da API

---

## Documentação da API

A documentação da API, construída com Swagger, permite explorar e testar endpoints diretamente. Acesse pelo seguinte link: [Swagger UI](https://api-grupo10-1.onrender.com/swagger-ui/index.html#/).

## Funcionalidades

- **Cadastro e Gerenciamento de Funcionários**: Autenticação segura para criação e gerenciamento de perfis de funcionários.
- **Cadastro e Gerenciamento de Alunos e Turmas**: Criação e manipulação de registros de alunos e suas turmas.
- **Autenticação e Autorização**: Uso de Spring Security e JWT para garantir o acesso seguro aos dados e ações disponíveis.
- **Implementação de HATEOAS**: Navegação facilitada entre os endpoints, proporcionando uma estrutura de API RESTful mais interativa.
- **Caching com Spring Cache**: Cache integrado com Spring para otimizar o desempenho de consultas frequentes sem a necessidade de um cache externo.
- **Containerização com Docker**: Configuração simplificada para garantir que a aplicação rode em qualquer ambiente de forma consistente.

---

## Rodando o Projeto Localmente

1. **Pré-requisitos**:
   - Java 21+
   - Docker
   - Docker Compose
   - PostgreSQL (opcional, caso queira rodar o banco fora do Docker)

2. **Clonando o Repositório**:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```
3. **Configurando Variáveis de Ambiente:** Crie um arquivo .env na raiz do projeto e configure as seguintes variáveis:
   ```bash
    PSUSER=postgres
    PSPASS=root
    PSHOST=localhost
    PSDATABASE=deploygeneration
    JWT_SECRET=12345678  # Modifique o valor conforme necessário
   ```
4. **Inicializando o Banco de Dados e Aplicação com Docker:** Certifique-se de que o Docker está instalado e ativo. Rode o seguinte comando para iniciar os serviços:
   ```bash
    docker-compose up -d
   ```
