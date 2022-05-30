public enum CardSuit
{
    DIAMONDS, CLUBS, HEARTS, SPADES;

    public static CardSuit getFromInt(int suitInt)
    {
        return switch (suitInt)
        {
            case 0 -> DIAMONDS;
            case 1 -> CLUBS;
            case 2 -> HEARTS;
            case 3 -> SPADES;
            default -> null;
        };
    }

    @Override
    public String toString()
    {
        return switch (this)
        {
            case DIAMONDS -> "♦";
            case CLUBS -> "♣";
            case HEARTS -> "♥";
            case SPADES -> "♠";
        };
    }
}
