
public class Board {
	
	Peice[][] board = new Peice[8][8];
	Move last;
	public Board() {
		super();
		
		board[0][0]=new Castle(true);
		board[7][0]=new Castle(true);
		board[1][0]=new Knight(true);
		board[6][0]=new Knight(true);
		board[2][0]=new Bishop(true);
		board[5][0]=new Bishop(true);
		board[4][0]=new King(true);
		board[3][0]=new Queen(true);
		board[0][1]=new Pon(true);
		board[1][1]=new Pon(true);
		board[2][1]=new Pon(true);
		board[3][1]=new Pon(true);
		board[4][1]=new Pon(true);
		board[5][1]=new Pon(true);
		board[6][1]=new Pon(true);
		board[7][1]=new Pon(true);
	 	
		board[0][7]=new Castle(false);
		board[7][7]=new Castle(false);
		board[1][7]=new Knight(false);
		board[6][7]=new Knight(false);
		board[2][7]=new Bishop(false);
		board[5][7]=new Bishop(false);
		board[4][7]=new King(false);
		board[3][7]=new Queen(false);
		board[0][6]=new Pon(false);
		board[1][6]=new Pon(false);
		board[2][6]=new Pon(false);
		board[3][6]=new Pon(false);
		board[4][6]=new Pon(false);
		board[5][6]=new Pon(false);
		board[6][6]=new Pon(false);
		board[7][6]=new Pon(false);
		
	}
	
	public Board(Board board2) {
		int x,y;
		last=board2.last;
		for(x=0;x<8;x++){
			for(y=0;y<8;y++){
				if(board2.board[x][y]!=null)board[x][y]=board2.board[x][y].clone();
			}
			
		}
	}

	public Move[] getMoves(Location l){
		
		return board[l.x][l.y].getMoves(l, board);
	}
	
	public int doMove(Move m){
		int k=0;
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(board[i][j]!=null&&board[i][j].getType()=="Pon"&&board[i][j].top()==board[m.getFrom()[0].x][m.getFrom()[0].y].top()){
					((Pon)board[i][j]).last2=null;
				}
			}
			
		}
		
		
		for(int i=0;i<m.getFrom().length;i++){
			
			if(board[m.getFrom()[i].x][m.getFrom()[i].y].getType()=="Pon")((Pon)board[m.getFrom()[i].x][m.getFrom()[i].y]).setLast(m.getFrom()[i]);
			if(m.getTo()[i].x!=-1&&board[m.getTo()[i].x][m.getTo()[i].y]!=null&&board[m.getTo()[i].x][m.getTo()[i].y].getType()=="King"&& board[m.getTo()[i].x][m.getTo()[i].y].top())k=1;//bottom team wins
			if(m.getTo()[i].x!=-1&&board[m.getTo()[i].x][m.getTo()[i].y]!=null&&board[m.getTo()[i].x][m.getTo()[i].y].getType()=="King"&& !board[m.getTo()[i].x][m.getTo()[i].y].top())k=-1;//top team wins.
			if(m.getTo()[i].x!=-1)board[m.getTo()[i].x][m.getTo()[i].y]=board[m.getFrom()[i].x][m.getFrom()[i].y];
			board[m.getFrom()[i].x][m.getFrom()[i].y]=null;
			
		}
		
		return k;
	}

	public Move getAllMoves(boolean curTeam) {
		//System.out.println("getallmoves");
		int x,y;
		Move moveLL = null;
		Move temp[];
		for(x=0;x<8;x++){
			for(y=0;y<8;y++){
				if(board[x][y]!=null&&board[x][y].top()==curTeam){
		        	temp=board[x][y].getMoves(new Location(x,y), board);
		        	int k;
		        	if(temp==null){
		        		k=0;
		        		//System.out.println("NULL");
		        	}else{
		        		//System.out.println("NOT NULL");
		        		k=temp.length;
		        		
		        	}
		        	for(int i=0;i<k;i++){
		        		temp[i].setNext(moveLL);
		        		moveLL=temp[i];
		        	}
				}
			}
		}
		//System.out.println("returning");
		return moveLL;
	}
	
	

}
