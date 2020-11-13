package br.com.basis.feriasmodule;

import org.opensingular.requirement.module.RequirementDefinition;
import org.opensingular.requirement.module.builder.RequirementConfigurationBuilder;
import org.opensingular.requirement.module.builder.RequirementDefinitionConfiguration;
import org.opensingular.requirement.module.service.RequirementInstance;
import br.com.basis.feriasmodule.form.FeriasForm;
import br.com.basis.feriasmodule.flow.FeriasFlow;

public class FeriasRequirement extends RequirementDefinition<RequirementInstance> {
    public FeriasRequirement() {
        super(FeriasRequirement.class.getSimpleName(), RequirementInstance.class);
    }

    @Override
    public RequirementDefinitionConfiguration configure(RequirementConfigurationBuilder conf) {
        return conf
                .name("Formulário Ferias")
                .mainForm(FeriasForm.class)
                .flowDefintion(FeriasFlow.class)
                .build();
    }
}