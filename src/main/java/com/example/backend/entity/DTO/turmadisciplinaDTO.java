package com.example.backend.entity.DTO;

public class turmadisciplinaDTO {
    package com.escola.dto;

import lombok.Data;
import java.util.List;

@Data
public class TurmaDisciplinaDTO {
    private Long id;
    private String nome;
    private Integer cargaHoraria;
    private String professorNome;
    private String semestre;
    private Integer ano;
    private Boolean ativo;
}
}
