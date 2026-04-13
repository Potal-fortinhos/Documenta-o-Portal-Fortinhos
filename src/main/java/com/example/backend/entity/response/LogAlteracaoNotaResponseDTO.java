package com.example.backend.entity.response;

public class LogAlteracaoNotaResponseDTO {
    package com.escola.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogAlteracaoNotaResponseDTO {
    private Long id;
    private NotaResponseDTO nota;
    private LocalDateTime dataAlteracao;
    private ProfessorResponseDTO professor;
    private BigDecimal valorAntigo;
    private BigDecimal valorNovo;
    private String motivo;
} 
}
