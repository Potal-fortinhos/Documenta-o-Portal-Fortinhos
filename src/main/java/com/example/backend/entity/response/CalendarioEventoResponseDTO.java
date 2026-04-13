package com.example.backend.entity.response;

public class CalendarioEventoResponseDTO {
    package com.escola.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarioEventoResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private LocalDate dataFim;
    private String tipo;
    private TurmaDisciplinaResponseDTO turma;
}
}
