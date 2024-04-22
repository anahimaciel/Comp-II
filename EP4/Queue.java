public class Queue {

	private String [] vetor;
	private int N = 0;		//índice da primeira posição vazia do vetor
	private int Z = 0;		//índice do primeiro elemento atualmente na fila

	private String max;
	private String min;

	//cria uma filha de strings com capacidade para armazenar cap strings
	public Queue (int cap) {
		vetor = new String[cap];
	}

	//adiciona s na fila
	public void enqueue(String s) {
		//se a fila está vazia, então não há ainda nenhum max/min então s é max e min
		if (N-Z==0) {
			max = s;
			min = s;
		}
		//se s for maior/menor que o máximo/mínimo atual, atualiza max/min
		else {
			if (s.compareToIgnoreCase(max) > 0) {
				max = s;
			}
			if (s.compareToIgnoreCase(min) < 0) {
				min = s;
			}
		}
		vetor[N++] = s;
	}

	//remove e retorna o primeiro elemento atualmente na fila
	public String dequeue() {
		if (N-Z > 0) {
			//se o primeiro elemento (vetor[Z]) for o atual máximo/mínimo, muda o máximo/mínimo
			if (max.equals(vetor[Z])) {
				max = this.newMax();
			}
			if (min.equals(vetor[Z])) {
				min = this.newMin();
			}	
		}	
		return vetor[Z++];
	}

	private String newMax() {
		String newMax = vetor[Z+1];
		for (int i=Z+2; i<N; i++ ) {
			if (vetor[i].compareToIgnoreCase(newMax)>0) {
				newMax = vetor[i];
			} 
		}
		return newMax;
	}

	private String newMin() {
		String newMin = vetor[Z+1];
		for (int i=Z+2; i<N; i++ ) {
			if (vetor[i].compareToIgnoreCase(newMin)<0) {
				newMin = vetor[i];
			} 
		}
		return newMin;
	}

	//tamanho da fila = N-Z. Retorna True se o tamanho = 0.
	public boolean isEmpty() {
		return N-Z == 0;
	}

	//retorna a quantidade de elementos da fila
	public int size() {
		return N-Z;
	}

	//retorna o primeiro elemento atualmente na fila, se houver algum
	public String peek() {
		if (N-Z > 0) {
			return vetor[Z];
		}
		return "Fila vazia";
	}

	//retorna maior string (ordem lexicografica)
	public String maior() {
		if (N-Z > 0) {							//se nenhum elemento foi adicionado na fila, então não há max/min
			return max;				
		}
		return "Inexistente";
	}

	//retorna menor string (ordem lexicografica)
	public String menor() {
		if (N-Z > 0) {
			return min;
		}
		return "Inexistente";
	}

	public static void main(String[] args) {
		int cap = Integer.parseInt(args [0]);		//capacidade da fila
		String[] orig = new String[cap];			//vetor que guarda a ordem original
		Queue ordem = new Queue(cap);				//cria nova fila para ordem final
		int j = 0; 									//contador de elementos do vetor
		
		//faz um vetor com as linhas do arquivo 
		while(!StdIn.isEmpty()) {
			String line = StdIn.readLine();
			orig[j]=line;
			j++;									//conta os elementos da fila
		}

		int[] geral = new int[j];					//vetor que vai contar quantos passaram por cada não idoso. 
		//ou seja, geral[i] = número de idosos que passaram na frente da pessoa de posição i no vetor original
		
		//Laço for vai percorrer todo o vetor original
		for (int i=0;i<j ;i++ ) {
			if (orig[i].endsWith("idoso")) {  			//se achar um idoso ainda não "enfilado", adiciona-o à fila
				ordem.enqueue(orig[i]);
				orig[i] = "enfilado";
			}

			if (orig[i].endsWith("geral")) {			//(1/3)se achar um não idoso ainda não "enfilado",
				int[] indice = new int[2];				//vetor que guarda os índices dos idosos que passaram na frente

				for (int k=i+1;k<j ;k++ ) {				//(2/3)passa no máximo dois idosos na frente,
					if (geral[i]>1) {
						break;
					}
					if (orig[k].endsWith("idoso")) {
						ordem.enqueue(orig[k]);
						orig[k]="enfilado";
						indice[geral[i]] = k;			//guarda o índice do idoso que passou
						geral[i]++;
					}
					if (geral[i]>1) {
						break;
					}
				}

				ordem.enqueue(orig[i]);					//(3/3)e bota o não idoso na fila.
				orig[i] = "enfilado";

				for (int a=i;a<indice[0];a++) {			//quem estiver entre i e o primeiro idoso foi passado por dois idosos
					geral[a]+=2;		
				}

				for (int a=indice[0]+1;a<indice[1];a++) {	//quem estiver entre os dois foi passadp por um 
					geral[a]++;
				}
			}
		}
		
		for (int i = 0;i<j ;i++ ) {
			System.out.print(ordem.dequeue().split(" ")[0] + " ");
		}
	}
}