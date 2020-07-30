package ACSL2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StringMathNMK {
	/**
	 * Output the place number where the missing bracket/parenthesis/curly-cue could
	 * be **NOTE: THE LIST STARTS AT PLACE NUMBER 1
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		String equation = "";
		StringBuffer e;
		File file = new File("Data2.txt"); // takes in file
		Scanner scan = new Scanner(file); // scanner to scan the file
		while (scan.hasNextLine()) { // while there is more data in the file
			int rp = 0;
			int lp = 0;
			int rb = 0;
			int lb = 0;
			int rc = 0;
			int lc = 0;
			int left = 0;
			int right = 0;
			equation = scan.nextLine(); // scan first line of the file
			System.out.println(equation);
			e = new StringBuffer(equation);
			lp = e.indexOf("(");
			rp = e.indexOf(")");
			lb = e.indexOf("[");
			rb = e.indexOf("]");
			lc = e.indexOf("{");
			rc = e.indexOf("}");
			String missing = "";
			int index = 0;
			if (lp == -1) {
				missing = "(";
				index = rp;
			} else if (rp == -1) {
				missing = ")";
				index = lp;
			} else if (lb == -1) {
				missing = "[";
				index = rb;
			} else if (rb == -1) {
				missing = "]";
				index = lb;
			} else if (lc == -1) {
				missing = "{";
				index = rc;
			} else if (rc == -1) {
				missing = "}";
				index = lc;
			}

			int first = 1;
			int last = 0;
			if (missing.equals("(")) {
				first = lb; // from left bracket
				last = rp; // to right parenthesis
				if (rb < rp) {
					first = rb + 1;
					// System.out.println(e.charAt(first));
					// System.out.println("first " + first);
				}
			} else if (missing.equals(")")) {
				first = lp; // from left parenthesis
				last = rb; // to right bracket
				if (lb > lp) {
					last = lb - 1;
				}
			} else if (missing.equals("[")) {
				first = lc; // from the left curly-cue
				last = rb; // to the right bracket
				if (rc < rb) {
					first = rc + 1;
					// System.out.println(e.charAt(first));
					// System.out.println("first " + first);
				}
			} else if (missing.equals("]")) {
				first = lb; // from the left bracket
				last = rc; // to the right curly-cue
				if (lc > lb) {
					last = lc - 1;
				}
			} else if (missing.equals("{")) {
				first = 0; // from the beginning
				last = rc; // to the right curly-cue
			} else if (missing.equals("}")) {
				first = lc; // from the left curly-cue
				last = e.length(); // to the end
			}

			ArrayList<Integer> answers = new ArrayList<Integer>();
			for (int i = first; i < last; i++) {
				if (i == first) {
					if (missing.equals("(")) {
						answers.add(i + 2);
					} else if (missing.equals("[")) {
						answers.add(first + 2);
					} else if (missing.equals("{")) {
						answers.add(1);
					}
				} else if (e.charAt(i) == '+' || e.charAt(i) == '-' || e.charAt(i) == '*' || e.charAt(i) == '/') {
					String ending = e.substring(i, last);
					String starting = e.substring(first, i);
					// FACING LEFT
					if (missing.equals("]")) {
						if ((!(i >= lp && i <= rp))
								&& (ending.indexOf("+") != -1 || ending.indexOf("-") != -1 || ending.indexOf("/") != -1
										|| ending.indexOf("*") != -1)
								&& (starting.indexOf("+") != -1 || starting.indexOf("-") != -1
										|| starting.indexOf("/") != -1 || starting.indexOf("*") != -1)) {
							answers.add(i + 1);
						}
					} else if (missing.equals("}")) {
						if ((!(i >= lb && i <= rb))
								&& (ending.indexOf("+") != -1 || ending.indexOf("-") != -1 || ending.indexOf("/") != -1
										|| ending.indexOf("*") != -1)
								&& (starting.indexOf("+") != -1 || starting.indexOf("-") != -1
										|| starting.indexOf("/") != -1 || starting.indexOf("*") != -1)) {
							answers.add(i + 1);
						}
					} else if (missing.equals(")")) {
						if ((ending.indexOf("+") != -1 || ending.indexOf("-") != -1 || ending.indexOf("/") != -1
								|| ending.indexOf("*") != -1)
								&& (starting.indexOf("+") != -1 || starting.indexOf("-") != -1
										|| starting.indexOf("/") != -1 || starting.indexOf("*") != -1)) {
							answers.add(i + 1);
						}
					}

					// FACING RIGHT
					// PROBLEM: it is splitting a number in half
					ending = e.substring(i + 1, last);
					starting = e.substring(first, i + 1);
					if (missing.equals("[")) {
						if ((!(i >= lp && i <= rp))
								&& (ending.indexOf("+") != -1 || ending.indexOf("-") != -1 || ending.indexOf("/") != -1
										|| ending.indexOf("*") != -1)
								&& (starting.indexOf("+") != -1 || starting.indexOf("-") != -1
										|| starting.indexOf("/") != -1 || starting.indexOf("*") != -1)) {
							answers.add(i + 2);
						}
					} else if (missing.equals("{")) {
						if ((!(i >= lb && i <= rb))
								&& (ending.indexOf("+") != -1 || ending.indexOf("-") != -1 || ending.indexOf("/") != -1
										|| ending.indexOf("*") != -1)
								&& (starting.indexOf("+") != -1 || starting.indexOf("-") != -1
										|| starting.indexOf("/") != -1 || starting.indexOf("*") != -1)) {
							answers.add(i + 2);
						}
					} else if (missing.equals("(")) {
						if ((ending.indexOf("+") != -1 || ending.indexOf("-") != -1 || ending.indexOf("/") != -1
								|| ending.indexOf("*") != -1)
								&& (starting.indexOf("+") != -1 || starting.indexOf("-") != -1
										|| starting.indexOf("/") != -1 || starting.indexOf("*") != -1)) {
							answers.add(i + 2);
						}
					}

				}
				if (missing.equals(")") || missing.equals("]") || missing.equals("}")) {
					if (i + 1 == last) {
						answers.add(i + 2);
					}
				}
			}

			// PRINTING
			for (int i = 0; i < answers.size(); i++) {
				if (i < answers.size() - 1) {
					System.out.print(answers.get(i) + ", ");
				} else {
					System.out.println(answers.get(i));
				}
			}
		}
	}
}
