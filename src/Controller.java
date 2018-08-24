import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Controller extends MouseAdapter implements MouseListener {
    private MainView parent;
    private int bracketsCounter = 0;


    public Controller(MainView parent) {
        this.parent = parent;
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        JButton clickedButton = (JButton) event.getComponent();
        String text = parent.currentExpression.getText();
        String lastSymbol = text.substring(text.length() - 1);
        String symbol = clickedButton.getText();
        switch (clickedButton.getText()) {
            case "?":
                clickedButton.setText(":");
                parent.error.setText("Support for : button in work");
                break;
            case ":":
                clickedButton.setText("?");
                parent.error.setText("Support for ? button in work");
                break;
            case "c":


                if (lastSymbol.equals("(")) bracketsCounter--;
                if (lastSymbol.equals(")")) bracketsCounter++;
                if (text.length() > 1) parent.currentExpression.setText(text.substring(0, text.length() - 1));
                else if (!(text.equals(" "))) parent.currentExpression.setText(" ");


                break;

            case "=": {

                if (bracketsCounter == 0) {
                    if (isSign(lastSymbol)) {
                        parent.error.setText("Invalid expression");
                    } else {
                        parent.answer.setText(evaluate(text));
                        parent.currentExpression.setText(" ");
                        bracketsCounter = 0;
                    }
                } else parent.error.setText("Mistake in parentheses");
                break;
            }
            case "(":

                if (text.equals(" ")) {
                    parent.currentExpression.setText("(");
                    bracketsCounter++;
                } else if (lastSymbol.equals("(") || isSign(lastSymbol)) {
                    bracketsCounter++;
                    parent.currentExpression.setText(text + symbol);
                } else parent.error.setText("Can not open parentheses");
                break;
            case ")":
                if (bracketsCounter > 0) {
                    if (!(lastSymbol.equals(".") || lastSymbol.equals("(") || isSign(lastSymbol))) {
                        bracketsCounter--;
                        parent.currentExpression.setText(text + symbol);
                    } else parent.error.setText("Can not close parentheses");

                } else parent.error.setText("Can not close parentheses");

                break;

            case "+":
            case "*":
            case "/":
                if (!text.equals(" ")) {
                    if (isSign(lastSymbol)) {
                        parent.currentExpression.setText(text.substring(0, text.length() - 1) + symbol);
                    } else if (!(lastSymbol.equals(".") || lastSymbol.equals("(") || isSign(lastSymbol)))
                        parent.currentExpression.setText(text + symbol);
                    else parent.error.setText("Can not add sign");
                } else parent.error.setText("Can not add sign");
                break;
            case "-":
                if (isSign(lastSymbol)) {
                    parent.currentExpression.setText(text.substring(0, text.length() - 1) + symbol);
                } else if (!(lastSymbol.equals(".") || isSign(lastSymbol)))
                    parent.currentExpression.setText(text + symbol);
                else parent.error.setText("Can not add sign");
                break;

            case ".":
                if (!(lastSymbol.equals(")") || lastSymbol.equals(".") || lastSymbol.equals("(") || isSign(lastSymbol)))
                    parent.currentExpression.setText(text + symbol);
            default:

                if (text.equals(" ")) {
                    parent.currentExpression.setText(symbol);
                } else if (!lastSymbol.equals(")")) {
                    parent.currentExpression.setText(text + symbol);
                } else parent.error.setText("Can not add number");


                break;
        }
    }

    //evaluation using Shunting-yard algorithm
    private String evaluate(String expression) {
        String result = makeReversePolishNotation(expression);

        //TODO convert to polish notation and evaluate
        return result;

    }

    private String makeReversePolishNotation(String expression) {
        String separators = "()*+/-";
        String result = "";
        Stack<String> stackOperations = new Stack<String>();
        // RPN - reverse polish notation
        Stack<String> stackRPN = new Stack<String>();
        Stack<String> stackTemp = new Stack<String>();
        //splitting expression into tokens
        StringTokenizer stringTokenizer = new StringTokenizer(updateUnaryMinus(expression), separators, true);


        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (isNumber(token)) {
                stackRPN.push(token);
            } else if (isOpenBracket(token)) {
                stackOperations.push(token);
            } else if (isCloseBracket(token)) {
                while (!isOpenBracket(stackOperations.lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
                stackOperations.pop();
            } else if (isSign(token)) {

                while (!stackOperations.empty() && isSign(stackOperations.lastElement())
                        && getPrecedence(stackOperations.lastElement()) > getPrecedence(token)) {
                    stackRPN.push(stackOperations.pop());
                }
                stackOperations.push(token);
            }

        }
        while (!stackOperations.empty()) {
            stackRPN.push(stackOperations.pop());
        }

        Collections.reverse(stackRPN);
        while (!stackRPN.empty()) {
            if (isNumber(stackRPN.lastElement())) stackTemp.push(stackRPN.pop());
            else stackTemp.push(makeOperation(stackRPN.pop(), stackTemp.pop(), stackTemp.pop()));
        }
        result = stackTemp.pop();
        return result;
    }

    private String updateUnaryMinus(String expression) {
        String previous = expression.substring(0, 1);
        if (previous.equals("-")) {
            expression = "(0" + expression + ")";
            previous = "0";
        }
        if (expression.length() > 1) {

            for (int i = 1; i < expression.length(); i++) {

                if (expression.substring(i, i + 1).equals("-") && !isNumber(previous)) {
                    expression = expression.substring(0, i) + "0" + expression.substring(i, expression.length());
                    previous = "-";
                    i = i + 1;
                    System.out.println(expression);
                } else previous = expression.substring(i, i + 1);
            }

        }
        return expression;
    }

    private boolean isSign(String symbol) {
        switch (symbol) {
            case "-":
            case "+":
            case "*":
            case "/":
                return true;
        }
        return false;
    }

    private boolean isNumber(String symbol) {
        try {
            Double.parseDouble(symbol);
        } catch (Exception e) {

            return false;
        }
        return true;
    }

    private boolean isOpenBracket(String symbol) {
        return symbol.equals("(");
    }

    private boolean isCloseBracket(String symbol) {
        return symbol.equals(")");
    }

    private byte getPrecedence(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1;
        }
        return 2;
    }

    private String makeOperation(String operation, String secondOperand, String firstOperand) {
        switch (operation) {
            case "+":
                return String.valueOf(Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand));
            case "-":
                return String.valueOf(Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand));
            case "*":
                return String.valueOf(Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand));
            case "/":
                return String.valueOf(Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand));
            default:
                return operation;
        }
    }
}
