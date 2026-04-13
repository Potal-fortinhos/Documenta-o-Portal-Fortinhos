package com.example.backend.entity.Mapper;

public class alunoMapper {
    package com.escola.mapper;

import com.escola.entities.Aluno;
import com.escola.dto.AlunoDTO;
import com.escola.dto.AlunoDTO.AlunoResumoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoMapper {
    
    AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);
    
    // Mapeamento completo (ignora senha e dados criptografados)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "dadosPessoaisCript", ignore = true)
    AlunoDTO toDTO(Aluno aluno);
    
    // Mapeamento reverso (sem senha - será gerada pelo service)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "dadosPessoaisCript", ignore = true)
    @Mapping(target = "notas", ignore = true)
    @Mapping(target = "rematriculas", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    Aluno toEntity(AlunoDTO alunoDTO);
    
    // Mapeamento para resumo (listagens)
    AlunoResumoDTO toResumoDTO(Aluno aluno);
    
    // Listas
    List<AlunoDTO> toDTOList(List<Aluno> alunos);
    List<AlunoResumoDTO> toResumoDTOList(List<Aluno> alunos);
}
}
