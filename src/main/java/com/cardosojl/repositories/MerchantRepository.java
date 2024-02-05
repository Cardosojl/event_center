package com.cardosojl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardosojl.models.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer>{

}
