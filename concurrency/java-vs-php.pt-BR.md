# 🚀 Java vs PHP

🌐 Idioma:

* 🇺🇸 [English](./java-vs-php.md)
* 🇧🇷 Português (atual)

## 🧠 Seção Crítica

Uma seção crítica é uma parte do código que acessa um recurso compartilhado (como memória, arquivos ou bancos de dados) e não deve ser executada simultaneamente por múltiplas threads ou processos, caso contrário pode levar a resultados inconsistentes.

Uma seção crítica deve seguir o princípio da exclusão mútua, garantindo que apenas uma thread acesse o recurso por vez.

Um exemplo clássico é o problema do contador:

* Duas threads leem o valor 100  
* Ambas adicionam +10  
* Ambas escrevem 110  

Resultado esperado: 120  
Resultado real: 110 (condição de corrida)

## ☕ Como o Java Lida com Concorrência

Java fornece suporte nativo para concorrência através de multithreading, juntamente com vários mecanismos para controlar o acesso a seções críticas.

### 🔹 Variáveis Volatile

A palavra-chave `volatile` garante visibilidade entre threads, ou seja:

* Toda leitura é feita diretamente da memória principal  
* Evita o uso de cache da CPU  

📌 Importante:

`volatile` resolve o problema de visibilidade, mas não garante atomicidade.  
Portanto, operações como `count++` ainda podem causar condições de corrida.

### 🔹 Variáveis Atômicas (AtomicInteger)

Java fornece classes como:

* `AtomicInteger`  
* `AtomicLong`  

Essas utilizam um mecanismo chamado:

👉 CAS (Compare-And-Swap)

* Tenta atualizar um valor  
* Se outra thread modificou, tenta novamente  

Essa abordagem é conhecida como **lock-free**, pois evita bloqueio de threads.

✔ Vantagens:

* Alta performance  
* Seguro para operações concorrentes  

### 🧠 Diferença Principal

| Feature     | Volatile                          | Atomic Classes             |
| ----------- | --------------------------------- | -------------------------- |
| Propósito   | Garantir visibilidade das altera- | Garante atomicidade e      |
|             | ções entre threads                | visibilidade               |
| Atomicidade | Não garantida; apenas visibilidade| Garantida para operações   |
|             |                                   | individuais                |
| Operações   | Não é thread-safe (count++ não é  | Thread-safe                |
| Compost as  | atômico)                          | (incrementAndGet())        |
| Uso         | Flags simples ou variáveis de     | Contadores, flags, refe-   |
|             | estado                            | rências que exigem ações   |
|             |                                   | atômicas                   |
| Sincroniza- | Não, mas garante visibilidade     | Não usa sincronização tra- |
| ção         |                                   | dicional; baseado em CAS   |
| Performance | Baixo overhead, mas funcionali-   | Um pouco maior overhead,   |
|             | dade limitada                     | mas ainda sem bloqueio     |

### 🔹 Outros Mecanismos em Java

Java também oferece:

* blocos `synchronized`  
* interfaces `Lock`  
* semáforos  

## 🐘 Como o PHP Lida com Concorrência

Diferente de linguagens como Java, o PHP não foi originalmente projetado para suportar multithreading tradicional com memória compartilhada.

Em vez disso, o PHP segue uma abordagem diferente baseada em:

* Isolamento de processos  
* Execução stateless  
* Escalabilidade horizontal  

De acordo com o artigo “PHP and Multithreading: A Lighthearted Look at Concurrency”, a arquitetura do PHP evita memória compartilhada, o que muda a forma como a concorrência é tratada.

### 🔹 Modelo de Execução do PHP

Aplicações PHP normalmente rodam usando um modelo baseado em processos.

* Cada requisição roda de forma independente  
* Não há memória compartilhada entre execuções  
* Cada processo é isolado  

Em ambientes web (PHP-FPM):

* Um pool de processos workers é criado  
* Cada requisição é tratada por um processo diferente  

👉 Isso permite múltiplas requisições rodarem em paralelo no nível do sistema.  
👉 Por isso, conceitos como `volatile` não existem no PHP.

### 🔹 Por que PHP Não Usa Multithreading Tradicional

* PHP é projetado para ser stateless e isolado (sandbox)  
* Isso facilita a escalabilidade horizontal  

