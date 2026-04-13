package com.example.backend.entity.response;

public class AlunoResponseDTO {
    package com.escola.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponseDTO {
    private Long id;
    private String ra;
    private String nome;
    private String email;
    private LocalDateTime dataCadastro;
    private Boolean ativo;
    private List<NotaResponseDTO> notas;
    private List<RematriculaResponseDTO> rematriculas;
}
}
