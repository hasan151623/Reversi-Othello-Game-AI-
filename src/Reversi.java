
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hasan
 */
public class Reversi extends Game{
    /* -1=black, +1=white, 0=empty*/
    public int[][] board;
    int[] directionX={-1,-1,-1,0 ,0, 1,1,1};
    int[] directionY={-1, 0, 1,-1,1,-1,0,1};
    ReversiGui gui;
           
    public Reversi(Agent a, Agent b,ReversiGui g) {
        super(a, b);
        a.setRole(-1);//black
        b.setRole(1); //white
        name="Reversi";
        board= new int[8][8]; 
        gui=g;
    }

    
    @Override
    boolean isFinished() {
        if(isBoardFull()){
            int i;
            i = checkForWin();
            return true;
        }
        else if(terminalTest()){
            return true;
        }
            
        else
            return false;
    }

    @Override
    void initialize(boolean fromFile) {
        for (int i=0;i<board.length;i++){
             for(int j=0;j<board.length;j++){
                    board[i][j]=0;
             }
        }
       board[3][3]=board[4][4]=1;//white
       board[3][4]=board[4][3]=-1;//black
       ArrayList<Cell> moves=new ArrayList<Cell>();
       int role=agent[turn].role;
       moves=probableMoves(role);
       for(Cell cell:moves){
           board[cell.row][cell.col]=2;
       }
       showGUI();
    }

    @Override
    void showGameState() {
        /*System.out.println("   0  1  2  3  4  5  6  7");
        System.out.println("   ----------------------");
        for(int i=0;i<8;i++){
            System.out.print(i+"| ");
            for(int j=0;j<8;j++){
                  if(board[i][j]==0)
                      System.out.print(".  ");
                  else if(board[i][j]==1){
                      System.out.print("W  ");
                  }
                  else if(board[i][j]==-1)
                      System.out.print("B  ");
                  else if(board[i][j]==2){
                      System.out.print("o  ");
                      
                      board[i][j]=0;  // for refreshing
                  }
            }
            System.out.println();
        }*/
       showGUI();
        score();
    }
    
    public boolean isBoardFull(){
         for(int i=0;i<8;i++){
             for(int j=0;j<8;j++){
                 if(board[i][j]==0)
                     return false;
             }
         }
         return true;
    }
    public void movesSuggestion(int role){
         ArrayList<Cell> moves=new ArrayList<Cell>();
       
       moves=probableMoves(role);
       if(moves==null || moves.isEmpty() ){
              updateMessage("NO valid Moves");
              return;
       }
       for(Cell cell:moves){
           if(cell!=null)
                  board[(int)cell.row][(int)cell.col]=2;
       }
    }
    
