package br.com.basis.feriasmodule.util;

import org.apache.commons.io.FileUtils;
import org.opensingular.lib.commons.dto.HtmlToPdfDTO;
import org.opensingular.lib.commons.pdf.HtmlToPdfConverter;
import org.opensingular.lib.commons.pdf.PDFUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class LocalPdfConverter implements HtmlToPdfConverter {

    @Override
    public Optional<File> convert(HtmlToPdfDTO DTO) {
        return Optional.of(PDFUtil.getInstance().convertHTML2PDF(DTO.getBody(),DTO.getHeader(),DTO.getFooter(),DTO.getAdditionalParams()));
    }

    @Override
    public InputStream convertStream(HtmlToPdfDTO DTO) {
       return convert(DTO).map(i -> {
           i.deleteOnExit();
           try {
               return FileUtils.openInputStream(i);
           } catch (IOException e) {
               throw  new RuntimeException(e.getMessage(),e);
           }

       }).orElse(null);
    }
}
