import java.util.Random;
public class Computer {
	Random rand= new Random();
	public int findMove(Board board, boolean curTeam) {
		int depth =2;
		Move a= new Move(null, null);
		a.setValue(Double.MIN_VALUE);
		Move b= new Move(null, null);
		b.setValue(Double.MAX_VALUE);
		
		Move bestMove =alphabeta(depth,a,b,curTeam,board,curTeam,null);
		System.out.println("done");
		return board.doMove(bestMove);
	}

	
	private Move fullSearch(int depth,Board board,Move last,boolean curTeam,boolean maxTeam){
		Board temp;
		if(depth==0){
			last.setValue(evaluate(board,curTeam));
			return last;
		}
		
		Move moves=board.getAllMoves(curTeam);
		
		if(curTeam==maxTeam){
			Move best = null;
			while(moves!=null){
				temp=new Board(board);//shallow copy
				temp.doMove(moves);
				Move tempMove=fullSearch(depth-1,board,moves,!curTeam,maxTeam);
				if(best==null || best.getValue()>tempMove.getValue())best=tempMove;
			}
			return best;
		}else{
			Move best = null;
			while(moves!=null){
				temp=new Board(board);//shallow copy
				temp.doMove(moves);
				Move tempMove=fullSearch(depth-1,board,moves,!curTeam,maxTeam);
				if(best==null || best.getValue()<tempMove.getValue())best=tempMove;
			}
			return best;
		}
		
	}
	private Move alphabeta(int depth, Move a,Move b, boolean curTeam,Board board,boolean maxTeam,Move last) {
		System.out.println("d="+depth);
		Board temp;
		if(depth==0){
			last.setValue(evaluate(board,curTeam));
			return last;
		}
		
		Move moves=board.getAllMoves(curTeam);
		
		if(curTeam==maxTeam){
			while(moves!=null){
				temp=new Board(board);//shallow copy
				temp.doMove(moves);
				a= max(a,alphabeta(depth-1,a,b,!curTeam,temp,maxTeam,moves) );
				moves=moves.getNext();
				if(a.getValue()>=b.getValue()){
					if(last==null)return a;
					last.setValue(a.getValue());
					last.setPv(a);
					return last;
					
				}
					
			}
			if(last==null)return a;
			last.setPv(a);
			last.setValue(a.getValue());
			return last;
		}else{
			while(moves!=null){
				temp=new Board(board);//shallow copy
				temp.doMove(moves);
				b= min(b,alphabeta(depth-1,a,b,!curTeam,temp,maxTeam,moves) );
				moves=moves.getNext();
				
				if(a.getValue()>=b.getValue()){
					if(last==null)return b;
					last.setValue(b.getValue());
					last.setPv(b);
					return last;
				}
			}		
			if(last==null)return b;
			last.setValue(b.getValue());
			last.setPv(b);
			return last;
		}
		
	}

	private Move min(Move a, Move b) {
		//if(a==null)return b;
		//if(b==null)return a;
		
		if (a.getValue()<b.getValue()){
			return a;
		}
		
		return b;
	}

	private Move max(Move a, Move b) {
		//if(a==null)return b;
		//if(b==null)return a;
		
		if (a.getValue()>b.getValue()){
			return a;
		}
		
		return b;
	}

	private double evaluate(Board board, boolean curTeam) {
		
		return rand.nextDouble();
	}

}
