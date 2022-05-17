package com.heylichen.algorithm.util.visualize;;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreeLine {
    private String text;
    private int leftOffset;
    private int rightOffset;

    public void addLeftOffset(int delta) {
        leftOffset += delta;
    }

    public void addRightOffset(int delta) {
        rightOffset+= delta;
    }

    public void addOffsets(int delta) {
        leftOffset += delta;
        rightOffset+= delta;
    }
}
