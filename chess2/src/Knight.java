
public class Knight implements Peice{
	private Boolean top;
	private String type = "Knight";
	@Override
	public Boolean top() {
		return top;
	}

	public Knight(Boolean top) {
		super();
		this.top = top;
	}

	@Override
	public Move[] getMoves(Location l, Peice[][] board) {
		Location[] loc = new Location[8];
		loc[0]= new Location(l.x-1,l.y-2);
		loc[1]= new Location(l.x-2,l.y-1);
		loc[2]= new Location(l.x+1,l.y-2);
		loc[3]= new Location(l.x+2,l.y-1);
		loc[4]= new Location(l.x-1,l.y+2);
		loc[5]= new Location(l.x-2,l.y+1);
		loc[6]= new Location(l.x+1,l.y+2);
		loc[7]= new Location(l.x+2,l.y+1);
		int k=0;
		for(int i =0;i<8;i++){
			if(loc[i].x>=0&&loc[i].x<8 && loc[i].y>=0&&loc[i].y<8 && (board[loc[i].x][loc[i].y]==null  || board[loc[i].x][loc[i].y].top()!=top) )loc[k++]=loc[i];
		}
		
		if(k==0)return null;
		

		Move[] moves = new Move[k];
		for(int j=0;j<k;j++){
			Location[] h= {l};
			Location[] g= {loc[j]};
			moves[j]=new Move(h,g);
		}
		
		return moves;
	}

	@Override
	public String getType() {
		return type;
	}

}
