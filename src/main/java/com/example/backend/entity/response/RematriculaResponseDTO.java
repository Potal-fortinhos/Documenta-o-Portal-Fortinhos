package com.example.backend.entity.response;

public class RematriculaResponseDTO {
    package com.escola.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RematriculaResponseDTO {
    private Long id;
    private AlunoResponseDTO aluno;
    private String semestre;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataProcessamento;
    private String boletoSimulado;
    private String observacao;
    private ProfessorResponseDTO processadoPor;
    private String status;
}
}
