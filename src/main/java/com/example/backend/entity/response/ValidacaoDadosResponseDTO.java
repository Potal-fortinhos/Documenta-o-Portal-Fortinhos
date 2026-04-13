package com.example.backend.entity.response;

public class ValidacaoDadosResponseDTO {
   package com.escola.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidacaoDadosResponseDTO {
    private Long id;
    private String tabela;
    private String campo;
    private String valorReferencia;
    private String descricao;
    private String tipoValidacao;
    private Boolean ativo;
    private String mensagemErro;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 
}
