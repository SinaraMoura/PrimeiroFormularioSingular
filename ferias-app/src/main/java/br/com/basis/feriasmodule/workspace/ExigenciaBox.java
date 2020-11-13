package br.com.basis.feriasmodule.workspace;

import br.com.basis.feriasmodule.action.ResponderExigenciaAction;
import br.com.basis.feriasmodule.flow.FeriasFlow;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.requirement.module.ActionProviderBuilder;
import org.opensingular.requirement.module.box.BoxItemData;
import org.opensingular.requirement.module.box.action.BoxItemActionList;
import org.opensingular.requirement.module.persistence.filter.BoxFilter;
import org.opensingular.requirement.module.provider.RequirementBoxItemDataProvider;
import org.opensingular.requirement.module.service.dto.DatatableField;
import org.opensingular.requirement.module.service.dto.ItemBox;
import org.opensingular.requirement.module.workspace.AbstractRequirementBoxDefinition;

import java.util.ArrayList;
import java.util.List;

public class ExigenciaBox extends AbstractRequirementBoxDefinition {
   public ExigenciaBox() {
   }

   public void configure(ItemBox itemBox) {
      itemBox
         .name("Exigência")
         .description("Requerimentos em exigência")
         .icon(DefaultIcons.EXCLAMATION_TRIANGLE);
   }

   protected void addActions(ActionProviderBuilder builder) {
      builder
         .addCustomActions(new ActionProviderBuilder.ActionConfigurer() {
            @Override
            public void configure(BoxItemData boxItemData, BoxFilter boxFilter, BoxItemActionList boxItemActionList) {
               boxItemActionList.addAction(new ResponderExigenciaAction(boxItemData));
            }
         });
      ;
   }

   public List<DatatableField> getDatatableFields() {
      List<DatatableField> fields = new ArrayList<>();
      fields.add(DatatableField.of("Número", "codRequirement"));
      fields.add(DatatableField.of("Descrição", "description"));
      fields.add(DatatableField.of("Dt. Entrada", "processBeginDate"));
      fields.add(DatatableField.of("Situação", "situation"));
      fields.add(DatatableField.of("Dt. Situação", "situationBeginDate"));
      return fields;
   }

   @Override
   public RequirementBoxItemDataProvider getDataProvider() {
      return super.getDataProvider()
         .addTask(FeriasFlow.Tasks.SOLICITACAO_COM_PENDENCIAS);
   }

   public BoxFilter createBoxFilter() {
      return super.createBoxFilter().checkApplicant(true);
   }
}