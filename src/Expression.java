public class Expression {
    private String text;

    public Expression(){
        text="0";
    }

    public String getText() {
        return text;
    }

    public void addSymbol(String symbol){
        if (text.equals("0")){
        text=symbol;
        }
        else {text=text+symbol;}
    }
    public void deleteSymbol(){
        if (!text.equals("0")) text=text.substring(0,text.length()-1);
    }

    public String evaluate() {
        //TODO code this method
        String result="TEST";
        return result;
    }
}
