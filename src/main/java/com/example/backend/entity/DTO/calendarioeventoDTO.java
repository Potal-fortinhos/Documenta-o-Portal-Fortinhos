package com.example.backend.entity.DTO;

public class calendarioeventoDTO {
    package com.escola.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CalendarioEventoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private LocalDate dataFim;
    private String tipo;
    private Long turmaId;
    private String turmaNome;
}
}
