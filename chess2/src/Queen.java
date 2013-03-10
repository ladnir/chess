import java.util.LinkedList;


public class Queen implements Peice {
	private Boolean top;
	private double value=1000;
	private String type = "Queen";
	public Queen(Boolean top) {
		super();
		this.top = top;
	}
	@Override
	public Boolean top() {
		return top;
	}
	
	public double getValue() {
		return value;
	}
	public Peice clone(){
		
		return this;
	}
	@Override
	public Move[] getMoves(Location l, Peice[][] board) {
		LinkedList<Location> loc= new LinkedList<Location>();
		int x=l.x;
		int y=l.y;
		while(x-1>=0 && y-1>=0  && ( board[x-1][y-1] ==null)){
			loc.add(new Location(--x,--y));
		}
		if(x-1>=0  && y-1>=0 && ( board[x-1][y-1].top()!=top))loc.add(new Location(x-1,y-1));
		
		x=l.x;
		y=l.y;
		while(x+1<8 && y-1>=0  && ( board[x+1][y-1] ==null)){
			loc.add(new Location(++x,--y));
		}
		if(x+1<8  && y-1>=0 && ( board[x+1][y-1].top()!=top))loc.add(new Location(x+1,y-1));
		
		x=l.x;
		y=l.y;
		while(x-1>=0 && y+1<8  && ( board[x-1][y+1] ==null)){
			loc.add(new Location(--x,++y)); 
		}
		if(x-1>=0  && y+1<8 && ( board[x-1][y+1].top()!=top))loc.add(new Location(x-1,y+1));
		
		x=l.x;
		y=l.y;
		while(x+1<8 && y+1<8  && ( board[x+1][y+1] ==null)){
			loc.add(new Location(++x,++y));
		}
		if(x+1<8  && y+1<8 && ( board[x+1][y+1].top()!=top))loc.add(new Location(x+1,y+1));
		
		x=l.x;
		while(x-1>=0  && ( board[x-1][l.y] ==null)){
			loc.add(new Location(--x,l.y));
		}
		if(x-1>=0  && ( board[x-1][l.y].top()!=top))loc.add(new Location(x-1,l.y));
		
		x=l.x;
		while(x+1<8  && ( board[x+1][l.y] ==null)){
			loc.add(new Location(++x,l.y));
		}
		if(x+1<8  && ( board[x+1][l.y].top()!=top))loc.add(new Location(x+1,l.y));
		
		
		y=l.y;
		while(y-1>=0  && ( board[l.x][y-1] ==null)){
			loc.add(new Location(l.x,--y));
		}
		if(y-1>=0  && (  board[l.x][y-1].top()!=top))loc.add(new Location(l.x,y-1));
		
		y=l.y;
		while(y+1<8  && ( board[l.x][y+1] ==null)){
			loc.add(new Location(l.x,++y));
		}
		if(y+1<8  && (  board[l.x][y+1].top()!=top))loc.add(new Location(l.x,y+1));
		
		if (loc.size()==0)return null;

		int f=loc.size();
		//System.out.println("queen size="+f);
		Move[] moves = new Move[loc.size()];
		for(int j=0;j<f;j++){
			Location[] h= {l};
			Location[] g= {loc.removeFirst()};
			moves[j]=new Move(h,g);
		}
		
		return moves;
	}

	@Override
	public String getType() {
		return type;
	}

}