    public void score(){
        int black=0,white=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board[i][j]==-1)
                    black++;
                else if(board[i][j]==1)
                    white++;
            }
        }
        gui.score1.setText(" " + black + "  ");
        gui.score2.setText(" " + white + "  ");
        updateMessage("Score: "+"Black: "+black+"  White: "+white);
    }
    public  ArrayList<Cell> probableMoves(int role){
        ArrayList<Cell> moves=new ArrayList<Cell>();
        int opponent=-1*role;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if (board[i][j]!=0)
                    continue;
                int k,l;
                for(k=0;k<8;k++){
                    int new_i=0,new_j=0;
                    for(l=0;l<8;l++){
                        if(k==l){
                            new_i=i+directionX[k];
                            new_j=j+directionY[l];
                            break;
                        }
                            
                    }
                    if(!isValidCell(new_i,new_j) || board[new_i][new_j]==role)
                        continue;
                    while(board[new_i][new_j]==opponent){
                         new_i=new_i+directionX[k];
                         new_j=new_j+directionY[l];
                         if(!isValidCell(new_i,new_j))
                             break;
                         
                    }
                    if (isValidCell(new_i,new_j)&& board[new_i][new_j]==role  ){
                             Cell cell=new Cell();
                             cell.row=i;
                             cell.col=j;
                             moves.add(cell);
                            }
                  }
                }
        }
        /*if any agent doesn't have a move and the game is not over then
         return the None move or "no valid move." */  
        if(moves.isEmpty() && !terminalTest()){
            //Cell cell=null;
            //moves.add(cell);
            return null;
        
       }
        return moves;
    }
    public void ChangeRole(int role,int row,int col){
              int opponent=-1*role;
        
        
                int k,l;
                for(k=0;k<8;k++){
                    int new_i=0,new_j=0;
                    for(l=0;l<8;l++){
                        if(k==l){
                            new_i=row+directionX[k];
                            new_j=col+directionY[l];
                            break;
                        }
                            
                    }
                    if(!isValidCell(new_i,new_j) || board[new_i][new_j]==role)
                        continue;
                    while(board[new_i][new_j]==opponent){
                         new_i=new_i+directionX[k];
                         new_j=new_j+directionY[l];
                         if(!isValidCell(new_i,new_j))
                             break;
                         
                    }
                    if (isValidCell(new_i,new_j) &&board[new_i][new_j]==role){
                       new_i=row+directionX[k];
                       new_j=col+directionY[l];
                       board[row][col]=role;
                       while(isValidCell(new_i,new_j) && board[new_i][new_j]==opponent){
                           board[new_i][new_j]=role;
                           new_i=new_i+directionX[k];
                           new_j=new_j+directionY[l];
                         
                           if(!isValidCell(row,col))
                             break;
                         
                    }
                             
                             
                            }
                  }
            
    }
    
    public boolean terminalTest(){
         //"Is game over?"  
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if (board[i][j]!=0)
                    continue;
                int[] players={1,-1};
                
                for(int role:players){
                    int opponent=-1*role;
                    int k,l;
                    for(k=0;k<8;k++){
                        int new_i=0,new_j=0;
                        for(l=0;l<8;l++){
                            if(k==l){
                                new_i=i+directionX[k];
                                new_j=j+directionY[l];
                                break;
                            }
                            
                        }
                        if(!isValidCell(new_i,new_j) || board[new_i][new_j]==role)
                              continue;
                        while(isValidCell(new_i,new_j) && board[new_i][new_j]==opponent){
                              new_i=new_i+directionX[k];
                              new_j=new_j+directionY[l];
                              if(!isValidCell(new_i,new_j))
                                  break;
                         }
                        if (isValidCell(new_i,new_j)&& board[new_i][new_j]==role){
                                 return false;
                         }
                     }
                 }
               }
             }
          return true;
    }
    public boolean isValidCell(int row,int col){
       if(row<0 || row>7 || col<0 || col>7)return false;
       else
           return true;
    }

    public int checkForWin() {
        winner=null;
        int winRole=0;//empty
        int black=0,white=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board[i][j]==-1)
                    black++;
                else if(board[i][j]==1)
                    white++;
            }
        }
        if(black>white){
            winRole=-1;
            winner=agent[0];
        }
        else if(black<white){
            winRole=1;
            winner=agent[1];
        }
        else  
            winner=null;
        return winRole;
        
    }

    
   

   

    
    class Cell{
        int row,col,utility;
        public Cell(){
            row=0;
            col=0;
        }
    } 

    @Override
    void updateMessage(String msg) {
        System.out.println(msg);
        gui.textArea.setText(msg);
    }
    
    
    /*public static void main(String args[]){
            HumanAgent h=new HumanAgent("hum");
            MinimaxAgent m=new MinimaxAgent("pc");
            
            Reversi rv=new Reversi(h,m);
            rv.initialize(false);
            rv.showGameState();
            rv.ChangeRole(1, 5, 3);
            rv.showGameState();
            System.out.println(rv.checkForWin());
            
            
    }
    */
    public void showGUI(){
              
        int k=0;
        for(int i=0;i<8;i++){
                 for(int j=0;j<8;j++){
                     if(board[i][j]==-1){
                           try 
                           {
                               Image img = ImageIO.read(getClass().getResource("images/dark.png"));
                               gui.cell[k].setIcon(new ImageIcon(img));
                            } catch (IOException ex) {}
                     }
                     else if(board[i][j]==1){
                          try 
                          {
                               Image img = ImageIO.read(getClass().getResource("images/light.png"));
                               gui.cell[k].setIcon(new ImageIcon(img));
                          } catch (IOException ex) {}   
                     }
                     else if(board[i][j]==2){
                         try 
                          {
                               Image img = ImageIO.read(getClass().getResource("images/legalMoveIcon.png"));
                               gui.cell[k].setIcon(new ImageIcon(img));
                               
                          } catch (IOException ex) {} 
                        //board[i][j]=0;  // for refreshing
                        //gui.cell[k].setIcon(null);
                     }
                     k++;
                 }
                 
              }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Reversi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        refreshMoveSuggestions();
      }
    
    public void refreshMoveSuggestions(){
         int k=0;
         for(int i=0;i<8;i++){
             for(int j=0;j<8;j++){
                 if(board[i][j]==2){
                     board[i][j]=0;
                     gui.cell[k].setIcon(null);
                 }
                 k++;
             }
         }
    }
}