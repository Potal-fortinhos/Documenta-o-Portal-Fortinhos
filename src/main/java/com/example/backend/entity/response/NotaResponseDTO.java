package com.example.backend.entity.response;

public class NotaResponseDTO {
   package com.escola.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaResponseDTO {
    private Long id;
    private AlunoResponseDTO aluno;
    private TurmaDisciplinaResponseDTO disciplina;
    private Integer bimestre;
    private BigDecimal valor;
    private LocalDateTime dataLancamento;
    private ProfessorResponseDTO lancadoPor;
    private String observacao;
    private List<LogAlteracaoNotaResponseDTO> logsAlteracao;
} 
}
