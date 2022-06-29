package br.ufmg.watchdogs.server.api.service;

import br.ufmg.watchdogs.server.api.model.Address;
import br.ufmg.watchdogs.server.api.model.form.CreateAddressForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateAddressForm;

public interface AddressService extends BasicCrudService<Address, CreateAddressForm, UpdateAddressForm> {

}
