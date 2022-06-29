package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.Animal;
import br.ufmg.watchdogs.server.api.model.form.CreateAnimalForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateAnimalForm;
import br.ufmg.watchdogs.server.api.service.AnimalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class AnimalServiceImpl implements AnimalService {



    @Override
    public Animal create(CreateAnimalForm createAnimalForm) {
        return null;
    }

    @Override
    public Page<Animal> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Animal findById(Long id) {
        return null;
    }

    @Override
    public Animal updateById(UpdateAnimalForm updateAnimalForm) {
        return null;
    }

    @Override
    public Animal deleteById(Long id) {
        return null;
    }
}
