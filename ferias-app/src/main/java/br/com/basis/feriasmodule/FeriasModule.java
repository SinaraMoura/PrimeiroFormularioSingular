package br.com.basis.feriasmodule;

import br.com.basis.feriasmodule.box.ChefiaBox;
import br.com.basis.feriasmodule.box.DiretorBox;
import br.com.basis.feriasmodule.box.FinalizadosBox;
import br.com.basis.feriasmodule.box.RHBox;
import br.com.basis.feriasmodule.workspace.ExigenciaBox;
import org.apache.wicket.Page;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.requirement.module.RequirementRegistry;
import org.opensingular.requirement.module.config.DefaultContexts;
import org.opensingular.requirement.module.config.ServerContext;
import org.opensingular.requirement.module.config.workspace.Workspace;
import org.opensingular.requirement.module.config.workspace.WorkspaceSettings;
import org.opensingular.requirement.module.wicket.SingularRequirementApplication;
import org.opensingular.requirement.module.workspace.DefaultDonebox;
import org.opensingular.requirement.module.workspace.DefaultDraftbox;
import org.opensingular.requirement.module.workspace.DefaultInbox;
import org.opensingular.requirement.module.workspace.DefaultOngoingbox;
import org.opensingular.requirement.module.workspace.WorkspaceRegistry;
import org.opensingular.requirement.studio.init.StudioSingularModule;

public class FeriasModule implements StudioSingularModule {
    public static final String FERIAS = "FERIAS";

    @Override
    public String abbreviation() {
        return FERIAS;
    }

    @Override
    public String name() {
        return "Módulo Ferias";
    }

    @Override
    public void requirements(RequirementRegistry requirementRegistry) {
        requirementRegistry
            .add(FeriasRequirement.class);
    }

    @Override
    public void workspace(WorkspaceRegistry workspaceRegistry) {
        workspaceRegistry
            .add(RequirementContext.class)
            .add(WorklistContext.class);
    }

    public static class WorklistContext extends DefaultContexts.WorklistContext {
        @Override
        public void configure(Workspace workspace) {
            workspace
            .menu()
            .addCategory("Worklist", category -> category
                .icon(DefaultIcons.FOLDER)
                .addBox(ChefiaBox.class)
                .addBox(RHBox.class)
                .addBox(DiretorBox.class)
                .addBox(FinalizadosBox.class));


        }
    }

     public static class RequirementContext extends DefaultContexts.RequirementContext {
        @Override
        public void configure(Workspace workspace) {
            workspace
                .menu()
                .addCategory("Requerimentos", reqs -> reqs
                    .icon(DefaultIcons.ROCKET)
                    .addBox(DefaultDraftbox.class, box -> box.newFor(FeriasRequirement.class))
                    .addBox(ExigenciaBox.class)
                    .addBox(DefaultOngoingbox.class));
        }
    }
}