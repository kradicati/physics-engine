package com.kradicati.engine.geometry;

import com.kradicati.engine.util.datastructure.Vector;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Transform {

    private Vector linearTransform;
    private double angularTransform;

}
