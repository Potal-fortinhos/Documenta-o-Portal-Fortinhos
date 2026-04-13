package com.example.backend.entity.response;

public class ProfessorResponseDTO {
    package com.escola.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String especialidade;
    private LocalDate dataContratacao;
    private Boolean ativo;
    private List<TurmaDisciplinaResponseDTO> disciplinas;
}
}
