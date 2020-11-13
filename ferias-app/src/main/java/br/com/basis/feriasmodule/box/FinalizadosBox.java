package br.com.basis.feriasmodule.box;

import br.com.basis.feriasmodule.action.ImprimirTermoFeriasAction;
import org.opensingular.requirement.module.ActionProviderBuilder;
import org.opensingular.requirement.module.workspace.DefaultDonebox;



public class FinalizadosBox extends DefaultDonebox {
    protected void addActions(ActionProviderBuilder Builder) {
        Builder
                .addHistoryAction()
                .addCustomActions((boxItemData, boxFilter, boxItemActionList) -> {
                    boxItemActionList.addAction(new ImprimirTermoFeriasAction(boxItemData.getRequirementId()));
                });
    }

}
