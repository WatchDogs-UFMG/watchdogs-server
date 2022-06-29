package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.Address;
import br.ufmg.watchdogs.server.api.model.form.CreateAddressForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateAddressForm;
import br.ufmg.watchdogs.server.api.repository.AddressRepository;
import br.ufmg.watchdogs.server.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

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
