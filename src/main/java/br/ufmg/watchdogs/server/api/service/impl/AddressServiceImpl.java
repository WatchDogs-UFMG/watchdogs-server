package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.Address;
import br.ufmg.watchdogs.server.api.model.form.CreateAddressForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateAddressForm;
import br.ufmg.watchdogs.server.api.service.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class AddressServiceImpl implements AddressService {



    @Override
    public Address create(CreateAddressForm createAddressForm) {
        return null;
    }

    @Override
    public Page<Address> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Address findById(Long id) {
        return null;
    }

    @Override
    public Address updateById(UpdateAddressForm updateAddressForm) {
        return null;
    }

    @Override
    public Address deleteById(Long id) {
        return null;
    }
}
