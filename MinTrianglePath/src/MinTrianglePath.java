import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * This program solves the minimum path from the top to bottom of a triangle of
 * integers. eg.
 * 
 * <pre>
 *          7
 *       6     3
 *    3     8     5
 * 11    2     10    9
 * </pre>
 * 
 * The minimum path for this triangle is 7+3+8+2 = 18
 * 
 * The program is run from the command line:
 * 
 * java MinTrianglePath < triangleFile.txt
 * 
 * triangle file is read from stdin and the answer is output to stdout
 */
public class MinTrianglePath {

	public static void main(String[] args) {
		TriangleFileParser parser = null;
		InputStreamReader stdin = new InputStreamReader(System.in);
		OutputStreamWriter stdout = new OutputStreamWriter(System.out);
		try {
			parser = new TriangleFileParser(stdin);
			TrianglePathProcessor processor = new TrianglePathProcessor(parser);
			processor.calculateMinimumPath();
			for (TriangleNode node : processor.getMinimumPathsSoFar()) {
				stdout.write("Minimal path is: " + node.getPathAsString() + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeQuietly(stdin);
			closeQuietly(stdout);
		}
	}
	
	public static void closeQuietly(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException e) {
		}
	}
}
