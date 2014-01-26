import java.util.LinkedList;
import java.util.List;


public class TriangleNode implements Comparable<TriangleNode>{

	private TriangleNode parent;
	private int value;
	private int pathCostTotal;
	
	public TriangleNode(int value) {
		this.value = value;
		this.pathCostTotal = value;
	}
	
	public TriangleNode getParent() {
		return parent;
	}
	
	public void setParent(TriangleNode parent) {
		this.parent = parent;
		this.pathCostTotal = parent.getPathCostTotal() + value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getPathCostTotal() {
		return pathCostTotal;
	}

	@Override
	public String toString() {
		return value + "";
	}
	
	/**
	 * Prints out the nodes path in relation to all of its parents.
	 * @return a String in the format "n + n + n + n = pathCostTotal"
	 */
	public String getPathAsString() {
		TriangleNode parent = this;
		List<TriangleNode> pathList = new LinkedList<TriangleNode>();
		while (parent != null) {
			pathList.add(0, parent); // build the list backwards
			parent = parent.getParent();
		}
		StringBuilder sb = new StringBuilder();
		for (TriangleNode node : pathList) {
			sb.append(node.getValue());
			if (node != this) {
				sb.append(" + ");
			}
		}
		sb.append(" = ").append(this.pathCostTotal);
		return sb.toString();
	}

	@Override
	public int compareTo(TriangleNode otherNode) {
		return new Integer(pathCostTotal).compareTo(otherNode.pathCostTotal);
	}
	
}
