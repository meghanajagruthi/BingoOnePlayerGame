/*Bingo game for one player*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

	public static boolean checkBingo(Cells cells[][]) {

		boolean check = false;
		for (int i = 0; i < 5; i++) {
			int count = 0;
			for (int j = 0; j < 5; j++) {
				if (cells[i][j].isVisited() == true) {
					count++;
				}
			}

			if (count == 5) {
				check = true;
				break;
			}

		}

		if (check == false) {
			for (int j = 0; j < 5; j++) {
				int count = 0;
				for (int i = 0; i < 5; i++) {
					if (cells[i][j].isVisited() == true) {
						count++;
					}
				}
				if (count == 5) {
					check = true;
					break;
				}

			}
		}

		if (check == false) {
			int count = 0;
			for (int i = 0; i < 5; i++) {
				if (cells[i][i].isVisited() == true) {
					count++;
				}
			}
			if (count == 5) {
				check = true;
			}
		}
		return check;
	}

	public static void searchAnElementInArray(Cells cell[][], int number) {

		boolean check = false;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (cell[i][j].isVisited() == false) {

					if (cell[i][j].getNumber() == number) {
						cell[i][j].setVisited(true);
						check = true;
						break;
					}

				}

			}
			if (check) {
				break;
			}
		}

	}

	public static int[][] getCardArray(BufferedReader br) throws IOException {

		int array[][] = new int[5][5];
		String input[] = new String[5];
		for (int i = 0; i < 5; i++) {
			input[i] = br.readLine();
		}
		for (int i = 0; i < 5; i++) {
			String temp[] = input[i].split(" ");
			for (int j = 0; j < 5; j++) {
				array[i][j] = Integer.parseInt(temp[j]);
			}
		}

		/*
		 * System.out.println("The card array is "); for(int i=0;i<5;i++){
		 * for(int j=0;j<5;j++){ System.out.print(array[i][j]);
		 * System.out.print("\t"); } System.out.println(" "); }
		 */
		return array;
	}

	public static int[] getNumberArray(BufferedReader br) throws IOException {

		int array[] = new int[75];
		String input[] = new String[7];

		for (int i = 0; i < 7; i++) {
			input[i] = br.readLine();
		}

		int k = 0;
		for (int i = 0; i < 7; i++) {
			String temp[] = input[i].split(" ");
			for (int l = 0; l < 10; l++) {
				array[k] = Integer.parseInt(temp[l]);
				k++;
			}
		}

		String last[] = br.readLine().split(" "); // to extract last five
													// numbers

		for (int i = 70; i < 75; i++) {

			array[i] = Integer.parseInt(last[i % 70]);
		}

		/*
		 * System.out.println("The number array is  "); for(int i=0;i<75;i++){
		 * System.out.print(array[i]+"\t"); }
		 */
		return array;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer noOfGames = Integer.parseInt(br.readLine());

		for (int l = 0; l < noOfGames; l++) {
			int array[][] = getCardArray(br);

			Cells cells[][] = new Cells[5][5];
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					cells[i][j] = new Cells();
					cells[i][j].setNumber(array[i][j]);
					cells[i][j].setVisited(false);
				}
			}

			int numberArray[] = getNumberArray(br);
			int counter = 0;
			for (int i = 0; i < numberArray.length; i++) {
				searchAnElementInArray(cells, numberArray[i]);
				counter++;

				if (checkBingo(cells)) {
					break;
				}

			}
			System.out.println("BINGO after " + counter + " numbers announced");

			System.out.println("The card array is ");
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					System.out.print(cells[i][j].getNumber());
					System.out.print(cells[i][j].isVisited());
					System.out.print("\t");
				}
				System.out.println(" ");
			}

		}
	}

}

class Cells {
	public int number;
	public boolean visited = false;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}