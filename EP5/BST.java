

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // raiz da BST
    private int N = 0;

    private class Node {
        private final Key key;       
        private Value val;           
        private Node left, right;    

        public Node(Key key, Value val) {
	       this.key = key;
	       this.val = val;
	   }
    }

    public BST() {

    }

    public boolean isEmpty() {
	   return root == null;
    }

    /* Retorna o valor correspondente a uma dada chave */
    public Value get(Key key) {
	   return get(root, key);
    }
    
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /* retorna TRUE se a chave key contém um valor na tabela de símbolos */
    public boolean contains(Key key) {
	   return get(key) != null;
    }

    /* Imprime os elementos da tabela de símbolo por ordem de chaves */
    public void inOrder() {
    	inOrder(root);
    }
    
    private void inOrder(Node x) { 
        if (x == null) return; 
        inOrder(x.left); 
        StdOut.println(x.key + " "); 
        inOrder(x.right); 
    }


/*
---------------------
---------------------
---------------------
---------------------
---------------------
Abaixo temos os métodos que você deve implementar
---------------------
---------------------
---------------------
---------------------
---------------------
*/

    /* Adiciona um par (key, value) na tabela de símbolos 
     e retorna a RAIZ da árvore*/
    public Node put(Key key, Value val) {
	   return root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {                                 //quando chega num nó vazio, coloca o novo nó com a chave e o valor especificados
            N++;                                         //aumenta contagem
            return new Node(key,val); 
        }
        int comp = key.compareTo(x.key);
        if (comp<0) x.left = put(x.left,key,val);        //se a chave do nó for maior que key, faz o algoritmo com o nó filho da esquerda
        else if (comp>0) x.right = put(x.right,key,val); // e vice-versa
        else x.val = val;                                //se a chave já estiver na tabela, o valor é substituído
        return x;                                        //retorna a raiz
    }



    /* Imprime os elementos da tabela de símbolos por nível da árvore, começando pela raiz

DICA: usar filas pode ser útil. Por exemplo, os seguintes comandos criam filas de chaves e de nós.

Queue<Key> keys = new Queue<Key>();
Queue<Node> queue = new Queue<Node>();

Os comandos abaixo podem ser úteis: 
queue.enqueue(nó) - coloca "nó" na fila "queue"
keys.enqueue(x.key) - coloca a chave do nó x na fila "keys"

Da mesma forma você pode usar os comandos queue.dequeue e keys.dequeue()   */  
    public void levelOrder() {
        this.levelOrder(this.root);
    }
    
    private void levelOrder(Node x) { 
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> nodes = new Queue<Node>();
        
        if (x == null) return;                  //x (root) é o primeiro nó adicionado à fila.
        else nodes.enqueue(x);

        while (!nodes.isEmpty()) {              //loop roda enquanto a fila não está vazia.
            Node i = nodes.dequeue();           //retira um nó da fila
            if (i == null) continue;            //se ele é null pula pra próxima rodagem do loop
            keys.enqueue(i.key);                //bota a chave do nó na fila de chaves
            nodes.enqueue(i.left);              //bota o nó da esquerda e depois o da direita na fila de nós
            nodes.enqueue(i.right);
        } 
        while(!keys.isEmpty()) {
            System.out.println(keys.dequeue());
        }
    }

    /* Imprime os elementos da tabela de símbolos por nível da árvore, começando das folhas no último nível */
    public void levelOrderFolhas() {
        this.levelOrderFolhas(this.root);
    }
    
    private void levelOrderFolhas(Node x) { 
        Queue<Node> nodes = new Queue<Node>();
        Stack<Key> keys = new Stack<Key>();     //pilha de chaves, para imprimir das folhas até a raiz
        
        if (x == null) return;                  //x (root) é o primeiro nó adicionado à fila.
        else nodes.enqueue(x);

        while (!nodes.isEmpty()) {              //loop roda enquanto a fila não está vazia.
            Node i = nodes.dequeue();           //retira um nó da fila
            if (i == null) continue;            //se ele é null pula pra próxima rodagem do loop
            keys.push(i.key);                   //bota a chave do nó na pilha de chaves
            nodes.enqueue(i.right);             //bota o nó da direita e depois o da esquerda na fila de nós
            nodes.enqueue(i.left);              //para ele continuar lendo da esquerda para direitas
        }
        while(!keys.isEmpty()) {
            System.out.println(keys.pop());
        }
    }

    /* Retorna o valor associado à menor chave da tabela de símbolos */
    public Value min() {
	   return min(root);
    }
    
    private Value min(Node x) {
        if (x == null)          return null;                        //se a árvore é nula 
        if (x.left != null)     return min(x.left);                 //se o filho esquerdo não é zero, ainda não chegamos na menor chave
        else                    return x.val;                       //caso contrário, chegamos na menor chave. retorna o valor dessa chave.
    }

    /* Retorna o valor associado à maior chave da tabela de símbolos */
    public Value max() {
	   return max(root);
    }
    
    private Value max(Node x) {
        if (x == null)          return null;                        //mesma lógica do método acima
        if (x.right != null)    return max(x.right);
        else                    return x.val;
    }

    /* Retorna a quantidade de pares key-value salvos na tabela de símbolos */
    /* Você pode criar outras variáveis de instância se achar necessário */
    public int size(){
        return N;                                                   //N é uma variável privada que aumenta quando botamos um par e diminui quando retiramos
    }

    /* imprime todas as chaves menores que key */
    public void menores(Key key){
        menores(root, key);
    }
    
    private void menores(Node x, Key key){
        if (x == null) return;                                      //se árvore é nula
        int cmp = key.compareTo(x.key);                             
        if      (cmp > 0) {                                         //a chave de x for menor que a chave fornecida, 
            System.out.println(x.key);                              //imprime essa chave
            menores(x.left,key);                                    //e checa tanto o filho esquerdo como direito
            menores(x.right,key);                                   //já que ambos pode ter chaves menores que key
        }
        else if (cmp < 0) {
            menores(x.left, key);                                   //se a chave de x for maior que key, checa o filho esquerdo, que pode ser menor
        }
        else {
            menores(x.left,key);                                    //se forem iguais idem
        }       
    }

    /* imprime todas as chaves maiores que key */
    public void maiores(Key key){
	   maiores(root, key);
    }
    
    private void maiores(Node x, Key key){                      //mesma lógica do método acima
        if (x == null) return;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) {
            System.out.println(x.key);
            maiores(x.left,key);
            maiores(x.right,key);
        }
        else if (cmp > 0) {
            maiores(x.right, key);
        }
        else {
            maiores(x.right,key);       //se forem iguais
        }       
    }

    
    /* imprime todas as chaves maiores que "menor" e menores que "maior" */
    public void entre(Key menor, Key maior){
        entre(root, menor, maior);
    }
    
    private void entre(Node x, Key menor, Key maior){
        if (x==null) return;                     //se árvore for nula 
        int cmpMenor = menor.compareTo(x.key);
        int cmpMaior = maior.compareTo(x.key);  //>0: maior>x.key
        if (cmpMaior<=0) {                      //Se x.key for maior ou igual à chave maior 
            entre(x.left,menor,maior);          //checa o filho esquerdo
        }
        else if (cmpMenor>=0) {                  //se a chave menor for maior ou igual a x.chave
            entre(x.right,menor,maior);          //checa o filho direito
        }
        else {                                   //se x.chave está entre menor e maior
            System.out.println(x.key);           //imprime a chave
            entre(x.right,menor,maior);          //checa filhos esquerdo e direito
            entre(x.left,menor,maior);
        }
    }

    /* Remove o nó com menor chave na tabela de símbolos e retorna o valor associado a ele */
    public Value removeMin() {
        Value minVal = removeMin(root); 
        root = removeMinGetNode(root);
        return minVal;
    }

    private Value removeMin(Node x) {
        if (x == null)          return null;                        //se árvore for nula
        if (x.left != null)     return removeMin(x.left);           //se o filho esquerdo não for nulo, ainda não chegamos na menor chave, continua procurando
        else {                                                      //caso contrário, chegamos na menor chave, vamos retornar seu valor.                                       s
            return x.val;                                           //retorna o valor de x.
        }
    }

    private Node removeMinGetNode(Node x) {
        if (x == null)          return null;                        //se árvore for nula
        if (x.left == null) {                                       //se o filho esquerdo for nulo, é a menor chave
            N--;                                                    //diminui a contagem do tamanho
            return x.right;                                         //retorna o filho direito (x é substituído por x.right) 
        }
        x.left=removeMinGetNode(x.left);                            //se ainda não achou a menor chave, continua procurando
        return x;
    }

