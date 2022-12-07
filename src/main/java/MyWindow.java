
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class MyWindow {
    private JTextField tfA1;
    private JTextField tfA2;
    private JTextField tfA3;
    private JTextField tfB1;
    private JTextField tfB2;
    private JTextField tfB3;
    private JTextField tfC1;
    private JTextField tfC2;
    private JTextField tfC3;
    private JTextField tfC4;
    private JTextField tfAddOperation;
    private JButton solveBtn;
    private JLabel resultLabel;
    private JLabel labelA1;
    private JLabel labelA2;
    private JLabel labelA3;
    private JLabel labelB1;
    private JLabel labelB2;
    private JLabel labelB3;
    private JLabel labelC1;
    private JLabel labelC2;
    private JLabel labelC3;
    private JLabel labelC4;
    private JLabel labelAddOperation;
    private JPanel mainPanel;

    private List<JTextField> inputTF = new ArrayList<>();
    private ArrayList <String> rawInput = new ArrayList<>();
    int varPos = 0;         ///Tu przetrzymywana będzie pozycja w Liście rawInput na której znajduje się zmienna

    public  MyWindow(){
        //tworze tablice wszystkich TF
        inputTF.add(tfA1);              //inputTF.get(0)
        inputTF.add(tfA2);              //inputTF.get(1)
        inputTF.add(tfA3);              //inputTF.get(2)
        inputTF.add(tfB1);              //inputTF.get(3)
        inputTF.add(tfB2);              //inputTF.get(4)
        inputTF.add(tfB3);              //inputTF.get(5)
        inputTF.add(tfC1);              //inputTF.get(6)
        inputTF.add(tfC2);              //inputTF.get(7)
        inputTF.add(tfC3);              //inputTF.get(8)
        inputTF.add(tfC4);              //inputTF.get(9)
        inputTF.add(tfAddOperation);    //inputTF.get(10)
        ArrayList <JLabel> labels = new ArrayList<>();
        for(JTextField tf:inputTF){
            LineBorder lineBorder =new LineBorder(Color.white, 0, true);
            tf.setBackground(new Color(79, 143, 192));
            tf.setBorder(lineBorder);
            tf.setForeground(new Color(255, 227, 179));
        }

        labels.add(labelA1);
        labels.add(labelA2);
        labels.add(labelA3);
        labels.add(labelB1);
        labels.add(labelB2);
        labels.add(labelB3);
        labels.add(labelC1);
        labels.add(labelC2);
        labels.add(labelC3);
        labels.add(labelC4);
        labels.add(labelAddOperation);
        for( JLabel label:labels){
            label.setFont(new Font("Verdana", Font.PLAIN, 18));
            label.setForeground(new Color(255, 227, 179));
        }

        solveBtn.addActionListener(new DoCalculation());
    }

    private void takingRawInput()   {
        for (JTextField tf:inputTF) {
            rawInput.add(tf.getText());
        }
    }       ///pobieranie inputu

    private boolean checkInput(){
        int countVariables = 0;
        int counter = 0;
        for (JTextField tf:inputTF) {
            if(Objects.equals(tf.getText(), "")){
                return false;
            }
            else{
                String input = tf.getText();
                if(Pattern.matches("[a-zA-Z]+", input)){
                    varPos = counter;
                    countVariables++;
                }
            }
            counter++;
        }
        return countVariables == 1;
    }       /// sprawdzam czy wszystkie pola wypełnione i czy tylko jedna zmienna

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyWindow");
        MyWindow window = new MyWindow();
        frame.add(window.mainPanel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    int[] fillInputInteger ( ){
        int []inputInteger = new int[10];
        for(int i=0; i<10; i++){
            if(i!=varPos){
                inputInteger[i] = Integer.parseInt(rawInput.get(i));
            }
            else{
                inputInteger[i] = 0;
            }
        }
        return  inputInteger;
    }


    class DoCalculation implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("Clicked");
            rawInput.clear();
            takingRawInput();
            System.out.println(rawInput);
            checkInput();
            System.out.println("Pozycja zmiennej to: "+ varPos);
            try{
                if(checkInput()){
                    String mathematicalOperator = inputTF.get(10).getText();
                    float A = 0;
                    float B = 0;
                    float C = 0;
                    float result=0;

                    int []inputInteger = fillInputInteger(); // wypełniam tablicę intami z inputu. W miejce zmiennej daję 0

                    switch (mathematicalOperator){

                        case "+":{
                            // Obliczanie gdy znak operacji to +
                            switch (varPos){
                                case 0:
                                case 1:
                                case 2:{
                                    B = inputInteger[3]*10*10 + inputInteger[4]*10 + inputInteger[5];
                                    C = inputInteger[6]*10*10*10 + inputInteger[7]*10*10 + inputInteger[8]*10 + inputInteger[9];
                                    switch (varPos){
                                        case 0:{
                                            result = (C-B - inputInteger[2] - inputInteger[1]*10 )/100;
                                            break;
                                        }
                                        case 1:{
                                            result = (C-B - inputInteger[2] - inputInteger[0]*100 )/10;
                                            break;
                                        }
                                        case 2:{
                                            result = (C-B - inputInteger[0]*100 - inputInteger[1]*10 );
                                            break;
                                        }

                                    }
                                    break;
                                }
                                case 3:
                                case 4:
                                case 5:{
                                    C = inputInteger[6]*10*10*10 + inputInteger[7]*10*10 + inputInteger[8]*10 + inputInteger[9];
                                    A = inputInteger[0]*100 + inputInteger[1]*10 + inputInteger[2];
                                    switch (varPos){
                                        case 3:{
                                            result = (C-A - inputInteger[5] - inputInteger[4]*10)/100;
                                            break;
                                        }
                                        case 4:{
                                            result = (C-A - inputInteger[5] - inputInteger[3]*100)/10;
                                            break;
                                        }
                                        case 5:{
                                            result = (C-A - inputInteger[3]*100 - inputInteger[4]*10);
                                            break;
                                        }
                                    }
                                    break;
                                }
                                case 6:
                                case 7:
                                case 8:
                                case 9:{
                                    A = inputInteger[0]*100 + inputInteger[1]*10 + inputInteger[2];
                                    B = inputInteger[3]*10*10 + inputInteger[4]*10 + inputInteger[5];
                                    switch (varPos){
                                        case 6:{
                                            result = (A+B - inputInteger[7]*100 - inputInteger[8]*10 - inputInteger[9])/1000;
                                            break;
                                        }
                                        case 7:{
                                            result = (A+B - inputInteger[6]*1000 - inputInteger[8]*10 - inputInteger[9])/100;
                                            break;
                                        }
                                        case 8:{
                                            result = (A+B - inputInteger[6]*1000 - inputInteger[7]*100  - inputInteger[9])/10;
                                            break;
                                        }
                                        case 9:{
                                            result = (A+B - inputInteger[6]*1000 - inputInteger[7]*100  - inputInteger[8]*10);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                        case "-":{
                            // Obliczanie gdy znak operacji to -
                            switch (varPos){
                                case 0:
                                case 1:
                                case 2:{
                                    B = inputInteger[3]*10*10 + inputInteger[4]*10 + inputInteger[5];
                                    C = inputInteger[6]*10*10*10 + inputInteger[7]*10*10 + inputInteger[8]*10 + inputInteger[9];
                                    switch (varPos){
                                        case 0:{
                                            result = (C+B - inputInteger[2] - inputInteger[1]*10 )/100;
                                            break;
                                        }
                                        case 1:{
                                            result = (C+B - inputInteger[2] - inputInteger[0]*100 )/10;
                                            break;
                                        }
                                        case 2:{
                                            result = (C+B - inputInteger[0]*100 - inputInteger[1]*10 );
                                            break;
                                        }

                                    }
                                    break;
                                }
                                case 3:
                                case 4:
                                case 5:{
                                    C = inputInteger[6]*10*10*10 + inputInteger[7]*10*10 + inputInteger[8]*10 + inputInteger[9];
                                    A = inputInteger[0]*100 + inputInteger[1]*10 + inputInteger[2];
                                    switch (varPos){
                                        case 3:{
                                            result = (A-C - inputInteger[5] - inputInteger[4]*10)/100;
                                            break;
                                        }
                                        case 4:{
                                            result = (A-C - inputInteger[5] - inputInteger[3]*100)/10;
                                            break;
                                        }
                                        case 5:{
                                            result = (A-C - inputInteger[3]*100 - inputInteger[4]*10);
                                            break;
                                        }
                                    }
                                    break;
                                }
                                case 6:
                                case 7:
                                case 8:
                                case 9:{
                                    A = inputInteger[0]*100 + inputInteger[1]*10 + inputInteger[2];
                                    B = inputInteger[3]*10*10 + inputInteger[4]*10 + inputInteger[5];
                                    switch (varPos){
                                        case 6:{
                                            result = (A-B - inputInteger[7]*100 - inputInteger[8]*10 - inputInteger[9])/1000;
                                            break;
                                        }
                                        case 7:{
                                            result = (A-B - inputInteger[6]*1000 - inputInteger[8]*10 - inputInteger[9])/100;
                                            break;
                                        }
                                        case 8:{
                                            result = (A-B - inputInteger[6]*1000 - inputInteger[7]*100  - inputInteger[9])/10;
                                            break;
                                        }
                                        case 9:{
                                            result = (A-B - inputInteger[6]*1000 - inputInteger[7]*100  - inputInteger[8]*10);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                        case "*":{
                            // Obliczanie gdy znak operacji to -
                            switch (varPos){
                                case 0:
                                case 1:
                                case 2:{
                                    B = inputInteger[3]*10*10 + inputInteger[4]*10 + inputInteger[5];
                                    C = inputInteger[6]*10*10*10 + inputInteger[7]*10*10 + inputInteger[8]*10 + inputInteger[9];
                                    switch (varPos){
                                        case 0:{
                                            result = (C*B - inputInteger[2] - inputInteger[1]*10 )/100;
                                            break;
                                        }
                                        case 1:{
                                            result = (C*B - inputInteger[2] - inputInteger[0]*100 )/10;
                                            break;
                                        }
                                        case 2:{
                                            result = (C*B - inputInteger[0]*100 - inputInteger[1]*10 );
                                            break;
                                        }

                                    }
                                    break;
                                }
                                case 3:
                                case 4:
                                case 5:{
                                    C = inputInteger[6]*10*10*10 + inputInteger[7]*10*10 + inputInteger[8]*10 + inputInteger[9];
                                    A = inputInteger[0]*100 + inputInteger[1]*10 + inputInteger[2];
                                    switch (varPos){
                                        case 3:{
                                            result = (A/C - inputInteger[5] - inputInteger[4]*10)/100;
                                            break;
                                        }
                                        case 4:{
                                            result = (A/C - inputInteger[5] - inputInteger[3]*100)/10;
                                            break;
                                        }
                                        case 5:{
                                            result = (A/C - inputInteger[3]*100 - inputInteger[4]*10);
                                            break;
                                        }
                                    }
                                    break;
                                }
                                case 6:
                                case 7:
                                case 8:
                                case 9:{
                                    A = inputInteger[0]*100 + inputInteger[1]*10 + inputInteger[2];
                                    B = inputInteger[3]*10*10 + inputInteger[4]*10 + inputInteger[5];
                                    switch (varPos){
                                        case 6:{
                                            result = (A/B - inputInteger[7]*100 - inputInteger[8]*10 - inputInteger[9])/1000;
                                            break;
                                        }
                                        case 7:{
                                            result = (A/B - inputInteger[6]*1000 - inputInteger[8]*10 - inputInteger[9])/100;
                                            break;
                                        }
                                        case 8:{
                                            result = (A/B - inputInteger[6]*1000 - inputInteger[7]*100  - inputInteger[9])/10;
                                            break;
                                        }
                                        case 9:{
                                            result = (A/B - inputInteger[6]*1000 - inputInteger[7]*100  - inputInteger[8]*10);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }

                        default:{
                            JOptionPane.showMessageDialog( null, "Wprowadzono nieprawidłowy znak operacji matematycznej. Dostępne znaki: +,-,*");
                        }
                    }

                    resultLabel.setText(inputTF.get(varPos).getText()+ ": " + result);

                }
                else{
                    JOptionPane.showMessageDialog( null, "Podanane dane wejsciowe są błędne. Podano więcej niż jedną niewiadomą lub/i nie podano wszystkich wymagacnyh danych");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

/*

case 0:{
                                    B = Integer.parseInt(rawInput.get(3))*10*10 + Integer.parseInt(rawInput.get(4))*10 + Integer.parseInt(rawInput.get(5));
                                    C = Integer.parseInt(rawInput.get(6))*10*10*10 + Integer.parseInt(rawInput.get(7))*10*10 + Integer.parseInt(rawInput.get(8))*10 + Integer.parseInt(rawInput.get(9));
                                    result =
                                }
                                case 1:{



                                }
                                case 2:{



                                }
                                case 3:{
                                    A = Integer.parseInt(rawInput.get(0))*10*10 + Integer.parseInt(rawInput.get(1)) *10 + Integer.parseInt(rawInput.get(2));

                                }
                                case 4:{
                                    A = Integer.parseInt(rawInput.get(0))*10*10 + Integer.parseInt(rawInput.get(1)) *10 + Integer.parseInt(rawInput.get(2));


                                }
                                case 5:{
                                    A = Integer.parseInt(rawInput.get(0))*10*10 + Integer.parseInt(rawInput.get(1)) *10 + Integer.parseInt(rawInput.get(2));

                                }case 6:{
                                    A = Integer.parseInt(rawInput.get(0))*10*10 + Integer.parseInt(rawInput.get(1)) *10 + Integer.parseInt(rawInput.get(2));

                                }
                                case 7:{
                                    A = Integer.parseInt(rawInput.get(0))*10*10 + Integer.parseInt(rawInput.get(1)) *10 + Integer.parseInt(rawInput.get(2));

                                }
                                case 8:{
                                    A = Integer.parseInt(rawInput.get(0))*10*10 + Integer.parseInt(rawInput.get(1)) *10 + Integer.parseInt(rawInput.get(2));

                                }
 */
