package com.example.demo.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.AllArtistsDTO;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Service

public class ArtistReportService {
	
	public byte[] exportArtistsDTOReport(List<AllArtistsDTO> artistDTOs) throws Exception {

        // Charging the report template
        InputStream reportStream = new ClassPathResource("reports/artist_report.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Getting the data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(artistDTOs);

        // Params if needed
        HashMap<String, Object> parameters = new HashMap<>();

        // Fill report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}
