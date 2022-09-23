import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FifteenNumbersPuzzle implements ActionListener{
    
    JFrame frame;
    JPanel panel;
    JButton[][] numbers = new JButton[4][4];
    JButton button1,button2,button3,button4,button5,button6,button7,button8;
    JButton button9,button10,button11,button12,button13,button14,button15,button16;

    int mainX ;
    int mainY ;
    boolean isMainClicked;

    int newMainX;
    int newMainY;

    int[][] numbersArr;

    public FifteenNumbersPuzzle(int[] arr){
        
        isMainClicked = false;
        frame = new JFrame("15 Numbers Puzzle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,550);
        
    

        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout(new GridLayout(4,4,10,10));
        panel.setBackground(Color.black);

        numbersArr = new int[4][4];
        for(int i = 0; i < 16; i++){
            numbersArr[i/4][i%4] = arr[i];
        }

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4 ; j++){
                numbers[i][j] = new JButton();
                //numbers[i][j].setEnabled(false);
            }
        }

        setPuzzle();
        

        frame.add(panel);
        frame.setVisible(true);
    }

    public boolean isComplete(){
        for(int i = 0; i < 15 ; i++){
            if(numbersArr[i/4][i%4] != i+1){
                return false;
            }
        }
        return true;
    }

    

    public class ALLeft implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            numbersArr[mainX][mainY] = numbersArr[mainX][mainY-1];
            numbersArr[mainX][mainY-1] = 0;
            
            ((JButton) e.getSource()).removeActionListener(this);
            isMainClicked = false;
            
            if(mainX >= 1){
                numbers[mainX-1][mainY].removeActionListener(AlUp);
            }
            if(mainX <= 2){
                numbers[mainX+1][mainY].removeActionListener(AlDown);
            }
            if(mainY <= 2){
                numbers[mainX][mainY+1].removeActionListener(AlRight);
            }

            setPuzzle();
        }
        
    }
    

    public class ALRight implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            numbersArr[mainX][mainY] = numbersArr[mainX][mainY+1];
            numbersArr[mainX][mainY+1] = 0;
            
            ((JButton) e.getSource()).removeActionListener(this);
            isMainClicked = false;

            if(mainX >= 1){
                numbers[mainX-1][mainY].removeActionListener(AlUp);
            }
            if(mainX <= 2){
                numbers[mainX+1][mainY].removeActionListener(AlDown);
            }
            if(mainY >= 1){
                numbers[mainX][mainY-1].removeActionListener(AlLeft);
            }

            setPuzzle();
        }
        
    }

    public class ALUp implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            numbersArr[mainX][mainY] = numbersArr[mainX-1][mainY];
            numbersArr[mainX-1][mainY] = 0;
            
            ((JButton) e.getSource()).removeActionListener(this);
            isMainClicked = false;
            
            if(mainX <= 2){
                numbers[mainX+1][mainY].removeActionListener(AlDown);
            }
            if(mainY >= 1){
                numbers[mainX][mainY-1].removeActionListener(AlLeft);
            }
            if(mainY <= 2){
                numbers[mainX][mainY+1].removeActionListener(AlRight);
            }

            setPuzzle();
        }
        
    }

    public class ALDown implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            numbersArr[mainX][mainY] = numbersArr[mainX+1][mainY];
            numbersArr[mainX+1][mainY] = 0;
            
            ((JButton) e.getSource()).removeActionListener(this);
            isMainClicked = false;      
            
            if(mainX >= 1){
                numbers[mainX-1][mainY].removeActionListener(AlUp);
            }
            if(mainY >= 1){
                numbers[mainX][mainY-1].removeActionListener(AlLeft);
            }
            if(mainY <= 2){
                numbers[mainX][mainY+1].removeActionListener(AlRight);
            }

            setPuzzle();
        }
        
    }

    ALLeft AlLeft = new ALLeft();
    ALRight AlRight = new ALRight();
    ALUp AlUp = new ALUp();
    ALDown AlDown = new ALDown();


    

    public void setPuzzle(){

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4 ; j++){
                numbers[i][j].setText(String.valueOf(numbersArr[i][j]));
                numbers[i][j].setForeground(Color.black);
                //numbers[i][j].setEnabled(false);
                numbers[i][j].setBackground(Color.LIGHT_GRAY);
                numbers[i][j].setFocusable(false);
                panel.add(numbers[i][j]);
                if(numbersArr[i][j]==0){
                    mainX = i;
                    mainY = j;
                    numbers[i][j].setText("");
                }
            }
        }
        setMain();

        if(isComplete()){
            
            

            Font myFont = new Font("Ink Free",Font.BOLD,30);
            JTextField winText = new JTextField("Tebrikler");
            winText.setFont(myFont);
            frame.remove(panel);
            frame.add(winText);
            winText.setVisible(true);

        }
        
    }


    public void setMain(){
        numbers[mainX][mainY].setEnabled(true);
        numbers[mainX][mainY].setBackground(Color.blue);
        numbers[mainX][mainY].addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isMainClicked){
            if(mainX >= 1){
                numbers[mainX-1][mainY].setEnabled(true);
                numbers[mainX-1][mainY].setBackground(Color.orange);
                numbers[mainX-1][mainY].addActionListener(AlUp);
            }
            if(mainX <= 2){
                numbers[mainX+1][mainY].setEnabled(true);
                numbers[mainX+1][mainY].setBackground(Color.orange);
                numbers[mainX+1][mainY].addActionListener(AlDown);
            }
            if(mainY >= 1){
                numbers[mainX][mainY-1].setEnabled(true);
                numbers[mainX][mainY-1].setBackground(Color.orange);
                numbers[mainX][mainY-1].addActionListener(AlLeft);
            }
            if(mainY <= 2){
                numbers[mainX][mainY+1].setEnabled(true);
                numbers[mainX][mainY+1].setBackground(Color.orange);
                numbers[mainX][mainY+1].addActionListener(AlRight);
            }
            //numbers[mainX][mainY].setEnabled(false);
            isMainClicked = true;
            ((JButton)e.getSource()).removeActionListener(this);
        }
    }

}
