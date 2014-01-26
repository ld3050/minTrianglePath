import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import junit.framework.TestCase;

import org.junit.Test;

public class TriangleProcessorTest extends TestCase {

	@Test
	public void testCalculateMinimumPath() throws FileNotFoundException,
			IOException {
		assertPathForFile("7 + 6 + 3 + 2 = 18", "testfile.txt");
	}

	@Test
	public void testCalculateMinimumPathTriangle10()
			throws FileNotFoundException, IOException {
		assertPathForFile("10 + 1 + 1 + 2 + 1 + 3 + 2 + 8 + 5 + 1 = 34",
				"triangle10.txt");
	}
	
	@Test
	public void testNotEnoughTriangle()
			throws FileNotFoundException, IOException {
		try {
			assertPathForFile("", "notenoughtriangle.txt");
			fail();
		} catch (InvalidTriangleException e) {
			assertTrue(e.getMessage().indexOf("not enough") > -1);
			assertTrue(e.getMessage().indexOf("line 3") > -1);
		}
	}
	
	@Test
	public void testTooManyTriangle()
			throws FileNotFoundException, IOException {
		try {
			assertPathForFile("", "toomanytriangle.txt");
			fail();
		} catch (InvalidTriangleException e) {
			assertTrue(e.getMessage().indexOf("too many") > -1);
			assertTrue(e.getMessage().indexOf("line 3") > -1);
		}
	}

	private void assertPathForFile(String expectedPath, String fileName)
			throws IOException, FileNotFoundException {
		String path = getFullPath(fileName);
		TriangleFileParser parser = new TriangleFileParser(new FileReader(path));

		TrianglePathProcessor processor = new TrianglePathProcessor(parser);

		processor.calculateMinimumPath();
		for (TriangleNode node : processor.getMinimumPathsSoFar()) {
			assertEquals(expectedPath, node.getPathAsString());
		}
	}

	private String getFullPath(String fileName) {
		URL resource = getClass().getResource("TriangleProcessorTest.class");
		File file = new File(resource.getFile());
		String path = file.getParent();
		return path + "/" + fileName;
	}

}
