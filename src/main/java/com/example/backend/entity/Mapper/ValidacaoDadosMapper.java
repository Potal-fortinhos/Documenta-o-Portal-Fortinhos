package com.example.backend.entity.Mapper;

public class ValidacaoDadosMapper {
    package com.escola.mapper;

import com.escola.entities.ValidacaoDados;
import com.escola.dto.ValidacaoDadosDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ValidacaoDadosMapper {
    
    ValidacaoDadosMapper INSTANCE = Mappers.getMapper(ValidacaoDadosMapper.class);
    
    @Mapping(source = "tipoValidacao", target = "tipoValidacao")
    ValidacaoDadosDTO toDTO(ValidacaoDados validacao);
    
    ValidacaoDados toEntity(ValidacaoDadosDTO validacaoDTO);
    
    List<ValidacaoDadosDTO> toDTOList(List<ValidacaoDados> validacoes);
}
}
