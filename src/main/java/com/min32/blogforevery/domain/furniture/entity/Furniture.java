package com.min32.blogforevery.domain.furniture.entity;

import com.min32.blogforevery.util.Location;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Furniture extends Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
