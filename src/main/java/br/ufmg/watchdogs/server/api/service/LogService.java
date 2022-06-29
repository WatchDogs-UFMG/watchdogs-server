package br.ufmg.watchdogs.server.api.service;

import br.ufmg.watchdogs.server.api.model.Log;
import br.ufmg.watchdogs.server.api.model.form.CreateLogForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateLogForm;

public interface LogService extends BasicCrudService<Log, CreateLogForm, UpdateLogForm> {

}
