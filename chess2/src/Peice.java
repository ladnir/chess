
public interface Peice {

	public Boolean top();
	public Move[] getMoves(Location l,Peice[][] board);
	public String getType();
	
}
