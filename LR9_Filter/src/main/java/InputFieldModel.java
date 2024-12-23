public class InputFieldModel {
    public String inputText = "";

    public boolean optional = false;
    public InputFieldModel(boolean optional){
        this.optional = optional;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }
}
