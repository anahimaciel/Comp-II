/***********************************************************************
 * $ cat lorem.txt
 * Fusce suscipit, wisi nec facilisis facilisis, est dui fermentum leo, quis tempor ligula erat quis odio.
 * $ java-introcs Split < lorem.txt
 * 0: "Fusce"
 * 1: "suscipit,"
 * 2: "wisi"
 * 3: "nec"
 * 4: "facilisis"
 * 5: "facilisis,"
 * 6: "est"
 * 7: "dui"
 * 8: "fermentum"
 * 9: "leo,"
 * 10: "quis"
 * 11: "tempor"
 * 12: "ligula"
 * 13: "erat"
 * 14: "quis"
 * 15: "odio."
 *
***********************************************************************/

public class Split {

    public static void main(String[] args) {
	String l = StdIn.readLine();
	String[] s = l.split("\\s+");
	for (int i = 0; i < s.length; i++)
	    StdOut.println(i + ": " + "\"" + s[i] + "\"");
    }

}
