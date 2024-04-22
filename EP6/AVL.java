
public class AVL<Key extends Comparable<Key>, Value> {
    private Node root;             //raiz da árvore
    
    private class Node {
        private final Key key;       
        private Value val;
        private int height;
        private Node left, right;  
        
        public Node(Key key, Value val, int height) {
	    this.key = key;
	    this.val = val;
        this.height = height;                           //altura do nó
	   }
    }
    
    public AVL() {

    }

    //Checa se a árvore está vazia: se a árvore não estiver raiz, então está vazia
    public boolean isEmpty() {
        return root==null;
    }

    /* Retorna o valor correspondente a uma dada chave */
    public Value get(Key key){
	   return get(root, key);
    }

    private Value get(Node x, Key key) {                    //igual o do ep passado, vai procurando recursivamente pela chave
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }
    
    /* retorna TRUE se a chave key contém um valor na tabela de símbolos */
    public boolean contains(Key key) {
        return get(key) != null;                            //se o get não achar a chave, é porque ela não está na árvore 
    }
    
    /* Imprime os elementos da tabela de símbolo por ordem de chaves */
     public void inOrder() {
        inOrder(root);
    }
    
    private void inOrder(Node x) {                          //igual o do ep passado, vai imprimindo em ordem, usando a propriedade da bst que o que está na esqurda é menor do que o que está na direita.
        if (x == null) return; 
        inOrder(x.left); 
        StdOut.println(x.key + " "); 
        inOrder(x.right); 
    }

    public int height() {
        return height(root);
    }

    //Retorna a altura do nó x
    private int height(Node x) {
        if (x==null)                                     //se o nó for nulo, altura 0
            return 0;
        //if (x.right ==null && x.left==null)              //folhas têm altura zero
        //    return 0;
        return x.height;                                 //caso contrário, retorna a altura do nó
    }

    /* Insercao */
    public Node put(Key key, Value val) {
        return root = put(root, key, val);
    }    
    private Node put(Node x, Key key, Value val) {                  //quase igual do ep passado, só muda que tem que atualizar as alturas e balancear
        if (x == null) return new Node(key, val, 0);                //quando chega num nó vazio, coloca o novo nó com a chave e o valor especificados e altura 0(folhas têm altura 0)
        
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);         //se a chave do nó for maior que key, faz o algoritmo com o nó filho da esquerda
        else if (cmp > 0) x.right = put(x.right, key, val);         // e vice-versa
        else              x.val   = val;                          //se a chave já estiver na tabela, o valor é substituído
        
