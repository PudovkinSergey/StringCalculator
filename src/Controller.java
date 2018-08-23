import javax.swing.*;
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

        JButton clickedButton = (JButton) event.getComponent();
        String text = parent.currentExpression.getText();
        switch (clickedButton.getText()){
            case "c":
                if (!text.equals("0")) parent.currentExpression.setText(text.substring(0,text.length()-1));
                break;

            case "=": {
                String result="TEST";
                parent.answer.setText(result);
                parent.currentExpression.setText("0");
                break;
            }
            default:
                String symbol=clickedButton.getText();
                if (text.equals("0")){
                    text=symbol;
                }
                else {text=text+symbol;}
                parent.currentExpression.setText(text);
                break;
        }
    }
}
