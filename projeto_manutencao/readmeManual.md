# Manual do Projeto de Gerenciamento de Manutenção

## 1. Introdução
Bem-vindo ao **Manual do Projeto de Gerenciamento de Manutenção**. Este documento tem como objetivo apresentar as funcionalidades da aplicação, que foi desenvolvida para facilitar o gerenciamento de máquinas, manutenções, falhas e técnicos. A seguir, você encontrará uma descrição detalhada de cada painel disponível no sistema, além da nova funcionalidade de **geração de relatórios completos**.

## 2. Painéis

### 2.1. Painel de Máquinas
- **Descrição**: Este painel permite o cadastro e gerenciamento das máquinas da empresa.
- **Funcionalidades**:
  - **Botão Cadastrar**: 
    - Ao clicar neste botão, uma nova janela será exibida para que você possa inserir os dados da máquina. Os campos obrigatórios são:
      - **Código**
      - **Nome**
      - **Fabricante**
      - **Modelo**
      - **Detalhes**
      - **Localização**
      - **Tempo de Vida (anos)**
      - **Data de Aquisição** (formato **yyyy-mm-dd**)
  - **Atualização de Dados**: 
    - Para atualizar as informações de uma máquina já cadastrada, basta dar um **duplo clique** na linha correspondente. Isso abrirá a janela de cadastro com os dados preenchidos, permitindo que você faça as alterações necessárias.

### 2.2. Painel de Manutenções
- **Descrição**: Este painel permite o registro e gerenciamento das manutenções realizadas nas máquinas.
- **Funcionalidades**:
  - **Botão Cadastrar**: 
    - Clique neste botão para abrir uma nova janela onde você poderá inserir os dados da manutenção. Os campos obrigatórios são:
      - **Data**
      - **Tipo de Manutenção**
      - **Peças Trocadas**
      - **Tempo de Parada** (em horas)
      - **ID do Técnico**
      - **Observações**
  - **Atualização de Dados**: 
    - A atualização funciona da mesma forma que no painel de Máquinas, através de um **duplo clique** na linha desejada.

### 2.3. Painel de Falhas
- **Descrição**: Este painel permite o registro e gerenciamento das falhas identificadas nas máquinas.
- **Funcionalidades**:
  - **Botão Cadastrar**: 
    - Clique neste botão para abrir uma janela onde você poderá inserir os dados da falha. Os campos obrigatórios são:
      - **ID da Máquina**
      - **Data**
      - **Problema**
      - **Prioridade**
      - **Operador**
  - **Atualização de Dados**: 
    - Assim como nos painéis anteriores, você pode atualizar as informações de uma falha existente através de um **duplo clique** na linha correspondente.

### 2.4. Painel de Técnicos
- **Descrição**: Este painel gerencia as informações dos técnicos disponíveis para a realização das manutenções.
- **Funcionalidades**:
  - **Botão Cadastrar**: 
    - Ao clicar, uma nova janela será aberta para que você insira os dados do técnico. Os campos obrigatórios são:
      - **ID**
      - **Nome**
      - **Especialidade**
      - **Disponibilidade**
  - **Botão Deletar**: 
    - Para remover um técnico da lista, selecione a linha correspondente e clique neste botão. Uma confirmação será solicitada para garantir que a ação é intencional.
  - **Botão Gerar Relatório Completo**: 
    - Este botão gera um arquivo **.txt** contendo as informações de todos os dados cadastrados. É uma funcionalidade útil para manter registros ou para análises futuras.
  - **Atualização de Dados**: 
    - Você pode atualizar as informações de um técnico já cadastrado da mesma forma que nos outros painéis, utilizando um **duplo clique** na linha desejada.

### 2.5. Relatório Completo
- **Descrição**: Esta nova funcionalidade permite gerar um relatório completo que inclui dados sobre **Máquinas**, **Manutenções**, **Falhas** e **Técnicos**.
- **Funcionalidades**:
  - **Botão Gerar Relatório Completo**:
    - Ao clicar neste botão, um relatório detalhado será gerado e salvo como um arquivo **.txt**. O relatório inclui as seguintes informações:
      - **Máquinas**: ID, Código, Nome, Fabricante, Modelo, Data de Aquisição, Localização e Detalhes.
      - **Manutenções**: ID, Data, Tipo, Peças Trocadas, Tempo de Parada, Técnico ID e Observações.
      - **Falhas**: ID, ID da Máquina, Data, Problema, Prioridade e Operador.
      - **Técnicos**: ID, Nome, Especialidade e Disponibilidade.
    - **Estatísticas** adicionais são incluídas no relatório:
      - Número total de máquinas cadastradas.
      - Número total de manutenções realizadas e o tempo total de parada.
      - Número total de falhas registradas.
      - Número total de técnicos cadastrados.

## 3. Conclusão
Este manual apresenta uma visão geral das funcionalidades da aplicação de gerenciamento de manutenção, incluindo a geração de relatórios detalhados que abrangem todas as áreas do sistema, com estatísticas detalhadas para facilitar a análise. Acreditamos que, com este sistema, você poderá gerenciar as operações de maneira eficiente e prática. Para mais informações ou suporte técnico, não hesite em entrar em contato com a equipe de desenvolvimento.

Esperamos que este manual seja útil para você!
