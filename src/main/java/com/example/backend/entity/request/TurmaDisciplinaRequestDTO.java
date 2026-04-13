package com.escola.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDisciplinaRequestDTO {
    
    @NotBlank(message = "Nome da turma/disciplina é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;
    
    @NotNull(message = "Carga horária é obrigatória")
    @Positive(message = "Carga horária deve ser positiva")
    private Integer cargaHoraria;
    
    @NotNull(message = "ID do professor é obrigatório")
    private Long professorId;
    
    @Size(max = 10, message = "Semestre deve ter no máximo 10 caracteres")
    private String semestre;
    
    @Positive(message = "Ano deve ser positivo")
    private Integer ano;
    
    private Boolean ativo;
}