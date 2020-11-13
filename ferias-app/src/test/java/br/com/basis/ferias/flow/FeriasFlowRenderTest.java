package br.com.basis.ferias.flow;

import br.com.basis.feriasmodule.flow.FeriasFlow;
import org.junit.Test;
import org.opensingular.requirement.module.test.AbstractFlowRenderTest;

public class FeriasFlowRenderTest extends AbstractFlowRenderTest {
    /*public FeriasFlowRenderTest(){
        setOpenGeneratedFiles(false);
    }*/

    @Test
    public void render (){
        super.setOpenGeneratedFiles(true);
        super.renderImage(new FeriasFlow());
    }
}
