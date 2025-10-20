
# SGHSS-Backend

**Sistema de Gerenciamento Hospitalar e de Saúde Simplificado (SGHSS)**  
Backend desenvolvido em **Java + Spring Boot + MySQL (via Docker)** para gerenciar usuários (pacientes, profissionais e administradores), agendamentos, prescrições, prontuários médicos e hospitais.

---

## Tecnologias Utilizadas

- **Java 21+**
- **Spring Boot 3**
- **Spring Data JPA**
- **MySQL 8**
- **Lombok**
- **Docker / Docker Compose**

---

## Requisitos

Antes de iniciar, certifique-se de ter instalado:

- [Docker Desktop](https://docs.docker.com/desktop/setup/install/windows-install)
- [Git](https://git-scm.com/)

---

## Estrutura do Projeto

```
SGHSS-Backend/
├── src/main/java/com/example/uninter/projeto_backend_api/
│   ├── entity/
│   │   ├── BaseEntity.java
│   │   ├── User.java
│   │   ├── UserType.java
│   │   ├── Appointment.java
│   │   ├── AppointmentStatus.java
│   │   ├── AppointmentType.java
│   │   ├── Prescription.java
│   │   ├── MedicalRecord.java
│   │   ├── RecordEntry.java
│   │   ├── Notification.java
│   │   ├── Hospital.java
│   │   └── DTO/
│   ├── controller/
│   ├── service/
│   └── repository/
├── docker/
│   └── docker-compose.yml
├── Dockerfile
└── README.md
```

---

## Execução com Docker

O projeto já está configurado para rodar completamente em containers.

### 1 Subir os containers

Na raiz do projeto, execute:

```bash
docker compose -f docker/docker-compose.yml up --build
```

### 2️ Acessar a API

A aplicação ficará disponível em:

[http://localhost:8080](http://localhost:8080)

Banco de dados MySQL:  
- Host: `mysql`  
- Porta: `3306`  
- Usuário: `admin`  
- Senha: `123`  
- Banco: `uninter_backend`

---

## Entidades Principais

### User
Representa pacientes, profissionais e administradores.

| Campo | Tipo | Descrição |
|-------|------|------------|
| id | Long | Identificador |
| fullName | String | Nome completo |
| email | String | E-mail único |
| password | String | Senha criptografada |
| userType | Enum(UserType) | PACIENTE, PROFISSIONAL, ADMINISTRADOR |
| cpf | String | Documento nacional |

---

### Appointment
Agendamentos entre pacientes e profissionais.

| Campo | Tipo | Descrição |
|--------|------|-----------|
| id | Long | Identificador |
| patient | User | Paciente |
| professional | User | Profissional |
| dateTime | LocalDateTime | Data/hora da consulta |
| status | Enum(AppointmentStatus) | SCHEDULED, COMPLETED, CANCELLED |
| notes | String | Observações |

---

### Prescription
Prescrições médicas digitais vinculadas ao Appointment.

| Campo | Tipo | Descrição |
|--------|------|-----------|
| id | Long | Identificador |
| appointment | Appointment | Consulta associada |
| professional | User | Profissional que emitiu |
| date | LocalDateTime | Data da emissão |
| digitalSignature | Boolean | Indica se é digital |
| medicationsInfo | String | Detalhes dos medicamentos |

---

### MedicalRecord & RecordEntry
Histórico clínico do paciente, contendo múltiplas entradas.

- **MedicalRecord:** Representa o prontuário principal de um paciente.  
- **RecordEntry:** Registros de evoluções médicas, diagnósticos e observações.

---

## Endpoints Principais

### Usuários (`/api/users`)

#### Criar Usuário
**POST** `/api/users`

```json
{
  "fullName": "Leandro Ferreira",
  "email": "leandro@example.com",
  "password": "123456",
  "cpf": "12345678900",
  "userType": "PACIENTE"
}
```

**Resposta**
```json
{
  "id": 1,
  "fullName": "Leandro Ferreira",
  "email": "leandro@example.com",
  "userType": "PACIENTE",
  "cpf": "12345678900"
}
```

---

#### 🔍 Listar Todos
**GET** `/api/users`

**Resposta**
```json
[
  {
    "id": 1,
    "fullName": "Leandro Ferreira",
    "email": "leandro@example.com",
    "userType": "PACIENTE"
  },
  {
    "id": 2,
    "fullName": "Dra. Maria",
    "email": "maria@hospital.com",
    "userType": "PROFISSIONAL"
  }
]
```

---

### 📅 Agendamentos (`/api/appointments`)

#### ➕ Criar Agendamento
**POST** `/api/appointments`

```json
{
  "patientEmail": "leandro@example.com",
  "professionalEmail": "maria@hospital.com",
  "dateTime": "2025-10-21T15:30:00",
  "status": "SCHEDULED",
  "notes": "Consulta inicial"
}
```

**Resposta**
```json
{
  "id": 10,
  "patient": "leandro@example.com",
  "professional": "maria@hospital.com",
  "status": "SCHEDULED",
  "notes": "Consulta inicial"
}
```

---

#### Listar por Paciente
**GET** `/api/appointments/patient/{email}`

---

### Prescrições (`/api/prescriptions`)

#### Criar Prescrição
**POST** `/api/prescriptions`

```json
{
  "appointmentId": 10,
  "data": "2025-10-21T15:30:00",
  "digitalSignature": true,
  "medicationsInfo": "Paracetamol 500mg, tomar 1 comprimido a cada 8 horas por 5 dias."
}
```

**Resposta**
```json
{
  "id": 5,
  "appointmentId": 10,
  "date": "2025-10-21T16:00:00",
  "digitalSignature": true,
  "medicationsInfo": "Paracetamol 500mg, tomar 1 comprimido a cada 8 horas por 5 dias."
}
```

---

### Prontuários (`/api/medical-records`)

#### Criar Prontuário
**POST** `/api/medical-records/create/{patientId}`

```json
{
  "patientEmail": "leandro@example.com"
}
```

#### Adicionar Entrada ao Prontuário
**POST** `/api/medical-records/entry/`

```json
{
  "medicalRecordId": 1,
  "appointmentId": 1,
  "prescriptionId": 1,
  "date": "2025-10-21T15:30:00",
  "notes": "Paciente com piora significativa"
}
```

---

## Notificações (conceito)

Quando um agendamento é criado, uma **notificação lógica** é gerada para o paciente, podendo futuramente ser integrada com e-mail ou push notification.  
Atualmente, o sistema apenas **registra o evento internamente** no banco de dados (tabela `notification`).

---

## Boas Práticas e Requisitos Não Funcionais

- Utiliza **DTOs** para isolamento entre camadas.
- Senhas **nunca são retornadas** nas respostas da API.
- Todas as entidades herdam de `BaseEntity` com `id`, `createdAt`, `updatedAt`.
- Arquitetura em camadas (Controller → Service → Repository).
- Compatível com **Docker e ambientes multiplataforma**.
