import java.util.*;

public class Hand implements Comparable<Hand>
{
    public final List<Card> cards = new ArrayList<>();

    public void draw(Deck deck, int number)
    {
        for(int i = 0; i < number; i++)
        {
            cards.add(deck.draw());
        }
    }

    public PokerScore evaluateScore()
    {
        sortCards();
        if(isFlush() && isStraight())
        {
            return PokerScore.STRAIGHT_FLUSH;
        }
        if((cards.get(0).value == cards.get(3).value) || (cards.get(1).value == cards.get(4).value))
        {
            return PokerScore.FOUR_OF_A_KIND;
        }
        if(cards.stream().map(card -> card.value).distinct().count() == 2)
        {
            return PokerScore.FULL_HOUSE;
        }
        if(isFlush())
        {
            return PokerScore.FLUSH;
        }
        if(isStraight())
        {

            return PokerScore.STRAIGHT;
        }
        if((cards.get(0).value == cards.get(2).value) ||
                (cards.get(1).value == cards.get(3).value) ||
                (cards.get(2).value == cards.get(4).value))
        {
            return PokerScore.THREE_OF_A_KIND;
        }
        if(cards.stream().map(card -> card.value).distinct().count() == 3)
        {
            return PokerScore.TWO_PAIR;
        }
        if(cards.stream().map(card -> card.value).distinct().count() == 4)
        {
            return PokerScore.ONE_PAIR;
        }
        return PokerScore.HIGH_CARD;
    }

    private boolean isStraight()
    {
        if((cards.get(4).value.getValue() == 14) &&
                (cards.get(0).value.getValue() == 2) &&
                (cards.get(1).value.getValue() == 3) &&
                (cards.get(2).value.getValue() == 4) &&
                (cards.get(3).value.getValue() == 5))
        {
            return true;
        }
        int min = cards.get(0).value.getValue();
        for(int i = 0; i < cards.size(); i++)
        {
            if(cards.get(i).value.getValue() - min != i)
            {
                return false;
            }
        }

        return true;

//        int previousValue = cards.get(0).value.getValue();
//        for(int i = 1; i < cards.size(); i++)
//        {
//            int currentValue = cards.get(i).value.getValue();
//            if((currentValue - previousValue) != 1)
//            {
//                return false;
//            }
//            previousValue = currentValue;
//        }
//        return true;
    }

    private boolean isFlush()
    {
        CardSuit suit = cards.get(0).suit;
        for(Card card : cards)
        {
            if(card.suit != suit)
            {
                return false;
            }
        }
        return true;
    }

    public void sortCards()
    {
        Collections.sort(cards);
    }

    @Override
    public int compareTo(Hand hand)
    {
        if(this.evaluateScore().compareTo(hand.evaluateScore()) != 0)
        {
            return this.evaluateScore().compareTo(hand.evaluateScore());
        }
        if(this.evaluateScore() == PokerScore.STRAIGHT || this.evaluateScore() == PokerScore.STRAIGHT_FLUSH)
        {
            Integer firstHighestCard = this.cards.stream()
                    .mapToInt(card -> card.value.getValue())
                    .max().getAsInt();
            if(this.cards.stream().map(card -> card.value).anyMatch(cardValue -> cardValue == CardValue.ACE)
                    && this.cards.stream().map(card -> card.value).anyMatch(cardValue -> cardValue == CardValue.TWO))
            {
                firstHighestCard = 5;
            }
            Integer secondHighestCard = hand.cards.stream()
                    .mapToInt(card -> card.value.getValue())
                    .max().getAsInt();
            if(hand.cards.stream().map(card -> card.value).anyMatch(cardValue -> cardValue == CardValue.ACE)
                    && hand.cards.stream().map(card -> card.value).anyMatch(cardValue -> cardValue == CardValue.TWO))
            {
                secondHighestCard = 5;
            }

            return firstHighestCard.compareTo(secondHighestCard);
        }
        if(this.evaluateScore() == PokerScore.FULL_HOUSE)
        {
            Integer firstTrips = this.cards.get(0).value.getValue();
            Integer firstDouble = this.cards.get(4).value.getValue();
            if(firstTrips != this.cards.get(2).value.getValue())
            {
                firstDouble = firstTrips;
                firstTrips = this.cards.get(2).value.getValue();
            }
            Integer secondTrips = hand.cards.get(0).value.getValue();
            Integer secondDouble = hand.cards.get(4).value.getValue();
            if(secondTrips != hand.cards.get(2).value.getValue())
            {
                secondDouble = secondTrips;
                secondTrips = hand.cards.get(2).value.getValue();
            }
            if(firstTrips.equals(secondTrips))
            {
                return firstDouble.compareTo(secondDouble);
            }
            else
            {
                return firstTrips.compareTo(secondTrips);
            }
        }

        if(this.evaluateScore() == PokerScore.THREE_OF_A_KIND)
        {
            Integer firstTrips = this.cards.get(2).value.getValue();
            Integer secondTrips = hand.cards.get(2).value.getValue();

            if(firstTrips.equals(secondTrips))
            {
                return calculateHighCard(hand);
            }
            else
            {
                return firstTrips.compareTo(secondTrips);
            }
        }
        if(this.evaluateScore() == PokerScore.FOUR_OF_A_KIND)
        {
            Integer firstFour = this.cards.get(2).value.getValue();
            Integer secondFour = hand.cards.get(2).value.getValue();
            if(firstFour.equals(secondFour))
            {
                return calculateHighCard(hand);
            }
            else
            {
                return firstFour.compareTo(secondFour);
            }
        }
        if((this.evaluateScore() == PokerScore.TWO_PAIR))
        {
            Integer firstBigPair = this.cards.get(3).value.getValue();
            Integer firstSmallPair = this.cards.get(1).value.getValue();
            Integer secondBigPair = hand.cards.get(3).value.getValue();
            Integer secondSmallPair =  hand.cards.get(1).value.getValue();

            if(firstBigPair.equals(secondBigPair))
            {
                if(firstSmallPair.equals(secondSmallPair))
                {
                    return calculateHighCard(hand);
                }
                else
                {
                    return firstSmallPair.compareTo(secondSmallPair);
                }
            }
            return firstBigPair.compareTo(secondBigPair);
        }
        if((this.evaluateScore() == PokerScore.ONE_PAIR))
        {
            Set<Integer> cardSet = new HashSet<>();
            Integer firstPair = this.cards.stream()
                    .map(card -> card.value.getValue())
                    .filter(card -> !cardSet.add(card))
                    .findFirst().get();
            cardSet.clear();
            Integer secondPair = hand.cards.stream()
                    .map(card -> card.value.getValue())
                    .filter(card -> !cardSet.add(card))
                    .findFirst().get();

            if(firstPair.equals(secondPair))
            {
                return calculateHighCard(hand);
            }
            return firstPair.compareTo(secondPair);
        }
        return calculateHighCard(hand);
    }

    public int calculateHighCard(Hand secondHand)
    {
        for(int i = 4; i >= 0; i--)
        {
            Integer firstHighestCard = this.cards.get(i).value.getValue();
            Integer secondHighestCard = secondHand.cards.get(i).value.getValue();
            if(!firstHighestCard.equals(secondHighestCard))
            {
                return firstHighestCard.compareTo(secondHighestCard);
            }
        }
        return 0;
    }

    @Override
    public String toString()
    {
        return cards.toString();
    }
}
