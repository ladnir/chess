
public class Move {
	private Move next;
	
	private double value;
	private Location[] from;
	private Location[] to;
	private Move pv;
	
	public Move(Location[] from, Location[] to) {
		super();
		this.from = from;
		this.to = to;
	}
	public Move getPv() {
		return pv;
	}
	public void setPv(Move pv) {
		this.pv = pv;
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
