package testing;

import calculations.Matrix;
import calculations.Calcs;

public class Testclass {

	public static void main(String[] args) {
		Matrix m1 = new Matrix();
		Matrix m1_klon = new Matrix();
		Calcs c1 = new Calcs();
		Matrix m2 = new Matrix(c1.calcDistanzmatrix(m1));

		System.out.println("=======");

		System.out.println("Exzentrizitäten");
		c1.calcEccentricity(m2);
		System.out.println("=======\n");
		System.out.println("Wegematrix");

		Integer[][] WegeMatrix = c1.calcWegematrix(m1);

		int components = c1.calcComponents(WegeMatrix);
		System.out.println("Anzahl an Komponenten: " + components);

		c1.calcArticulations(m1_klon, components);
		c1.calcBridges(m1_klon, components);

	}

}
