import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FifteenNumbersPuzzle implements ActionListener{
    
    Board board;

    JFrame frame;
    JPanel panel;
    JButton[][] numbers = new JButton[4][4];
    JTextField moveCounter;
    int numberOfMoves;
    JTextField timerText;
    Timer timer;
    int minute;
    int second;
    JTextField hint;
    JTextField solve;

    int mainX ;
    int mainY ;
    boolean isMainClicked;

    int newMainX;
    int newMainY;

    int[][] numbersArr;

    public void simpleTimer(){
        timer = new Timer(1000, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                second++;
                if(second == 60){
                    minute++;
                    second = 0;
                }

                if(minute < 9 && second < 10){
                    timerText.setText("0" + minute + ":0" + second); 
                }else if(minute < 9 && second > 9){
                    timerText.setText("0" + minute + ":" + second); 
                }else if(minute > 9 && second < 10){
                    timerText.setText(minute + ":0" + second); 
                }else{
                    timerText.setText(minute + ":" + second);
                }

            }

        });
    }

    public FifteenNumbersPuzzle(int[] arr){
        
        isMainClicked = false;
        frame = new JFrame("15 Numbers Puzzle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);
        frame.setResizable(false);
        

        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout(new GridLayout(5,4,10,10));
        panel.setBackground(Color.black);

        numbersArr = new int[4][4];
        for(int i = 0; i < 16; i++){
            numbersArr[i/4][i%4] = arr[i];
        }

        moveCounter = new JTextField();
        timerText = new JTextField();
        hint = new JTextField();
        solve = new JTextField();

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4 ; j++){
                numbers[i][j] = new JButton();
                //numbers[i][j].setEnabled(false);
            }
        }
        
        moveCounter.setHorizontalAlignment(JTextField.CENTER);
        moveCounter.setEditable(false);
        moveCounter.setForeground(Color.black);
        moveCounter.setBackground(Color.LIGHT_GRAY);
        moveCounter.setFocusable(false);
        moveCounter.setFont(new Font("Arial", Font.BOLD, 20));

        timerText.setText("00:00");
        timerText.setHorizontalAlignment(JTextField.CENTER);
        timerText.setEditable(false);
        timerText.setForeground(Color.black);
        timerText.setBackground(Color.LIGHT_GRAY);
        timerText.setFocusable(false);
        timerText.setFont(new Font("Arial", Font.BOLD, 30));

        hint.setHorizontalAlignment(JTextField.CENTER);
        hint.setText("Hint");
        hint.setEditable(false);
        hint.setForeground(Color.black);
        hint.setBackground(Color.LIGHT_GRAY);
        hint.setFocusable(false);
        hint.setFont(new Font("Arial", Font.BOLD, 30));

        solve.setHorizontalAlignment(JTextField.CENTER);
        solve.setText("Solve");
        solve.setEditable(false);
        solve.setForeground(Color.black);
        solve.setBackground(Color.LIGHT_GRAY);
        solve.setFocusable(false);
        solve.setFont(new Font("Arial", Font.BOLD, 30));

        panel.add(moveCounter);
        panel.add(timerText);
        panel.add(hint);
        panel.add(solve);

        setPuzzle();
        
        
        
        frame.add(panel);
        frame.setVisible(true);

        minute = 0;
        second = 0;
        simpleTimer();
        timer.start();
    }

    public boolean isComplete(){
        for(int i = 0; i < 15 ; i++){
            if(numbersArr[i/4][i%4] != i+1){
                return false;
            }
        }
        return true;
    }

    public void setPuzzle(){

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4 ; j++){
                numbers[i][j].setText(String.valueOf(numbersArr[i][j]));
                numbers[i][j].setForeground(Color.black);
                //numbers[i][j].setEnabled(false);
                numbers[i][j].setBackground(Color.LIGHT_GRAY);
                numbers[i][j].setFocusable(false);
            
                numbers[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                panel.add(numbers[i][j]);
                if(numbersArr[i][j]==0){
                    mainX = i;
                    mainY = j;
                    numbers[i][j].setText("");
                }
            }
        
        }
        
        moveCounter.setText("Count:" + numberOfMoves);
        
        
        setMain();

        if(isComplete()){
            
            

            Font myFont = new Font("Ink Free",Font.BOLD,30);
            JTextField winText = new JTextField("Congratulations");
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

    ALLeft AlLeft = new ALLeft();
    ALRight AlRight = new ALRight();
    ALUp AlUp = new ALUp();
    ALDown AlDown = new ALDown();

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
            numberOfMoves++;


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
            numberOfMoves++;


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
            numberOfMoves++;


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
            numberOfMoves++;


            setPuzzle();
        }
        
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
