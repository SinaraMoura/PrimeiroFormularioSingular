package br.com.basis.feriasmodule.form;

import org.apache.commons.io.IOUtils;
import org.opensingular.form.type.core.SIHTML;
import org.opensingular.form.type.generic.SIGenericComposite;
import org.opensingular.requirement.module.exception.SingularServerException;

import java.io.IOException;

public class SIParecerTecnico extends SIGenericComposite<STParecerTecnico> {
   public SIHTML parecer() {
      return getField(getType().parecer);
   }

   public void preencherModelo() {
      try {
         parecer().setValue(IOUtils.toString(getClass().getResource("/templateParecer.html"), "UTF-8"));
      } catch (IOException e) {
         throw new SingularServerException("Erro ao carregar o modelo de parecer");
      }
   }
}
