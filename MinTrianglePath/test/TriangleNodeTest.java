import org.junit.Test;

import junit.framework.TestCase;


public class TriangleNodeTest extends TestCase {

	@Test
	public void testPathAsString() {
		TriangleNode t0 = new TriangleNode(7);
		TriangleNode t1 = new TriangleNode(12);
		TriangleNode t2 = new TriangleNode(2);
		TriangleNode t3 = new TriangleNode(34);
		TriangleNode t4 = new TriangleNode(1);
		TriangleNode t5 = new TriangleNode(2);
		
		t1.setParent(t0);
		t2.setParent(t1);
		t3.setParent(t2);
		t4.setParent(t3);
		t5.setParent(t4);
		
		assertEquals("7 + 12 + 2 + 34 + 1 + 2 = 58", t5.getPathAsString());
	}
}
