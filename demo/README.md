# Pague Certo - Sistema de Gerenciamento de Clientes e Pagamentos
<hr>

O **Sistema de Gerenciamento Pague Certo** é uma aplicação desenvolvida em Java que permite o gerenciamento de clientes, endereços, cartões, dívidas e pagamentos. Com esta aplicação, você pode realizar várias operações relacionadas a clientes e pagamentos.

## Tecnologias Utilizadas

- **Linguagem de Programação:** Java
- **Framework de Persistência:** Spring Boot
- **Banco de Dados:** PostgreSQL

## Modelos de Dados

O sistema possui as seguintes entidades/modelos de dados:

### Cliente (Customer)

- Identificação única (ID)
- Nome
- Email
- Senha
- Data de Nascimento
- CPF
- Telefone

### Endereço (Address)

- Identificação única (ID)
- Rua/Avenida
- Número da Casa
- Estado
- Cidade
- CEP
- Bairro

### Cartão (Card)

- Identificação única (ID)
- Limite do Cartão
- Data de Validade
- Nome do Cliente do Cartão
- Número do Cartão
- CVV

### Dívidas (Debts)

- Identificação única (ID)
- Valor da Dívida
- Cartão Associado
- Pagamento Associado

### Pagamento (Payment)

- Identificação única (ID)
- Cartão Associado
- Dívidas Associadas
- Data de Vencimento
- Status
- Número de Parcelas
- Valor Total da Parcela
- Valor Total do Empréstimo
- Data do Pagamento

## Funcionalidades

**Para Clientes:**

- Cadastro de Cliente
- Atualização de Dados do Cliente
- Cadastro de Endereço
- Atualização de Endereço
- Cadastro de Cartão de Crédito
- Visualização de Informações do Cliente
- Realização de Pagamentos

**Para Administradores (Gerentes):**

- Visualização de Informações de Todos os Clientes
- Visualização de Informações dos Pagamentos

## Como Executar

Para executar o Sistema de Gerenciamento de Clientes e Pagamentos, siga os seguintes passos:

1. Clone o repositório para o seu computador ou faça o download como um arquivo ZIP.
2. Abra o projeto em uma IDE que suporte Java, como o IntelliJ IDEA.
3. Localize o arquivo principal (geralmente com o nome "Main.kt") e execute a função principal.
4. O programa será iniciado, e você poderá acessar as funcionalidades a partir de uma interface de linha de comando ou API.




