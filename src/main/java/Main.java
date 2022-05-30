import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        List<Hand> hands = new ArrayList<>();
        Deck deck = new Deck();
        deck.shuffle();

        Hand customHand = new Hand();
        customHand.cards.add(new Card(CardSuit.HEARTS, CardValue.ACE));
        customHand.cards.add(new Card(CardSuit.HEARTS, CardValue.TWO));
        customHand.cards.add(new Card(CardSuit.HEARTS, CardValue.THREE));
        customHand.cards.add(new Card(CardSuit.HEARTS, CardValue.FOUR));
        customHand.cards.add(new Card(CardSuit.HEARTS, CardValue.FIVE));
        hands.add(customHand);
        customHand = new Hand();
        customHand.cards.add(new Card(CardSuit.CLUBS, CardValue.SIX));
        customHand.cards.add(new Card(CardSuit.HEARTS, CardValue.TWO));
        customHand.cards.add(new Card(CardSuit.HEARTS, CardValue.THREE));
        customHand.cards.add(new Card(CardSuit.HEARTS, CardValue.FOUR));
        customHand.cards.add(new Card(CardSuit.HEARTS, CardValue.FIVE));
        hands.add(customHand);

        for(int i = 0; i < 500; i++)
        {
            deck.shuffle();
            Hand myHand = new Hand();
            myHand.draw(deck, 5);
            hands.add(myHand);
        }
        hands.sort(Collections.reverseOrder());
        for (Hand hand : hands)
        {
            System.out.printf("%s %s\n", hand, hand.evaluateScore());
        }
    }
}
