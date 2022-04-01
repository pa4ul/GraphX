package testing;

import calculations.Matrix;
import calculations.Calcs;

public class Testclass {

	public static void main(String[] args) {
		Matrix m1 = new Matrix();
		Calcs c1 = new Calcs();
		Matrix m2 = new Matrix(c1.calcDistanzmatrix(m1));
		
		System.out.println("=======");
		System.out.println("Exzentrizitäten");
		c1.calcEccentricity(m2);
		System.out.println("=======");
		c1.calcRadius();
		c1.calcDiameter();
		c1.calcCenter();
		System.out.println("=======");

	}

}