        x.height = 1 + Math.max(height(x.right),height(x.left));    //atualizando as alturas
        if (balance(x)==2 ||balance(x)==-2) x=balanceamento(x);       //balanceamento
        return x;                                                   //retorna a raiz
    }

    //Método para balancear a subárvore, mantendo a propriedade de balanceamento da árvore:
    //Quatro algoritmos possíveis para balancear a subárvore: rotação esquerda, rotação direita, rotação dupla esquerda e rotação dupla direita
    private Node balanceamento(Node x) {
        if (x == null) return null;

        int balx = balance(x);
        Node yLeft = x.left;
        Node yRight = x.right;
        int balyLeft = balance(yLeft);
        int balyRight = balance(yRight);

        if (balx ==2) {               //se balx =2, a árvore com raiz em x está desbalanceada para a esquerda, e se balyLeft também, fazemos uma rotação direita
            if (balyLeft>0 ) 
                x = rotDir(x);
            else x = rotDDir(x); 
        }
        else if (balx== -2) {        //contrário da anterior
            if (balyRight<0) 
                x = rotEsq(x);
            else x = rotDEsq(x);
        }

        x.height = 1 + Math.max(height(x.right),height(x.left));
        return x;
    }

    //Método para ver o balanço de um nó
    private int balance(Node x) {      //se essa função dá um valor negativo, a árvore está desbalanceada para a direita
        if (x==null) return 0;
        if (x.left == null && x.right == null) return 0;
        if (x.left == null) return (-1*x.right.height);
        if (x.right == null) return x.left.height;
        return height(x.left) -height(x.right);
    }

    //Métodos para rotações 
    private Node rotEsq(Node x) {
        Node y = x.right;                       //rotação
        x.right = y.left;
        y.left = x;
        
        //Atualizando as alturas de x e y
        x.height = 1 + Math.max(height(x.right),height(x.left));
        y.height = 1 + Math.max(height(y.right),height(y.left));

        //Retornando y, que é a nova raiz
        return y;
    }

    private Node rotDir(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        
        //Atualizando as alturas de x e y
        x.height = 1 + Math.max(height(x.right),height(x.left));
        y.height = 1 + Math.max(height(y.right),height(y.left));
 
        //Retornando y, que é a nova raiz
        return y;
    }

    private Node rotDEsq(Node x) {
        x.right=rotDir(x.right);        //primeiro fazemos uma rotação a direita com base no filho direito de x
        x=rotEsq(x);                    //depois, uma rotação à esquerda em x
        return x;                       
    }    

    private Node rotDDir(Node x) {
        x.left=rotEsq(x.left);          //contrário do anterior
        x=rotDir(x);
        return x;
    }

    /* Remove elemento com menor chave*/
     public Value removeMin() {
        Value minVal = removeMin(root); 
        root = removeMinGetNode(root);
        return minVal;
    }

    private Value removeMin(Node x) {                               //retorna o valor
        if (x == null)          return null;                        //se árvore for nula
        if (x.left != null)     return removeMin(x.left);           //se o filho esquerdo não for nulo, ainda não chegamos na menor chave, continua procurando
        else {                                                      //caso contrário, chegamos na menor chave, vamos retornar seu valor.                                       s
            return x.val;                                           //retorna o valor de x.
        }
    }

    private Node removeMinGetNode(Node x) {                         //retorna o nó
        if (x == null)          return null;                        //se árvore for nula
        if (x.left == null) {                                       //se o filho esquerdo for nulo, é a menor chave
            return x.right;                                         //retorna o filho direito (x é substituído por x.right) 
        }
        x.left=removeMinGetNode(x.left);                            //se ainda não achou a menor chave, continua procurando
        x.height = 1 + Math.max(height(x.right),height(x.left));;   //atualizar alturas
        if (balance(x)==2 ||balance(x)==-2) x=balanceamento(x);       //balancear a árvore novamente se necessário
        return x;
    }
     
    /* Remove o nó com chave "key" e retorna o valor correspondente a essa chave 
    
 ******** OBS: O seu algoritmo DEVE ser recursivo ********/
    
    public Value remove(Key key) {
        return remove(root,key);
    }

    private Value remove(Node x, Key key) {                         //retorna o valor
        Value removed = get(key);
        x = removeGetNode(x,key);
        return removed;
    }
    
    private Node removeGetNode(Node x, Key key) {                   //retorna o nó
        if (x==null) return null;                                   //árvore vazia

        int cmp = key.compareTo(x.key);                             //procurando o nó a ser deletado recursivamente
        if      (cmp < 0) x.left  = removeGetNode(x.left,  key);
        else if (cmp > 0) x.right = removeGetNode(x.right, key);
        else {                                                      //se chegarmos no nó que é igual à chave fornecida, deletamos esse nó
            if (x.left == null)                                     //se o nó não tem o filho esquerdo, é substituído pelo direito e vice-versa.
                x= x.right;                                         //se ele não tiver nenhum filho, é simplesmente substituído por null, ou seja, removido.
            else if (x.right == null)
                x= x.left;
            else {
                    //Se x tem dois filhos: achamos o sucessor, que é o menor nó da subárvore direita
                    Node t = x;                                     //salvamos x em t
                    x = minNode(t.right);                           //substituímos x pelo sucessor
                    x.right = removeMinGetNode(t.right);            //agora o filho direito de x vai ser a subárvore direita em x original menos o sucessor
                    x.left = t.left;
            }                                
        }
        x.height = 1 + Math.max(height(x.right),height(x.left));    //atualizar alturas

        if (balance(x)==2 ||balance(x)==-2) x=balanceamento(x);       //balanceamento
        return x;                                                   //retornar o novo x
    }

    //Retorna o menor nó da subárvore com base em x
    private Node minNode(Node x) {                                   
        if (x == null)          return null;                        //se a árvore é nula 
        if (x.left != null)     return minNode(x.left);             //se o filho esquerdo não é zero, ainda não chegamos na menor chave
        else                    return x;                           //caso contrário, chegamos na menor chave. retorna o nó.
    }
    
    //Remove todos os nós da árvore 
    public void clean(){
        root=clean(root);
    }
    private Node clean(Node x) {
        x=null; 
        return x;
    }

    public static void main(String[] args) {
        //Relatório de n = 100. 
        int n = 100;
        //Criando a árvore:
        AVL<Integer, Integer> st = new AVL<Integer, Integer>();        
        
        System.out.println("Para n = 100: ");
        System.out.println("------------------------------------");
        System.out.println("Chaves crescentes: ");
        
        double a = System.currentTimeMillis();
        
        //Preenchendo a árvore crescente:
        for (int i = 1; i<n+1; i++) {
            st.put(i, i);                 //chaves crescentes de 1 a 100
        }
        
        double b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());

        //Limpando a árvore:
        st.clean();

        System.out.println("------------------------------------");
        System.out.println("Chaves decrescentes: ");

        a = System.currentTimeMillis();
        
        //Preenchendo a árvore decrescente:
        for (int i = n; i>0; i--) {
            st.put(i, i);                 //chaves decrescentes de 100 a 1
        }
        
        b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());
        
        //Limpando a árvore:
        st.clean();

        System.out.println("------------------------------------");
        System.out.println("Chaves aleatorias: ");

        a = System.currentTimeMillis();
        
        //Preenchendo a árvore aleatória:
        //Criamos um vetor com uma permutação de 0 a 100
        int[] vetor = StdRandom.permutation(n+1);

        //Preenchendo a árvore
        for (int i = 0; i<n+1; i++) {
            if (vetor[i] !=0 )
                st.put(vetor[i],i);                      //se vetor[i] não for 0, botamos na árvore                                  
        }
        
        b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());

        //Limpando a árvore:
        st.clean();

        //Agora para n = 1000:
        n=1000;

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Para n = 1000: ");
        System.out.println("------------------------------------");
        System.out.println("Chaves crescentes: ");
        
        a = System.currentTimeMillis();
        
        //Preenchendo a árvore crescente:
        for (int i = 1; i<n+1; i++) {
            st.put(i, i);                 //chaves crescentes de 1 a 100
        }
        
        b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());

        //Limpando a árvore:
        st.clean();

        System.out.println("------------------------------------");
        System.out.println("Chaves decrescentes: ");

        a = System.currentTimeMillis();
        
        //Preenchendo a árvore decrescente:
        for (int i = n; i>0; i--) {
            st.put(i, i);                 //chaves decrescentes de 100 a 1
        }
        
        b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());

        //Limpando a árvore:
        st.clean();

        System.out.println("------------------------------------");
        System.out.println("Chaves aleatorias: ");

        a = System.currentTimeMillis();
        
        //Preenchendo a árvore aleatória:
        //Criamos um vetor com uma permutação de 0 a 100
        vetor = StdRandom.permutation(n+1);

        //Preenchendo a árvore
        for (int i = 0; i<n+1; i++) {
            if (vetor[i] !=0 )
                st.put(vetor[i],i);                      //se vetor[i] não for 0, botamos na árvore                                  
        }
        
        b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());

        //Limpando a árvore:
        st.clean();

        //Agora para n = 10000:
        n=10000;

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Para n = 10000: ");
        System.out.println("------------------------------------");
        System.out.println("Chaves crescentes: ");
        
        a = System.currentTimeMillis();
        
        //Preenchendo a árvore crescente:
        for (int i = 1; i<n+1; i++) {
            st.put(i, i);                 //chaves crescentes de 1 a 100
        }
        
        b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());

        //Limpando a árvore:
        st.clean();

        System.out.println("------------------------------------");
        System.out.println("Chaves decrescentes: ");

        a = System.currentTimeMillis();
        
        //Preenchendo a árvore decrescente:
        for (int i = n; i>0; i--) {
            st.put(i, i);                 //chaves decrescentes de 100 a 1
        }
        
        b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());

        //Limpando a árvore:
        st.clean();

        System.out.println("------------------------------------");
        System.out.println("Chaves aleatorias: ");

        a = System.currentTimeMillis();
        
        //Preenchendo a árvore aleatória:
        //Criamos um vetor com uma permutação de 0 a 100
        vetor = StdRandom.permutation(n+1);

        //Preenchendo a árvore
        for (int i = 0; i<n+1; i++) {
            if (vetor[i] !=0 )
                st.put(vetor[i],i);                      //se vetor[i] não for 0, botamos na árvore                                  
        }
        
        b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());

        //Limpando a árvore:
        st.clean();

        //Agora para n = 100000:
        n=100000;

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Para n = 100000: ");
        System.out.println("------------------------------------");
        System.out.println("Chaves crescentes: ");
        
        a = System.currentTimeMillis();
        
        //Preenchendo a árvore crescente:
        for (int i = 1; i<n+1; i++) {
            st.put(i, i);                 //chaves crescentes de 1 a 100
        }
        
        b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());

        //Limpando a árvore:
        st.clean();

        System.out.println("------------------------------------");
        System.out.println("Chaves decrescentes: ");

        a = System.currentTimeMillis();
        
        //Preenchendo a árvore decrescente:
        for (int i = n; i>0; i--) {
            st.put(i, i);                 //chaves decrescentes de 100 a 1
        }
        
        b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());

        //Limpando a árvore:
        st.clean();

        System.out.println("------------------------------------");
        System.out.println("Chaves aleatorias: ");

        a = System.currentTimeMillis();
        
        //Preenchendo a árvore aleatória:
        //Criamos um vetor com uma permutação de 0 a 100
        vetor = StdRandom.permutation(n+1);

        //Preenchendo a árvore
        for (int i = 0; i<n+1; i++) {
            if (vetor[i] !=0 )
                st.put(vetor[i],i);                      //se vetor[i] não for 0, botamos na árvore                                  
        }
        
        b = System.currentTimeMillis();
        
        System.out.println("Tempo em segundos: " + (b-a)/1000);
        System.out.println("Altura: " + st.height());

        //Limpando a árvore:
        st.clean();
    }
}
