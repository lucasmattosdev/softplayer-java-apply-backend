package br.com.lucasmattos.softplayer.java.apply.backend.repository;

import br.com.lucasmattos.softplayer.java.apply.backend.service.AbstractService;
import br.com.lucasmattos.softplayer.java.apply.backend.mapper.AbstractMapper;

import javax.inject.Inject;

public abstract class AbstractRepository<M extends AbstractMapper, S extends AbstractService> {
    @Inject
    protected M mapper;

    @Inject
    protected S service;
}
