package com.example.backend.entity.DTO;

public class rematriculaDTO {
    package com.escola.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RematriculaDTO {
    private Long id;
    private Long alunoId;
    private String alunoNome;
    private String semestre;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataProcessamento;
    private String status;
    private String observacao;
}
}
