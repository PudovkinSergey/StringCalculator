import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller extends MouseAdapter implements MouseListener{
    private MainView parent;

    public Controller(MainView parent) {
        this.parent=parent;
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        CalculatorButton clickedButton = (CalculatorButton) event.getComponent();
        switch (clickedButton.getSymbol()){
            case "c":
                parent.expression.deleteSymbol();
                break;

            case "=": {
                System.out.println(parent.expression.evaluate());
                parent.answer.setText( parent.expression.evaluate());
                break;
            }
        }
    }
}
