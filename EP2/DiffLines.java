public class DiffLines {
	public static void main(String[] args) {
		//Ler os argumentos passados no terminal
        In in0 = new In(args[0]);
        In in1 = new In(args[1]);
        String arqx = in0.readLine();
        String arqy = in1.readLine();

        //Criar vetores com as palavras de cada argumento
        String[] x = arqx.split("\\s+");
        String[] y = arqy.split("\\s+");

        //Chamando a função lcs
        String[] lines = lcs(x, y);

        //Imprimir
        StdOut.println("First line:");
        StdOut.println(lines[0]);
        StdOut.println("Second line:");
        StdOut.println(lines[1]);
	}

	// Computar LCS (maior subsequência comum) e retornar as linhas
    public static String[] lcs(String[] x, String[] y) {
    	
    	//Número de palavras de cada arquivo
        int m = x.length;
        int n = y.length;

        // O array opt[][] vai guardar o tamanho da maior subsequência em comum (LCS) entre um determindado sufixo dos arquivos. 
        // opt[i][j] = tamanho da LCS de x[i..m] e y[j..n]
        int[][] opt = new int[m+1][n+1];

        //Laço for percorre os vetores
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (x[i].equals(y[j])){                 
                    opt[i][j] = opt[i+1][j+1] + 1;          //se as palavras na posição i e j forem iguais, então a LCS a partir dessa posição é a LCS a partir da próxima posição + 1
                }
                else {
                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);     //se forem diferentes, computamos a LCS a partir dessa posição de x e a partir da próxima posição de y e vice versa, e a LCS será a maior delas
                }
            }
        }

        // Imprimir a LCS
        //Criando Arrays que vão guardar as palavras de cada frase
        String[] first = new String[m];
        String[] second = new String[n];

        int i = 0;
        int j = 0;

        //Preenchendo os arrays, usando o array que preenchemos recursivamente opt[][] para comparar os elementos
        while (i < m && j < n) {

            if (x[i].equals(y[j])) {        
                
                first[i] = x[i];                           //se os elementos são iguais, apenas os copiamos os novos arrays  
                second[j] = y[j];
                i++;
                j++;
            }
            
            else if (opt[i+1][j] >= opt[i][j+1]) {		   //se não, 

            	first[i] = "*" + x[i] + "*";               //marcamos os elementos diferentes com * *
            	i++;
            }

            else  {

            	second[j] = "*" + y[j] + "*";
            	j++;
            	
            }                              
        }

       	//Criando strings a partir dos arrays criados
        String firstline = String.join(" ", first);   
        String secondline = String.join(" ", second);

        //Retornaremos um array com ambas as strings
        String[] lines = new String[2];
        lines[0] = firstline;
        lines[1] = secondline;
        return lines;
    }
 
}