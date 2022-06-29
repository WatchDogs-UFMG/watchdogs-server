package br.ufmg.watchdogs.server.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BasicCrudService<EntityModel, CreateForm, UpdateForm> {

    EntityModel create(CreateForm createForm);
    Page<EntityModel> findAll(Pageable pageable);
    EntityModel findById(Long id);
    EntityModel updateById(UpdateForm updateForm);
    EntityModel deleteById(Long id);
}
