package com.example.backend.entity.request;

public class ValidacaoDadosRequestDTO {
    package com.escola.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidacaoDadosRequestDTO {
    
    @NotBlank(message = "Nome da tabela é obrigatório")
    @Size(max = 50, message = "Nome da tabela deve ter no máximo 50 caracteres")
    private String tabela;
    
    @NotBlank(message = "Nome do campo é obrigatório")
    @Size(max = 50, message = "Nome do campo deve ter no máximo 50 caracteres")
    private String campo;
    
    @NotBlank(message = "Valor de referência é obrigatório")
    @Size(max = 255, message = "Valor de referência deve ter no máximo 255 caracteres")
    private String valorReferencia;
    
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    private String descricao;
    
    @NotNull(message = "Tipo de validação é obrigatório")
    private String tipoValidacao; // existencia, formato, faixa, unico, relacionamento
    
    private Boolean ativo;
    
    @Size(max = 255, message = "Mensagem de erro deve ter no máximo 255 caracteres")
    private String mensagemErro;
}
}
