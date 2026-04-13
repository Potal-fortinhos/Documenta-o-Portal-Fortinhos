package com.example.backend.entity.request;

public class RematriculaRequestDTO {
    package com.escola.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RematriculaRequestDTO {
    
    @NotNull(message = "ID do aluno é obrigatório")
    private Long alunoId;
    
    @NotBlank(message = "Semestre é obrigatório")
    @Size(max = 10, message = "Semestre deve ter no máximo 10 caracteres")
    private String semestre;
    
    private String observacao;
    
    private Long processadoPorId;
    
    private String status; // pendente, aprovado, rejeitado, concluido
}
}
