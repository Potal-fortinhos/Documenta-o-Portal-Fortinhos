package com.example.backend.entity.DTO;

public class notaDTO {
    package com.escola.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class NotaDTO {
    private Long id;
    private Long alunoId;
    private String alunoNome;
    private Long disciplinaId;
    private String disciplinaNome;
    private Integer bimestre;
    private BigDecimal valor;
    private LocalDateTime dataLancamento;
    private String professorLancador;
    private String observacao;
}
}
