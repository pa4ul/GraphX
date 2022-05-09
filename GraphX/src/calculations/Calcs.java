package calculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Calcs {
	private HashMap<String, Integer> Eccentricity = new HashMap<String, Integer>();
	private int radius = 9999;

	public Integer[][] calcDistanzmatrix(Matrix m) {
		int size = m.getSize();
		boolean hasNull = true;
		Integer[][] AMatrix = m.getMatrix();
		Integer[][] DMatrix = copyMatrix(AMatrix, size);
		Integer[][] AMatrixInitial = copyMatrix(AMatrix, size);
		int counter = 1;

		// D(1) erstellen
		// Jede 0 aus der Angabe mit "unendlich" übernehmen
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (DMatrix[i][j] == 0) {
					DMatrix[i][j] = null;
				}
			}
		}
		// Hauptdiagonale wieder mit 0er füllen
		for (int i = 0; i < size; i++) {
			DMatrix[i][i] = 0;
		}

		while (counter <= m.getSize()) {
			// A^x erstellen
			AMatrix = matrixMultiplication(AMatrix, AMatrixInitial, size);
			counter++;
			// D^x erstellen
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (DMatrix[i][j] == null && AMatrix[i][j] != 0) {
						DMatrix[i][j] = counter;
					}
				}
			}
			hasNull = checkNull(size, DMatrix);
		}

		System.out.println("Distanzmatrix");
		outputMatrix(DMatrix, size);
		return DMatrix;
	}

	// Exzentrizität berechnen
	public void calcEccentricity(Matrix m) {
		Integer[][] Matrix = m.getMatrix();

		if (checkNull(Matrix.length, Matrix)) {
			System.out.println("Graph nicht zusammenhängend");
		} else {
			int run = 0;
			for (int j = 0; j < Matrix.length; j++) {
				int counter = 0;
				for (int i = 0; i < Matrix.length; i++) {
					if (Matrix[j][i] > counter) {
						counter = Matrix[j][i];

					}
				}
				String letter = toAlphabetic(run);
				Eccentricity.put(letter, counter);
				run++;
			}

			System.out.println(Eccentricity);
			calcDiameter();
			calcRadius();
			calcCenter();
		}

	}

	public void calcDiameter() {
		int counter = 0;
		for (String key : Eccentricity.keySet()) {
			if (counter < Eccentricity.get(key))
				counter = Eccentricity.get(key);

		}
		System.out.println("dm(G)=" + counter);
	}

	public void calcRadius() {
		for (String key : Eccentricity.keySet()) {
			if (radius > Eccentricity.get(key))
				radius = Eccentricity.get(key);

		}
		System.out.println("rad(G)=" + radius);
	}

	public void calcCenter() {
		HashMap<String, Integer> temp = new HashMap<String, Integer>();

		for (Entry<String, Integer> entry : Eccentricity.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			if (value == radius) {
				temp.put(key, value);
			}
		}
		System.out.print("Z(G)={ ");
		for (Entry<String, Integer> entry2 : temp.entrySet()) {
			String key = entry2.getKey();
			Integer value = entry2.getValue();
			System.out.print(key + " ");
		}
		System.out.println("}");

	}

	public Integer[][] matrixMultiplication(Integer[][] m1, Integer[][] m2, int sizeOfMatrix) {
		int size = sizeOfMatrix;
		Integer[][] a = m1;
		Integer[][] b = m2;
		Integer[][] c = new Integer[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				c[i][j] = 0;
				for (int k = 0; k < size; k++) {
					c[i][j] += a[i][k] * b[k][j];
				} // end of k loop
			} // end of j loop
		}

		return c;

	}

	public boolean checkNull(int size, Integer[][] DMatrix) {
		// testen ob Distanzmatrix noch "unendlich" values hat
		boolean containNull = false;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (DMatrix[i][j] == null) {
					containNull = true;
				}
			}
		}
		return containNull;
	}

	public void outputMatrix(Integer[][] m, int size) {
		// aktuelle Matrix ausgeben - zum Testen
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println("\n");
		}
	}

	public Integer[][] copyMatrix(Integer[][] m, int size) {
		Integer[][] a = new Integer[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				a[i][j] = m[i][j];
			}
		}
		return a;
	}

	// Nummer in Buchstabe
	public static String toAlphabetic(int i) {
		if (i < 0) {
			return "-" + toAlphabetic(-i - 1);
		}

		int quot = i / 26;
		int rem = i % 26;
		char letter = (char) ((int) 'A' + rem);
		if (quot == 0) {
			return "" + letter;
		} else {
			return toAlphabetic(quot - 1) + letter;
		}
	}

	public boolean compareMatrix(Integer[][] m1, Integer[][] m2, int size) {
		boolean isEqual = true;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (m1[i][j] != m2[i][j]) {
					isEqual = false;
				}
			}
		}
		return isEqual;
	}

	public boolean hasMatrixNewValues(Integer[][] tempMatrix, Integer[][] PMatrix, int size) {
		boolean hasNewValues = false;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (tempMatrix[i][j] == 0 && PMatrix[i][j] != 0) {
					hasNewValues = true;
				}
			}
		}
		return hasNewValues;

	}

	public Integer[][] calcWegematrix(Matrix m) {
		int size = m.getSize();
		int counter = 0; // nicht Laufzeitoptimiert!!
		Integer[][] WMatrix = m.getMatrix();
		Integer[][] initialMatrix = copyMatrix(WMatrix, size);
		Integer[][] PMatrix = copyMatrix(WMatrix, size);
		Integer[][] tempMatrix = copyMatrix(WMatrix, size);

		// Diagonale füllen

		for (int i = 0; i < size; i++) {
			WMatrix[i][i] = 1;
		}

		// PMatrix potenzieren
		PMatrix = matrixMultiplication(initialMatrix, PMatrix, size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (WMatrix[i][j] != 1 && PMatrix[i][j] != 0) {
					WMatrix[i][j] = 1;
				}
			}
		}
		// !hasMatrixNewValues(tempMatrix, PMatrix, size) -> while schleife nicht
		// laufzeitoptimiert
		while (counter < m.getSize()) {
			counter++;
			tempMatrix = copyMatrix(PMatrix, size);
			// PMatrix potenzieren
			PMatrix = matrixMultiplication(initialMatrix, PMatrix, size);
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (WMatrix[i][j] != 1 && PMatrix[i][j] != 0) {
						WMatrix[i][j] = 1;
					}
				}
			}

		}

		outputMatrix(WMatrix, size);
		return WMatrix;

	}

	public int calcComponents(Integer[][] w) {

		ArrayList<String> storage = new ArrayList<>();
		ArrayList<String> components = new ArrayList<>();
		String sum = "";

		for (int i = 0; i < w.length; i++) {

			for (int j = 0; j < w.length; j++) {
				sum += w[i][j];
			}
			storage.add(sum);
			sum = "";
		}
		System.out.println("________");

		for (String i : storage) {
			// System.out.println(i);
			if (!components.contains(i))
				components.add(i);
		}

		return components.size();
	}

}
