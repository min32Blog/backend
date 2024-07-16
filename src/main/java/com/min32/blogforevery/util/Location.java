package com.min32.blogforevery.util;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class Location extends BaseTimeEntity{
    private double x;
    private double y;
    private double z;
}
