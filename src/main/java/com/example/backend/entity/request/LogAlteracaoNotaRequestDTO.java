package com.example.backend.entity.request;

public class LogAlteracaoNotaRequestDTO {
    package com.escola.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogAlteracaoNotaRequestDTO {
    
    @NotNull(message = "ID da nota é obrigatório")
    private Long notaId;
    
    @NotNull(message = "ID do professor é obrigatório")
    private Long professorId;
    
    @NotNull(message = "Valor antigo é obrigatório")
    private BigDecimal valorAntigo;
    
    @NotNull(message = "Valor novo é obrigatório")
    private BigDecimal valorNovo;
    
    @Size(max = 255, message = "Motivo deve ter no máximo 255 caracteres")
    private String motivo;
}
}
