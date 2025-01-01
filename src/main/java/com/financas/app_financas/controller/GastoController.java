package com.financas.app_financas.controller;

import com.financas.app_financas.dto.GastoDTO;
import com.financas.app_financas.model.gastos.Gasto;
import com.financas.app_financas.service.Gasto.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @PostMapping("/{userId}/cadastrar")
    public ResponseEntity<Gasto> cadastrarGasto(
            @PathVariable UUID userId,
            @RequestBody GastoDTO gastoDTO) {
        Gasto gasto = gastoService.cadastrarGasto(userId, gastoDTO);
        return ResponseEntity.ok(gasto);
    }
}
