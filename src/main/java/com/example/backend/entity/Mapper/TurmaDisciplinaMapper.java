package com.example.backend.entity.Mapper;

public class TurmaDisciplinaMapper {
    package com.escola.mapper;

import com.escola.entities.TurmaDisciplina;
import com.escola.dto.TurmaDisciplinaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfessorMapper.class})
public interface TurmaDisciplinaMapper {
    
    TurmaDisciplinaMapper INSTANCE = Mappers.getMapper(TurmaDisciplinaMapper.class);
    
    @Mapping(source = "professor.nome", target = "professorNome")
    @Mapping(target = "notas", ignore = true)
    @Mapping(target = "eventos", ignore = true)
    TurmaDisciplinaDTO toDTO(TurmaDisciplina turma);
    
    @Mapping(target = "professor", ignore = true)
    @Mapping(target = "notas", ignore = true)
    @Mapping(target = "eventos", ignore = true)
    TurmaDisciplina toEntity(TurmaDisciplinaDTO turmaDTO);
    
    List<TurmaDisciplinaDTO> toDTOList(List<TurmaDisciplina> turmas);
}
}
