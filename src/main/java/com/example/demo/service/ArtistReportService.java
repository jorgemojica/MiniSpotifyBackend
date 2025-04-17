package com.example.demo.service;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AllArtistsDTO;
import com.example.demo.DTO.ArtistDetailDTO;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service

public class ArtistReportService {
	
	public byte[] exportArtistsDTOReport(List<AllArtistsDTO> artistDTOs) throws Exception {

        // Cargar plantilla
        InputStream reportStream = new ClassPathResource("reports/artist_report.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Crear datasource desde lista de DTOs
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(artistDTOs);

        // Parámetros si necesitas pasarlos al reporte
        HashMap<String, Object> parameters = new HashMap<>();

        // Llenar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exportar a PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}
