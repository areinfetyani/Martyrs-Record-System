
public class Node {
	private Martyr element;
	private Node next;

	public Node(Martyr element) {
		this.element = element;
	}

	public void setElement(Martyr element) {
		this.element = element;
	}

	public Martyr getElement() {
		return element;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}


	@Override
	public String toString() {
		return element.toString();
	}

}