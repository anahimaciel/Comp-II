# Implement Queue and Stack

Você deve entregar as 2 seguintes classes, Stack e Queue, todas
implementadas utilizando vetores, para armazenar somente Strings, cuja
API é descrita abaixo:

- Stack.java

public class Stack
  Stack(int cap)         : Cria uma pilha de strings com capacidade para cap strings
  void push(String s)    : Adiciona s na pilha
  String pop()           : remove e retorna o último elemento colocado na pilha
  boolean isEmpty()      : true ou false - pilha está vazia?
  int size()             : retorna a quantidade de elementos da pilha
  String peek()          : retorna o último elemento adicionado à pilha
  String maior()            : retorna a maior string da pilha (ordem lexicográfica)
  String menor()            : retorna a menor string da pilha (ordem lexicográfica)


Método main:

Implemente um teste que recebe a capacidade de args[0] e
recebe um texto da entrada padrão e, usando pilhas e/ou filas, deve 
imprimir as seguintes informações, na ordem numérica abaixo:
1 - Lista das strings começadas com 'a' ou 'A', na reversa da que aparecem no texto
2 - Lista das strings começadas com 'a' ou 'A', na ordem em que aparecem no texto
3 - Maior string do texto que começa com 'a' ou 'A' (em ordem lexicográfica)
4 - Menor string do texto que começa com 'a' ou 'A' (em ordem lexicográfica)

IMPORTANTE: Seus códigos devem ser eficientes. Em particular, os
métodos maior() e menor() devem, ao ser chamados, retornar o resultado
em tempo constante. Por exemplo, não devem verificar todos os
elementos da pilha para dar a resposta.

entrada: 
$ more names.txt
wendy
alice
dave
aline
walter
eve
Amanda
carlos
carol
Antonio
erin
azaleia
elza
peggy
trudy
bob
armenia
craig

Saída:
$ java-introcs Stack 20 < names.txt
armenia
azaleia
Antonio
Amanda
aline
alice
alice
aline
Amanda
Antonio
azaleia
armenia
maior: azaleia
menor: alice




- Queue.java

public class Queue

  Queue(int cap)             : Cria uma fila de strings com capacidade para armazenar cap strings
  void enqueue (String s)    : Adiciona s na fila
  String dequeue()           : remove e retorna o primeiro elemento atualmente na fila
  boolean isEmpty()          : true ou false - fila está vazia?
  int size()                 : retorna a quantidade de elementos da fila
  String peek()              : retorna o primeiro elemento atualmente na fila
  String maior()                : retorna a maior string da fila (ordem lexicográfica)
  String menor()                : retorna a menor string da fila (ordem lexicográfica)    retorna a menor string da pilha (ordem lexicográfica)


Método main:

Você deve implementar um teste que resolve o seguinte problema:

Em uma agência do Poupa Tempo que contém somente um caixa todos devem
ser atendidos em ordem de chegada, mas pessoas idosas têm prioridade e
devem ir para o início da fila. Porém, como em vários dias a quantidade
de idosos é muito grande, foram criadas as seguintes regra
adicional:
1 - Cada pessoa pode ser ultrapassada na fila por no
máximo 2 idosos.
Lembramos que idoso não passa na frente de idoso.

Você deve ler na entrada padrão um arquivo contendo uma sequência de
linhas, onde cada linha contém a informação da ordem de chegada e
categoria do cliente e imprima a ordem de atendimento
(todos chegaram na fila antes de começar o atendimento).
Exemplo

entrada:
1 geral
2 geral
3 idoso
4 idoso
5 idoso
6 geral
7 idoso

saída:
3 4 1 2 5 7 6


IMPORTANTE: Seus códigos devem ser eficientes. 
