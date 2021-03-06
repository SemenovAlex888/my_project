package com.github.SemenovAlex888.VotingRestaurant.repository;

import com.github.SemenovAlex888.VotingRestaurant.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DataJpaDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Dish save(Dish dish);

    @Override
    Optional<Dish> findById(Integer id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurId ORDER BY d.name DESC")
    List<Dish> getAll(@Param("restaurId") int restaurId);

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurId")
    Dish getByRestaurantId(@Param("id") int dishId, @Param("restaurId") int restaurantId);
}
