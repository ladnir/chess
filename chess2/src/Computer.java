
public class Computer {

	public void findMove(Board board, boolean curTeam) {
		int x,y,depth =10;
		Move bestMove = null;
		
		for(x=0;x<8;x++){
			for(y=0;y<8;y++){
				if(board.board[x][y]!=null&&board.board[x][y].top()==curTeam){
		        	//Location selected = new Location(x,y);	
					//Move moves[]=board.board[x][y].getMoves(selected, board.board);
					Move cur=alphabeta(board.board[x][y],depth,null,null,curTeam,board);
					if(bestMove==null || cur.getValue()>bestMove.getValue())bestMove=cur;
				
				}
			}
		}
		
	}

	private Move alphabeta(Peice peice, int depth, Object object,Object object2, boolean curTeam,Board board) {
		if(depth==0)return evaluate(board,curTeam);
		
		
		return null;
	}

	private Move evaluate(Board board, boolean curTeam) {
		// TODO Auto-generated method stub
		return null;
	}

}
