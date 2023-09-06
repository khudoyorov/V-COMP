package com.example.vcomp.service.mapper;

public interface CommonMapper<D, M> {
    D toDto(M m);
    M toModel(D d);
}
