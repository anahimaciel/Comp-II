# Number of partitions of a positive integer

Estude inicialmente o Web Exercise 35, Integer partitions, de IntroCS:

https://introcs.cs.princeton.edu/java/23recursion/

Repetimos aqui rapidamente a definição central nesse exercício. Seja N um inteiro positivo. Uma partição de N é uma forma de escrever N como uma soma de inteiros positivos. Por exemplo, N = 4 admite as partições

4

3 + 1

2 + 2

2 + 1 + 1

1 + 1 + 1 + 1

Seja p(N) o número de partições de N. Temos p(4) = 5. Note que, aqui, a ordem das parcelas não é considerada relevante, de forma que consideramos as partições 1 + 2 + 1 e 2 + 1 + 1 de 4 como sendo a mesma partição.

-------------------

Escreva um programa chamado Partitions.java que, dado N, determina p(N). Seu programa deve ser rápido o suficiente para calcular p(400) basicamente instantaneamente. Note que seu programa não precisa listar as partições: basta que ele imprima o número delas.

Você pode ler mais sobre partições aqui:

https://en.wikipedia.org/wiki/Partition_(number_theory)

A teoria de partições de inteiros foi estudada a fundo por Hardy e Ramanujan. Partições ocorrem no filme "The Man Who Knew Infinity", que conta a história de S. Ramanujan:

http://www.imdb.com/title/tt0787524/

Veja também

http://theconversation.com/the-man-who-knew-infinity-inspiration-rigour-and-the-art-of-mathematics-59520

Importante. Se você usar fatos sobre partições que não sejam óbvios, você deve justificar tais fatos explicitamente.

Observação. Use variáveis long. A função p(N) cresce muito rapidamente com N.

Exemplos de execução.  Seu programa deve funcionar como ilustrado abaixo.

$ java-introcs Partitions 1
1
$ java-introcs Partitions 2
2
$ java-introcs Partitions 3
3
$ java-introcs Partitions 4
5
$ java-introcs Partitions 5
7
$ java-introcs Partitions 100
190569292
$ java-introcs Partitions 200
3972999029388


$ time java-introcs Partitions 400
6727090051741041926
real 0m0.155s
user 0m0.123s
sys 0m0.034s
$



Sugestão. Defina p(N, M) como sendo o número de partições de N com todas as partes (parcelas) menores ou iguais a M. Por exemplo, p(4, 2) = 3, p(5, 2) = 3 e p(5, 3) = 5. Naturalmente, queremos p(N) = p(N, N).

Pode ajudar pensar em valores concretos de N e M. Por exemplo, p(7, 3) = 8, p(7 - 3, 3) = p(4, 3) = 4, p(7, 2) = 4. Qual é a relação entre p(7, 3), p(7 - 3, 3) e p(7, 2)?
