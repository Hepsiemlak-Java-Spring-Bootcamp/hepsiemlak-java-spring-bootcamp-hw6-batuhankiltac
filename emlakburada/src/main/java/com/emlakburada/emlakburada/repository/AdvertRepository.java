package com.emlakburada.emlakburada.repository;

import com.emlakburada.emlakburada.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Integer> {

}