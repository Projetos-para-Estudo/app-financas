package com.financas.app_financas.controller;

import com.financas.app_financas.dto.OrcamentoDTO;
import com.financas.app_financas.dto.OrcamentoResponseDTO;
import com.financas.app_financas.model.orcamento.Orcamento;
import com.financas.app_financas.service.Orcamento.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @PostMapping("/{userId}/criar")
    public ResponseEntity<OrcamentoResponseDTO> criarOrcamento(
            @PathVariable UUID userId,
            @RequestBody OrcamentoDTO orcamentoDTO) {
        Orcamento orcamento = orcamentoService.criarOrcamento(userId, orcamentoDTO);
        return ResponseEntity.ok(mapToResponseDTO(orcamento));
    }

    @GetMapping("/{userId}/listar")
    public ResponseEntity<List<OrcamentoResponseDTO>> listarOrcamentos(@PathVariable UUID userId) {
        List<Orcamento> orcamentos = orcamentoService.listarOrcamentos(userId);
        List<OrcamentoResponseDTO> response = orcamentos.stream()
                .map(this::mapToResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}/atualizar/{orcamentoId}")
    public ResponseEntity<OrcamentoResponseDTO> atualizarOrcamento(
            @PathVariable UUID userId,
            @PathVariable UUID orcamentoId,
            @RequestBody OrcamentoDTO orcamentoDTO) {
        Orcamento orcamentoAtualizado = orcamentoService.atualizarOrcamento(userId, orcamentoId, orcamentoDTO);
        return ResponseEntity.ok(mapToResponseDTO(orcamentoAtualizado));
    }


    @DeleteMapping("/{userId}/deletar/{orcamentoId}")
    public ResponseEntity<Void> deletarOrcamento(
            @PathVariable UUID userId,
            @PathVariable UUID orcamentoId) {
        orcamentoService.deletarOrcamento(userId, orcamentoId);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }


    private OrcamentoResponseDTO mapToResponseDTO(Orcamento orcamento) {
        OrcamentoResponseDTO dto = new OrcamentoResponseDTO();
        dto.setId(orcamento.getId().toString());
        dto.setValue(orcamento.getValue());
        dto.setDateCreation(orcamento.getDateCreation());
        dto.setStartDate(orcamento.getStartDate());
        dto.setEndDate(orcamento.getEndDate());
        dto.setCategory(orcamento.getCategory());
        dto.setDescription(orcamento.getDescription());
        return dto;
    }
}
