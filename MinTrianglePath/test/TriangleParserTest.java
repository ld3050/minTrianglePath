import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;


public class TriangleParserTest extends TestCase {
	
	@Test
	public void testReader() throws FileNotFoundException, IOException {
		String path = getFullPath("testfile.txt");
		TriangleFileParser parser = new TriangleFileParser(new FileReader(path));
		List<TriangleNode> line0 = parser.next();
		assertEquals(1, line0.size());
		assertEquals(7, line0.get(0).getValue());
		
		List<TriangleNode> line1 = parser.next();
		assertEquals(2, line1.size());
		assertEquals(3, line1.get(1).getValue());
		
		List<TriangleNode> line2 = parser.next();
		assertEquals(3, line2.size());
		assertEquals(5, line2.get(2).getValue());
		
		List<TriangleNode> line3 = parser.next();
		assertEquals(4, line3.size());
		assertEquals(9, line3.get(3).getValue());
	}
	
	@Test
	public void testReaderWithInvalidValues() throws FileNotFoundException, IOException {
		String path = getFullPath("badnumbertriangle.txt");
		TriangleFileParser parser = new TriangleFileParser(new FileReader(path));
		try {
			parser.next();
			parser.next();
			parser.next();
			parser.next();
			fail();
		} catch (InvalidTriangleException e) {
			assertEquals("Triangle contains invalid number [2a] on line 4", e.getMessage());
		}
	}

	private String getFullPath(String fileName) {
		URL resource = getClass().getResource("TriangleParserTest.class");
		File file = new File(resource.getFile());
		String path = file.getParent();
		return path + "/" + fileName;
	}

}
