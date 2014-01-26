import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class TriangleFileParser implements Iterable<List<TriangleNode>>,
		Iterator<List<TriangleNode>> {

	private BufferedReader br;
	private String currentLine;
	private String nextLine;
	private int count = 0;

	public TriangleFileParser(Reader reader) throws IOException {
		br = new BufferedReader(reader);
		nextLine = br.readLine();
	}

	@Override
	public boolean hasNext() {
		return nextLine != null && !"".equals(nextLine.trim());
	}

	@Override
	public List<TriangleNode> next() {
		count++;
		currentLine = nextLine;
		try {
			nextLine = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		List<TriangleNode> nodes = new LinkedList<TriangleNode>();
		StringTokenizer st = new StringTokenizer(currentLine);
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			int nodeValue = -1;
			try {
				nodeValue = Integer.parseInt(token);
			} catch (NumberFormatException e) {
				throw new InvalidTriangleException("Triangle contains invalid number [%s] on line %s", e, token, count);
			}
			if (nodeValue < 0) {
				throw new InvalidTriangleException("Triangle contains a negative number [%s] on line %s", nodeValue, count);
			}
			nodes.add(new TriangleNode(nodeValue));
		}
		return nodes;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<List<TriangleNode>> iterator() {
		return this;
	}

}
