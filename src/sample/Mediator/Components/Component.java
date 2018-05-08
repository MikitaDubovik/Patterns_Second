package sample.Mediator.Components;

import sample.Mediator.Mediator;

public interface Component {
    void SetMediator(Mediator mediator);
    String GetName();
}
