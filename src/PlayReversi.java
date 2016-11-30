
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
public class PlayReversi {
      public static void main(String[] args) 
	{
	      Agent human = new HumanAgent("black");
              Agent human1 = new HumanAgent("black");
	      Agent machine1 = new MinimaxAgent("white");
              Agent machine2 = new MinimaxAgent("black");
              
              ReversiGui gui=new ReversiGui();
              while(gui.ai_vs_ai==0 && gui.human_vs_ai==0){
                  try {
                      Thread.sleep(50);
                  } catch (InterruptedException ex) {
                      Logger.getLogger(PlayReversi.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
             
              if(gui.ai_vs_ai==1){
	      Game game = new Reversi(machine1,machine2,gui);
              game.play();
              }
              else if(gui.human_vs_ai==1){
                  Game game = new Reversi(human,machine1,gui);
                  game.play();
              }
	}
}
