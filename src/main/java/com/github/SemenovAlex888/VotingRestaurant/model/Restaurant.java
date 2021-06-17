package com.github.SemenovAlex888.VotingRestaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "restaurant")
@Proxy(lazy = false)
// to solve the LazyInitializationException issue while testing: https://javarevisited.blogspot.com/2014/04/orghibernatelazyinitializationException-Could-not-initialize-proxy-no-session-hibernate-java.html#axzz6y2mY6Vio
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "address")
    @NotBlank
    @Size(min = 5, max = 130)
    private String address;

    // difference between @JoinColumn and mappedBy: https://stackoverflow.com/questions/11938253/whats-the-difference-between-joincolumn-and-mappedby-when-using-a-jpa-onetoma
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    // using mappedBy: https://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Unidirectional_OneToMany.2C_No_Inverse_ManyToOne.2C_No_Join_Table_.28JPA_2.x_ONLY.29
    @JsonManagedReference
    // https://stackoverflow.com/questions/31319358/jsonmanagedreference-vs-jsonbackreference/47351918
    private Set<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getDishes(), r.getAddress());
    }

    public Restaurant(String name, Set<Dish> dishes, String address) {
        this(null, name, dishes, address);
    }

    public Restaurant(Integer id, String name, Set<Dish> dishes, String address) {
        super(id, name);
        this.dishes = dishes;
        this.address = address;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> menu) {
        this.dishes = dishes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                ", address='" + address + '\'' +
                '}';
    }
}
