package Chapter1;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {
    Counter heads;
    Counter tails;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        heads = new Counter("heads");
        tails = new Counter("tails");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        heads = null;
        tails = null;
    }

    @org.junit.jupiter.api.Test
    void increment() {
        heads.increment();
        heads.increment();
        assertEquals(2, heads.tally());
    }

    @org.junit.jupiter.api.Test
    void tally() {
        for (int i = 1; i <= 10; i++) {
            heads.increment();
            tails.increment();
        }
        assertEquals(20, heads.tally() + tails.tally());
    }



}