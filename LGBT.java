public class LGBT {
	//N cidades, M estradas, K membros, V cidade segura para membro H
	//Achar cidades seguras para todos os membros
	
	//Classe para os membros
	private static class Membro {
    	private final String inicio;					//guarda a cidade inicial do membro

    	public Membro(String inicio){
    		this.inicio = inicio;
    	}
    }

    //Implementando grafo direcionado
    public static class Digraph {
		private final int V;							// número de vértices do grafo
		private int E;									// número de arestas do grafo
		public ST<Integer, Integer>[] adj;				// adj[v] = lista de adjacência para o vértice v
		private int K;									//número de membros
		private Membro biroliro; 						//objeto do biroliro
		public Membro[] membros;						//vetor para guardar todos os membros
		private int pesoMax=0;							//maior peso das estradas
		private int M; //= pesoMax*E;					//número que é garantido ser maior do que qualquer caminho possível (mesmo se usasse todas as arestas, usaria pesos menores que o peso máximo)
		private int VMax=0;								//maior vértice

		public Digraph(In in) {			
    		String line1 = in.readLine();				//lendo o arquivo

    		String[] nmk = line1.split(" ");
 
        	V = Integer.parseInt(nmk[0]);
        	E = Integer.parseInt(nmk[1]);
        	K = Integer.parseInt(nmk[2]);

        	membros=new Membro[K];						//vetor para guardar os membros

        	adj = (ST<Integer,Integer>[]) new ST[V];	//adj é um vetor de symbol tables. Criamos uma ST para cada vértice, guardando sua lista de adjacência.
        	for (int v = 0; v < V; v++) {
                adj[v] = new ST<Integer, Integer>();
            }

        	int i =0;
            while(i<E) {
        		String line = in.readLine();			//continuamos lendo o arquivo
        		String[] abt = line.split(" ");
        		int a = Integer.parseInt(abt[0]);
        		int b = Integer.parseInt(abt[1]);
        		int t = Integer.parseInt(abt[2]);
        		if(t>pesoMax) pesoMax=t;				//achando o maior peso
        		if(a>VMax) VMax=a;						//e maior vértice
        		addEdge(a,b,t); 						//criando a aresta de a para b com peso t
        		i++;
        	}

        	while(i<K+E) {
        		String line = in.readLine();			//lendo o arquivo e criando os membros
        		Membro pessoa = new Membro(line);
        		membros[i-E]= pessoa;
        		i++;
        	}

    	   	String line = in.readLine();
        	biroliro= new Membro(line);					//biroliro é um membro para o qual salvamos a referência
        	M= pesoMax*E;								//M é maior do que qualquer caminho possível, então usamos esse valor para inicializar os vértices no dijkstra/bellman
        }
		
		public int V() { return V; }					//utilitárias
		
		public int E() { return E; }

		public int M() { return M; }

		public int K() { return K; }

		public int VMax() { return VMax; }
		
		public void addEdge(int v, int w, int p) {		//função que cria aresta v para w com peso p, botando w na lista de adjacência de v. A lista é uma ST, onde a chave é w e o valor é o peso da aresta p.
			adj[v].put(w,p);
		}
		
		public Iterable<Integer> adj(int v) { 			//para usar no for each loop	
			return adj[v]; 
		}
    }

    //Implementando dijkstra
    private static class Dijkstra {
	  
    	public int[] prev;										//vetores para guardar os valores de distância, se já foi visitado, e qual vértice vem antes no caminho
    	public int[] distancia;
    	public int[] visitado;

    	public Dijkstra(Digraph g, int s) {						//para calcular a distância e caminho mínimo de outros vértices até s
      		prev = new int[g.V()];								//inicializando
    		distancia = new int[g.V()];
      		visitado = new int[g.V()];

      		for(int x = 0;x<g.V();x++) {						//inicializando
    			prev[x] = g.VMax()+1;  							//g.Vmax+1 é maior do que qualquer vértice
      			distancia[x] = g.M();
      			visitado[x]=0;
      		}

      		distancia[s]=0;										//a distância  de s até s é 0

      		int prox = next(g,s,distancia,visitado);			//a função next escolhe o próximo vértice a ser relaxado
    		
     	 	while(prox!=g.VMax()+1) {							//iterando para todos os vértices
        		visitado[prox]=1;								//marcamos o vértice como visitado

        		for(int v : g.adj(prox)) {													//para todo vértice v na lista de adjacência de prox
        	  		if (visitado[v]==0) {													//se v ainda não foi visitado, relaxamos v
        	    		if (distancia[v]>distancia[prox] + g.adj[prox].get(v)) { 			//adj[u] é uma BST em que cada nó é um vértice, valor da key v é o peso da aresta u->v
        	   	   			distancia[v]=distancia[prox] + g.adj[prox].get(v);
        	   	 	  		prev[v]=prox;
   						}	
        			} 				
   	   		 	} 
   	    		prox=next(g,s,distancia,visitado);				//escolhemos outro vértice para ser o próximo a relaxar.
 	    	}
    	}
	}

	private static int next(Digraph g, int s, int[] distancia, int[] visitado) { //função para achar o próximo vértice a ser relaxado no dijkstra
		int proxVertice = g.VMax()+1;
		int menorDist = g.M();

		for(int p =0;p<g.V();p++) {
    		if(visitado[p]==0 && distancia[p]!=g.M()) {							//o próximo vértice é aquele com menor distância até s que ainda não foi visitado
    			if (distancia[p] < menorDist) {
    				menorDist = distancia[p];
    				proxVertice=p;
    			}			
    		}
    	} 
    	return proxVertice;
	}

	public static Stack<Integer> Teste(Digraph grafo) {			//função para resolver o main usando dijkstra
		int[]caminhos = new int[grafo.membros.length]; 			//caminhos[0] é o tamanho do caminho do primeiro membro
		int[]caminhosDoMal = new int[grafo.membros.length];		//vetor de tamanhos dos caminhos do biroliro

		Stack<Integer> seguras = new Stack<Integer>();			//pilha para salvar as cidades seguras

		boolean skip=false;										//variável para controlar o fluxo

		int covil= Integer.parseInt(grafo.biroliro.inicio);		//cidade onde biroliro começa
		Dijkstra biro = new Dijkstra(grafo,covil);				//cria dijkstra com as distâncias do biroliro até os outros vértices

		int casa;												//cidade onde cada membro começa
		Dijkstra[] dijs = new Dijkstra[grafo.membros.length];	//vetor de dijkstras dos membros

		for (int h = 0;h<grafo.membros.length;h++) {
			casa = Integer.parseInt(grafo.membros[h].inicio);	//cidade onde o membro h começou
			dijs[h] = new Dijkstra(grafo,casa);					//cria um dijkstra para cada membro	
		}

		for (int c=0;c<grafo.V();c++) {					 				//para cada cidade c do grafo

			for (int h = 0;h<grafo.membros.length;h++) {				//para cada membro h
				skip=false;

				caminhos[h]=dijs[h].distancia[c];						//tamanho do caminho da cidade de h até uma cidade c
				caminhosDoMal[h]=biro.distancia[c];						//tamanho do caminho do biroliro até a mesma cidade de c
				
				if(caminhos[h]>caminhosDoMal[h]) {						//se o caminho do membro for maior, ele é interceptado por biroliro
					skip = true;
					break;												//sai do loop desse membro
				}
			}
			if(skip==true) continue;									//se skip for true, um membro foi interceptado, então passa direto para a próxima cidade
			seguras.push(c);											//se não, adiciona c nas cidades seguras
			skip=false;
		}
		return seguras;
	}

	//Implementando Bellman-Ford
	private static class Bellman {
		public int[] prevBell;									//vetores para guardar as distâncias e o vértice que vem antes no caminho mínimo
    	public int[] distanciaBell;

    	public Bellman(Digraph g, int s) {						//algoritmo bellman-ford para calcular distâncias e caminhos mínimos de s a qualquer vértice
    		prevBell = new int[g.V()];							//inicializando
    		distanciaBell = new int[g.V()];

      		for(int x = 0;x<g.V();x++) {						//inicializando
    			prevBell[x] = g.VMax()+1;  						//g.Vmax+1 é maior do que qualquer vértice
      			distanciaBell[x] = g.M();						//M é maior que qualquer caminho
      		}

      		distanciaBell[s]=0;									//distância de s até s é 0
      		
      		//Relaxamos V-1 vezes todos os arcos do grafo para calcular as distâncias/caminhos mínimos
      		for(int r=1;r<g.V()-1;r++) {						//menor caminho possível de u a v:1 aresta. maior caminho possível de u a v: V-1 arestas
      			for(int u=0;u<g.V();u++) {						//para cada vértice u							
        			for(int v : g.adj(u)) {						//para cada v na lista de adjacência de u							
        	    		if (distanciaBell[v]>distanciaBell[u] + g.adj[u].get(v)) { 			//relaxamos o arco
        	   	   			distanciaBell[v]=distanciaBell[u] + g.adj[u].get(v);
        	   	   			prevBell[v]=u;													//guardamos que antes de v vem u.
   						} 					
   	   		 		} 
 	    		}
 	    	}
    	} 
	}

	//Função para o main usando bellman-ford, mesma lógica da função teste usando dijkstra.
	public static Stack<Integer> TesteBell(Digraph grafo) {
		int[]caminhos = new int[grafo.membros.length]; 		//caminhos[0] é o tamanho do caminho do primeiro membro
		int[]caminhosDoMal = new int[grafo.membros.length];	//vetor de tamanhos dos caminhos do biroliro
		
		Stack<Integer> seguras = new Stack<Integer>();

		boolean skip=false;

		int covil= Integer.parseInt(grafo.biroliro.inicio);			//cidade onde biroliro começa
		Bellman biro = new Bellman(grafo,covil);

		int casa;
		Bellman[] bells = new Bellman[grafo.membros.length];

		for (int h = 0;h<grafo.membros.length;h++) {
			casa = Integer.parseInt(grafo.membros[h].inicio);		//cidade onde o membro h começou
			bells[h] = new Bellman(grafo,casa);
		}

		for (int c=0;c<grafo.V();c++) {					 					//para cada cidade c do grafo:

			for (int h = 0;h<grafo.membros.length;h++) {					//para cada membro
				skip=false;

				caminhos[h]=bells[h].distanciaBell[c];						//tamanho do caminho da cidade de h até uma cidade c
				caminhosDoMal[h]=biro.distanciaBell[c];						//tamanho do caminho do biroliro até a mesma cidade de c
				
				if(caminhos[h]>caminhosDoMal[h]) {							//se o caminho do membro for maior, ele é interceptado
					skip = true;
					break;													//sai do loop 
				}
			}
			if(skip==true) continue;
			seguras.push(c);
			skip=false;
		}
		return seguras;
	}

	//Imprimimos no main a saída conforme pedido
	public static void main(String[] args) {
		In input = new In(args[0]);
		Digraph grafo = new Digraph(input);

		Stack<Integer> seguras = Teste(grafo);
		int seg = seguras.size();

		System.out.println("Dijkstra:");

		if (seg==0) {
			System.out.println("INFELIZMENTE O PRECONCEITO VENCEU :(");
		}
		else {
			System.out.println("O REINO ESTA SALVO!");
			System.out.println(seg);
			for (int h = 0;h<seg ;h++ ) {
				System.out.print(seguras.pop()+" ");
			}
		}

		System.out.println();
		System.out.println();
		System.out.println("Bellman:");

		Stack<Integer> segurasBell = TesteBell(grafo);
		int segBell = segurasBell.size();

		if (segBell==0) {
			System.out.println("INFELIZMENTE O PRECONCEITO VENCEU :(");
		}
		else {
			System.out.println("O REINO ESTA SALVO!");
			System.out.println(segBell);
			for (int h = 0;h<segBell;h++ ) {
				System.out.print(segurasBell.pop()+" ");
			}
		}

		System.out.println();
	}
}


