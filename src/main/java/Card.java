public class Card implements Comparable<Card>
{
    public CardSuit suit;
    public CardValue value;

    public Card(CardSuit suit, CardValue value)
    {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public int compareTo(Card card)
    {
        return this.value.compareTo(card.value);
    }

    @Override
    public String toString()
    {
        return (value.toString() + suit.toString());
    }
}
