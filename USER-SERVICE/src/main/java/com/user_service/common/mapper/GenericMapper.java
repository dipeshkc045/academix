package com.user_service.common.mapper;

public interface GenericMapper<D, E> {
    E toEntity(D dto);
    D toDto(E entity);
}
