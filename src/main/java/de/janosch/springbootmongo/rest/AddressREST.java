package de.janosch.springbootmongo.rest;

import de.janosch.springbootmongo.Address;
import de.janosch.springbootmongo.rest.resource.V1AddressResource;
import de.janosch.springbootmongo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AddressREST
 */
@Component
public class AddressREST implements V1AddressResource {

    @Autowired
    private AddressService addressService;

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