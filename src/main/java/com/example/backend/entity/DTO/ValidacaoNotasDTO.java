package com.example.backend.entity.DTO;

public class ValidacaoNotasDTO {
    package com.escola.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ValidacaoDadosDTO {
    private Long id;
    private String tabela;
    private String campo;
    private String valorReferencia;
    private String descricao;
    private String tipoValidacao;
    private Boolean ativo;
    private String mensagemErro;
}
}
