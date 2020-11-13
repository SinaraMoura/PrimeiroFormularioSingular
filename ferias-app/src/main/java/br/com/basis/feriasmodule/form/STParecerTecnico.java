package br.com.basis.feriasmodule.form;

import org.opensingular.form.SInfoType;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeHTML;
import org.opensingular.form.type.generic.STGenericComposite;

import javax.annotation.Nonnull;

@SInfoType(label = "Parecer Técnico")
public class STParecerTecnico extends STGenericComposite<SIParecerTecnico> {
    public STypeHTML parecer;

    public STParecerTecnico() {
        super(SIParecerTecnico.class);
    }

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        parecer = addField("parecer", STypeHTML.class);
        this.withInitListener(SIParecerTecnico::preencherModelo);
    }
}