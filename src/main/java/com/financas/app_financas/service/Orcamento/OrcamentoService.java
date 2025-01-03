package com.financas.app_financas.service.Orcamento;


import com.financas.app_financas.dto.OrcamentoDTO;
import com.financas.app_financas.model.orcamento.Orcamento;
import com.financas.app_financas.model.users.User;
import com.financas.app_financas.repository.orcamento.OrcamentoRepository;
import com.financas.app_financas.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private UserRepository  userRepository;

    public Orcamento criarOrcamento(UUID userId, OrcamentoDTO orcamentoDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Orcamento orcamento = new Orcamento();
        orcamento.setUser(user);
        orcamento.setValue(orcamentoDTO.getValue());
        orcamento.setCategory(orcamentoDTO.getCategory());
        orcamento.setDescription(orcamentoDTO.getDescription());
        orcamento.setStartDate(orcamentoDTO.getStartDate());
        orcamento.setEndDate(orcamentoDTO.getEndDate());

        return orcamentoRepository.save(orcamento);
    }

    public List<Orcamento> listarOrcamentos(UUID userId){
        return orcamentoRepository.findByUserId(userId);
    }


    public void deletarOrcamento(UUID userId, UUID orcamentoId) {
        Orcamento orcamento = orcamentoRepository.findByIdAndIdUser(orcamentoId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Orçamento não encontrado ou não pertence ao usuário."));
        orcamentoRepository.delete(orcamento);
    }



    public Orcamento atualizarOrcamento(UUID userId, UUID orcamentoId, OrcamentoDTO orcamentoDTO) {
        Orcamento orcamento = orcamentoRepository.findByIdAndIdUser(orcamentoId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Orçamento não encontrado ou não pertence ao usuário."));

        orcamento.setValue(orcamentoDTO.getValue());
        orcamento.setCategory(orcamentoDTO.getCategory());
        orcamento.setDescription(orcamentoDTO.getDescription());
        orcamento.setStartDate(orcamentoDTO.getStartDate());
        orcamento.setEndDate(orcamentoDTO.getEndDate());

        return orcamentoRepository.save(orcamento);
    }



}
