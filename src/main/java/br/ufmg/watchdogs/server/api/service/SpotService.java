package br.ufmg.watchdogs.server.api.service;

import br.ufmg.watchdogs.server.api.model.Spot;
import br.ufmg.watchdogs.server.api.model.form.CreateSpotForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateSpotForm;

public interface SpotService extends BasicCrudService<Spot, CreateSpotForm, UpdateSpotForm> {

}
