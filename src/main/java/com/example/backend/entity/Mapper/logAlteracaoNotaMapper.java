package com.example.backend.entity.Mapper;

public class logAlteracaoNotaMapper {
    package com.escola.mapper;

import com.escola.entities.LogAlteracaoNota;
import com.escola.dto.LogAlteracaoNotaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring", uses = {NotaMapper.class, ProfessorMapper.class})
public interface LogAlteracaoNotaMapper {
    
    LogAlteracaoNotaMapper INSTANCE = Mappers.getMapper(LogAlteracaoNotaMapper.class);
    
    @Mapping(source = "nota.id", target = "notaId")
    @Mapping(source = "professor.nome", target = "professorNome")
    LogAlteracaoNotaDTO toDTO(LogAlteracaoNota log);
    
    @Mapping(target = "nota", ignore = true)
    @Mapping(target = "professor", ignore = true)
    @Mapping(target = "dataAlteracao", ignore = true)
    LogAlteracaoNota toEntity(LogAlteracaoNotaDTO logDTO);
    
    List<LogAlteracaoNotaDTO> toDTOList(List<LogAlteracaoNota> logs);
}
}
