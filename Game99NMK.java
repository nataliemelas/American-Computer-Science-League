package ACSL1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game99NMK {
	/**
	 * PROBLEM: ACSL Ninety-Nine is a two-player card game played with a standard
	 * deck of 52- card (2 – 9, T, J, Q, K, A). To start, each player gets 5 cards
	 * and the remaining cards are placed face down on the table (the pile). In an
	 * actual game the point total is set to zero but for this program an initial
	 * point total will be given. Each player in turn puts down one card. 
	 * The player  adds the point value of his card to the point total and selects a card from
	 * the top of the pile. Each card adds its face value in points (e.g. a 5 is
	 * worth five points, a T is worth 10 points, a J is worth 11 points, a Q is
	 * worth 12 points, a K is worth 13 points, an A is worth 14 points) except for
	 * certain cards that have special values or meanings:
	 * 	• A 9 is a pass (and does not change point total).
	 * 	• A T subtracts 10 points from the total.
	 * 	• A 7 adds either 1 or 7 to the point total. The 7 is added first as long as it does not
	 * 		put the point total over 99.
	 * 	• Also, crossing point total borders 33:34, 55:56 and 77:78 in either direction adds
	 * 		an additional 5 points to the point total.
	 * Cards are played using the following rule: play the card that is the
	 * median point value of the 5 cards. The game ends when the card played puts
	 * the point total over 99.
	 * 
	 * @throws FileNotFoundException
	 */

	public static void main(String[] args) throws FileNotFoundException {
		String startingHands = "";
		File file = new File("TestDataTooToo99"); // takes in file
		Scanner scan = new Scanner(file); // scanner to scan the file
		startingHands = scan.nextLine(); // scan first line of the file
		String[] InitialCards = startingHands.split(", "); // split of the first line of the file into strings
		while (scan.hasNextLine()) { // while there is more data in the file
			int[] POne = new int[5]; // creating player one hand
			// converting cards to point values
			for (int i = 0; i < 5; i++) {
				if (InitialCards[i].equals("T")) {
					POne[i] = 10;
				} else if (InitialCards[i].equals("J")) {
					POne[i] = 11;
				} else if (InitialCards[i].equals("Q")) {
					POne[i] = 12;
				} else if (InitialCards[i].equals("K")) {
					POne[i] = 13;
				} else if (InitialCards[i].equals("A")) {
					POne[i] = 14;
				} else {
					POne[i] = Integer.parseInt(InitialCards[i]);
				}

			}
			Arrays.sort(POne); // sorting in numerical order

			int[] PTwo = new int[5]; // player two hand
			// converting cards to point values
			for (int i = 5; i < 10; i++) {
				if (InitialCards[i].equals("T")) {
					PTwo[i - 5] = 10;
				} else if (InitialCards[i].equals("J")) {
					PTwo[i - 5] = 11;
				} else if (InitialCards[i].equals("Q")) {
					PTwo[i - 5] = 12;
				} else if (InitialCards[i].equals("K")) {
					PTwo[i - 5] = 13;
				} else if (InitialCards[i].equals("A")) {
					PTwo[i - 5] = 14;
				} else {
					PTwo[i - 5] = Integer.parseInt(InitialCards[i]);
				}
			}
			Arrays.sort(PTwo); // sorting in numerical order

			String GameOne = "";
			GameOne = scan.nextLine(); // taking in the data for the game
			String[] CardsDrawn = GameOne.split(", "); // splitting it up into arrays
			// creating the deck of cards
			int[] Deck = new int[11];
			// changing the deck of cards into a deck of the card point values
			for (int i = 0; i < Deck.length; i++) {
				if (CardsDrawn[i].equals("T")) {
					Deck[i] = 10;
				} else if (CardsDrawn[i].equals("J")) {
					Deck[i] = 11;
				} else if (CardsDrawn[i].equals("Q")) {
					Deck[i] = 12;
				} else if (CardsDrawn[i].equals("K")) {
					Deck[i] = 13;
				} else if (CardsDrawn[i].equals("A")) {
					Deck[i] = 14;
				} else {
					Deck[i] = Integer.parseInt(CardsDrawn[i]);
				}
			}
			// taking the starting points
			int TotalPoints = Deck[0];
			// creating an arraylist of the cards played so it's easy to follow
			ArrayList<Integer> CardsPlayed = new ArrayList<Integer>();
			int card = 1; // because already took the first "card" in the deck (the total point value)
			boolean PlayerOneTurn = true; // player one first first
			int points = TotalPoints;

			while (TotalPoints < 100) { // breaks of someone loses
				PlayerOneTurn = true; // player one's turn
				points = TotalPoints; // points is the amount of points before player one plays his/her card
				// cards with special point values
				if (POne[2] == 9) { // if plays a nine
					TotalPoints = TotalPoints + 0;
				} else if (POne[2] == 7) { // if plays a seven
					if (TotalPoints + 7 <= 99) {
						TotalPoints = TotalPoints + 7;
					} else {
						TotalPoints = TotalPoints + 1;
					}
				} else if (POne[2] == 10) { // if plays a ten
					TotalPoints = TotalPoints - 10;
				} else {
					TotalPoints = TotalPoints + POne[2]; // if plays anything else
				}
				// checks to see if it crossed a boundary
				if ((points <= 33 && TotalPoints >= 34) || (TotalPoints <= 33 && points >= 34)) {
					TotalPoints = TotalPoints + 5;
				}
				if ((points <= 55 && TotalPoints >= 56) || (TotalPoints <= 55 && points >= 56)) {
					TotalPoints = TotalPoints + 5;
				}
				if ((points <= 77 && TotalPoints >= 78) || (TotalPoints <= 77 && points >= 78)) {
					TotalPoints = TotalPoints + 5;
				}
				CardsPlayed.add(POne[2]); // adds card to array of cards played
				if (TotalPoints < 100) {
					POne[2] = Deck[card]; // player one draws a new card if the game is to continue
				}
				Arrays.sort(POne); // sort player one's hard in numerical order
				card++; // moves you down one in the array

				if (TotalPoints < 100) {
					PlayerOneTurn = false; // player two's turn
					points = TotalPoints;// updates points variable
					// cards with special point values:
					if (PTwo[2] == 9) { // if playing a 9
						TotalPoints = TotalPoints + 0;
					} else if (PTwo[2] == 7) { // if playing a 7
						if (TotalPoints + 7 <= 99) {
							TotalPoints = TotalPoints + 7;
						} else {
							TotalPoints = TotalPoints + 1;
						}
					} else if (PTwo[2] == 10) {// playing a 10
						TotalPoints = TotalPoints - 10;
					} else {
						TotalPoints = TotalPoints + PTwo[2]; // playing any other card
					}
					// checks to see if it crossed a boundary
					if ((points <= 33 && TotalPoints >= 34) || (TotalPoints <= 33 && points >= 34)) {
						TotalPoints = TotalPoints + 5;
					}
					if ((points <= 55 && TotalPoints >= 56) || (TotalPoints <= 55 && points >= 56)) {
						TotalPoints = TotalPoints + 5;
					}
					if ((points <= 77 && TotalPoints >= 78) || (TotalPoints <= 77 && points >= 78)) {
						TotalPoints = TotalPoints + 5;
					}
					CardsPlayed.add(PTwo[2]); // adds card to array of cards played
					if (TotalPoints < 100) {
						PTwo[2] = Deck[card]; // player one draws a new card if the game is to continue
					}
					Arrays.sort(PTwo); // sort player one's hard in numerical order
					card++; // moves you down one in the array
				}
			}
			// prints out winner and total points
			if (PlayerOneTurn == true) {
				System.out.println(TotalPoints + ", " + "Player #2");
			} else {
				System.out.println(TotalPoints + ", " + "Player #1");
			}
		}
		scan.close();
	}
}
