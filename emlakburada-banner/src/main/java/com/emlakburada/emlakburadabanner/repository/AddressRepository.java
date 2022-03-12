package com.emlakburada.emlakburadabanner.repository;

import com.emlakburada.emlakburadabanner.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}