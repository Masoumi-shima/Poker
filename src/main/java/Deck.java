import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck
{
    private final List<Card> usedCards = new ArrayList<>();
    private final List<Card> unusedCards = new ArrayList<>();

    public Deck()
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 1; j < 14; j++)
            {
                Card card = new Card(CardSuit.getFromInt(i), CardValue.getFromInt(j));
                unusedCards.add(card);
            }
        }
    }

    public void shuffle()
    {
        unusedCards.addAll(usedCards);
        usedCards.clear();
        Collections.shuffle(unusedCards);
    }

    public Card draw()
    {
        if(unusedCards.size() == 0)
        {
            return null;
        }
        Card drawnCard = unusedCards.remove(0);
        usedCards.add(drawnCard);
        return drawnCard;
    }

    @Override
    public String toString()
    {
        return unusedCards.toString();
    }
}
