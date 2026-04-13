package com.example.backend.entity.Mapper;

public class rematriculaMapper {
    package com.escola.mapper;

import com.escola.entities.Rematricula;
import com.escola.dto.RematriculaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring", uses = {AlunoMapper.class, ProfessorMapper.class})
public interface RematriculaMapper {
    
    RematriculaMapper INSTANCE = Mappers.getMapper(RematriculaMapper.class);
    
    @Mapping(source = "aluno.id", target = "alunoId")
    @Mapping(source = "aluno.nome", target = "alunoNome")
    @Mapping(source = "status", target = "status")
    RematriculaDTO toDTO(Rematricula rematricula);
    
    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "processadoPor", ignore = true)
    @Mapping(target = "dataSolicitacao", ignore = true)
    Rematricula toEntity(RematriculaDTO rematriculaDTO);
    
    List<RematriculaDTO> toDTOList(List<Rematricula> rematriculas);
}
}
