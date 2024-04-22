Orgulho LGBTQIA+

O ano é 2050. Após anos de perseguição, os membros da comunidade LGBTQIA+ vivem em paz e harmonia em seu reino. Mas essa harmonia está ameaçada, pois restou um preconceituoso nesse reino, conhecido como "Biroliro, o tosco". Biroliro agora tem um plano que ameaça os membros da comunidade. Sabendo de seus planos, os grandes membros da comunidade LGBTQIA+ estão se mobilizando para combatê-lo.

O reino é formado por N cidades e M estradas, cada uma das estradas liga duas das cidades, e demora-se um certo tempo para se percorrer cada uma delas. O reino fica em uma região montanhosa e as estradas só podem ser percorridas em uma direção.

Há K membros da comunidade LGBTQIA+, localizados em cidades variadas. Para que consigam derrotar Biroliro, todos devem se reunir em uma mesma cidade, estritamente antes da chegada de Biroliro a essa mesma cidade. Se Biroliro encontrar qualquer heróimembro da comunidade (ou grupo de membros) antes da reunião, Biroliro conseguirá atrapalhar os planos da comunidade (todos os membros da comunidade são essenciais para derrotar Biroliro).

A comunidade LGBTQIA+ precisa de sua ajuda! Você precisa encontrar um ponto de encontro seguro para que se reúnam.

Definimos uma cidade V como segura para um membro H da comunidade se existir um caminho que H pode seguir para chegar a V sem ser interceptado por Biroliro, independentemente de como Biroliro resolver perambular pelo reino (e incrivelmente ele tem um faro perfeito para procurar os membros dessa comunidade).

Seu trabalho é encontrar as cidades que sejam seguras para todos os membros da comunidade que, assim como Biroliro, movem-se ao longo das estradas respeitando as direções (mãos únicas) e os tempos das estradas.
-------------

A entrada de seu programa será da seguinte forma:

Na primeira linha, são dados três inteiros N, M e K separados por espaços (3 <= N <= 10000; 3 <= M <= 1000000; 1 <= K <= 200), respectivamente o número de cidades do reino, o número de estradas e o número de membros da comunidade.

Em cada uma das próximas M linhas, são dados três inteiros A, B e T separados por espaços (0 <= A, B < N ; 1 <= T <= 10000), representando uma estrada que liga a cidade A até B, e o tempo T que se leva para percorrer essa estrada (atenção: A B T é diferente de B A T).

Em cada uma das próximas K linhas, um único inteiro C (0 <= C < N), indicando a cidade inicial do K-ésimo membro da comunidade. E na próxima linha, um único inteiro F (0 <= F < N), indicando a cidade inicial de Biroliro.

A saída deve ser da seguinte forma:

Caso existam cidades seguras para o encontro, na primeira linha imprima "O REINO ESTÁ SALVO!". Na próxima linha, imprima um inteiro S, indicando o número de cidades seguras para o encontro. Na linha seguinte, imprima S inteiros separados por espaço, indicando quais são as cidades seguras.

Caso não existam cidades seguras, imprima apenas "INFELIZMENTE O PRECONCEITO VENCEU :(". É garantido que todas as cidades e estradas formam uma única componente fortemente conexa (Pesquise o que é uma componente fortemente conexa - strongly connected component).

* Você deve fazer uso do algoritmo de Dijkstra.

* EXTRA: Se você fizer, além da implementação com o algoritmo de Dijkstra, uma implementação que faz uso do algoritmo de Bellman-Ford, você pode ganhar até 2 pontos extra (a nota máxima desse EP é 12). A correção da parte extra será bem criteriosa e avaliará inclusive a organização do seu código.

* Depois serão fornecidos exemplos de arquivos de entrada
