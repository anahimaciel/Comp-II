public class BetterGenerator {
	public static String randomString(int L, String alpha) {
		char[] a = new char[L];
		for (int i = 0; i<L; i++) {
			int t = StdRandom.uniform(alpha.length());
			a[i] = alpha.charAt(t);
		}
		return new String(a);
	}
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int L = 10;
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i< N; i++) {
			StdOut.println(randomString(L,alpha));
		}
	}
}