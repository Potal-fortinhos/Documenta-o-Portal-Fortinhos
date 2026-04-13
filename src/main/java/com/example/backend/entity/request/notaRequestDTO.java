package com.escola.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaRequestDTO {
    
    @NotNull(message = "ID do aluno é obrigatório")
    private Long alunoId;
    
    @NotNull(message = "ID da disciplina é obrigatório")
    private Long disciplinaId;
    
    @NotNull(message = "Bimestre é obrigatório")
    @Min(value = 1, message = "Bimestre deve ser entre 1 e 4")
    @Max(value = 4, message = "Bimestre deve ser entre 1 e 4")
    private Integer bimestre;
    
    @NotNull(message = "Valor da nota é obrigatório")
    @DecimalMin(value = "0.00", message = "Nota mínima é 0")
    @DecimalMax(value = "10.00", message = "Nota máxima é 10")
    private BigDecimal valor;
    
    @NotNull(message = "ID do professor que lançou é obrigatório")
    private Long lancadoPorId;
    
    private String observacao;
}