* Multithreading não é a solução ideal para PHP  
* Pode aumentar a complexidade e reduzir a escalabilidade  

👉 Em vez de threads, o PHP incentiva:

* Separação de responsabilidades  
* Arquitetura distribuída  

### 🔹 Como o PHP Alcança Paralelismo

Mesmo sem threads com memória compartilhada, problemas de concorrência ainda podem ocorrer.  
Problemas surgem quando múltiplos processos acessam:

* Arquivos  
* Bancos de dados  
* Recursos externos  

O PHP alcança paralelismo através de:

✔ Múltiplos Processos  

* Cada requisição roda em um processo separado  
* Gerenciado pelo PHP-FPM ou servidor web  

✔ Escalabilidade Horizontal  

* Mais servidores = mais processos  
* Melhora a performance sem memória compartilhada  

✔ Sistemas Externos  

* Bancos de dados  
* Redis  
* Filas de mensagens  

controlados

### 🔹 Alternativas Modernas de Multithreading no PHP

Embora não seja nativo, o PHP oferece algumas ferramentas avançadas:

✔ Extensões  

* `parallel` → permite execução em threads separadas  

✔ Fibers (PHP 8+)  

* Permitem multitarefa cooperativa  
* Usadas para programação assíncrona  

✔ Frameworks Assíncronos  

* ReactPHP  
* Swoole  

👉 Esses permitem execução não bloqueante e melhor performance  

### 🧠 Insight Principal

👉 PHP não é mais fraco em concorrência — ele é projetado de forma diferente.

Em vez de:

❌ Multithreading com memória compartilhada  

PHP utiliza:

✔ Isolamento de processos  
✔ Sistemas externos  
✔ Arquitetura escalável  

### ⚠️ Observação sobre Laravel (Framework PHP)

Embora o PHP não suporte multithreading nativamente, frameworks modernos como o Laravel fornecem mecanismos para lidar com concorrência e execução paralela através de arquitetura e sistemas externos.

O Laravel não implementa multithreading real dentro de uma única requisição. Em vez disso, ele alcança paralelismo usando:

**Filas e Workers**  
Tarefas podem ser enviadas para workers em background, permitindo que múltiplos jobs sejam processados simultaneamente.

**Múltiplos Processos**  
Aplicações Laravel rodam sobre PHP-FPM, onde múltiplos processos tratam requisições em paralelo.

**Jobs Assíncronos**  
Operações demoradas (ex: envio de emails, processamento de dados) são executadas fora do ciclo da requisição principal.

**Integração com Sistemas Externos**  
Ferramentas como Redis e filas de mensagens permitem processamento concorrente seguro e eficiente.

👉 Isso significa que o Laravel alcança paralelismo na prática, mas não multithreading real com memória compartilhada, como em linguagens como Java.

# 📚 Referências

## 📘 Material de Aula

Seção Crítica – Slides de aula (Prof. Cristiane Imamura)

## ☕ Concorrência em Java

Java volatile vs Atomic classes  
https://medium.com/@qingedaig/java-volatile-vs-atomic-classes-7599eb70a661

## 🐘 Concorrência e Paralelismo em PHP

PHP and Multithreading: A Lighthearted Look at Concurrency  
https://infinitypaul.medium.com/php-and-multithreading-a-lighthearted-look-at-concurrency-dac4540f3726  

Programação Paralela e Assíncrona com PHP  
https://medium.com/@sschonss/programação-paralela-e-assíncrona-com-php-5969c49d0bba  

Unlocking Parallel Processing in PHP  
https://devriazul.medium.com/unlocking-parallel-processing-in-php-yes-its-possible-e915dab720b2  

## ⚙️ Arquitetura do PHP & Modelo de Execução

PHP-FPM gerencia múltiplos processos e trata requisições concorrentes de forma eficiente.

Understanding PHP-FPM  
https://dev.to/arsalanmee/understanding-php-fpm-a-comprehensive-guide-3ng8  

## 🔒 Controle de Concorrência em PHP

PHP fornece mecanismos de lock de arquivos como `flock` para exclusão mútua.

Parallel processing in PHP (StackOverflow discussion)  
https://stackoverflow.com/questions/6107339/parallel-processing-in-php-how-do-you-do-it  

## 🧪 Leitura Adicional

PHP multithreading faking it  
https://w-shadow.com/blog/2007/08/20/php-multithreading-faking-it/  