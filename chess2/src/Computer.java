import java.util.Random;
//import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Computer extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random rand= new Random();
	MyPanel panel;
	private double num;
	public Computer(){
//		super("Chess");
//		setSize(500, 500);
//		setVisible(true);
//		panel = new MyPanel();
//		panel.setBackground(Color.WHITE);
//		add(panel);
		
	}
	
	public int findMove(Board board, boolean curTeam) {
		int depth =4;
		
		Move a= new Move(null, null);
		a.setValue(Double.MIN_VALUE);
		Move b= new Move(null, null);
		b.setValue(Double.MAX_VALUE);
		num=0;
		Move bestMove =fullSearch(depth,board,null,curTeam,curTeam);//alphabeta(depth,a,b,curTeam,board,curTeam,null);
		System.out.println("done "+num);
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
				temp=new Board(board);//shallow/medium copy
				temp.doMove(moves);
				Move tempMove=fullSearch(depth-1,temp,moves,!curTeam,maxTeam);
				
				if(best==null || best.getValue()<tempMove.getValue()){
					best=tempMove;
				}else if(best.getValue()==tempMove.getValue() && rand.nextInt(depth)%4==3){
					best=tempMove;
				}
				
				moves=moves.getNext();
				
			}
			if (last==null)return best;
			if(best!=null)last.setValue(best.getValue());
			else last.setValue(Double.MIN_VALUE);
			return last;
		}else{
			Move best = null;
			while(moves!=null){
				temp=new Board(board);//shallow copy
				
				temp.doMove(moves);
				Move tempMove=fullSearch(depth-1,temp,moves,!curTeam,maxTeam);
				if(best==null || best.getValue()>tempMove.getValue())best=tempMove;
				else if(best.getValue()==tempMove.getValue() && rand.nextInt(depth)%4==3){
					best=tempMove;
				}
				moves=moves.getNext();
			}
			if (last==null)return best;
			if(best!=null)last.setValue(best.getValue());
			else last.setValue(Double.MIN_VALUE);
			return last;
		}
		
	}
	
	private void show(Board board, Move moves) {
		
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(board.board[j][i]!=null){
					System.out.print(board.board[j][i].getType());
					if(board.board[j][i].top())System.out.print("^");
					else System.out.print(".");
					System.out.print("	");
				}
				else System.out.print("*	");
			}
			System.out.println(" ");
		}System.out.println("__________________________________ ");
		
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
		double score=0;
		num++;
		for(int x=0;x<8;x++){
			for(int y=0;y<8;y++){
				if(board.board[x][y] != null){
					if(board.board[x][y].top()==curTeam)
						score+=board.board[x][y].getValue();
					else score-=board.board[x][y].getValue();
					
				}
			}
		}
		return score;
	}
	
	public void computerOnly(Board board) {
		boolean curTeam=true;
		int k=0;
		while(k==0){
			k=findMove(board,curTeam);
			curTeam=!curTeam;
			repaint();
		}
		
	}

}
