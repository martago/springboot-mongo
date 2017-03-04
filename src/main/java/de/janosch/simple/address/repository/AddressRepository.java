package de.janosch.simple.address.repository;

import de.janosch.simple.address.model.Address;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * AddressRepository
 */
public interface AddressRepository extends MongoRepository<Address, ObjectId> {
}