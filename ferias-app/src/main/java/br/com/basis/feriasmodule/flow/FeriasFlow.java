package br.com.basis.feriasmodule.flow;

import org.opensingular.flow.core.DefinitionInfo;
import org.opensingular.flow.core.FlowInstance;
import org.opensingular.flow.core.ITaskDefinition;
import org.opensingular.flow.core.defaults.PermissiveTaskAccessStrategy;
import org.opensingular.requirement.module.flow.builder.RequirementFlowBuilder;
import org.opensingular.requirement.module.flow.builder.RequirementFlowDefinition;
import org.opensingular.requirement.module.wicket.view.form.FormPage;
import br.com.basis.feriasmodule.FeriasModule;
import br.com.basis.feriasmodule.page.AnaliseTecnicaPage;

import javax.annotation.Nonnull;

import static br.com.basis.feriasmodule.flow.FeriasFlow.Tasks.*;


@DefinitionInfo("ferias")
public class FeriasFlow extends RequirementFlowDefinition<FlowInstance> {

        public static final String SOLICITAR_AJUSTES = "Solicitar ajustes";
        public static final String ENVIAR_PARA_DIRETOR = "Enviar para Diretor";
        public static final String ENVIAR_PARA_RH= "Enviar para o RH";
        public static final String APROVAR = "Aprovar";
        public static final String REPROVAR = "Reprovar";
        public static final String CONCLUIR_PENDÊNCIA = "Concluir Pendência";

        public enum Tasks implements ITaskDefinition {
        ANALISE_CHEFIA("Analise da Chefia"),
        DECISAO_ANTECIPACAO_13("Encaminhar"),
        ANALISE_DIRETOR("Analise do Diretor"),
        ANALISE_RH("Análise do RH"),
        APROVADO("Aprovado"),
        REPROVADO("Reprovado"),
        SOLICITACAO_COM_PENDENCIAS("Solicitação com pendências"),
        FINALIZADOS ("Requirementos concluidos");

        private String taskName;

        Tasks(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String getName() {
            return taskName;
        }
    }

    public FeriasFlow() {
        super(FlowInstance.class);
        this.setName(FeriasModule.FERIAS, "Ferias");
    }

    @Override
    protected void buildFlow(@Nonnull RequirementFlowBuilder flow) {

        flow.addHumanTask(ANALISE_CHEFIA)
                .uiAccess(new PermissiveTaskAccessStrategy())
                .withExecutionPage(FormPage.class);

        flow.addJavaTask(DECISAO_ANTECIPACAO_13)
                .call(new DecisaoAnaliseDiretor());

        flow.addHumanTask(ANALISE_DIRETOR)
                .uiAccess(new PermissiveTaskAccessStrategy())
                .withExecutionPage(FormPage.class);

        flow.addHumanTask(ANALISE_RH)
                .uiAccess(new PermissiveTaskAccessStrategy())
                .withExecutionPage(FormPage.class);

        flow.addWaitTask(SOLICITACAO_COM_PENDENCIAS)
                .uiAccess(new PermissiveTaskAccessStrategy())
                .withExecutionPage(FormPage.class);

        flow.addEndTask(REPROVADO);
        flow.addEndTask(APROVADO);

        flow.setStartTask(ANALISE_CHEFIA);

        flow.from(ANALISE_CHEFIA).go(SOLICITAR_AJUSTES, SOLICITACAO_COM_PENDENCIAS);
        flow.from(ANALISE_CHEFIA).go(DECISAO_ANTECIPACAO_13);
        flow.from(ANALISE_CHEFIA).go(REPROVAR, REPROVADO);

        flow.from(DECISAO_ANTECIPACAO_13).go(ENVIAR_PARA_DIRETOR, ANALISE_DIRETOR);
        flow.from(DECISAO_ANTECIPACAO_13).go(ENVIAR_PARA_RH,ANALISE_RH);

        flow.from(ANALISE_DIRETOR).go(ENVIAR_PARA_RH,ANALISE_RH);
        flow.from(ANALISE_DIRETOR).go(REPROVAR, REPROVADO);

        flow.from(ANALISE_RH).go(APROVAR,APROVADO);
        flow.from(ANALISE_RH).go(REPROVAR,REPROVADO);

        flow.from(SOLICITACAO_COM_PENDENCIAS).go(CONCLUIR_PENDÊNCIA, ANALISE_CHEFIA);
    }

}