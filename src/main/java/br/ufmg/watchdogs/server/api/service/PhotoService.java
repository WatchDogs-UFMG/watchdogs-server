package br.ufmg.watchdogs.server.api.service;

import br.ufmg.watchdogs.server.api.model.Photo;
import br.ufmg.watchdogs.server.api.model.form.CreatePhotoForm;
import br.ufmg.watchdogs.server.api.model.form.UpdatePhotoForm;

public interface PhotoService extends BasicCrudService<Photo, CreatePhotoForm, UpdatePhotoForm> {

}
