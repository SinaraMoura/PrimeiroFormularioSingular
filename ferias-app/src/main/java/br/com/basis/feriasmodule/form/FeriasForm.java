package br.com.basis.feriasmodule.form;


import javax.annotation.Nonnull;

import org.opensingular.form.*;

import org.opensingular.form.type.core.*;
import org.opensingular.form.util.SingularPredicates;
import org.opensingular.form.validation.InstanceValidatable;
import org.opensingular.form.view.SViewByBlock;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Predicate;


@SInfoType(label = "Formulário")
public class FeriasForm extends STypeComposite<SIComposite> {
    public STypeString nome;
    public STypeString cargo;
    public STypeDate dataInicio;
    public STypeDate dataFim;
    public STypeBoolean venderFerias;
    public STypeInteger quantidadeDias;
    public STypeBoolean anteciparDecimoTerceiro;
    public STypeInteger contato;
    public STypeBoolean categoriaFuncional;


    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = addFieldString("nome");
        nome
                .asAtr()
                .required()
                .label("Nome")
                .asAtrBootstrap()
                .colPreference(6);
        cargo = addFieldString("cargo");
        cargo
                .asAtr()
                .required()
                .label("Cargo/Função")
                .asAtrBootstrap()
                .colPreference(6);
        categoriaFuncional = addFieldBoolean("categoriaFuncional");
        categoriaFuncional.withRadioView("Efetivo","Comissionado")
                .asAtr()
                .required()
                .label("Categoria Funcional")
                .asAtrBootstrap()
                .colPreference(6);
        contato = addFieldInteger("contato");
        contato
                .asAtr()
                .label("Telefone")
                .asAtrBootstrap()
                .colPreference(6);
        dataInicio= addFieldDate("dataInicio");
        dataInicio
                .asAtr()
                .required()
                .label("Inicio das Férias")
                .asAtrBootstrap()
                .colPreference(6);

        dataFim= addFieldDate("dataFim");
        dataFim
                .asAtr()
                .required()
                .label("Fim das Férias")
                .asAtrBootstrap()
                .colPreference(6);

        venderFerias= addFieldBoolean("venderFerias");
        venderFerias.withRadioView("sim","Não")
                .asAtr()
                .label("Vender Férias?")
                .required()
                .asAtrBootstrap()
                .colPreference(6);

        quantidadeDias = addFieldInteger("quantidadeDias");
        quantidadeDias
                .addInstanceValidator(this::validarNumeroMaxDiasVendidos)
                .asAtr()
                .label("Quantidade de dias vendidos")
                .dependsOn(venderFerias)
                .exists(isVenderDias())
                .asAtrBootstrap()
                .colPreference(6);
        anteciparDecimoTerceiro = addFieldBoolean("anteciparDecimoTerceiro");
        anteciparDecimoTerceiro.withRadioView("Sim","Não")
                .asAtr()
                .label("Antecipar 13º salário?")
                .asAtrBootstrap()
                .colPreference(3);


        this.addInstanceValidator(this::validarDiasFerias);

        this.withView(new SViewByBlock(), block ->
                block
                        .newBlock("Requerente")
                        .add(nome)
                        .add(contato)
                        .add(cargo)
                        .add(categoriaFuncional)


                        .newBlock("Requerimento de Férias")
                        .add(dataInicio)
                        .add(dataFim)
                        .add(venderFerias)
                        .add(quantidadeDias)
                        .add(anteciparDecimoTerceiro)
        );


    }

    private Predicate<SInstance> isVenderDias() {
        return SingularPredicates.typeValueIsTrueAndNotNull(venderFerias);
    }


    private void validarNumeroMaxDiasVendidos(InstanceValidatable<SIInteger> qtdDias) {
        if (qtdDias.getInstance().getInteger()!= null && qtdDias.getInstance().getInteger()>10){
            qtdDias.error("O número máximo de dias que pode ser vendido é 10");
        }
    }


    private void validarDiasFerias(InstanceValidatable<SIComposite> siCompositeInstanceValidatable) {
        SIComposite form = siCompositeInstanceValidatable.getInstance();
        Date inicio=form.findNearestValueOrException(this.dataInicio);
        Date fim =form.findNearestValueOrException(this.dataFim);


        LocalDate dataInicio = inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFim = fim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        long diasFerias = ChronoUnit.DAYS.between(dataInicio,dataFim);
        if (dataFim.isBefore(dataInicio)){
            siCompositeInstanceValidatable.error("A data final não pode ser anterior a data inicial");
        }
        if (diasFerias>30){
            siCompositeInstanceValidatable.error("Não é possivel solicitar mais de 30 dias de ferias");
        }
        if (diasFerias<5){
            siCompositeInstanceValidatable.error("Nãó é posspivel solicitar menos de 5 dias de férias");
        }
    }
}