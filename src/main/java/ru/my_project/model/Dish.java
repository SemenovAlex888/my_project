package ru.my_project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames =
        {"restaurant_id", "name"}, name = "dishes_unique_restaurant_id_name")})
public class Dish extends AbstractNamedEntity {

    @Column(name = "price")
    @NotNull
    @DecimalMin(value = "0.0")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference  // https://stackoverflow.com/questions/31319358/jsonmanagedreference-vs-jsonbackreference/47351918
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(String name, BigDecimal price) {    // Todo check the need
        this(null,name, price);
    }

    public Dish(Integer id, String name, BigDecimal price) {
        super(id, name);
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }
}