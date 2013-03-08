
public class Move {
	private Move next;
	private double value;
	private Location[] from;
	private Location[] to;
	public Move(Location[] from, Location[] to) {
		super();
		this.from = from;
		this.to = to;
	}
	public Location[] getFrom() {
		return from;
	}
	public Location[] getTo() {
		return to;
	}
	public Move getNext() {
		return next;
	}
	public void setNext(Move next) {
		this.next = next;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

}
