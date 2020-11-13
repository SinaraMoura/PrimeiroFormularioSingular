package br.com.basis.feriasmodule.page;

import  br.com.basis.feriasmodule.flow.FeriasFlow;
import  br.com.basis.feriasmodule.form.STParecerTecnico;
import  br.com.basis.feriasmodule.transition.SimpleTransitionController;
import org.opensingular.requirement.module.wicket.view.form.FormPage;
import org.opensingular.requirement.module.wicket.view.form.TransitionController;
import org.opensingular.requirement.module.wicket.view.util.ActionContext;

import javax.annotation.Nullable;
import java.util.Map;

public class AnaliseTecnicaPage extends FormPage {
    public AnaliseTecnicaPage() {
    }

    public AnaliseTecnicaPage(@Nullable ActionContext context) {
        super(context);
    }

    @Override
    protected void fillTransitionControllerMap(Map<String, TransitionController<?>> transitionControllerMap) {
        super.fillTransitionControllerMap(transitionControllerMap);
        transitionControllerMap.put(FeriasFlow.APROVAR, new SimpleTransitionController<>(STParecerTecnico.class));
        transitionControllerMap.put(FeriasFlow.REPROVAR, new SimpleTransitionController<>(STParecerTecnico.class));
    }
}