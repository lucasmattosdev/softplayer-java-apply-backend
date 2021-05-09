package br.com.lucasmattos.softplayer.java.apply.backend.resource;

import br.com.lucasmattos.softplayer.java.apply.backend.repository.AbstractRepository;

import javax.inject.Inject;

public abstract class AbstractResource<R extends AbstractRepository> {
    @Inject
    protected R repository;
}
