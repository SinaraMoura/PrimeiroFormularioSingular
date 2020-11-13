package br.com.basis.feriasmodule.rest;

import br.com.basis.feriasmodule.form.FeriasForm;
import br.com.basis.feriasmodule.util.LocalPdfConverter;
import org.apache.commons.io.IOUtils;
import org.opensingular.app.commons.pdf.PServerFreeMarkerUtil;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInstance;

import org.opensingular.lib.commons.dto.HtmlToPdfDTO;
import org.opensingular.requirement.module.service.RequirementInstance;
import org.opensingular.requirement.module.service.RequirementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
public class FeriasResource {
    @Inject
    private RequirementService requirementService;

    @GetMapping("**/ferias/impressao/{requirementId}")
    public void imprimirTermoFerias(HttpServletRequest request, HttpServletResponse response, @PathVariable Long requirementId) throws IOException {
        RequirementInstance requirementInstance = requirementService.loadRequirementInstance(requirementId);

        Optional<SInstance> form = requirementService.findLastFormRequirementInstanceByType(requirementInstance, FeriasForm.class);

        if (form.isPresent()){
            SIComposite feriasForm = (SIComposite) form.get();
            Map<String,Object> dadosForm =new HashMap<>();
            dadosForm.put("solicitante", requirementInstance.getApplicant().getName());
            dadosForm.put("dataInicio", formatarData(feriasForm.getValue("dataInicio")));
            dadosForm.put("dataFim", formatarData(feriasForm.getValue("dataFim")));
            dadosForm.put("venderFerias", feriasForm.getValue("venderFerias")? "Sim": "Não");
            dadosForm.put("quantidadeDias", feriasForm.getValue("quantidadeDias")== null ? 0:feriasForm.getValue("quantidadeDias"));
            dadosForm.put("anteciparDecimoTerceiro", feriasForm.getValue("anteciparDecimoTerceiro")? "Sim":"Não");

            String html = PServerFreeMarkerUtil.mergeWithFreemarker("termoFerias.ftl",dadosForm);

            LocalPdfConverter pdfConverter = new LocalPdfConverter();
            InputStream inputStream= pdfConverter.convertStream(new HtmlToPdfDTO(html));

            response.setContentType("application/pdf");
            IOUtils.copy(inputStream,response.getOutputStream());
            inputStream.close();

        }

    }

    private String formatarData(Object data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return sdf.format(data);
    }
}
