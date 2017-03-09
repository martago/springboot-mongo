package de.janosch.springbootmongo.repository;

import de.janosch.springbootmongo.model.Address;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * AddressRepository
 */
public interface AddressRepository extends MongoRepository<Address, ObjectId> {
}