
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hasan
 */
public class MinimaxAgent extends Agent{

    public MinimaxAgent(String name) {
        super(name);
    }

    @Override
    public void makeMove(Game game) {
         try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
               
		Reversi reversi = (Reversi) game;
                int depthLimit=5;
                if(Math.random()<=0.5){
                     depthLimit=6;
                }
                else{
                    depthLimit=3;
                }
        
		CellValueTuple bestMove = Max(reversi,depthLimit,-10000,10000);
		if( bestMove.col!=-1)
		{
			reversi.board[bestMove.row][bestMove.col] = role;
                        reversi.ChangeRole(role, bestMove.row, bestMove.col);
                        
                       // reversi.refreshMoveSuggestions();
                        reversi.movesSuggestion(-1*role);
                        reversi.showGUI();
                        // reversi.refreshMoveSuggestions();
		}
    }
  	
    private CellValueTuple Max(Reversi reversi,int depthLimit,int alpha,int beta){
        
        CellValueTuple maxCVT = new CellValueTuple();
        maxCVT.utility = -1000;
	
        if(reversi.isFinished()){
            maxCVT.utility= Utility(reversi);
            return maxCVT;
        }
        if(depthLimit==0){
           maxCVT.utility=Evaluate(reversi);
           return maxCVT;
        }
        
        ArrayList<Reversi.Cell> moves=reversi.probableMoves(role);
        if(moves==null || moves.isEmpty()){
           reversi.updateMessage("NO VALID MOVE");
           return maxCVT;
        }
	
       if(moves!=null){
            for(Reversi.Cell cell:moves){
                if(cell!=null){
                    
                    reversi.board[cell.row][cell.col]=role; //result(state,action)
                    int currentScore=Min(reversi,depthLimit-1,alpha,beta).utility;
                    
                    reversi.board[cell.row][cell.col]=0;  //undo last state
                    if(maxCVT.utility<=currentScore){
                         maxCVT.utility=currentScore;
                         maxCVT.row=cell.row;
                         maxCVT.col=cell.col;
                    }
                    if(maxCVT.utility>=beta)//pruning
                        return maxCVT;
                    alpha=Math.max(alpha,maxCVT.utility);
                    System.out.println("Alpha: "+ alpha);
                }
            }
       
       }
		
        return maxCVT;
    }
    
    private CellValueTuple Min(Reversi reversi, int depthLimit, int alpha, int beta) {
        CellValueTuple minCVT=new CellValueTuple();
        minCVT.utility=1000;
        if(reversi.isFinished()){
            minCVT.utility= Utility(reversi);
            return minCVT;
        }
        else if(depthLimit==0){
           minCVT.utility=Evaluate(reversi);
           return minCVT;
        }
        
        ArrayList<Reversi.Cell> moves=reversi.probableMoves(role);
        if(moves==null || moves.isEmpty()){
           reversi.updateMessage("NO VALID MOVE");
           return minCVT;
        }
	
       if(moves!=null){
            for(Reversi.Cell cell:moves){
                if(cell!=null){
                    reversi.board[cell.row][cell.col] = -1*role; //opponent's state //result(state,action)
                    int currentScore=Max(reversi,depthLimit-1,alpha,beta).utility;
                    
                    reversi.board[cell.row][cell.col]=0;  //undo last state
                    if(minCVT.utility>=currentScore){
                         minCVT.utility=currentScore;
                         minCVT.row=cell.row;
                         minCVT.col=cell.col;
                    }
                    if(minCVT.utility<=alpha)//pruning
                        return minCVT;
                    beta=Math.min(beta,minCVT.utility);
                   System.out.println("Beta: "+ beta);
                }
            }
       
       }
        return minCVT;
               
                
    }
    public int Utility(Reversi reversi){
        int score_difference=0;  
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                 score_difference+=reversi.board[i][j];
            }
        }
        return score_difference*role;
    }

    public int Evaluate(Reversi reversi){
        int score=0;
        int value;
        int opponent=-1*role;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                value=1;
                if(i==0 || i==7)
                    value+=5;
                if(j==0 || j==7)
                    value+=5;
                if(i==1 || i==6)
                    value-=5;
                if(j==1 || j==6)
                    value-=5;
                if(reversi.board[i][j]==role)
                    score+=value;
                else if(reversi.board[i][j]==opponent)
                    score-=value;
                    
            }
        }
        int value2=Utility(reversi);
        return (int)(0.75*score+0.25*value2);
    }
    class CellValueTuple
	{
		int row,col, utility;
		public CellValueTuple() {
			// TODO Auto-generated constructor stub
			row =-1;
			col =-1;
                        utility=0;
		}
	}
    
}
