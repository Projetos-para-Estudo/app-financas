package com.financas.app_financas.service.Gasto;

import com.financas.app_financas.dto.GastoDTO;
import com.financas.app_financas.model.gastos.Gasto;
import com.financas.app_financas.model.parcela.Parcela;
import com.financas.app_financas.model.users.User;
import com.financas.app_financas.repository.gasto.GastoRepository;
import com.financas.app_financas.repository.parcela.ParcelaRepository;
import com.financas.app_financas.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private UserRepository userRepository;

    public Gasto cadastrarGasto(UUID userId, GastoDTO gastoDTO) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Gasto gasto = new Gasto();
        gasto.setUser(user);
        gasto.setCategory(gastoDTO.getCategory());
        gasto.setDescription(gastoDTO.getDescription());
        gasto.setValorTotal(gastoDTO.getValorTotal());
        gasto.setParcelado(gastoDTO.isParcelado());
        gasto.setFormaPagamento(gastoDTO.getFormaPagamento());
        gasto = gastoRepository.save(gasto);

        if (gasto.isParcelado() &&
                gastoDTO.getNumeroParcelas() != null &&
                gastoDTO.getNumeroParcelas() > 1) {
            List<Parcela> parcelas = gerarParcelas(gasto, gastoDTO);
            parcelaRepository.saveAll(parcelas);
        }
        return gasto;
    }

    private List<Parcela> gerarParcelas(Gasto gasto, GastoDTO gastoDTO) {
        List<Parcela> parcelas = new ArrayList<>();
        BigDecimal valorTotal = gastoDTO.getValorTotal();
        Integer numeroParcelas = gastoDTO.getNumeroParcelas();
        BigDecimal juros = gastoDTO.getJuros() != null ? gastoDTO.getJuros() : BigDecimal.ZERO;

        // Cálculo do valor das parcelas com juros
        BigDecimal taxa = BigDecimal.ONE.add(juros.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
        BigDecimal valorParcela = valorTotal.multiply(taxa).divide(BigDecimal.valueOf(numeroParcelas), RoundingMode.HALF_UP);

        for (int i = 1; i <= numeroParcelas; i++) {
            Parcela parcela = new Parcela();
            parcela.setGasto(gasto);
            parcela.setNumeroParcela(i);
            parcela.setValorParcela(valorParcela);
            parcela.setDataVencimento(LocalDate.now().plusMonths(i));
            parcelas.add(parcela);
        }

        return parcelas;
    }
}
