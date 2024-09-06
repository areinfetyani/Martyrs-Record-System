
public class SortedDLL {
	DNode head, tail;
	int count = 0;

	public DNode getFirst() {
		if (count == 0)
			return null;
		else
			return head;
	}

	public DNode getLast() {
		if (count == 0)
			return null;
		else
			return tail;
	}

	public void addFirst(String location) {

		if (count == 0)
			head = tail = new DNode(location);
		else {
			DNode temp = new DNode(location);
			temp.setNext(head);
			temp.setPrev(tail);
			tail.setNext(temp);
			head.setPrev(temp);
			head = temp;

		}
		count++;
	}

	public void addLast(String location) {
		if (count == 0)
			head = tail = new DNode(location);
		else {
			DNode temp = new DNode(location);
			tail.setNext(temp);
			head.setPrev(temp);
			temp.setPrev(tail);
			temp.setNext(head);
			tail = temp;
		}
		count++;
	}

	public void add(String location, int index) {
		if (index == 0)
			addFirst(location);
		else {
			if (index == count)
				addLast(location);
			else {
				DNode temp = new DNode(location);
				DNode current = head;
				for (int i = 0; i < index - 1; i++) {
					current = current.getNext();
				}
				temp.setNext(current.getNext());
				temp.setPrev(current);
				current.setNext(temp);
				temp.getNext().setPrev(temp);
				count++;
			}
		}
	}

	public void add(String location) {

		DNode curr = head;
		DNode o = new DNode(location);
		int i = 0;
		while (curr != null && ((curr.compareTo(o) <= 0))) {

			if (curr.getLocation().equals(location)) {
				return;
			}
			i++;
			if (curr.getNext() == head) {
				add(location, i);
				return;
			}
			curr = curr.getNext();
		}
		add(location, i);
	}

	public boolean removeFirst() {
		if (count == 0)
			return false;
		else {
			if (count == 1)
				head = tail = null;
			else {
				DNode temp = head;
				head = head.getNext();
				tail.setNext(head);
				head.setPrev(tail);
				temp.setNext(null);
				temp.setPrev(null);
			}
			count--;
			return true;
		}
	}

	public boolean removeLast() {
		if (count == 0)
			return false;
		else {
			if (count == 1)
				tail = head = null;
			else {
				DNode current = head;
				for (int i = 0; i < count - 2; i++) {
					current = current.getNext();
				}
				current.getNext().setNext(null);
				current.getNext().setPrev(null);
				current.setNext(head);
				head.setPrev(current);
				tail = current;
			}
			count--;
			return true;
		}
	}

	public boolean remove(int index) {
		if (count == 0)
			return false;
		else {
			if (index == 0)
				return removeFirst();
			else {
				if (index == count - 1)
					return removeLast();
				if (index < 0 || index >= count)
					return false;
				else {
					DNode current = head;
					for (int i = 0; i < index - 1; i++)
						current = current.getNext();
					current.setNext((current.getNext()).getNext());
					count--;
					return true;
				}
			}
		}
	}

	public boolean remove(String location) {
		DNode current = null;
		if (count != 0) {
			if (location.equals(head.getLocation())) {
				return removeFirst();
			} else if (location.equals(tail.getLocation())) {
				return removeLast();
			} else {
				current = head;
				for (int i = 0; i < count - 1; i++) {
					if (location.equals(current.getLocation())) {
						return remove(i);
					}
					current = current.getNext();
				}
			}
		}
		return false;
	}

	public DNode getNode(int index) {
		if (index < count) {
			DNode curr = head;
			for (int i = 0; i < index; i++) {
				curr = curr.getNext();
			}
			return curr;
		} else {
			return null;
		}
	}

	public DNode getNode(String location) {
		DNode curr = head;
		for (int i = 0; i < count; i++) {
			if (curr.getLocation().equals(location)) {
				return curr;
			}
			curr = curr.getNext();
		}
		return null;
	}

	public void printList() {
		DNode current = head;
		if (count == 0)
			return;
		for (int i = 0; i < count; i++) {
			System.out.println(current.toString());
			current = current.getNext();
		}
	}
}
