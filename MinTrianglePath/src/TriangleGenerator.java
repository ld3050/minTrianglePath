import java.util.Random;


public class TriangleGenerator {

	public static void main(String [] args) {
		if (args.length == 0) {
			printUsage();
		}
		int depth = 0;
		try {
			depth = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			printUsage();
		}
		Random rand = new Random();
		for (int i=1; i<=depth; i++) {
			for (int j=0; j<i; j++) {
				System.out.print((rand.nextInt(29)+1) + " ");
			}
			System.out.println("");
		}
	}

	private static void printUsage() {
		System.out.println("Usage: TriangleGenerator <number>");
		System.out.println("This print out a triangle with <number> depth");
	}
}
