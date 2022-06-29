package br.ufmg.watchdogs.server.api.service;

import br.ufmg.watchdogs.server.api.model.Vaccine;
import br.ufmg.watchdogs.server.api.model.form.CreateVaccineForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateVaccineForm;

public interface VaccineService extends BasicCrudService<Vaccine, CreateVaccineForm, UpdateVaccineForm> {

}
