package org.example.yodybe.form;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.yodybe.entity.Color}
 */
@Value
public class ColorForm implements Serializable {
    Long id;
    String name;
}