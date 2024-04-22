# Implementing AVLs

Neste EP você deve implementar uma tabela de símbolos utilizando a estrutura de árvore AVL e realizar algumas simulações no método main().

Você deve completar/alterar o código AVL.java em anexo como desejar, implementando todos os métodos descritos, com o ponto de que a remoção deve ser obrigatoriamente feita por um algoritmo recursivo.

Você pode criar outros métodos que julgar necessário, como um método para restaurar a propriedade de árvore AVL que for estragada por uma remoção ou inserção.

Perceba que vários dos códigos pedidos já foram feitos no EP05, de modo que você pode utilizar esses códigos aqui.



No seu método main() você deve analisar duas coisas: o tempo utilizado para gerar uma Árvore AVL e a altura da árvore. Isso deve ser feito para árvores com n vértices em que

* n = 100, 

* n = 1000, 

* n = 10000.

* n = 100000.

Em sua análise, para cada n (100, 1000, 10000, 100000), gere 3 árvores AVL com chaves {1,2,3,...,n} como segue: inserindo as chaves

em ordem crescente; inserindo as chaves em ordem decrescente; e inserindo as chaves de aleatoriamente (escolha uniformemente ao acaso uma das n! permutações de {1,2,...,n} para usar como ordem de inserção).

Seu programa deve imprimir na saída um relatório mostrando de forma clara os resultados dos testes para cada valor de n e para cada uma das ordens de inserção.

Bom trabalho!  
