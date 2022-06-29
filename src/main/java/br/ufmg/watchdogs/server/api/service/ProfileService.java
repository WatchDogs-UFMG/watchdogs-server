package br.ufmg.watchdogs.server.api.service;

import br.ufmg.watchdogs.server.api.model.Profile;
import br.ufmg.watchdogs.server.api.model.form.CreateProfileForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateProfileForm;

public interface ProfileService extends BasicCrudService<Profile, CreateProfileForm, UpdateProfileForm> {

    Profile findByRole(String roleName);
}
