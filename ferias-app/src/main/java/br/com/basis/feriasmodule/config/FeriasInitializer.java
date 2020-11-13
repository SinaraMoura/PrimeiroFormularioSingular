package br.com.basis.feriasmodule.config;

import org.opensingular.requirement.studio.init.RequirementStudioAppInitializer;

public class FeriasInitializer extends RequirementStudioAppInitializer {
    @Override
    public String[] getSpringPackagesToScan() {
        return new String[]{"br.com.basis"};
    }
}