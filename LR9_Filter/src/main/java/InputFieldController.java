import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;
import java.util.function.Predicate;

public class InputFieldController {
    public InputField inputField;
    private Predicate<String> validateFunc;
    public InputFieldModel inputFieldModel;

    public InputFieldController(InputField inputField,Predicate<String> validateFunc,boolean optional){
        this.inputField = inputField;
        this.validateFunc = validateFunc;
        this.inputFieldModel = new InputFieldModel(optional);

        this.inputField.mainInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                this.processInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                this.processInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                this.processInput();
            }

            public void processInput() {
                InputFieldController.this.setTextValue();
                InputFieldController.this.checkInputCorrect();
            }
        });

    }
    public void changeInputFieldBack(boolean correctness){
        if (correctness) {
            this.inputField.mainInputField.setBackground(Color.WHITE); // Óñòàíàâëèâàåì áåëûé ôîí äëÿ êîððåêòíîãî ââîäà
        } else {
            this.inputField.mainInputField.setBackground(Color.RED); // Óñòàíàâëèâàåì êðàñíûé ôîí äëÿ íåêîððåêòíîãî ââîäà
        }
    }
    public boolean checkInputCorrect() {
        boolean result = this.validateFunc.test(this.inputField.mainInputField.getText()) ||
                (Objects.equals(this.inputField.mainInputField.getText(), "") && this.inputFieldModel.optional);
        changeInputFieldBack(result);
        return result;
    }
    public void setTextValue(){
        this.inputFieldModel.setInputText(this.inputField.mainInputField.getText());
    }

    public void setInputText(String val){
        this.inputField.mainInputField.setText(val);
    }
    public void setInputText(){
        this.inputField.mainInputField.setText(this.inputFieldModel.inputText);
    }

    public String getTextValue() {return this.inputField.mainInputField.getText();}

    public void changeEditable(boolean edit){
        this.inputField.mainInputField.setEnabled(edit);
    }
}
