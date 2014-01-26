import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TrianglePathProcessor {

	TriangleFileParser parser;
	private List<TriangleNode> minimumPathsSoFar;

	public TrianglePathProcessor(TriangleFileParser parser) {
		this.parser = parser;
	}

	/**
	 * Iterate through all the lines in {@link TriangleFileParser} and match
	 * each line of {@link TriangleNode}s with its parents in the line above.
	 * 
	 * Each node is responsible for tracking the minimum path it took to reach
	 * it.
	 */
	public void calculateMinimumPath() throws InvalidTriangleException {
		List<TriangleNode> parentList = null;
		int depth = 1;
		for (List<TriangleNode> nodeList : parser) {
			if (nodeList.size() != depth) {
				throw new InvalidTriangleException(
						"Triangle is in an invalid state on line %s. There are %s values in the following row: %s",
						depth, (nodeList.size() > depth) ? "too many"
								: "not enough", nodeList);
			}
			if (parentList != null) {
				matchTriangleNodeToParent(nodeList, parentList);
			}
			parentList = nodeList;
			depth++;
		}
	}

	/**
	 * The strategy for matching a child to a parent is to consider the left and
	 * right parent candidates. Each parent will have a pathCostTotal. The child
	 * will be added to the parent with the lowest pathCostTotal.
	 * 
	 * The left-most and right-most nodes are exceptional in that they will only
	 * have one parent candidate.
	 * 
	 * @param nodeList
	 * @param parentList
	 */
	private void matchTriangleNodeToParent(List<TriangleNode> nodeList,
			List<TriangleNode> parentList) throws InvalidTriangleException {
		// reset the minimumPathSoFar for the new line
		minimumPathsSoFar = new LinkedList<TriangleNode>();
		Iterator<TriangleNode> parentIt = parentList.iterator();
		TriangleNode leftParent = null;
		TriangleNode rightParent = parentIt.next();
		for (TriangleNode childNode : nodeList) {
			if (leftParent == null && rightParent == null) {
				throw new InvalidTriangleException(
						"Triangle is in an invalid state. The following row is missing values: %s",
						nodeList);
			} else if (leftParent == null) {
				childNode.setParent(rightParent);
			} else if (rightParent == null) {
				childNode.setParent(leftParent);
			} else if (leftParent.compareTo(rightParent) <= 0) {
				childNode.setParent(leftParent);
			} else {
				childNode.setParent(rightParent);
			}

			populateMinimumSoFar(childNode);

			leftParent = rightParent;
			rightParent = parentIt.hasNext() ? parentIt.next() : null;
		}
	}

	/**
	 * Populate the minimumSoFarList with TriangleNodes that have the lowest
	 * totalPathCost.
	 * 
	 * @param childNode
	 */
	private void populateMinimumSoFar(TriangleNode childNode) {
		if (minimumPathsSoFar.isEmpty()) {
			minimumPathsSoFar.add(childNode);
		} else {
			int comparison = childNode.compareTo(minimumPathsSoFar.get(0));
			if (comparison == 0) {
				minimumPathsSoFar.add(childNode);
			} else if (comparison < 0) {
				minimumPathsSoFar.clear();
				minimumPathsSoFar.add(childNode);
			}
		}
	}

	/**
	 * Track a list of {@link TriangleNode}s that have the lowest totalPathCost.
	 * Use a list to track nodes with equal totalPathCost.
	 * 
	 * @return
	 */
	public List<TriangleNode> getMinimumPathsSoFar() {
		return minimumPathsSoFar;
	}

}
