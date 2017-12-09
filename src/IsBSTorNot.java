
public class IsBSTorNot {

	public static void main(String[] args) {
		Node node = new Node(10);
		Node node1 = new Node(5);
		node.left = node1;

		Node node2 = new Node(15);
		node.right = node2;

		Node node3 = new Node(3);
		node2.left = node3;

		Node node4 = new Node(7);
		node2.right = node4;

		Node node5 = new Node(12);
		node2.left = node5;

		Node node6 = new Node(17);
		node2.right = node6;

		System.out.println(isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	public static boolean isBST(Node node, int min, int max) {
		if(node == null) {
			return true;
		}

		if(node.value < min || node.value > max) {
			return false;
		}

		return isBST(node.left, min, node.value) && isBST(node.right, node.value, max);
	}
}

class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}	
}
