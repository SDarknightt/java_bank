# Aplicação Bancária em Java

Projeto desenvolvido com o objetivo de reforçar os fundamentos da linguagem Java estudados no livro  
**Use a Cabeça Java (3ª Edição)**.

A aplicação simula um sistema bancário, executado via console, permitindo operações básicas
sobre contas bancárias.

## Funcionalidades

- Criação de múltiplas contas por usuário;
- Depósito em conta bancária;
- Saque com validação de saldo;
- Transferência entre contas;
- Leitura de arquivo contendo informações do usuário (document/user-document.txt).

## Conceitos de Java aplicados

Durante o desenvolvimento deste projeto, foram praticados os seguintes fundamentos:

- Tratamento de Exceções
  - Lançamento de exceções personalizadas com mensagens claras, orientando o usuário na identifição de erros de negócio;
- Herança 
  - Hierarquia aplicada aos usuários do sistema, utilizando uma classe abstrata User no topo da hierarquia e Costumer como implementação concreta;
- Polimorfismo 
  - Utilizado por meio da abstração (interfaces e classes abstratas) permitindo manipulação das classes independentemente da implementação da classe utilizada;
- Interfaces
  - Definição de contratos de implementação no repository, reduzindo o acoplamento;
- Collections  
  - Armazenamento e manipulação dos dados utilizando implementações de Map e List;
- Streams 
  - Aplição na filtragem das contas pertencentes ao usuário atualmente logado;
- Organização em pacotes  
  - Código com camadas bem definidas: Model, View, Service e Repository;
- Leitura de arquivos 
  - Leitura de arquivo por meio das classes Files e BufferedReader;
- Classe `main` e fluxo de execução
  - Controle do ciclo de vida da aplicação a partir do ponto de entrada.

## Como executar o projeto (localmente)

### Pré-requisitos
- Java JDK 21+
- Maven **ou** Maven Wrapper (`mvnw`)

### Executando com Maven Wrapper
Na raiz do projeto, execute:

```bash
./mvnw exec:java
