public class Stack {

	private String[] vetor;
	private int N = 0;      //guarda primeira posição vazia do vetor
	
	private int T = 0;		//guarda a posição do maior elemento atual no vetor max
	private int P = 0;		//guarda a posição do maior elemento atual no vetor min
	private String[] max;
	private String[] min;

	//cria uma pilha de strings com capacidade para cap strings
	public Stack(int cap) {
		vetor = new String[cap];
		max = new String[cap];	//vetor que guarda os elementos máximos
		min = new String[cap];	//vetor que guarda os elementos mínimos
	}

	//adiciona s na pilha
	public void push (String s) {
		//se não há ainda nenhum max/min, s é max e min. o primeiro max/min tem T=1/P=1
		if (T==0) {
			max[++T] = s;
		}
		if (P==0) {
			min[++P] = s;
		}
		//se s for maior/menor que o máximo/mínimo atual, atualiza o valor de T/P e adiciona s ao vetor max/min
		if (T>0) {
			if (s.compareToIgnoreCase(max[T]) > 0) {
				max[++T] = s;
			}
		}		
		if (P>0) {
			if (s.compareToIgnoreCase(min[P]) < 0) {
				min[++P] = s;
			}
		}
		//vetor[N] = s;
		//N++;
		//forma sucinta de escrever as duas linhas anteriores
		vetor[N++] = s;
	}

	//remove e retorna o último elemento colocado na pilha 
	public String pop() {
		//se o último elemento (vetor[N-1]) for o atual máximo/mínimo, atualiza o valor de T/P para o atual máximo/mínimo ser o anterior
		if (T>0) {
			if (max[T].equals(vetor[N-1])) {
				T--;
			}
		}
		if (P>0) {
			if (min[P].equals(vetor[N-1])) {
				P--;
			}
		}
		//N--;
		//return vetor[N];
		//forma sucinta de escrever as duas linhas anteriores
		return vetor[--N];
	}

	//retorna True se a pilha estiver vazia
	public boolean isEmpty() {
		return N==0;
	}

	//retorna a quantidade de elementos
	public int size() {
		return N;
	}

	//retorna o último elemento adicionado à pilha, se algum tiver sido adicionado
	public String peek() {
		if (N>0) {
			return vetor[N-1];
		}
		return "Pilha vazia"; 
	}

	
	//retorna o maior string (ordem lexicográfica)
	public String maior() {
		if (T>0) {							//se T ou P = 0 então nenhum elemento foi adicionado na pilha, então não há max/min
			return max[T];				
		}
		return "Inexistente";
	}

	//retorna o menor string (ordem lexicográfica)
	public String menor() {
		if (P>0) {
			return min[P];
		}
		return "Inexistente";
	}
	

	//teste
	public static void main(String[] args) {
		int cap = Integer.parseInt(args [0]);		//capacidade da pilha
		Stack stack = new Stack (cap);				//cria nova pilha
		Stack stackAux = new Stack (cap);			//cria pilha auxiliar
		int j = 0;									//contador de elementos da pilha

		//faz uma pilha com as palavras do arquivo que começam com "a"
		while(!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if (word.toUpperCase().startsWith("A")) {
				stack.push(word);
				j++;								//conta os elementos da pilha
			}
		}

		//Se a pilha  tiver pelo menos um elemento
		if (stack.size() > 0) { 
			String maior = stack.maior();				//guarda o maior elemento da pilha
			String menor = stack.menor();				//e o menor
			
			for (int i = 0; i<j ; i++ ) {				//vai tirando os elementos da pilha um por um, imprime cada um e guarda na pilha auxiliar
				String a = stack.pop();
				stackAux.push(a);
				System.out.println(a);
			}
			
			System.out.println();

			for (int i=0; i < j ; i++) {
				System.out.println(stackAux.pop());		//imprime os elementos da pilha auxiliar (contrário da pilha original)
			}

			System.out.println();
			System.out.println("Maior: " + maior);		//imprime maior elemento	
			System.out.println();		
			System.out.println("Menor: " + menor);		//e o menor
		}
	}
}