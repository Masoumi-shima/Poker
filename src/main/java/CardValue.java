public enum CardValue
{
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE;

    public static CardValue getFromInt(int valueInt)
    {
        return switch (valueInt)
        {
            case 1, 14 -> ACE;
            case 2 -> TWO;
            case 3 -> THREE;
            case 4 -> FOUR;
            case 5 -> FIVE;
            case 6 -> SIX;
            case 7 -> SEVEN;
            case 8 -> EIGHT;
            case 9 -> NINE;
            case 10 -> TEN;
            case 11 -> JACK;
            case 12 -> QUEEN;
            case 13 -> KING;
            default -> null;
        };
    }

    public int getValue()
    {
        return switch (this)
        {
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN -> 10;
            case JACK -> 11;
            case QUEEN -> 12;
            case KING -> 13;
            case ACE -> 14;
        };
    }

    @Override
    public String toString()
    {
        int intValue = getValue();
        return switch (intValue)
        {
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            case 14 -> "A";
            default -> Integer.toString(intValue);
        };
    }
}
