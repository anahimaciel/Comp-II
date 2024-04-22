public class Merge {

	//Criamos o vetor auxiliar como variável estática privada (habitual usar variável privada para que apenas os métodos dessa classe possam acessá-la) 
	private static String[] vec;

	//Atualizar o vetor a e imprimi-lo
	public static void main(String[] args) {

		String[] a = StdIn.readAllStrings();
		
		double start = System.currentTimeMillis();
		sort(a);
		double end = System.currentTimeMillis();
		
		//Parte desnecessária
		/*
		for (int i = 0; i < vec.length; i++) {
			a[i] = vec[i];
			System.out.print(a[i] + " ");
		}
		*/

		//Imprimir o vetor a
		//for (int i = 0; i<vec.length; i++ ) {
		//	System.out.println(a[i]);
		//}

		//System.out.println();
		System.out.println(Math.round(end-start)+ " milliseconds");
	}

	//Juntar dois vetores ordenados em um vetor ordenado
	public static void merge(String [] a, int lo, int mid, int hi) {

		int k = lo;
		int i = lo;
		int j = mid;

		//	Enquanto ambos forem menores que os limites, substituimos no novo vetor o elemento menor a cada rodada e aumentamos o index do subvetor escolhido.
		while (i < mid && j < hi) {
			int comp = a[i].compareTo(a[j]);
			if (comp >= 0) {
					vec[k] = a[j];
					j++;
				} 
			else {
					vec[k] = a[i];
					i++;
				}
			k++;
		}
		//Se ainda permanece algum elemento, adicionamos eles
		while (i < mid && j>=mid) {
            vec[k] = a[i];
            i++;
            k++;
        }
        while (j < hi && i>=mid) {
            vec[k] = a[j];
            j++;
            k++;
        }

        //Atualizar o vetor a (parte esquecida no programa original)
        for (int f = lo; f < hi; f++) {
			a[f] = vec[f];
		}
	}

	//Para ser mais simples chamar o método
	public static void sort(String [] a) {
		//Inicializamos o vetor vec neste método, já que o outro sort roda várias vezes
		vec = new String[a.length];
		sort(a, 0, a.length);
	}

	public static void sort(String[] a, int lo, int hi) {
		//Condição para sair da recursão: o subvetor tem apenas um elemento. Logo, se lo<hi-1 ele entra na recursão, ou seja, se o vetor tem mais de um elemento. 
		//criamos a variável mid para dividir o vetor no meio	
		if (lo < hi-1) {
		int mid = (lo+(hi-lo)/2);

		//recursão
		sort(a, lo, mid);
		sort(a, mid, hi);

		//juntar os subvetores pelo método merge
		merge(a,lo,mid,hi);			
		}
	}


}