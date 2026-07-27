# 💰 DIO Spring Boot Learning Track - Módulo Spring AI (Budgeting API)

Este repositório contém a minha versão da aplicação desenvolvida durante a trilha de Spring Boot da [Digital Innovation One (DIO)](https://www.dio.me/).

---

## 📋 O que o projeto faz

O projeto é uma API RESTful de gerenciamento de orçamentos e despesas pessoais (`Budgeting Application`). A aplicação permite o cadastro, controle e categorização de transações financeiras (como compras de mercado, gastos automotivos, farmácia, etc.), garantindo regras de validação sobre os valores inseridos.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.3**
* **Spring Data JPA / Hibernate**
* **H2 Database** (Banco de dados em memória para desenvolvimento/testes)
* **Gradle** (Gerenciador de dependências e build)
* **PowerShell / cURL** (Testes de requisições HTTP)

---

## 💡 Melhoria Implementada

Foi desenvolvida e integrada uma **Regra de Negócio** na camada de domínio/serviço para controle de limite máximo de transações:

* **Classe de Exceção de Domínio:** Criação da classe `BusinessRuleException`.
* **Validação de Limite Financeiro:** Nenhuma transação pode ser cadastrada com valor superior a **R$ 5.000,00** (`500000` centavos).
* **Tratamento de Erro na API:** Caso o limite seja ultrapassado, a API intercepta a exceção e responde com status HTTP `400 Bad Request` e uma mensagem clara ao usuário.

---

## 🚀 Como Executar a Aplicação

1. Certifique-se de ter o Java 21 ou superior instalado.
2. Clone o repositório:
   ```bash
   git clone https://github.com/Henriqueto/dio-spring-boot-learning-track.git
Acesse a pasta do módulo 05-spring-ai:

Bash
cd dio-spring-boot-learning-track/05-spring-ai
Execute o projeto usando o Wrapper do Gradle:

Windows (PowerShell/CMD):

PowerShell
.\gradlew.bat bootRun
Linux/Mac:

Bash
./gradlew bootRun
🧪 Como Testar o Fluxo Principal
Com a aplicação rodando (Tomcat started on port 8080), abra um terminal e execute os testes de integração abaixo:

1. Teste de Sucesso (Transação permitida <= R$ 5.000,00):
PowerShell
Invoke-RestMethod -Uri "http://localhost:8080/transactions" -Method Post -Headers @{"Content-Type"="application/json"} -Body '{"description": "Mercado", "amount": 10000, "category": "GROCERIES"}'
Retorno Esperado: Status 200 OK trazendo a transação persistida no banco com seu id UUID gerado.

2. Teste de Validação da Regra de Negócio (> R$ 5.000,00):
PowerShell
Invoke-RestMethod -Uri "http://localhost:8080/transactions" -Method Post -Headers @{"Content-Type"="application/json"} -Body '{"description": "Notebook", "amount": 600000, "category": "AUTO"}'
Retorno Esperado: Status 400 Bad Request bloqueando o cadastro da transação.

🎓 O que aprendi durante o desafio
Domain-Driven Design (DDD) & Regras de Negócio: Entender onde posicionar as validações de domínio para evitar que dados inconsistentes cheguem ao banco.

Tratamento de Exceções Customizadas: Como integrar exceções de negócio com os códigos de status HTTP corretos no Spring MVC.

Testes de API via CLI: Execução e validação de rotas RESTful utilizando utilitários de linha de comando (Invoke-RestMethod / curl).

Gerenciamento de Persistência com H2: Configuração de perfil de banco em memória para agilizar o ciclo de desenvolvimento e testes locais.
