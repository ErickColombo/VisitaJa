# VisitaJá - Sistema de Gestão de Visitação de Leilões

O VisitaJá é uma API REST desenvolvida para gerenciar e otimizar o agendamento de visitas presenciais a pátios de leilão. O sistema funciona como um portal de controle de acesso, permitindo que visitantes reservem horários específicos para examinar os bens antes dos arremates, garantindo organização e previsibilidade no fluxo físico de pessoas.

## Principais Funcionalidades

* **Visão do Visitante:** Consulta de leilões ativos e agendamento de visitas em datas e horários que possuam vagas disponíveis.
* **Visão do Administrador:** Gestão e cadastro de leilões e seus respectivos pátios, definição da capacidade de visitantes por turno, controle das confirmações de agendamento e emissão de relatório.

## Estrutura de Pastas
```text
src/main/java/br/com/visitaja
│
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── exception/
├── security/
└── config/
