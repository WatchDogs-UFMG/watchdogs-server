package br.ufmg.watchdogs.server.api.service;

import br.ufmg.watchdogs.server.api.model.User;
import br.ufmg.watchdogs.server.api.model.form.CreateUserForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateUserForm;

public interface UserService extends BasicCrudService<User, CreateUserForm, UpdateUserForm> {

    User findByEmail(String username);
}
