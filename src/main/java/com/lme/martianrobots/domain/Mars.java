package com.lme.martianrobots.domain;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Mars {

    private final int x;
    private final int y;

    private final Multimap<Integer, Integer> savedOutOfBoundsCoordinates = ArrayListMultimap.create();

    public boolean isOutOfBounds(int x, int y) {
        return x < 0 || x > this.x
                || y < 0 || y > this.y;
    }

    public void saveOutOfBoundsCoordinate(int x, int y) {
        savedOutOfBoundsCoordinates.put(x, y);
    }

    public boolean isExistingOutOfBoundsCoordinate(int x, int y) {
        return savedOutOfBoundsCoordinates.get(x).contains(y);
    }
}
