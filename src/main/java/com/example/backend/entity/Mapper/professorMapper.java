package com.example.backend.entity.Mapper;

public class professorMapper {
    package com.escola.mapper;

import com.escola.entities.Professor;
import com.escola.dto.ProfessorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    
    ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);
    
    @Mapping(target = "senha", ignore = true)
    ProfessorDTO toDTO(Professor professor);
    
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "disciplinas", ignore = true)
    @Mapping(target = "notasLancadas", ignore = true)
    @Mapping(target = "alteracoesNotas", ignore = true)
    @Mapping(target = "rematriculasProcessadas", ignore = true)
    Professor toEntity(ProfessorDTO professorDTO);
    
    List<ProfessorDTO> toDTOList(List<Professor> professores);
}
}
