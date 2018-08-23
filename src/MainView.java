import javax.swing.*;
import java.awt.*;
import java.awt.Button;

public class MainView extends JFrame {



    public MainView(){
        super("String Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Controller controller = new Controller(this);

        Box box = Box.createVerticalBox();


        box.add(Box.createVerticalStrut(10));

        JTextField currentString = new JTextField("0");
        currentString.setMaximumSize(new Dimension(400,15));
        currentString.setEditable(false);
        box.add(currentString);

        box.add(Box.createVerticalStrut(10));

        JPanel answerPanel = new JPanel();
        JLabel answerText = new JLabel("Answer: ");
        JTextField answer = new JTextField();

        answerPanel.setMaximumSize(new Dimension(400, 15));
        answerPanel.add(answerText,answer);
        answerPanel.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        box.add(Box.createVerticalStrut(10));
        box.add(answerPanel);

        box.add(Box.createVerticalStrut(10));

        JPanel buttonsPanel = new JPanel();

        buttonsPanel.setLayout(new GridLayout(5,4,10,10));
        buttonsPanel.add(new Button("("));
        buttonsPanel.add(new Button(")"));
        buttonsPanel.add(new Button("?"));
        buttonsPanel.add(new Button("c"));
        buttonsPanel.add(new Button("7"));
        buttonsPanel.add(new Button("8"));
        buttonsPanel.add(new Button("9"));
        buttonsPanel.add(new Button("/"));
        buttonsPanel.add(new Button("4"));
        buttonsPanel.add(new Button("5"));
        buttonsPanel.add(new Button("6"));
        buttonsPanel.add(new Button("*"));
        buttonsPanel.add(new Button("1"));
        buttonsPanel.add(new Button("2"));
        buttonsPanel.add(new Button("3"));
        buttonsPanel.add(new Button("-"));
        buttonsPanel.add(new Button("0"));
        buttonsPanel.add(new Button("."));
        buttonsPanel.add(new Button("="));
        buttonsPanel.add(new Button("+"));
        box.add(buttonsPanel);

        box.add(Box.createVerticalStrut(10));
        setContentPane(box);
        setSize(400, 600);
    }

}
