package com.shoestore.Server.repositories;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 9:24 PM
    ProjectName: Server
*/

import com.shoestore.Server.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryID(int id);
    @Query("SELECT c.categoryID, c.name, c.description, COUNT(p.productID) AS productCount " +
            "FROM Category  c LEFT JOIN Product p ON c.categoryID = p.category.categoryID " +
            "GROUP BY c.categoryID, c.name, c.description")
    List<Object[]> getAllCategoriesWithProductCount();
}
