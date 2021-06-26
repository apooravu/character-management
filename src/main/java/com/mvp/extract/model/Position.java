package com.mvp.extract.model;


import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Position {
    String number;
    Integer index;
    Integer line;
}
