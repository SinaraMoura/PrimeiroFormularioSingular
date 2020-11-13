package br.com.basis.feriasmodule.transition;

import org.opensingular.form.SType;
import org.opensingular.requirement.module.wicket.view.form.TransitionController;

public class SimpleTransitionController<T extends SType<?>> implements TransitionController<T> {
    private final Class<T> type;

    public SimpleTransitionController(Class<T> type) {
        this.type = type;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public boolean isValidatePageForm() {
        return false;
    }
}