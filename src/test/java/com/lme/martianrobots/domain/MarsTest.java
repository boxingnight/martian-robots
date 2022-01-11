package com.lme.martianrobots.domain;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MarsTest {

    @Test
    public void shouldBeOutOfBounds() {
        assertThat(new Mars(2,2).isOutOfBounds(2,3)).isTrue();
        assertThat(new Mars(2,2).isOutOfBounds(3,2)).isTrue();
        assertThat(new Mars(2,2).isOutOfBounds(-1,2)).isTrue();
        assertThat(new Mars(2,2).isOutOfBounds(-1,-1)).isTrue();
    }

    @Test
    public void shouldSeeExistingOutOfBoundsCoords() {
        Mars mars = new Mars(2,2);
        mars.saveOutOfBoundsCoordinate(2,3);
        assertThat(mars.isExistingOutOfBoundsCoordinate(2,3)).isTrue();
    }
}