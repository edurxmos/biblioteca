package com.eduardo.biblioteca.services;

import com.eduardo.biblioteca.dtos.EmprestimoDTO;
import com.eduardo.biblioteca.repositories.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Transactional(readOnly = true)
    public Page<EmprestimoDTO> findAll(Pageable pageable) {
        return emprestimoRepository.findAll(pageable).map(x -> new EmprestimoDTO(x));
    }

}
