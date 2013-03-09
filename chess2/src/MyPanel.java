import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.*;

public class MyPanel extends JPanel implements MouseListener{
	Board board;
	Move[] moves;
	Move finalMove;
	Location selected;
	Boolean topTeamsTurn=true;
	int k=0;
	int oldX,pointX,pointY,oldY;
	Computer chessComp;
	
	
	public MyPanel() {
		super();
		board =new Board();
		chessComp= new Computer();
		addMouseListener(this);  
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(topTeamsTurn&&k==0)g.drawString("red's turn", 8*50+5, 10);
		if(!topTeamsTurn&&k==0)g.drawString("blacks's turn", 8*50+5, 10);
		if(k==1)g.drawString("black wins!!!!!!!!!!!!", 8*50+5, 10);
		if(k==-1)g.drawString("red wins!!!!!!!!!!!!", 8*50+5, 10);
		
		if(selected!=null){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(selected.x*50, selected.y*50, 50, 50);
		}
		if(moves!=null){
			System.out.println("jjjjj"+moves.length);
			g.setColor(Color.getHSBColor(148, 160, 255));
			for(int i =0 ;i<moves.length;i++){
				g.fillRect(moves[i].getTo()[0].x*50, moves[i].getTo()[0].y*50, 50, 50);
			}
		}
		
		for(int x=0;x<=400;x=x+50){
			g.setColor(Color.BLACK);
			g.drawLine(x, 0, x, 400);
			g.drawLine(0, x, 400, x);
		}
		for(int x=0;x<8;x++){
			for(int y=0;y<8;y++){
				
				if(board.board[x][y]!=null){
					if(board.board[x][y].top())g.setColor(Color.RED);
					else g.setColor(Color.BLACK);
					g.drawString(board.board[x][y].getType(), (x)*50+1, (1+y)*50-1);
				}
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent mouse) {
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
		boolean twoPlayer=false;
		// Copy the last clicked location into the 'old' variables.
        oldX=pointX;
        oldY=pointY;
        boolean moved=false;
    // Get the location of the current mouse click.
        pointX = mouse.getX();
        pointY = mouse.getY();
        //boolean moved=false;
    // Tell the panel that we need to redraw things.
        
        System.out.println("click ("+pointX/50+","+pointY/50+")");
        if(k==0&&pointX/50<8 &&pointX/50>=0 && pointY/50<8 &&pointY/50>=0 &&board.board[pointX/50][pointY/50]!=null&&board.board[pointX/50][pointY/50].top()==topTeamsTurn){
        	//selection stage
        	
        	selected = new Location(pointX/50,pointY/50);
        	moves= board.board[pointX/50][pointY/50].getMoves(selected, board.board);
        	System.out.println("click 1");
        	if(moves==null)System.out.println("moves null");
        }else if(selected!=null && pointX/50<8 &&pointX/50>=0 && pointY/50<8 &&pointY/50>=0){
        	//movement stage
        	
        	System.out.println("selected=("+selected.x+","+selected.y+")");
        	for(int i=0;i<moves.length;i++){
        		if(moves[i].getTo()[0].x==pointX/50 &&moves[i].getTo()[0].y== pointY/50){
        			System.out.println("ggggg");
        			k=board.doMove(moves[i]);  	
        			moved=true;
        		}
        	}
        	if(twoPlayer)topTeamsTurn=!topTeamsTurn;
        	else if(k==0 && moved)k=chessComp.findMove(board,!topTeamsTurn);
        	
        	selected=null;
        	moves=null;
        	System.out.println("click 2");
        }else{
        	System.out.println("no good click");
        	moves=null;
        	selected=null;
        }
       
        repaint();
		
	}
	
	

}
