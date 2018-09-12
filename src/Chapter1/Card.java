package Chapter1;

public enum Card {

    Hearts(2, 3, 4, 5, 6, 7, 8, 9, 10, "Jack", "Queen", "King", "Ace"),
    Diamonds(2, 3, 4, 5, 6, 7, 8, 9, 10, "Jack", "Queen", "King", "Ace"),
    Clovers(2, 3, 4, 5, 6, 7, 8, 9, 10, "Jack", "Queen", "King", "Ace"),
    Spades(2, 3, 4, 5, 6, 7, 8, 9, 10, "Jack", "Queen", "King", "Ace");

    private int card1;
    private int card2;
    private int card3;
    private int card4;
    private int card5;
    private int card6;
    private int card7;
    private int card8;
    private int card9;
    private String card10;
    private String card11;
    private String card12;
    private String card13;

    Card(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String Jack, String Queen, String King, String Ace) {
        this.card1 = i;
        this.card2 = i1;
        this.card3 = i2;
        this.card4 = i3;
        this.card5 = i4;
        this.card6 = i5;
        this.card7 = i6;
        this.card8 = i7;
        this.card9 = i8;
        this.card10 = Jack;
        this.card11 = Queen;
        this.card12 = King;
        this.card13 = Ace;
    }

    public int Card1() {
        return card1;
    }

    public int Card2() {
        return card2;
    }

    public int Card3() {
        return card3;
    }

    public int Card4() {
        return card4;
    }

    public int Card5() {
        return card5;
    }

    public int Card6() {
        return card6;
    }

    public int Card7() {
        return card7;
    }

    public int Card8() {
        return card8;
    }

    public int Card9() {
        return card9;
    }

    public String Card10() {
        return card10;
    }

    public String Card11() {
        return card11;
    }

    public String Card12() {
        return card12;
    }

    public String Card13() {
        return card13;
    }
}
