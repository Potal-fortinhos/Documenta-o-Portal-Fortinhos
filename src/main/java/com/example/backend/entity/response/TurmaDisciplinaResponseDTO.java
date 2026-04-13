package com.example.backend.entity.response;

public class TurmaDisciplinaResponseDTO {
    package com.escola.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDisciplinaResponseDTO {
    private Long id;
    private String nome;
    private Integer cargaHoraria;
    private ProfessorResponseDTO professor;
    private String semestre;
    private Integer ano;
    private Boolean ativo;
    private List<NotaResponseDTO> notas;
    private List<CalendarioEventoResponseDTO> eventos;
}
}
