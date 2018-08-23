import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainView extends JFrame {

    Controller controller;
    Expression expression;
    JTextField answer;


    public MainView(){
        super("String Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controller = new Controller(this);
        expression = new Expression();

        Box box = Box.createVerticalBox();

        box.add(Box.createVerticalStrut(10));

        JTextField currentExpression = new JTextField(expression.getText());
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
        ArrayList<CalculatorButton> buttons = new ArrayList<>();
        buttons.add(new CalculatorButton("("));
        buttons.add(new CalculatorButton(")"));
        buttons.add(new CalculatorButton("?"));
        buttons.add(new CalculatorButton("c"));
        buttons.add(new CalculatorButton("7"));
        buttons.add(new CalculatorButton("8"));
        buttons.add(new CalculatorButton("9"));
        buttons.add(new CalculatorButton("/"));
        buttons.add(new CalculatorButton("4"));
        buttons.add(new CalculatorButton("5"));
        buttons.add(new CalculatorButton("6"));
        buttons.add(new CalculatorButton("*"));
        buttons.add(new CalculatorButton("1"));
        buttons.add(new CalculatorButton("2"));
        buttons.add(new CalculatorButton("3"));
        buttons.add(new CalculatorButton("-"));
        buttons.add(new CalculatorButton("0"));
        buttons.add(new CalculatorButton("."));
        buttons.add(new CalculatorButton("="));
        buttons.add(new CalculatorButton("+"));
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
