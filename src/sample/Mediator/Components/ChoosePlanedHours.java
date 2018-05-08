package sample.Mediator.Components;

import javafx.scene.control.ChoiceBox;
import sample.Mediator.Mediator;

public class ChoosePlanedHours extends ChoiceBox<String> implements Component {
    private Mediator mediator;

    @Override
    public void SetMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String GetName() {
        return "ChoosePlanedHours";
    }
}
