package br.com.basis.feriasmodule.box;

import br.com.basis.feriasmodule.flow.FeriasFlow;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.requirement.module.ActionProviderBuilder;
import org.opensingular.requirement.module.provider.RequirementBoxItemDataProvider;
import org.opensingular.requirement.module.service.dto.DatatableField;
import org.opensingular.requirement.module.service.dto.ItemBox;
import org.opensingular.requirement.module.workspace.AbstractRequirementBoxDefinition;

import java.util.ArrayList;
import java.util.List;

public class RHBox extends AbstractRequirementBoxDefinition {
    @Override
    protected void addActions(ActionProviderBuilder Builder) {
        Builder
                .addAssignAction()
                .addAnalyseAction()
                .addRelocateAction()
                .addHistoryAction();
    }

    @Override
    public RequirementBoxItemDataProvider getDataProvider(){
        return super.getDataProvider().addTask(FeriasFlow.Tasks.ANALISE_RH);
    }

    @Override
    protected void configure(ItemBox itemBox) {
            itemBox
                    .name("RH")
                    .description("Aguardando análise do RH")
                    .icon(DefaultIcons.DOCS)
                    .evalPermission(true);
    }

    @Override
    public List<DatatableField> getDatatableFields() {
        List<DatatableField> fields = new ArrayList();
        fields.add(DatatableField.of("Número", "codRequirement"));
        fields.add(DatatableField.of("Solicitante", "solicitante"));
        fields.add(DatatableField.of("Descrição", "description"));
        fields.add(DatatableField.of("Dt. de Entrada", "processBeginDate"));
        fields.add(DatatableField.of("Situação", "taskName"));
        fields.add(DatatableField.of("Dt. Situação", "situationBeginDate"));
        fields.add(DatatableField.of("Alocado", "nomeUsuarioAlocado"));
        return fields;
    }
}
