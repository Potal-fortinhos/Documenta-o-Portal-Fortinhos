package com.escola.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProfessorDTO {
    private Long id;
    private String nome;
    private String email;
    private String especialidade;
    private LocalDate dataContratacao;
    private Boolean ativo;
    private List<TurmaDisciplinaDTO> disciplinas;
}