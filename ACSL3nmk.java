package ACSL3;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ACSL3nmk {
	/**
	 * Natalie MK ACSL3
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		String ArrayValues = "";
		File file = new File("Data.txt3"); // takes in file
		Scanner scan = new Scanner(file); // scanner to scan the file
		ArrayValues = scan.nextLine(); // scan first line of the file
		String[] Array = ArrayValues.split(", "); // split of the first line of the file into strings
		int[] DecimalValues = new int[8]; // row 1
		for (int i = 0; i < 8; i++) {
			DecimalValues[i] = Integer.parseInt(Integer.toOctalString(Integer.parseInt(Array[i], 16)));
		}
		int[] Row1 = new int[8]; // row 1
		for (int i = 0; i < 8; i++) {
			String num = String.valueOf(DecimalValues[0]);
			char[] digits = num.toCharArray();
			String[] Sdigits = num.split("(?<=.)");
			Row1[i] = Integer.parseInt(Sdigits[i]);
		}

		int[] Row2 = new int[8]; // row 2
		for (int i = 0; i < 8; i++) {
			String num = String.valueOf(DecimalValues[1]);
			char[] digits = num.toCharArray();
			String[] Sdigits = num.split("(?<=.)");
			Row2[i] = Integer.parseInt(Sdigits[i]);
		}

		int[] Row3 = new int[8]; // row 3
		for (int i = 0; i < 8; i++) {
			String num = String.valueOf(DecimalValues[2]);
			char[] digits = num.toCharArray();
			String[] Sdigits = num.split("(?<=.)");
			Row3[i] = Integer.parseInt(Sdigits[i]);
		}

		int[] Row4 = new int[8]; // row 4
		for (int i = 0; i < 8; i++) {
			String num = String.valueOf(DecimalValues[3]);
			char[] digits = num.toCharArray();
			String[] Sdigits = num.split("(?<=.)");
			Row4[i] = Integer.parseInt(Sdigits[i]);
		}

		int[] Row5 = new int[8]; // row 5
		for (int i = 0; i < 8; i++) {
			String num = String.valueOf(DecimalValues[4]);
			char[] digits = num.toCharArray();
			String[] Sdigits = num.split("(?<=.)");
			Row5[i] = Integer.parseInt(Sdigits[i]);
		}

		int[] Row6 = new int[8]; // row 6
		for (int i = 0; i < 8; i++) {
			String num = String.valueOf(DecimalValues[5]);
			char[] digits = num.toCharArray();
			String[] Sdigits = num.split("(?<=.)");
			Row6[i] = Integer.parseInt(Sdigits[i]);
		}

		int[] Row7 = new int[8]; // row 7
		for (int i = 0; i < 8; i++) {
			String num = String.valueOf(DecimalValues[6]);
			char[] digits = num.toCharArray();
			String[] Sdigits = num.split("(?<=.)");
			Row7[i] = Integer.parseInt(Sdigits[i]);
		}

		int[] Row8 = new int[8]; // row 8
		for (int i = 0; i < 8; i++) {
			String num = String.valueOf(DecimalValues[7]);
			char[] digits = num.toCharArray();
			String[] Sdigits = num.split("(?<=.)");
			Row8[i] = Integer.parseInt(Sdigits[i]);
		}
		int[][] board = new int[8][8];
		for (int i = 0; i < board.length; i++) {
			board[0][i] = Row8[i];
			board[1][i] = Row7[i];
			board[2][i] = Row6[i];
			board[3][i] = Row5[i];
			board[4][i] = Row4[i];
			board[5][i] = Row3[i];
			board[6][i] = Row2[i];
			board[7][i] = Row1[i];
		}
		// for (int i = 0; i < board[1].length; i++) {
		// for (int j = 0; j < board.length; j++) {
		// System.out.print(board[i][j] + " ");
		// }
		// System.out.println("");
		// }
		// System.out.println(" ");

		while (scan.hasNextLine()) { // while there is more data in the file
			int rowStart = 0;
			int colStart = 0;
			int numMoves = 0;
			int angle = 0;
			String Game = "";
			Game = scan.nextLine(); // taking in the data for the game
			String[] Info = Game.split(", "); // splitting it up into arrays
			// creating the deck of cards
			rowStart = Integer.parseInt(Info[0]);
			colStart = Integer.parseInt(Info[1]);
			char direction = Info[2].charAt(0);
			numMoves = Integer.parseInt(Info[3]);
			int row = 8 - rowStart;
			int col = colStart - 1;

			for (int i = 0; i < numMoves; i++) {
				angle = board[row][col] * 45;
				// System.out.println(angle);

				if (direction == 'L') {
					if (angle == 45) {
						row = row - 1;
						col = col - 1;
						direction = 'Z';
						// (r+1, c-1)
					} else if (angle == 90) {
						row = row - 1;
						direction = 'B';
						// (r+1, c)
					} else if (angle == 135) {
						row = row - 1;
						col = col + 1;
						direction = 'Y';
						// (r+1, c+1) - 135
					} else if (angle == 180) {
						col = col + 1;
						direction = 'L';
						// (r, c+1) - 180
					} else if (angle == 225) {
						row = row + 1;
						col = col + 1;
						direction = 'W';
						// (r-1, c+1) - 225
					} else if (angle == 270) {
						row = row + 1;
						direction = 'A';
						// (r-1, c) - 270
					} else if (angle == 315) {
						row = row + 1;
						col = col - 1;
						direction = 'X';
						// (r-1, c-1) - 315
					}

				} else if (direction == 'R') {
					if (angle == 45) {
						row = row + 1;
						col = col + 1;
						direction = 'W';
						// (r-1, c+1)
					} else if (angle == 90) {
						row = row + 1;
						direction = 'A';
						// (r-1, c)
					} else if (angle == 135) {
						row = row + 1;
						col = col - 1;
						direction = 'X';
						// (r-1, c-1) - 135
					} else if (angle == 180) {
						col = col - 1;
						direction = 'R';
						// (r, c-1) - 180
					} else if (angle == 225) {
						row = row - 1;
						col = col - 1;
						direction = 'Z';
						// (r+1, c-1) - 225
					} else if (angle == 270) {
						row = row - 1;
						direction = 'B';
						// (r+1, c) - 270
					} else if (angle == 315) {
						row = row - 1;
						col = col + 1;
						direction = 'Y';
						// (r+1, c+1) - 315
					}

				} else if (direction == 'B') {
					if (angle == 45) {
						row = row + 1;
						col = col - 1;
						direction = 'X';
						// (r-1, c-1)
					} else if (angle == 90) {
						col = col - 1;
						direction = 'R';
						// (r, c-1)
					} else if (angle == 135) {
						row = row - 1;
						col = col - 1;
						direction = 'Z';
						// (r+1, c-1) - 135
					} else if (angle == 180) {
						row = row - 1;
						direction = 'B';
						// (r+1, c) - 180
					} else if (angle == 225) {
						row = row - 1;
						col = col + 1;
						direction = 'Y';
						// (r+1, c+1) - 225
					} else if (angle == 270) {
						col = col + 1;
						direction = 'L';
						// (r, c+1) - 270
					} else if (angle == 315) {
						row = row + 1;
						col = col + 1;
						direction = 'W';
						// (r-1, c+1) - 315
					}

				} else if (direction == 'A') {
					if (angle == 45) {
						row = row - 1;
						col = col + 1;
						direction = 'Y';
						// (r+1, c+1)
					} else if (angle == 90) {
						col = col + 1;
						direction = 'L';
						// (r, c+1)
					} else if (angle == 135) {
						row = row + 1;
						col = col + 1;
						direction = 'W';
						// (r-1, c+1) - 135
					} else if (angle == 180) {
						row = row + 1;
						direction = 'A';
						// (r-1, c) - 180
					} else if (angle == 225) {
						row = row + 1;
						col = col - 1;
						direction = 'X';
						// (r-1, c-1) - 225
					} else if (angle == 270) {
						col = col - 1;
						direction = 'R';
						// (r, c-1) - 270
					} else if (angle == 315) {
						row = row - 1;
						col = col - 1;
						direction = 'Z';
						// (r+1, c-1) - 315
					}

				} else if (direction == 'W') {
					if (angle == 45) {
						row = row - 1;
						direction = 'B';
						// (r+1, c)
					} else if (angle == 90) {
						col = col + 1;
						row = row - 1;
						direction = 'Y';
						// (r+1, c+1)
					} else if (angle == 135) {
						col = col + 1;
						direction = 'L';
						// (r, c+1) - 135
					} else if (angle == 180) {
						row = row + 1;
						col = col + 1;
						direction = 'W';
						// (r-1, c+1) - 180
					} else if (angle == 225) {
						row = row + 1;
						direction = 'A';
						// (r-1, c) - 225
					} else if (angle == 270) {
						col = col - 1;
						row = row + 1;
						direction = 'X';
						// (r-1, c-1) - 270
					} else if (angle == 315) {
						col = col - 1;
						direction = 'R';
						// (r, c-1) - 315
					}

				} else if (direction == 'Z') {
					if (angle == 45) {
						row = row + 1;
						direction = 'A';
						// (r-1, c)
					} else if (angle == 90) {
						col = col - 1;
						row = row + 1;
						direction = 'X';
						// (r-1, c-1)
					} else if (angle == 135) {
						col = col - 1;
						direction = 'R';
						// (r, c-1) - 135
					} else if (angle == 180) {
						row = row - 1;
						col = col - 1;
						direction = 'Z';
						// (r+1, c-1) - 180
					} else if (angle == 225) {
						row = row - 1;
						direction = 'B';
						// (r+1, c) - 225
					} else if (angle == 270) {
						col = col + 1;
						row = row - 1;
						direction = 'Y';
						// (r+1, c+1) - 270
					} else if (angle == 315) {
						col = col + 1;
						direction = 'L';
						// (r, c+1) - 315
					}

				} else if (direction == 'X') {
					if (angle == 45) {
						col = col + 1;
						direction = 'L';
						// (r, c+1)
					} else if (angle == 90) {
						col = col + 1;
						row = row + 1;
						direction = 'W';
						// (r-1, c+1)
					} else if (angle == 135) {
						row = row + 1;
						direction = 'A';
						// (r-1, c) - 135
					} else if (angle == 180) {
						row = row + 1;
						col = col - 1;
						direction = 'X';
						// (r-1, c-1) - 180
					} else if (angle == 225) {
						col = col - 1;
						direction = 'R';
						// (r, c-1) - 225
					} else if (angle == 270) {
						col = col - 1;
						row = row - 1;
						direction = 'Z';
						// (r+1, c-1) - 270
					} else if (angle == 315) {
						row = row - 1;
						direction = 'B';
						// (r+1, c) - 315
					}

				} else if (direction == 'Y') {
					if (angle == 45) {
						col = col - 1;
						direction = 'R';
						// (r, c-1)
					} else if (angle == 90) {
						col = col - 1;
						row = row - 1;
						direction = 'Z';
						// (r+1, c-1)
					} else if (angle == 135) {
						row = row - 1;
						direction = 'B';
						// (r+1, c) - 135
					} else if (angle == 180) {
						row = row - 1;
						col = col + 1;
						direction = 'Y';
						// (r+1, c+1) - 180
					} else if (angle == 225) {
						col = col + 1;
						direction = 'L';
						// (r, c+1) - 225
					} else if (angle == 270) {
						col = col + 1;
						row = row + 1;
						direction = 'W';
						// (r-1, c+1) - 270
					} else if (angle == 315) {
						row = row + 1;
						direction = 'A';
						// (r-1, c) - 315
					}

				}

				// has to stay in the boundaries
				if (row == 8) {
					row = 0;
				} else if (row == -1) {
					row = 7;
				}
				if (col == 8) {
					col = 0;
				} else if (col == -1) {
					col = 7;
				}
				// System.out.println(row + ", " + col);

			}
			System.out.println((8 - row) + ", " + (col + 1));
			// System.out.println(" ");
		}
	}
}
