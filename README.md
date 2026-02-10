# 🏨 Hotel API

API REST para gerenciamento de **hotéis, quartos, hóspedes e reservas**.  
Desenvolvida em **Spring Boot**, com **PostgreSQL** como banco de dados e documentação automática via **Swagger/OpenAPI**.

---

## 📌 Visão Geral

O sistema tem como objetivo gerenciar uma rede de hotéis, permitindo:

- Cadastro, atualização e remoção de **hotéis**
- Gerenciamento de **quartos** e controle de status
- Criação e cancelamento de **reservas**
- Cadastro e consulta de **hóspedes**
- Consultas de hotéis por **cidade** e **categoria**

A API segue os princípios REST, utilizando boas práticas de arquitetura, validação de dados e tratamento de erros.


---

## Índice

1. [Descrição do Projeto](#descrição-do-projeto)  
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)  
3. [Configuração do Banco de Dados](#configuração-do-banco-de-dados)  
4. [Modelos de Dados](#modelos-de-dados)  
5. [Endpoints Disponíveis](#endpoints-disponíveis)  
6. [Como Rodar a Aplicação](#como-rodar-a-aplicação)  
7. [Exemplos de Requisição](#exemplos-de-requisição)  
8. [Tratamento de Erros](#tratamento-de-erros)  
9. [Status do Projeto](#status-do-projeto)

---

## Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot 4.0.2**  
- **Spring Data JPA**  
- **Spring Validation**  
- **PostgreSQL**  
- **Lombok**  
- **Springdoc OpenAPI (Swagger)**  
- **Testes:** Framework de testes do Spring Boot (`spring-boot-starter-data-jpa-test`, `spring-boot-starter-webmvc-test`, etc.)

> ⚠️ O projeto utiliza os starters de teste do Spring Boot, que incluem suporte ao JUnit Platform, mas não há testes implementados ainda.

---

## Configuração do Banco de Dados

Configuração no `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel_db
spring.datasource.username=postgres
spring.datasource.password=1533
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.open-in-view=false

server.port=8080
```

> Certifique-se de criar o banco `hotel_db` no PostgreSQL antes de executar a aplicação.

---

## Modelos de Dados

As seguintes entidades estão definidas no projeto:  

- **Hotel** → informações do hotel, endereço, contatos e relacionamentos com funcionários, quartos e reservas.  
- **Funcionario** → dados de funcionários, relacionados a um hotel.  
- **Hospede** → dados de hóspedes, incluindo endereço completo.  
- **Quarto** → detalhes do quarto, preço e status, relacionado a um hotel.  
- **Reserva** → informações de reservas, vinculadas a hóspedes, funcionários e hotel.  
- **Pagamento** → controle de pagamentos, associado a reservas.  
- **ReservaQuarto** → tabela de associação entre reservas e quartos (muitos-para-muitos).

> Apenas a entidade **Hotel** possui serviço (`HotelService`) e controller implementados.

---

## Endpoints Disponíveis

### Hotéis (`/hoteis`)

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| POST | `/hoteis` | Cadastrar um hotel | 201 |
| GET | `/hoteis` | Listar todos os hotéis | 200 |
| GET | `/hoteis/{id}` | Buscar hotel por ID | 200 / 404 |
| GET | `/hoteis/cidade/{cidade}` | Buscar hotéis por cidade | 200 |
| GET | `/hoteis/categoria/{categoria}` | Buscar hotéis por categoria | 200 |
| PUT | `/hoteis/{id}` | Atualizar hotel | 200 / 404 |
| DELETE | `/hoteis/{id}` | Remover hotel | 204 / 404 |


### Quartos (/api/quartos)

| Método | Endpoint                       | Descrição                  |
| ------ | ------------------------------ | -------------------------- |
| GET    | `/api/quartos/hotel/{hotelId}` | Listar quartos de um hotel |
| POST   | `/api/quartos/hotel/{hotelId}` | Adicionar quarto ao hotel  |
| PATCH  | `/api/quartos/{id}/status`     | Alterar status do quarto   |


### Reservas (/reservas)

| Método | Endpoint                  | Descrição          |
| ------ | ------------------------- | ------------------ |
| POST   | `/reservas`               | Criar nova reserva |
| PATCH  | `/reservas/{id}/cancelar` | Cancelar reserva   |



### Hospedes (/api/hospedes)

| Método | Endpoint              | Descrição                |
| ------ | --------------------- | ------------------------ |
| GET    | `/api/hospedes`       | Listar todos os hóspedes |
| POST   | `/api/hospedes`       | Cadastrar hóspede        |
| GET    | `/api/hospedes/{cpf}` | Buscar hóspede por CPF   |

---


## Como Rodar a Aplicação

1. Clonar o repositório:

```bash
git clone <URL_DO_REPOSITORIO>
cd hotel-api
```

2. Configurar o banco `PostgreSQL` e atualizar `application.properties`.  

3. Compilar e rodar com Gradle:

```bash
./gradlew bootRun
```

4. A API estará disponível em `http://localhost:8080`.  

5. A documentação Swagger estará disponível em:  
```
http://localhost:8080/swagger-ui.html
```

---

## Exemplos de Requisição

**Criar hotel**

```http
POST /hoteis
Content-Type: application/json

{
  "nome": "Hotel Lux",
  "categoria": "Luxo",
  "telefone": "85 99999-0000",
  "email": "contato@hotellux.com",
  "rua": "Rua das Flores",
  "numero": "123",
  "bairro": "Centro",
  "cidade": "Fortaleza",
  "estado": "CE"
}
```

**Resposta:**

```json
{
  "id": 1,
  "nome": "Hotel Lux",
  "categoria": "Luxo",
  "telefone": "85 99999-0000",
  "email": "contato@hotellux.com",
  "rua": "Rua das Flores",
  "numero": "123",
  "bairro": "Centro",
  "cidade": "Fortaleza",
  "estado": "CE",
  "createdAt": "2026-02-10T00:00:00",
  "updatedAt": "2026-02-10T00:00:00"
}
```

---

## Tratamento de Erros

Formato padrão para erros:

```json
{
  "dataHora": "2026-02-10T00:00:00",
  "status": 404,
  "erro": "Recurso não encontrado",
  "mensagem": "Hotel não encontrado com id: 10",
  "caminho": "/hoteis/10"
}
```

- `404` → recurso não encontrado  
- `400` → dados inválidos  
- `409` → conflito de regra de negócio  
- `500` → erro interno do servidor  

---

## Status do Projeto

- **Hotéis:** funcional ✅  
- **Hospedes:** cadastro e consulta ✅ 
- **Quartos:** funcional (cadastro e status) ✅  
- **Reservas:** criação e cancelamento ✅
- **Funcionários:** entidades criadas, controller não implementado ⚠️  
- **Pagamentos:** entidades criadas, controller não implementado ⚠️  

> O projeto está parcialmente funcional e em **fase de desenvolvimento inicial**.

