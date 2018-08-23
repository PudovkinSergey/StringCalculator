import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainView extends JFrame {

    Controller controller;
    JTextField answer;
    JTextField currentExpression;

    public MainView(){
        super("String Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        controller = new Controller(this);

        Box box = Box.createVerticalBox();

        box.add(Box.createVerticalStrut(10));

        currentExpression = new JTextField("0");
        currentExpression.setMaximumSize(new Dimension(400,15));
        currentExpression.setEditable(false);
        box.add(currentExpression);

        box.add(Box.createVerticalStrut(10));

        answer = new JTextField("Answer");
        answer.setMaximumSize(new Dimension(400, 15));
        answer.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        answer.setEditable(false);
        box.add(answer);

        box.add(Box.createVerticalStrut(10));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5,4,10,10));
        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(new JButton("("));
        buttons.add(new JButton(")"));
        buttons.add(new JButton("?"));
        buttons.add(new JButton("c"));
        buttons.add(new JButton("7"));
        buttons.add(new JButton("8"));
        buttons.add(new JButton("9"));
        buttons.add(new JButton("/"));
        buttons.add(new JButton("4"));
        buttons.add(new JButton("5"));
        buttons.add(new JButton("6"));
        buttons.add(new JButton("*"));
        buttons.add(new JButton("1"));
        buttons.add(new JButton("2"));
        buttons.add(new JButton("3"));
        buttons.add(new JButton("-"));
        buttons.add(new JButton("0"));
        buttons.add(new JButton("."));
        buttons.add(new JButton("="));
        buttons.add(new JButton("+"));
        for (int i = 0; i<buttons.size();i++)
            {
                buttons.get(i).addMouseListener(controller);
                buttonsPanel.add(buttons.get(i));
            }
        box.add(buttonsPanel);

        box.add(Box.createVerticalStrut(10));
        setContentPane(box);
        setSize(400, 600);
    }

}
