package com.thiethaa.advproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="Advproject")
public class Advproject {
    @Id
    private String id;

    @Column (name="Name")
    private String name;

    @Column (name="Ingredient")
    private String ingredient;

    @Column (name="Image")
    @Lob
    private byte[] image;

    private String fileType;

    private String fileName;

    private String thumbnailUrl;

}
