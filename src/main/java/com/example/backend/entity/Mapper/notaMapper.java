package com.example.backend.entity.Mapper;

public class notaMapper {
    package com.escola.mapper;

import com.escola.entities.Nota;
import com.escola.dto.NotaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring", uses = {AlunoMapper.class, ProfessorMapper.class})
public interface NotaMapper {
    
    NotaMapper INSTANCE = Mappers.getMapper(NotaMapper.class);
    
    @Mapping(source = "aluno.id", target = "alunoId")
    @Mapping(source = "aluno.nome", target = "alunoNome")
    @Mapping(source = "disciplina.id", target = "disciplinaId")
    @Mapping(source = "disciplina.nome", target = "disciplinaNome")
    @Mapping(source = "lancadoPor.nome", target = "professorLancador")
    @Mapping(target = "logsAlteracao", ignore = true)
    NotaDTO toDTO(Nota nota);
    
    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "disciplina", ignore = true)
    @Mapping(target = "lancadoPor", ignore = true)
    @Mapping(target = "logsAlteracao", ignore = true)
    @Mapping(target = "dataLancamento", ignore = true)
    Nota toEntity(NotaDTO notaDTO);
    
    List<NotaDTO> toDTOList(List<Nota> notas);
}
}
