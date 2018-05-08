package sample.Mediator.Components;

import javafx.scene.control.TextField;
import sample.Mediator.Mediator;

public class NumberFilter implements Component {
    private Mediator mediator;
    private TextField textField;

    @Override
    public void SetMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String GetName() {
        return "NumberFilter";
    }

    public void setTextField(TextField textField){
        this.textField = textField;
    }

    public TextField getTextField() {
        return textField;
    }
}