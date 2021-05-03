package ru.my_project.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.my_project.model.Restaurant;
import ru.my_project.to.SumVotes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DataJpaRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> findAllWithDishes();

    @Override
    Optional<Restaurant> findById(Integer id);

    // JPQL allows you to define a constructor call in the SELECT clause: https://thorben-janssen.com/jpql/#Grouping_8211_The_GROUP_BY_and_HAVING_clause
    // How to join unrelated entities with JPA and Hibernate: https://thorben-janssen.com/how-to-join-unrelated-entities/
    @Query("SELECT new ru.my_project.to.SumVotes(restaur.id, restaur.name, count (v)) FROM Restaurant restaur " +
            "LEFT JOIN Vote v ON restaur.id = v.restaurant.id WHERE v.date = ?1 GROUP BY v.restaurant.id ORDER BY count (v) DESC")
    List<SumVotes> getSumVotesCurrentDay(LocalDate date);
}
