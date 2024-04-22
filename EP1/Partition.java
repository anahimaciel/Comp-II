
public class Partition{

    //Criamos um vetor que vai guardar as computações do método p, de forma que ele não tenha que fazer a mesma computação duas vezes, agilizando o programa.
    private static int len = 500;
    private static long[][] vec = new long [len+1][len+1];

    public static long p(int N) {
        return(p(N,N));
    }

    //Método que conta as partições de inteiro N com todas as partes menores ou iguais a M.
    public static long p(int N, int M) {  
        if (N < 0) return 0;                        //o inteiro N deve ser positivo
        if (M == 0) return 0;                       //não há parte com maior inteiro 0
        if (N == 0) return 1;                       //p(0)=1 por definição
        if(vec[N][M]>0) return vec[N][M];           //se a computação já foi feita, retorna o dado salvo no vetor
        vec[N][M]= p(N, M - 1) + p(N - M, M);       //propriedade de partições: uma partição de N com maior parte igual ou menor a M é formada por dois conjuntos, um com todas as partições cuja maior parte é igual a M e outro com as partições cuja maior parte é menor que M. O primeiro conjunto tem a mesma quantidade de partes de p(N-M, M) pois a primeira partição será M + (N-M), então as outras serão M mais alguma partição de N-M. Já o segundo tem por definição p(N,M-1) partições.
        return vec[N][M];                       
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        System.out.println(p(N));
    }
}
