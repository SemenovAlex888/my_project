package com.github.SemenovAlex888.VotingRestaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames =
        {"user_id", "date_time"}, name = "votes_unique_user_datetime_idx")})
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)      // unidirectional @ManyToOne https://habr.com/ru/post/542328/
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    // @JsonIgnore (instead of @JsonIgnore annotation, the jackson-datatype-hibernate module is connected)
    private User user;

    @Column(name = "date_time", nullable = false)
    // Confusion: @NotNull vs. @Column(nullable = false): https://stackoverflow.com/questions/7439504/confusion-notnull-vs-columnnullable-false-with-jpa-and-hibernate
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    // @JsonIgnore (instead of @JsonIgnore annotation, the jackson-datatype-hibernate module is connected)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(User user, LocalDate date, Restaurant restaurant) {
        this.id = null;
        this.user = user;
        this.date = date;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, User user, LocalDate date, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.date = date;
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", restaurant=" + restaurant +
                '}';
    }
}
