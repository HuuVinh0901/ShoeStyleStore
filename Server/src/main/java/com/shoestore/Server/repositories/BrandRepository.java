package com.shoestore.Server.repositories;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 9:26 PM
    ProjectName: Server
*/

import com.shoestore.Server.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Brand findByBrandID(int id);
}
