package com.example.demo.controller;

import com.example.demo.DTO.AllArtistsDTO;
import com.example.demo.service.ArtistReportService;
import com.example.demo.service.ArtistService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("api/artist")
public class ArtistReportController {
	
	private final ArtistReportService reportService;
    private final ArtistService artistService;

    public ArtistReportController(ArtistReportService reportService, ArtistService artistService) {
        this.reportService = reportService;
        this.artistService = artistService;
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> getArtistsDTOReport() {
        try {
            List<AllArtistsDTO> artistDTOs = artistService.listArtists();
            byte[] pdfBytes = reportService.exportArtistsDTOReport(artistDTOs);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "inline; filename=artist_report.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
        	e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("Error generando reporte: " + e.getMessage()).getBytes());
        }
    }

}
