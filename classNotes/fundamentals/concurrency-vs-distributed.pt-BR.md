# 🧠 Concorrência vs Sistemas Distribuídos

🌐 Idioma:

* 🇺🇸 [English](./concurrency-vs-distributed.md)
* 🇧🇷 Português (atual)

---

## 📍 Sistemas Distribuídos (Onde)

Sistemas distribuídos focam na **localização física**

* Componentes estão separados em diferentes máquinas
* A comunicação acontece pela rede
* Exemplo: computação em nuvem

## ⏱️ Sistemas Concorrentes (Quando)

Sistemas concorrentes focam no **tempo de execução**

* Múltiplas tarefas executam e competem por recursos
* As tarefas podem rodar intercaladas ou simultaneamente

## 🔗 Relação

Todo sistema distribuído é inerentemente concorrente,
mas nem todo sistema concorrente é distribuído.

* Distribuído → sempre concorrente
* Concorrente → pode rodar em uma única máquina

## ⚔️ Programação Paralela vs Programação Concorrente

### 🧵 Programação Concorrente

* Múltiplas tarefas progridem ao mesmo tempo
* As tarefas podem competir por recursos compartilhados
* Exemplos:

  * Duas threads acessando a mesma variável
  * Uma prossegue, a outra espera

### ⚡ Programação Paralela

* Múltiplas partes de um programa executam simultaneamente
* Requer múltiplos núcleos de CPU
* Foco: melhoria de desempenho

#### ⚠️ Desafios Comuns

* Depuração é difícil
* A ordem de execução é imprevisível
* Os resultados podem variar entre execuções

#### 📈 Motivação do Paralelismo

* Reduzir o tempo de execução
* Otimizar o uso de recursos

#### 🧠 Lei de Moore

O poder de processamento dobra ao longo do tempo — mas possui limites.

#### ⚖️ Lei de Amdahl

Um programa nunca é 100% paralelizável.

* Algumas partes são sempre sequenciais
* Isso limita o ganho de desempenho

##### Insight da Fórmula

A aceleração máxima:

```
1 / (1 - P)
```

Onde:

* `P` = parte paralelizável do programa

#### 📌 Exemplo

Se 90% de um programa for paralelo:

* A aceleração máxima é 10x
* Mesmo com processadores infinitos

#### 🚨 Starvation

Ocorre quando:

* Uma thread nunca recebe tempo de CPU
* Devido a prioridade ou escalonamento

### 🧠 Diferença Principal

| Conceito     | Foco                                       |
| ------------ | ------------------------------------------ |
| Concorrência | Gerenciar acesso a recursos compartilhados |
| Paralelismo  | Executar tarefas simultaneamente           |

## 🧠 Conceitos Importantes

### ⚠️ Condição de Corrida (Race Condition)

Ocorre quando múltiplas threads acessam o mesmo recurso simultaneamente, causando resultados imprevisíveis

### 🔒 Seção Crítica

Parte do código onde recursos compartilhados são acessados

* Deve ser protegida
* Apenas uma thread deve executá-la por vez

### 🧷 Exclusão Mútua

Garante que quando uma thread entra em uma seção crítica, as outras devem esperar

## 🔧 Ferramentas de Sincronização

### 🧱 Monitor / Lock

* Controla o acesso a um recurso
* Apenas uma thread pode possuir o lock por vez

### 🚦 Semáforo

* Permite que múltiplas threads acessem um recurso (limitado)
* Exemplo:

  * Um servidor suporta 1000 requisições ao mesmo tempo
  * A requisição 1001 deve esperar

## ⚡ Threads

### 📌 Definição

Uma thread é um processo leve:

* Compartilha memória com outras threads
* Possui sua própria pilha de execução
* Executa instruções de forma independente

### 🔁 Ciclo de Vida

New
Runnable
Running
Waiting / Blocked
Terminated

### 🧩 Métodos Principais

* `run()` → lógica executada pela thread
* `start()` → inicia a execução
* `sleep()` → pausa a execução
* `wait()` → aguarda uma condição
* `join()` → espera outra thread terminar

### 🔄 Variáveis Voláteis

* Garantem visibilidade entre threads
* Alterações em uma thread são visíveis para outras

### ⚡ Variáveis Atômicas

* Garantem operações indivisíveis
* Evitam condições de corrida em operações simples