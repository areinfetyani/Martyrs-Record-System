
public class DNode implements Comparable<DNode> {
	private SortedLL list = new SortedLL();
	private String location;
	private DNode prev, next;
	
	public DNode(String location) {
		this.location = location;
	}

	public SortedLL getList() {
		return list;
	}

	public void setList(SortedLL list,Martyr m) {
		list.add(m);
		this.list = list;
	}
	public void setList(SortedLL list) {
		this.list = list;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public DNode getPrev() {
		return prev;
	}

	public void setPrev(DNode prev) {
		this.prev = prev;
	}

	public DNode getNext() {
		return next;
	}

	public void setNext(DNode next) {
		this.next = next;
	}

	@Override
	public int compareTo(DNode o) {
		return this.getLocation().compareTo(o.getLocation());
	}

	@Override
	public String toString() {
		return "[location=" + location + "]";
	}
	
	
	
}
