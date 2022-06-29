package br.ufmg.watchdogs.server.api.service;

import br.ufmg.watchdogs.server.api.model.Animal;
import br.ufmg.watchdogs.server.api.model.form.CreateAnimalForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateAnimalForm;

public interface AnimalService extends BasicCrudService<Animal, CreateAnimalForm, UpdateAnimalForm> {

}
