package de.janosch.springbootmongo.service;

import de.janosch.springbootmongo.Address;
import de.janosch.springbootmongo.repository.AddressRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AddressService
 */
@Service
public class AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAll() {
        return mapAddressList(addressRepository.findAll());
    }

    public Address findAddress(String addressId) {
        return mapAddress(addressRepository.findOne(new ObjectId(addressId)));
    }

    public Address saveAddress(Address address) {
        return mapAddress(addressRepository.save(mapAddressEntity(address)));
    }

    private List<Address> mapAddressList(List<de.janosch.springbootmongo.model.Address> addresses) {
        return addresses.stream().map(this::mapAddress).collect(Collectors.toList());
    }

    private Address mapAddress(de.janosch.springbootmongo.model.Address address) {
        return new Address()
                .withId(address.getId().toHexString())
                .withStreet(address.getStreet())
                .withPostcode(address.getPostcode())
                .withCity(address.getCity())
                .withFirstname(address.getFirstname())
                .withLastname(address.getLastname());
    }

    private de.janosch.springbootmongo.model.Address mapAddressEntity(Address address) {
        return new de.janosch.springbootmongo.model.Address()
                .id(address.getId() != null ? new ObjectId(address.getId()) : null)
                .street(address.getStreet())
                .postcode(address.getPostcode())
                .city(address.getCity())
                .firstname(address.getFirstname())
                .lastname(address.getLastname());
    }

    public boolean updateAddress(String addressId, Address address) {
        de.janosch.springbootmongo.model.Address addressDB = addressRepository.findOne(new ObjectId(addressId));

        if (addressDB == null) {
            return false;
        }

        addressRepository.save(mapAddressEntity(address.withId(addressDB.getId().toHexString())));
        return true;
    }
}