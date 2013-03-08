
public class King implements Peice{
	private Boolean top;
	private String type = "King";
	private Boolean castled = false;
	@Override
	public Boolean top() {
		return top;
	}

	@Override
	public Move[] getMoves(Location l, Peice[][] board) {
		Location[] loc = new Location[10];
		int left,right,top,bottom,k = 0;
		if(l.x-1<0)left=l.x;
		else left=l.x-1;
		if(l.x+1>7)right=l.x;
		else right=l.x+1;
		
		if(l.y-1<0)top=l.y;
		else top=l.y-1;
		if(l.y+1>7)bottom=l.y;
		else bottom=l.y+1;
		
		
		for(int i=left;i<=right;i++){
			for(int j=top;j<=bottom;j++){
				if(board[i][j]==null  || board[i][j].top() != this.top)loc[k++]=new Location(i,j);
			}	
		}
		
		if(castled==false){
			if(this.top && l.x==3 && l.y==0 ){
				if(board[0][0]!=null&&board[0][0].top()&&board[0][0].getType()=="Castle"&&board[3][0]==null){
					loc[k++]=new Location(2,0);
				}
				
			}
			if(!this.top && l.x==3 && l.y==7){
				
			}
			
		}
		Move[] castling = new Move[2];
		int c=0;
		
		if(this.top && l.y==0 &&l.x==4){
			System.out.println("check1");
			if(board[0][0]!=null && board[0][0].getType()=="Castle" && board[0][0].top() &&board[2][0]==null && board[3][0]==null){
				System.out.println("check2");
				Location[] from= {l,new Location(0,0)};
				Location[] to= {new Location(2,0),new Location(3,0)};
				castling[c++]=new Move(from,to);
			}
			if(board[7][0]!=null && board[7][0].getType()=="Castle" && board[7][0].top() &&board[6][0]==null && board[5][0]==null){
				System.out.println("check3");
				Location[] from= {l,new Location(7,0)};
				Location[] to= {new Location(6,0),new Location(5,0)};
				castling[c++]=new Move(from,to);
			}
		}else if(!this.top && l.y==7 &&l.x==4){
			if(board[0][7]!=null && board[0][7].getType()=="Castle" && !board[0][7].top() &&board[2][7]==null && board[3][7]==null){
				Location[] from= {l,new Location(0,7)};
				Location[] to= {new Location(2,7),new Location(3,7)};
				castling[c++]=new Move(from,to);
			}
			if(board[7][7]!=null && board[7][7].getType()=="Castle" && !board[7][7].top() &&board[6][7]==null && board[5][7]==null){
				Location[] from= {l,new Location(7,7)};
				Location[] to= {new Location(6,7),new Location(5,7)};
				castling[c++]=new Move(from,to);
			}
		}
		
		
		if(k==0)return null;
		
		Move[] moves = new Move[k+c];
		for(int j=0;j<k;j++){
			Location[] h= {l};
			Location[] g= {loc[j]};
			moves[j]=new Move(h,g);
		}
		for(int j=0;j<c;j++){
			
			moves[j+k]=castling[j];
		}
		
		return moves;
	}

	public King(Boolean top) {
		super();
		this.top = top;
	}

	@Override
	public String getType() {
		return type;
	}

}