/*
---------------------
---------------------
---------------------
---------------------
---------------------
O método "remove" abaixo é opcional e vale 1 ponto extra no EP
---------------------
---------------------
---------------------
---------------------
---------------------
*/
    
/* Remove o nó com chave "key" e retorna o valor correspondente a essa chave 
******** OBS: O seu algoritmo --NÃO-- deve ser recursivo*/

    public Value remove(Key key){
	   return remove(root, key);
    }
    
    private Value remove(Node x, Key key){                          //remover iterativamente o valor da chave key
        Node agor = x;
        Node ante = null;
        //Value val;

        if (agor==null) return null;                                //árvore nula                               
       
        while(agor!=null && agor.key.compareTo(key)!=0) {         //loop para achar o nó a ser removido. loop para quando achar o nó 
            ante = agor;                                            //pai do nó a ser removido
            int cmp = agor.key.compareTo(key); 
            if (cmp<0) {                                            //se a chave atual for menor que a chave que queremos, passamos para o filho direito
                agor = agor.right;
            }
            else {                                       //e vice-versa
                agor = agor.left;
            }
            System.out.println(agor);
            System.out.println(ante);
        }

        if (agor.right== null || agor.left==null) {              //se o nó a ser removido stiver no máximo um filho
            Node newNode;                                        //nó que vai substituir o nó removido
            if (agor.left==null) newNode = agor.right;           //se não tiver o filho esquerdo, o novo nó será o filho direito
            else newNode=agor.left;                              //e vice versa

            if (agor == ante.left) ante.left = newNode;          //se o nó deletado for filho esquerdo do anterior, então o novo nó também será
            else ante.right = newNode;                           //e vice versa
            //obs: se ambos os filhos forem nulos, newNode também será, então não haverá problemas
            //val = agor.val;
        }

        else {                                                   //se o nó a ser removido tiver dois filhos                                              
            Node paiDoSuc = null;
            Node sucessor = agor.right;
 
            // Acharemos o sucessor do nó a ser removido (agor), ie o menor nó que é maior que agor
            //sucessor = agor.right;
            while (sucessor.left != null) {                      //loop vai para quando chegar no último descendente esquerdo do filho direito do nó a ser deletado
                paiDoSuc=sucessor;
                sucessor = sucessor.left;                          
            }
        
            if (paiDoSuc == agor)   //se o pai do sucessor for o nó a ser deletado, então passamos o filho direito do sucessor para o lugar do filho direito do nó a ser deletado
            //ante.right = sucessor.right;
                {
                    sucessor.left = agor.left;
                    if (agor ==ante.right) {
                        ante.right=sucessor;
                    }
                    else {
                        ante.left=sucessor;
                    }
                }
            else //paiDoSuc.left = sucessor.right;                //caso contrário, o filho esquerdo do pai do sucessor (i.e o sucessor) vai ser substituído pelo seu filho direito
                {   
                    paiDoSuc.left = sucessor.right;
                    sucessor.right= agor.right;
                    sucessor.left=agor.left;
                    if (agor ==ante.right) {
                        ante.right=sucessor;

                    }
                    else ante.left = sucessor;
                }
            //val = agor.val;                                     //salvar o valor do nó deletado
            //agor=sucessor;                                      //substituir o nó a ser removido pelo seu sucessor
        }
        N--;                                                    
        //diminui o tamanho
        return agor.val;                                             //retornar o valor do nó deletado
    } 

    //código do monitor
    public void inOrderNicer() {
        inOrderNicer(root, 0);
        //StdOut.println();
    }
    
    private void inOrderNicer(Node x, int h) { 
        if (x == null) return; 
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < h; i++) s.append("     |");     
        inOrderNicer(x.right,  h + 1);
        StdOut.println(s.toString() + x.key); 
        inOrderNicer(x.left, h + 1); 
    } 

    public static void main(String[] args) { 
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

    StdOut.println("Arvore em ordem de chave: ");
    st.inOrder();
    StdOut.println();

      /*
      Implemente aqui os testes para os seus métodos
       */
    System.out.println("Valor da maior chave: " + st.max());
    System.out.println("Valor da menor chave: " + st.min());

    System.out.println();
    StdOut.println("Arvore em ordem de nivel, da raiz para as folhas: ");
    st.levelOrder();
    System.out.println();
    StdOut.println("Arvore em ordem de nivel, das folhas para a raiz: ");
    st.levelOrderFolhas();
    System.out.println();
    System.out.println("A tabela tem " + st.size() + " pares key-value.");
    System.out.println();
    StdOut.println("Menores que " + args[0]);
    st.menores(args[0]);
    System.out.println();
    StdOut.println("Maiores que " + args[1]);
    st.maiores(args[1]);
    System.out.println();
    System.out.println("Chaves entre: " + args[2] + " e " + args[3]);
    st.entre(args[2],args[3]);
    System.out.println();
    System.out.println("Retirando a menor chave. Valor da menor chave: ");
    System.out.println(st.removeMin());
    System.out.println();
    System.out.println("Em ordem de chave apos retirar a menor: ");
    st.inOrder();
    System.out.println();
    StdOut.println("Tamanho apos retirar menor chave: ");
    StdOut.println(st.size());
    System.out.println();
    System.out.println("Em ordem de nivel apos retirar a menor: ");
    st.levelOrder();
    System.out.println();
    System.out.println("InOrderNicer antes de retirar");
    st.inOrderNicer();
    System.out.println();
    System.out.println("Retirando a chave " + args[4] + ". Seu valor e:");
    System.out.println(st.remove(args[4]));
    System.out.println();
    System.out.println("Em ordem de chave apos a retirada ");
    st.inOrder();
    System.out.println();
    StdOut.println("Tamanho apos retirar chave: ");
    StdOut.println(st.size());
    System.out.println();
    System.out.println("Em ordem de nivel apos retirar a chave: ");
    st.levelOrder();
    System.out.println();
    System.out.println("InOrderNicer depois de retirar");
    st.inOrderNicer();
    System.out.println();
    }
}
