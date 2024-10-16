# Manual do Projeto de Gerenciamento de Manutenção

## 1. Introdução
Este manual descreve as funcionalidades e o uso da aplicação de gerenciamento de manutenção, que inclui quatro painéis principais: Máquinas, Manutenções, Falhas e Técnicos.

## 2. Painéis

### 2.1. Painel de Máquinas
- **Descrição**: Permite cadastrar e gerenciar máquinas.
- **Funcionalidade**:
  - **Botão Cadastrar**: Ao clicar, uma nova janela é exibida para inserir os seguintes dados:
    - Código
    - Nome
    - Fabricante
    - Modelo
    - Detalhes
    - Localização
    - Tempo de vida (anos)
    - Data de aquisição (formato yyyy-mm-dd)
  - **Atualização de Dados**: Para atualizar informações de uma máquina, duplo clique na linha desejada abre a janela de cadastro com os dados já preenchidos.

### 2.2. Painel de Manutenções
- **Descrição**: Permite registrar e gerenciar as manutenções realizadas nas máquinas.
- **Funcionalidade**:
  - **Botão Cadastrar**: Ao clicar, uma nova janela é exibida para inserir os seguintes dados:
    - Data
    - Tipo de manutenção
    - Peças trocadas
    - Tempo de parada (em horas)
    - ID do Técnico
    - Observações
  - **Atualização de Dados**: Funciona da mesma forma que o painel de Máquinas.

### 2.3. Painel de Falhas
- **Descrição**: Permite registrar e gerenciar falhas das máquinas.
- **Funcionalidade**:
  - **Botão Cadastrar**: Ao clicar, uma nova janela é exibida para inserir os seguintes dados:
    - ID da Máquina
    - Data
    - Problema
    - Prioridade
    - Operador
  - **Atualização de Dados**: Funciona da mesma forma que os painéis anteriores.

### 2.4. Painel de Técnicos
- **Descrição**: Gerencia as informações dos técnicos disponíveis.
- **Funcionalidade**:
  - **Botão Cadastrar**: Ao clicar, uma nova janela é exibida para inserir os seguintes dados:
    - ID
    - Nome
    - Especialidade
    - Disponibilidade
  - **Botão Deletar**: Permite remover um técnico selecionado na tabela. É necessário confirmar a ação.
  - **Botão Gerar Relatório**: Ao clicar, um arquivo .txt é gerado com as informações dos técnicos cadastrados.

## 3. Conclusão
Este manual fornece uma visão geral das funcionalidades da aplicação. Para mais informações ou suporte técnico, entre em contato com a equipe de desenvolvimento.
