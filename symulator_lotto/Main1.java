package symulator_lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		int[] numbers = getNumbers();
		System.out.println("Wybrane liczby przez użytkownika to " + numToStr(numbers));
		int[] draw = drawNum();
		System.out.println("Szczęsliwe liczby dzisiaj to " + numToStr(draw));
		String compare = compare(numbers, draw);
		if (compare.equals("")) {
			System.out.println("Niestety nie wygrałeś");
		} else {
			System.out.println("Gratulacje trafiłeś " + compare);
		}
	}
	//metoda pobiera liczby od użytkownika
	static int[] getNumbers() {
		Scanner scan = new Scanner(System.in);
		int[] numbers = new int[6];
		boolean good = false;
		System.out.println("Podaj liczby, które typujesz w formacie - 1,25,32,41,5,30 : ");

		while (good == false) {
			String line = scan.nextLine();
			String[] parts = line.split(",");
			numbers = dataFormat(parts);

			if (numbers == null) {
				System.out.println("Podaj poprawny format");

			} else {
				Arrays.sort(numbers);
				if (!validateOfNumbers(numbers)) {
					System.out.println("Liczby się powtarzają lub są poza zakresem. Podaj jeszcze raz: ");
				} else {
					good = true;
				}
			}
		}
		return numbers;

	}

	//medota zamienia wprowdzany String na tablice int
	static int[] dataFormat(String[] parts) {
		
		if (parts.length == 6) {
			int[] numbers = new int[6];
			for (int i = 0; i < 6; i++) {
				try {
					numbers[i] = Integer.parseInt(parts[i]);
				} catch (NumberFormatException e) {
					return null;
				}
			}
			return numbers;
		}
		
		return null;

	}

	//metoda sprawdza poprawność wprowadzonych liczb
	static boolean validateOfNumbers(int[] numbers) {
		boolean good = true;
		for (int i = 0; i < 6; i++) {
			if (i < 5) {
				if (numbers[i] == numbers[i + 1]) {
					good = false;
					break;
				} else if (numbers[i] > 49 || numbers[i] < 0) {
					good = false;
					break;
				}
			} else if (numbers[i] > 49 || numbers[i] < 0) {
				good = false;
			}
		}
		return good;
	}

	// metoda zamienia tablice liczb na Stringa do wyświetlenia
	static String numToStr(int[] numbers) {
		String[] arrStr = new String[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			arrStr[i] = "" + numbers[i];
		}
		String str = String.join(", ", arrStr);
		return str;
	}

	// metoda losuje 6 liczb od 1 do 49
	static int[] drawNum() {
		Integer[] arr = new Integer[49];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		Collections.shuffle(Arrays.asList(arr));
		int[] draw = new int[6];
		for (int i = 0; i < draw.length; i++) {
			draw[i] = arr[i];
		}
		return draw;
	}

	// metoda porównuje tablice pod kątem ilości elementów wspólnych
	static String compare(int[] numbers, int[] draw) {
		int count = 0;
		String win = "";
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < draw.length; j++) {
				if (numbers[i] == draw[j]) {
					count++;
				}
			}
		}
		switch (count) {
		case 6:
			win = "szóstkę";
			break;
		case 5:
			win = "piątkę";
			break;
		case 4:
			win = "czwórkę";
			break;
		case 3:
			win = "trójkę";
			break;
		}
		return win;
	}
}
