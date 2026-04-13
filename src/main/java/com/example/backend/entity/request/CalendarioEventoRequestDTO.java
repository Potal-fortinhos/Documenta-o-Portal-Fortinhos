package com.example.backend.entity.request;

public class CalendarioEventoRequestDTO {
    package com.escola.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarioEventoRequestDTO {
    
    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    private String titulo;
    
    private String descricao;
    
    @NotNull(message = "Data é obrigatória")
    private LocalDate data;
    
    private LocalDate dataFim;
    
    @NotNull(message = "Tipo do evento é obrigatório")
    private String tipo; // prova, feriado, reuniao, evento, matricula
    
    private Long turmaId;
}
