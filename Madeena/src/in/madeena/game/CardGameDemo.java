package in.madeena.game;

import java.util.Scanner;

public class CardGameDemo
{
        public CardGameDemo()
        {

        }

        /**
         * @param args
         */
        public static void main(String[] args)
        {
                CardGame sl = new CardGame();
                System.out.println("Display of 4 Sets of 52 cards in the game");
                System.out.println(" 2 of club \n 3 of club \n 4 of club \n 5 of club \n 6 of club \n 7 of club \n 8 of club \n 9 of club \n 10 of club \n jack of club \n queen of club \n king of club \n ace of club \n 2 of4 diamonds \n 3 of diamonds \n 4 of diamonds \n 5 of diamonds \n 6 of diamonds \n 7 of diamonds \n 8 of diamonds \n 9 of diamonds \n 10 of diamonds \n jack of diamonds \n queen of diamonds \n king of diamonds \n ace of diamonds \n 2 of hearts \n 3 of hearts \n 4 of hearts \n 5 of hearts \n 6 of hearts \n 7 of hearts \n 8 of hearts \n 9 of hearts \n 10 of hearts \n jack of hearts \n queen of hearts \n king of hearts \n ace of hearts \n 2 of spade \n 3 of spade \n 4 of spade \n 5 of spade \n 6 of spade \n 7 of spade \n 8 of spade \n 9 of spade \n 10 of spade \n jack of spade \n queen of spade \n spade of spade \n ace of spade \n ");
                System.out.println("Welcome to Cards Game \nUser Options");
                System.out.println("1. Start Game \t  \t2. Exit Game");
                System.out.print("Please provide your option : ");

                int i = 1;

                while (i != 0)
                {
                        Scanner in = new Scanner(System.in);
                        i = in.nextInt();

                        switch (i)
                        {
                                case 1:
                                        System.out.println("Number of Players want to paly ( should be greater than 1 and less than or equal to 4) : ");
                                        in = new Scanner(System.in);
                                        i = in.nextInt();
                                        sl.playGame(i);

                                        sl.displayWinners();
                                        break;

                                case 2:
                                        System.exit(0);
                        }

                        System.out.println();
                        System.out.println("Welcome to Cards Game \t Select User Options");
                        System.out.println("1. Start Game \n2. Exit Game");
                        System.out.print("Please provide your option : ");
                }
        }
}