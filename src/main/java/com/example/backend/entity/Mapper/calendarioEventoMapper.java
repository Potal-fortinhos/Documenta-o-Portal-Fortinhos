package com.example.backend.entity.Mapper;

public class calendarioEventoMapper {
    package com.escola.mapper;

import com.escola.entities.CalendarioEvento;
import com.escola.dto.CalendarioEventoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CalendarioEventoMapper {
    
    CalendarioEventoMapper INSTANCE = Mappers.getMapper(CalendarioEventoMapper.class);
    
    @Mapping(source = "tipo", target = "tipo")
    @Mapping(source = "turma.id", target = "turmaId")
    @Mapping(source = "turma.nome", target = "turmaNome")
    CalendarioEventoDTO toDTO(CalendarioEvento evento);
    
    @Mapping(target = "turma", ignore = true)
    CalendarioEvento toEntity(CalendarioEventoDTO eventoDTO);
    
    List<CalendarioEventoDTO> toDTOList(List<CalendarioEvento> eventos);
}
}
