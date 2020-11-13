package br.com.basis.feriasmodule.action;

import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.requirement.module.box.BoxItemData;
import org.opensingular.requirement.module.box.action.AbstractURLPopupBoxItemAction;
import org.opensingular.requirement.module.form.FormAction;

public class ResponderExigenciaAction extends AbstractURLPopupBoxItemAction {
    public ResponderExigenciaAction(BoxItemData line) {
        super("responderExigencia", "Responder Exigencia", DefaultIcons.PENCIL, FormAction.FORM_FILL_WITH_ANALYSIS, line);
    }
}
