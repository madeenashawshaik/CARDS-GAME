package in.madeena.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CardGame implements Game
{
        private List<CARD>                              cards;

        private List<Player>                    players                                 = new ArrayList<Player>();

        private Map<Player, List<CARD>> cardsPlayerMap                  = new HashMap<Player, List<CARD>>();

        private int                                             currentPlayerIdx                = 0;

        private static final int                numberOfCardsPerPlayer  = 4;

        private int                                             numberOfPlayers                 = 2;

        public int getNumberOfPlayers()
        {
                return numberOfPlayers;
        }

        public List<Player> getPlayers()
        {
                return players;
        }

        public CardGame()
        {
                cards = CARD.getPackOfCards();
        }

        public void distributeCardsForPlayers(List<Player> plys)
        {
                this.players = plys;
                CARD.shuffleCards(cards);
                if (cardsPlayerMap.size() == 0)
                        cardsPlayerMap.clear();

                int m = 0;
                for (Player pl : players)
                {
                        pl.setPoints(0);
                        List<CARD> cds = new ArrayList<CARD>();
                        int cardLimit = m + numberOfCardsPerPlayer;
                        for (int i = m; i < cardLimit; i++)
                        {
                                cds.add(cards.get(i));
                        }
                        m = cardLimit;
                        cardsPlayerMap.put(pl, cds);
                }
        }

        public void playGame(int numberOfPlayers)
        {
                this.numberOfPlayers = numberOfPlayers;
                createMultipleUser(numberOfPlayers);
                int i = 0;
                System.out.println("Game Started.....  ");
                List<CARD> selCards = new ArrayList<CARD>();
                CARD maxCard = null;
                Player maxPlayer = new Player(0);
                distributeCardsForPlayers(players);
                for (int j = 0; j < numberOfCardsPerPlayer; j++)
                {
                        int s = 0;
                        do
                        {
                                Player player = getNextPlayer();
                                System.out.println("1. display Cards available  \n2. Stop Game");
                                System.out.println("Chance for Player..." + player.getPlayerId());
                                System.out.print("Please provide your option : ");

                                Scanner in = new Scanner(System.in);
                                i = in.nextInt();

                                switch (i)
                                {
                                        case 1:
                                                displayCardsForPlayer(player);
                                                System.out.println("Select your card number :");

                                                in = new Scanner(System.in);
                                                int m = in.nextInt();
                                                CARD c = cardsPlayerMap.get(player).get(m - 1);
                                                System.out.println("Card Selected -> " + c.toString());
                                                cardsPlayerMap.get(player).remove(m - 1);
                                                if (maxCard == null)
                                                {
                                                        maxCard = c;
                                                        maxPlayer = player;
                                                }
                                                else
                                                {
                                                        if (maxCard.compareTo(c) < 0)
                                                        {
                                                                maxCard = c;
                                                                maxPlayer = player;
                                                        }
                                                }
                                                selCards.add(c);

                                                break;
                                        case 2:
                                                return;
                                }

                                System.out.println();
                                s++;
                        } while (s < players.size());
                        if (maxPlayer.getPlayerId() > 0)
                                maxPlayer.setPoints((maxPlayer.getPoints()) + 1);
                        maxCard = null;
                        maxPlayer = null;
                        displayScores();
                }
        }

        private void displayScores()
        {
                for (Player pl : players)
                {
                        System.out.println("Player " + pl.getPlayerId() + " Score -> " + pl.getPoints());
                }

        }

        private void displayCardsForPlayer(Player pl)
        {
                int cards = cardsPlayerMap.get(pl).size();
                for (int i = 0; i < cards;)
                {
                        System.out.print((++i) + " ");
                }
        }

        public void displayWinners()
        {
                Collections.sort(players);
                int maxPoints = 0;
                Map<String, List<Player>> playerPointsMap = new TreeMap<String, List<Player>>();
                for (Player p : players)
                {

                        maxPoints = p.getPoints();
                        if (playerPointsMap.get(maxPoints + "") != null)
                        {
                                List<Player> lst = playerPointsMap.get(maxPoints + "");
                                lst.add(p);
                                playerPointsMap.put(maxPoints + "", lst);
                        }
                        else
                        {
                                List<Player> lst = new ArrayList<Player>();
                                lst.add(p);
                                playerPointsMap.put(maxPoints + "", lst);
                        }
                }
                
                String pts = new Integer(players.get(players.size() - 1).getPoints()).toString();
                if (playerPointsMap.get(pts) != null && playerPointsMap.get(pts).size() > 1)
                {
                        System.out.println("Its a draw among the following players ");
                        for (Player p : players)
                        {
                                System.out.println("Player -> " + p.getPlayerId());
                        }
                }
                else if (playerPointsMap.get(pts) != null)
                {
                        System.out.println("And the winner is Player -> " + playerPointsMap.get(pts).get(0).getPlayerId());
                        System.out.println("Number of rounds=4");
                }
        }

        private void createMultipleUser(int j)
        {
                if (players.size() != 0)
                {
                        players.clear();
                }

                for (int i = 0; i < j; i++)
                {
                        int id = i + 1;
                        Player usr = new Player(id);
                        players.add(usr);
                }
                distributeCardsForPlayers(players);
        }

        private Player getNextPlayer()
        {

                Player p = null;
                if (currentPlayerIdx == players.size())
                {
                        currentPlayerIdx = 1;
                        p = players.get(0);
                }
                else
                {
                        p = players.get(currentPlayerIdx);
                        currentPlayerIdx++;
                }

                return p;
        }
}
