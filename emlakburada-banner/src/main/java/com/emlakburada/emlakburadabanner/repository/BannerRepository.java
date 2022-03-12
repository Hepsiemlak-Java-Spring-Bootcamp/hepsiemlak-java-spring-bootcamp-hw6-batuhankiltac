package com.emlakburada.emlakburadabanner.repository;

import com.emlakburada.emlakburadabanner.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

}