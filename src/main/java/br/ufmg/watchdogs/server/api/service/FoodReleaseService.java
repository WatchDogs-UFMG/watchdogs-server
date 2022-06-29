package br.ufmg.watchdogs.server.api.service;

import br.ufmg.watchdogs.server.api.model.FoodRelease;
import br.ufmg.watchdogs.server.api.model.form.CreateFoodReleaseForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateFoodReleaseForm;

public interface FoodReleaseService extends BasicCrudService<FoodRelease, CreateFoodReleaseForm, UpdateFoodReleaseForm> {

}
