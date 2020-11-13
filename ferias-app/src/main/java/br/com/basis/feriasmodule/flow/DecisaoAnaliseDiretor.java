package br.com.basis.feriasmodule.flow;

import br.com.basis.feriasmodule.form.FeriasForm;
import org.opensingular.flow.core.ExecutionContext;
import org.opensingular.flow.core.TaskJavaCall;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInstance;
import org.opensingular.requirement.module.service.RequirementInstance;
import org.opensingular.requirement.module.service.RequirementService;

import javax.inject.Inject;
import java.util.Optional;

public class DecisaoAnaliseDiretor implements TaskJavaCall {

    @Inject
    private RequirementService requirementService;

    @Override
    public void call(ExecutionContext executionContext) {
        RequirementInstance requirementInstance=requirementService.getRequirementInstance(executionContext.getFlowInstance());

        Optional<SInstance> feriasForm=requirementService.findLastFormRequirementInstanceByType(requirementInstance, FeriasForm.class);

        if(feriasForm.isPresent()) {
            SIComposite form = (SIComposite) feriasForm.get();
            Boolean anteciparDecimoTerceiro = form.getValueBoolean("anteciparDecimoTerceiro");

            if (anteciparDecimoTerceiro) {
                executionContext.setTransition(FeriasFlow.ENVIAR_PARA_DIRETOR);
            } else {
                executionContext.setTransition(FeriasFlow.ENVIAR_PARA_RH);
            }
        }
    }
}
