# VisitaJá - Sistema de Gestão de Visitação de Pátios

O VisitaJá é um sistema desenvolvido para gerenciar e otimizar o agendamento de visitas presenciais a pátios de leilão. Ele funciona como um portal de controle de acesso, permitindo que visitantes reservem horários específicos para examinar os bens antes dos arremates, garantindo organização e previsibilidade no fluxo físico de pessoas.

## Principais Funcionalidades

* **Visão do Visitante:** Consulta de leilões ativos e agendamento de visitas em datas e horários que possuam vagas disponíveis.
* **Visão do Administrador:** Gestão e cadastro de leilões e seus respectivos pátios, definição da capacidade de visitantes por turno, controle das confirmações de agendamento e emissão de relatórios.

## Estrutura do Projeto

O repositório é dividido em duas partes principais: a API (Backend) e a Interface (Frontend).

```text
/
├── backend/                 
    └── ...            
└── frontend/
    └── ...
```

### Backend

API REST responsável pelas regras de negócio, gerenciamento de leilões, pátios, agendamentos, usuários e autenticação/autorização.

### Frontend

Interface web responsável pela interação dos visitantes e administradores com o sistema, permitindo realizar agendamentos, consultar horários e gerenciar as funcionalidades administrativas.

## Executar localmente

Primeiro suba o PostgreSQL e configure `backend/.env` conforme a documentação do backend. Depois, na raiz do projeto, execute:

```bash
./start.sh
```

O script inicia o backend em `http://localhost:8080` e o frontend em `http://localhost:5500`. Pressione `Ctrl+C` para encerrar os dois servidores.

## Documentação

- [Documentação do Backend](./backend/README.md)
- [Documentação do Frontend](./frontend/README.md)
