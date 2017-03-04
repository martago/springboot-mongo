package de.janosch.simple.address.rest;

import de.janosch.simple.address.Address;
import de.janosch.simple.address.rest.resource.V1AddressResource;
import de.janosch.simple.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AddressREST
 */
public class AddressREST implements V1AddressResource {

    private AddressService addressService;

    @Autowired
    public AddressREST(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public GetV1AddressResponse getV1Address() throws Exception {
        return GetV1AddressResponse.withJsonOK(addressService.getAll());
    }

    @Override
    public PostV1AddressResponse postV1Address(Address address) throws Exception {
        return PostV1AddressResponse.withJsonOK(addressService.saveAddress(address));
    }

    @Override
    public GetV1AddressByAddressIdResponse getV1AddressByAddressId(String addressId) throws Exception {
        return GetV1AddressByAddressIdResponse.withJsonOK(addressService.findAddress(addressId));
    }

    @Override
    public PutV1AddressByAddressIdResponse putV1AddressByAddressId(String addressId, Address address) throws Exception {
        if (addressService.updateAddress(addressId, address)) {
            return PutV1AddressByAddressIdResponse.withOK();
        }

        return PutV1AddressByAddressIdResponse.withNotFound();
    }


}