package br.com.basis.feriasmodule.action;

import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.requirement.module.box.action.AbstractURLPopupBoxItemAction;


import java.io.Serializable;

public class ImprimirTermoFeriasAction extends AbstractURLPopupBoxItemAction {
    public ImprimirTermoFeriasAction(Serializable requirementId) {
        super("imprimir_termo_ferias","Imprimir termo de férias", DefaultIcons.FILE_PDF,"/ferias/impressao/"+ requirementId);
    }
}
