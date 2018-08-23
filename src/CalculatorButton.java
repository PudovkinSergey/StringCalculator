import javax.swing.*;

public class CalculatorButton extends JButton {
    private String symbol;

    public CalculatorButton(String symbol) {
        super(symbol);
        this.symbol = symbol;

    }


    public String getSymbol() {
        return symbol;
    }
}
