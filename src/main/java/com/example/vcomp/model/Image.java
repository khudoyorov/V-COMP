package com.example.vcomp.model;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDetails productDetails;
    @OneToMany(mappedBy = "image")
    private List<ImageQuality> imageQuality = new ArrayList<>();
}
