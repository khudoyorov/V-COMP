package com.example.vcomp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer amount;
    private String description;
    private Integer price;
    private Integer discount;
    @ManyToOne
    private Category category;
//    @ManyToOne
//    @JoinColumn(name = "brand_id")
//    private Brand brand;
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductVariant> productVariants;
//    @ManyToMany(mappedBy = "favorited")
//    private Set<Users> favourited = new HashSet<>();`
//    @ManyToMany(mappedBy = "viewed")
//    private Set<Users> viewed = new HashSet<>();
    private Boolean isAvailable;
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime date;
}
