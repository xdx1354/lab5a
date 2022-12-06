import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
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
    private JLabel labelAddOperation;
    private JPanel mainPanel;

    private List<JTextField> inputTF = new ArrayList<>();
    private ArrayList <String> rawInput = new ArrayList<>();

    public  MyWindow(){
        //tworze tablice wszystkich TF
        inputTF.add(tfA1);              //inputTF.get(0)
        inputTF.add(tfA2);              //inputTF.get(1)
        inputTF.add(tfA3);              //inputTF.get(2)
        inputTF.add(tfB1);              //inputTF.get(3)
        inputTF.add(tfB2);              //inputTF.get(4)
        inputTF.add(tfB3);              //inputTF.get(5)
        inputTF.add(tfC1);              //inputTF.get(6)
        inputTF.add(tfC3);              //inputTF.get(7)
        inputTF.add(tfC4);              //inputTF.get(8)
        inputTF.add(tfAddOperation);    //inputTF.get(9)


        solveBtn.addActionListener(new DoCalculation());
    }

    private void takingRawInput(){
        for (JTextField tf:inputTF) {
            rawInput.add(tf.getText());
        }
    }       ///pobieranie inputu

    private boolean checkInput(){
        int countVariables = 0;
        for (JTextField tf:inputTF) {
            if(Objects.equals(tf.getText(), "")){
                return false;
            }
            else{
                String input = tf.getText();
                if(Pattern.matches("[a-zA-Z]+", input)){
                    countVariables++;
                }
            }
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

    class DoCalculation implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("Clicked");
            try{

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
