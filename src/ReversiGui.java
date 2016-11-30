/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hasan
 */
import java.applet.Applet;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

/**
 *
 * @author Onur Sezer
 */
public class ReversiGui extends JFrame{

    JPanel panel;
    JPanel boardPanel;
    static JLabel score1;
    static JLabel score2;
    static JButton newGame;
    static JButton undo;
    static JButton [] cell;
    static JTextArea textArea;
    static Reversi reversi;
    static ArrayList<Reversi>  arrReversi= new ArrayList<Reversi>();
    static int countUndo = 0;
    
    static public int playerScore = 2; 
    static public int pcScore = 2;
    private static int rows = 8;
    private static int cols = 8;
    private static Color col = Color.green;
    public  int xCor=-1;
    public  int yCor=-1;
    public int ai_vs_ai=0;
    public int human_vs_ai=0;
  
    public ReversiGui()
    {
        super("Reversi Game by Hasanul Islam");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800,700));
        setLocation(0, 0);
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,60));
     
        newGame = new JButton("AI vs AI");
        newGame.setPreferredSize(new Dimension(100,60));
        newGame.setFont(new Font("Serif", Font.BOLD, 18));
        /*try 
        {
            Image img = ImageIO.read(getClass().getResource("images/start.png"));
            newGame.setIcon(new ImageIcon(img));
        } catch (IOException ex) {}*/
        newGame.addActionListener(new Action());
        
        undo = new JButton("Human vs AI");
        undo.setPreferredSize(new Dimension(150,60));
        undo.setFont(new Font("Serif", Font.BOLD, 20));
        /*try 
        {
            Image img2 = ImageIO.read(getClass().getResource("images/undo.png"));
            undo.setIcon(new ImageIcon(img2));
        } catch (IOException ex) {}*/
        undo.addActionListener(new Action());
        JLabel name = new JLabel();
        name.setText("Developed by Hasan");
        name.setLocation(750, 680);
        panel.add(newGame);
        panel.add(undo);
        
        add(panel, BorderLayout.SOUTH);
        
        // Board Panel
        boardPanel = new JPanel(new GridLayout(8,8));
        cell = new JButton[64];
        int k=0;
        for(int row = 0; row < rows; row++) 
        {
            for(int colum=0; colum < cols; colum++) 
            {
                cell[k] = new JButton();
                cell[k].setSize(50, 50);
                cell[k].setBackground(new Color(100,200,50));
                cell[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                cell[k].addActionListener(new Action());
                boardPanel.add(cell[k]);
                k++;
            }
        }
        add(boardPanel, BorderLayout.CENTER);
        
        
        JPanel scorePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        scorePanel.setPreferredSize(new Dimension(300,1000));
        
        JLabel dark = new JLabel();
        try 
        {
            Image img = ImageIO.read(getClass().getResource("images/dark.png"));
            dark.setIcon(new ImageIcon(img));
        } catch (IOException ex) {}
        JLabel light = new JLabel();
        try 
        {
            Image img = ImageIO.read(getClass().getResource("images/light.png"));
            light.setIcon(new ImageIcon(img));
        } catch (IOException ex) {}
        score1 = new JLabel();
        score1.setText(" " + playerScore + "  ");
        score1.setFont(new Font("Serif", Font.BOLD, 22));
        
        score2 = new JLabel();   
        score2.setText(" " + pcScore + "  ");
        score2.setFont(new Font("Serif", Font.BOLD, 22));        
               
        c.gridx = 0;
        c.gridy = 1;
        scorePanel.add(dark, c);  
        c.gridx = 1;
        c.gridy = 1;
        scorePanel.add(score1,c);
        
        
        c.gridx = 0;
        c.gridy = 2;
        scorePanel.add(light, c);  
        c.gridx = 1;
        c.gridy = 2;
        scorePanel.add(score2,c);
        JPanel textPanel = new JPanel(new GridBagLayout());
         textArea=new JTextArea(12,16);
         c.gridx=0;
         c.gridy=3;
          textArea.setEditable(false);      
         scorePanel.add(textArea,c);
         //JPanel textPanel = new JPanel(new GridBagLayout());
         
        add(scorePanel, BorderLayout.EAST);
        
       // scorePanel.add(light);
        //scorePanel.add(score2);
        setSize(800, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }  

    class Action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()== newGame){
                         ai_vs_ai=1;
                 }
            else if(e.getSource()==undo){
                        human_vs_ai=1;
            }
            for(int i=0;i<64;i++){
                 if(e.getSource()==cell[i]){
                         
                         xCor=i/8;
                         yCor=i%8;
                         System.out.println("button : "+ i+"  xCor: "+ xCor +"yCor: "+yCor);
                         
                 }
                 
             
            }
           
        }
    
    }
    
  }
    
    
    
