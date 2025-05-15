package com.hexagonalarch.adapters.presenters;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenericConverter {

    private final ModelMapper modelMapper;

    public GenericConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <D, E> E toDomain(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <D, E> D toDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}

