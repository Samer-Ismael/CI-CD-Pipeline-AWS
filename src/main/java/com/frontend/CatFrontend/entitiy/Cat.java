package com.frontend.CatFrontend.entitiy;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cat")
public class Cat {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String color;
    int age;

    public Cat(String name, String color, int age) {

    }

    @Override
    public String toString() {
        return "Cat{id=" + id + ", name='" + name + "', color='" + color + "', age=" + age + '}';
    }
}
