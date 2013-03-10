import java.util.LinkedList;


public class Castle implements Peice {
	private Boolean top;
	private double value=525;
	public Castle(Boolean top) {
		super();
		this.top = top;
	}
	public double getValue() {
		return value;
	}
	public Peice clone(){
		
		return this;
	}
	private String type = "Castle";
	@Override
	public Boolean top() {
		return top;
	}

	@Override
	public Move[] getMoves(Location l, Peice[][] board) {
		LinkedList<Location> loc= new LinkedList<Location>();
		int x=l.x;
		while(x-1>=0  && ( board[x-1][l.y] ==null)){
			loc.add(new Location(--x,l.y));
		}
		if(x-1>=0  && ( board[x-1][l.y].top()!=top))loc.add(new Location(x-1,l.y));
		
		 x=l.x;
		while(x+1<8  && ( board[x+1][l.y] ==null)){
			loc.add(new Location(++x,l.y));
		}
		if(x+1<8  && ( board[x+1][l.y].top()!=top))loc.add(new Location(x+1,l.y));
		
		
		int y=l.y;
		while(y-1>=0  && ( board[l.x][y-1] ==null)){
			loc.add(new Location(l.x,--y));
		}
		if(y-1>=0  && (  board[l.x][y-1].top()!=top))loc.add(new Location(l.x,y-1));
		
		 y=l.y;
		while(y+1<8  && ( board[l.x][y+1] ==null)){
			loc.add(new Location(l.x,++y));
		}
		if(y+1<8  && (  board[l.x][y+1].top()!=top))loc.add(new Location(l.x,y+1));
		
		Move[] kingMoves = new Move[1];
		int k=0;
		
		if(top && l.y==0 && board[4][0]!=null && board[4][0].top() &&  board[4][0].getType()=="King" && l.x==0 &&board[1][0]!=null&& board[3][0]==null && board[2][0]==null){
			Location[] from= {l,new Location(4,0)};
			Location[] to= {new Location(3,0),new Location(2,0)};
			kingMoves[k++]=new Move(from,to);
			
		}
		if(!top && l.y==7 && board[4][7]!=null && !board[4][7].top() &&  board[4][7].getType()=="King" &&board[1][0]!=null && l.x==0 && board[3][7]==null && board[2][7]==null){
			Location[] from= {l,new Location(4,7)};
			Location[] to= {new Location(3,7),new Location(2,7)};
			kingMoves[k++]=new Move(from,to);
		
		}
		if (loc.size()+k==0)return null;
		
		int f=loc.size();
		Move[] moves = new Move[loc.size()+k];
		for(int j=0;j<f;j++){
			Location[] h= {l};
			Location[] g= {loc.removeFirst()};
			moves[j]=new Move(h,g);
		}
		for(int j=0;j<k;j++){
			moves[f+j]=kingMoves[j];
		}
		
		
		
		
		
		
		return moves;
	}

	@Override
	public String getType() {
		return type;
	}

}
