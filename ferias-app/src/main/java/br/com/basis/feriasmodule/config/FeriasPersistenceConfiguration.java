package br.com.basis.feriasmodule.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;
import org.opensingular.app.commons.spring.persistence.database.PackageScanConfiguration;
import org.opensingular.app.commons.spring.persistence.database.SingularPersistenceConfiguration;

import java.util.List;

public class FeriasPersistenceConfiguration implements SingularPersistenceConfiguration {
    @Override
    public void configureHibernatePackagesToScan(PackageScanConfiguration packagesToScan) {
    }

    @Override
    public void configureInitSQLScripts(List<String> scripts) {
        scripts.add("db/dml/import.sql");
    }

    @Override
    public Class<? extends Dialect> getHibernateDialect() {
        return H2Dialect.class;
    }
}