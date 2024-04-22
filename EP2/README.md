# Comparing difference between lines

Estude o Web Exercise 51 (Unix diff) de IntroCS, localizado no link:
http://introcs.cs.princeton.edu/java/23recursion/

Observação. Para ler rapidamente sobre o uso de In, você pode ver as páginas 34 a 36 de

https://www.ime.usp.br/~mota/courses/2021/2021i-CCM128/arquivos/31datatype.pdf

Veja também a seção "Input and output revisited" em

https://introcs.cs.princeton.edu/java/31datatype/

Você pode ler sobre o utilitário diff aqui:

https://en.wikipedia.org/wiki/Diff_utility

Você também pode executar "man diff" em um terminal no Mac ou no Linux (no windows eu acho que o comando é "diff /?")


--------------------------------------------------------------

Obs.: Você não precisa utilizar In. As sugestões acima servem para você entender o código em Diff.java (abaixo).
Pode utilizar a entrada padrão StdIn em seu código.

--------------------------------------------------------------

Escreva uma variante do programa Diff.java (arquivo anexado mais abaixo), chamado DiffLines.java, que compara duas linhas dadas e marca as diferenças entre as duas linhas. Vale a pena analisarmos um exemplo:

Suponha que temos as duas linhas

Happy families are all alike; every unhappy family is unhappy in its own way. 

Happy families are alike; every unhapy family is unhappy on its own way.

Seu programa deve produzir a seguinte saída quando alimentado com as duas alinhas acima:

First line:

Happy families are *all* alike; every *unhappy* family is unhappy *in* its own way.
Second line:
Happy families are alike; every *unhapy* family is unhappy *on* its own way.
Note que as palavras entre '*' são as "diferenças" entre as duas linhas dadas.

Seu programa deve ler as linhas dadas e deve decompô-las em "palavras" (cadeias maximais de "não-espaços" separadas por espaços). Compare a sequência de palavras da primeira linha contra a sequência de palavras da segunda linha usando o algoritmo das subsequências comuns mais longas (veja https://introcs.cs.princeton.edu/java/23recursion/). Marque as diferenças com '*'.

Observação. Para decompor um string em palavras, você pode usar split(), como no exemplo abaixo:

String l = StdIn.readLine();
String[] s = l.split("\\s+");
for (int i = 0; i < s.length; i++)
  StdOut.println(i + ": " + "\"" + s[i] + "\"");
Veja o programa Split.java abaixo.

------------------------------------------------------------------

Um exemplo maior. Anexo seguem arquivos que ilustram o funcionamento desejado em um exemplo maior. O arquivo de saída foi produzido fazendo

$ cat l0.txt l1.txt | java-introcs DiffLines > l.out
O comando cat concatena os arquivos dados (l0.txt e l1.txt no exemplo acima). O exemplo pequeno acima foi produzido de forma semelhante:

$ cat e0.txt
Happy families are all alike; every unhappy family is unhappy in its own way.
$ cat e1.txt
Happy families are alike; every unhapy family is unhappy on its own way.
$ cat e0.txt e1.txt | java-introcs DiffLines > e.out
$ cat e.out
First line:
Happy families are *all* alike; every *unhappy* family is unhappy *in* its own way.
Second line:
Happy families are alike; every *unhapy* family is unhappy *on* its own way.
