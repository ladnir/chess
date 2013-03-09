
public class Pon implements Peice{
	private String type;
	private Boolean top;
	private Location last;
	Location last2;
	@Override
	public Boolean top() {
		return top;
	}
	public Peice clone(){
		Pon clone = new Pon(top);
		if(last!=null){
			Location lastClone=new Location(last.x,last.y);
			clone.last=lastClone;
		}
		if(last2!=null){
			Location last2Clone=new Location(last2.x,last2.y);
			clone.last2=last2Clone;
		}
		return clone;
	}
	@Override
	public Move[] getMoves(Location l, Peice[][] board) {
		Location[] loc = new Location[4];
		int i=0;
		if(top){
			if(l.y==7)return null;
			if(board[l.x][l.y+1]==null)loc[i++]= new Location(l.x,l.y+1);
			if(l.x+1<8 && board[l.x+1][l.y+1]!=null && board[l.x+1][l.y+1].top()!=board[l.x][l.y].top())loc[i++]= new Location(l.x+1,l.y+1);
			if(l.x-1>=0 && board[l.x-1][l.y+1]!=null && board[l.x-1][l.y+1].top()!=board[l.x][l.y].top())loc[i++]= new Location(l.x-1,l.y+1);
			if(last==null && board[l.x][l.y+1]==null &&board[l.x][l.y+2]==null)loc[i++]= new Location(l.x,l.y+2);
		}else{
			if(l.y==0)return null;
			if(board[l.x][l.y-1]==null)loc[i++]= new Location(l.x,l.y-1);
			if(l.x+1<8 && board[l.x+1][l.y-1]!=null && board[l.x+1][l.y-1].top()!=board[l.x][l.y].top())loc[i++]= new Location(l.x+1,l.y-1);
			if(l.x-1>=0 && board[l.x-1][l.y-1]!=null && board[l.x-1][l.y-1].top()!=board[l.x][l.y].top())loc[i++]= new Location(l.x-1,l.y-1);
			if(last==null && board[l.x][l.y-1]==null &&board[l.x][l.y-2]==null)loc[i++]= new Location(l.x,l.y-2);

		}
		
		Move[] enPassant = new Move[2];
		int e=0;
		if(l.x+1<8 && board[l.x+1][l.y]!=null && board[l.x+1][l.y].top()!=top &&  board[l.x+1][l.y].getType()=="Pon" && ((Pon)board[l.x+1][l.y]).last2!=null&& ((Pon)board[l.x+1][l.y]).getLast().x==l.x+1){
			if(  board[l.x+1][l.y].top()==true  &&((Pon)board[l.x+1][l.y]).getLast().y==l.y-2){
				Location[] from = {l,new Location(l.x+1,l.y)};
				Location[] to = {new Location(l.x+1,l.y-1),new Location(-1,-1)};
				enPassant[e++]=new Move(from, to);
			}else if( board[l.x+1][l.y].top()==false  &&((Pon)board[l.x+1][l.y]).getLast().y==l.y+2){
				Location[] from = {l,new Location(l.x+1,l.y)};
				Location[] to = {new Location(l.x+1,l.y+1),new Location(-1,-1)};
				enPassant[e++]=new Move(from, to);
			}
		}
		if(l.x-1>=0 && board[l.x-1][l.y]!=null && board[l.x-1][l.y].top()!=top &&  board[l.x-1][l.y].getType()=="Pon" && ((Pon)board[l.x-1][l.y]).last2!=null && ((Pon)board[l.x-1][l.y]).getLast().x==l.x-1){
			if(  board[l.x-1][l.y].top()==true  &&((Pon)board[l.x-1][l.y]).getLast().y==l.y-2){
				Location[] from = {l,new Location(l.x-1,l.y)};
				Location[] to = {new Location(l.x-1,l.y-1),new Location(-1,-1)};
				enPassant[e++]=new Move(from, to);
			}else if( board[l.x-1][l.y].top()==false  &&((Pon)board[l.x-1][l.y]).getLast().y==l.y+2){
				Location[] from = {l,new Location(l.x-1,l.y)};
				Location[] to = {new Location(l.x-1,l.y+1),new Location(-1,-1)};
				enPassant[e++]=new Move(from, to);
			}
		}
		
		
		if(i+e==0) return null;
		Move[] moves = new Move[i+e];
		for(int j=0;j<i;j++){
			Location[] h= {l};
			Location[] g= {loc[j]};
			moves[j]=new Move(h,g);
		}
		
		for(int j=0;j<e;j++){
			moves[i+j]=enPassant[j];
		}
		
		
		
		return moves;
	}

	public Pon(Boolean top) {
		super();
		this.type = "Pon";
		this.top = top;
	}
	
	public Location getLast() {
		return last;
	}

	public void setLast(Location last) {
		this.last = last;
		this.last2=last;
	}

	@Override
	public String getType() {
		return type;
	}

}
