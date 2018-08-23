import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller extends MouseAdapter implements MouseListener {
    private MainView parent;
    private int bracketCounter = 0;


    public Controller(MainView parent) {
        this.parent = parent;
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        JButton clickedButton = (JButton) event.getComponent();
        String text = parent.currentExpression.getText();
        String lastSymbol =text.substring(text.length()-1);
        String symbol = clickedButton.getText();
        switch (clickedButton.getText()) {
            case "c":


                if (lastSymbol.equals("(")) bracketCounter--;
                if (lastSymbol.equals(")")) bracketCounter++;
                if (lastSymbol.equals("+")||lastSymbol.equals("-")||lastSymbol.equals("/")||lastSymbol.equals("*")) ;
                if (text.length()>1) parent.currentExpression.setText(text.substring(0, text.length() - 1));
                else if(!(text.equals("0"))) parent.currentExpression.setText("0");


                break;

            case "=": {
                String result = "TEST";
                parent.answer.setText(result);
                parent.currentExpression.setText("0");
                break;
            }
            case "(":

                if (text.equals("0")) {parent.currentExpression.setText("("); bracketCounter++;}
                else if (lastSymbol.equals("(")||lastSymbol.equals("+")||
                        lastSymbol.equals("-")||lastSymbol.equals("/")||lastSymbol.equals("*")) {
                    bracketCounter++;
                    parent.currentExpression.setText(text + symbol);
                } else parent.error.setText("Can not open parentheses");
                break;
            case ")":
                if (bracketCounter > 0) {
                    if (!(lastSymbol.equals(".")||lastSymbol.equals("(")||lastSymbol.equals("+")||
                            lastSymbol.equals("-")||lastSymbol.equals("/")||lastSymbol.equals("*")))
                    {bracketCounter--;
                    parent.currentExpression.setText(text + symbol);}
                    else parent.error.setText("Can not close parentheses");

                } else parent.error.setText("Can not close parentheses");

                break;

            case "+":
            case "-":
            case "*":
            case "/":
                if (lastSymbol.equals("+")||
                        lastSymbol.equals("-")||lastSymbol.equals("/")||lastSymbol.equals("*")) {
                    parent.currentExpression.setText(text.substring(0,text.length()-1)+symbol);
                }
                else if (!(lastSymbol.equals(".")||lastSymbol.equals("(")||lastSymbol.equals("+")||
                        lastSymbol.equals("-")||lastSymbol.equals("/")||lastSymbol.equals("*")))
                    parent.currentExpression.setText(text+symbol);
                else parent.error.setText("Can not add sign");
                break;
            case ".":
                if (!(lastSymbol.equals(")")||lastSymbol.equals(".")||lastSymbol.equals("(")||lastSymbol.equals("+")||
                        lastSymbol.equals("-")||lastSymbol.equals("/")||lastSymbol.equals("*")))
                    parent.currentExpression.setText(text+symbol);
            default:

                if (text.equals("0")) {
                    parent.currentExpression.setText(symbol);
                } else if (!lastSymbol.equals(")")) {
                    parent.currentExpression.setText(text + symbol);
                }
                else parent.error.setText("Can not add number");


                break;
        }
    }

}
