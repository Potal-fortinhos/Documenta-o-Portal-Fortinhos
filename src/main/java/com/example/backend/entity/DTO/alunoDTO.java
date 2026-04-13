package com.example.backend.entity.DTO;

public class alunoDTO {
    package com.escola.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AlunoDTO {
    private Long id;
    private String ra;
    private String nome;
    private String email;
    private LocalDateTime dataCadastro;
    private Boolean ativo;
    private List<NotaDTO> notas;
    private List<RematriculaDTO> rematriculas;
    
    // DTO específico para listagem (sem dados sensíveis)
    @Data
    public static class AlunoResumoDTO {
        private Long id;
        private String ra;
        private String nome;
        private String email;
        private Boolean ativo;
    }
}
}
