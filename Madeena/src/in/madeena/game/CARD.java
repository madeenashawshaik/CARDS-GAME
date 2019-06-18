package in.madeena.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CARD implements Comparable<CARD>
{
        private CARD()
        {
        }

        public enum CARDNUMBER
        {
                TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(
                                14);

                private int     ord;

                private CARDNUMBER(int i)
                {
                        this.ord = i;
                }

                /**
                 * Returns the ordinal position of the enum
                 * 
                 * @return int ord
                 */
                public int getOrd()
                {
                        return ord;
                }
        }

        public enum CARDTYPE
        {
                CLUB, DIAMOND, HEARTS, SPADE;
        }

        private CARDNUMBER      cdNumber;
        private CARDTYPE        cdType;

        public CARDNUMBER getCdNumber()
        {
                return cdNumber;
        }

        public CARDTYPE getCdType()
        {
                return cdType;
        }

        public static List<CARD> getPackOfCards()
        {
                List<CARD> crdLst = new ArrayList<CARD>();

                for (CARDTYPE types : CARDTYPE.values())
                {
                        for (CARDNUMBER cNums : CARDNUMBER.values())
                        {
                                CARD cd = new CARD();
                                cd.cdNumber = cNums;
                                cd.cdType = types;
                                crdLst.add(cd);
                        }
                }
                return crdLst;
        }

        public static void shuffleCards(List<CARD> cards)
        {
                Collections.shuffle(cards);
        }

        @Override
        public int compareTo(CARD o)
        {
                if (this.getCdNumber() == o.getCdNumber())
                {
                        return 0;
                }
                else if (this.getCdNumber().getOrd() > o.getCdNumber().getOrd())
                {
                        return 1;
                }
                else
                        return -1;
        }

        @Override
        public String toString()
        {
                return "CARD [" + cdNumber + ", " + cdType + "]";
        }
}

