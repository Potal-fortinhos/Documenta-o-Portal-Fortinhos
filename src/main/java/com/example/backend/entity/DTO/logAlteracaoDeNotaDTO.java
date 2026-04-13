package com.example.backend.entity.DTO;

public class logAlteracaoDeNotaDTO {
    package com.escola.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LogAlteracaoNotaDTO {
    private Long id;
    private Long notaId;
    private BigDecimal valorAntigo;
    private BigDecimal valorNovo;
    private LocalDateTime dataAlteracao;
    private String professorNome;
    private String motivo;
}
}
