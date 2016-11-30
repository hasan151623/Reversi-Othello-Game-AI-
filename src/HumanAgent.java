
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hasan
 */
public class HumanAgent extends Agent{
   Scanner in=new Scanner(System.in);
   ReversiGui gui;
    public HumanAgent(String name) {
        super(name);
        
    }

    @Override
    public void makeMove(Game game) {
       
        Reversi reversi=(Reversi)game;
        ArrayList<Reversi.Cell> moves=new ArrayList<Reversi.Cell>();
        moves=reversi.probableMoves(role);
        
        if(moves==null || moves.isEmpty()){
            reversi.updateMessage("No Valid Move");
            return;
        }
        //reversi.score();       
       //int row=in.nextInt();
        //int col=in.nextInt();
        while(reversi.gui.xCor==-1 || reversi.gui.yCor==-1){try {
            Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(HumanAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
}
        int row=reversi.gui.xCor;
        
        int col=reversi.gui.yCor;
        reversi.gui.xCor=-1;
        reversi.gui.yCor=-1;
        //gui.role=role;
        System.out.println("xCor: "+row+" yCor: "+col);
        System.out.println(role);
        int valid=0;
       if(!moves.isEmpty()){
             
           for (Reversi.Cell cell : moves) {
                //System.out.println(cell.row+" "+cell.col);
                  if(cell!=null){
                      if((int)cell.row==row && (int)cell.col==col){
                            reversi.ChangeRole(role,row,col);
                            valid=1;
                            break;
                            
                      }
                     /* else if((int)cell.row!=row || cell.col!=col){
                            //reversi.updateMessage("Enter valid move.");
                            continue;
                            
                      }*/
                      System.out.println(cell.row+" "+cell.col);
                      //else    
                         //reversi.board[(int)cell.row][(int)cell.col]=0;
                   }
                  /*else if(cell==null){
                      reversi.updateMessage("No Valid Move");
                      return;
                  }*/
             }
          }
       if(valid==0){
           reversi.updateMessage("Enter valid move.");
       makeMove(game);}
       
       int opponent=-1*role;
       reversi.movesSuggestion(opponent);
       //reversi.refreshMoveSuggestions();
      // .gui.showGUI();
        
    }
    

   
   
        
    
    
    
}
