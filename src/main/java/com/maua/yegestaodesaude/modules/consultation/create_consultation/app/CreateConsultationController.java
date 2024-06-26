package com.maua.yegestaodesaude.modules.consultation.create_consultation.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.domain.dtos.ConsultationDTO;
import com.maua.yegestaodesaude.shared.services.AutenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/consultation")
@Tag(name = "Consultation")
public class CreateConsultationController {

    @Autowired
    private CreateConsultationUsecase createConsultationUsecase;

    @Autowired
    private AutenticationService autenticationService;

    @PostMapping
    @Operation(summary = "Criar consulta")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "Consulta criada com sucesso", 
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Consulta criada com sucesso\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Erro ao processar solicitação para criar consulta",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao processar solicitação para criar consulta.\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Acesso negado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Acesso negado\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "422", 
            description = "Informações inválidas para criar consulta",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Informações inválidas para criar consulta\"}")
                )
            }
        )
    })
    public ResponseEntity<Object> createConsultation(@RequestBody ConsultationDTO consultationDTO,
            HttpServletRequest request) {
        try {
            String token = extractTokenFromRequest(request);

            Long clientId = autenticationService.getClientId(token);

            var result = this.createConsultationUsecase.execute(consultationDTO, clientId);

            return result;
        } catch (Exception e) {
            System.err.println("Erro ao processar solicitação para criar consulta: " + e.getMessage());
            return ResponseEntity.status(400).body(new CreateConsultationViewmodel("Erro ao processar solicitação para criar consulta."));
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        } else {
            throw new RuntimeException("Token not found in request headers");
        }
    }

}
