package calculations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Matrix {
	private final String CSV_FILE_PATH = "/Users/paulherbich/Desktop/Schule/POS/Graphentheorie-Programm/matrix_zusammen.csv";
	private ArrayList<String> MatrixBlock = new ArrayList<String>();
	private String[][] StringMatrix;
	private int matrixSize;
	private Integer[][] Matrix;

	public Matrix() {
		readMatrix();
	}

	public Matrix(Integer[][] m) {
		Matrix = m;
	}

	public void readMatrix() {
		try {
			FileReader filereader = new FileReader(CSV_FILE_PATH);
			CSVReader csvreader = new CSVReader(filereader);
			String[] nextRecord;

			while ((nextRecord = csvreader.readNext()) != null) {
				for (String record : nextRecord) {
					MatrixBlock.add(record);
				}
			}
			csvreader.close();
			matrixSize = MatrixBlock.size();
			StringMatrix = new String[matrixSize][matrixSize];
			Matrix = new Integer[matrixSize][matrixSize];

			for (int i = 0; i < matrixSize; i++) {
				String data = MatrixBlock.get(i);
				String[] split = data.split(";");
				for (int j = 0; j < split.length; j++) {
					StringMatrix[i][j] = split[j];
				}

			}
			typeCastMatrix();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void typeCastMatrix() {

		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				Matrix[i][j] = Integer.parseInt(StringMatrix[i][j]);
			}
		}
	}

	public void outputMatrix() {
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				System.out.print(Matrix[i][j] + " ");
			}
			System.out.println("\n");
		}

	}

	public int getSize() {
		return matrixSize;
	}

	public Integer[][] getMatrix() {
		return Matrix;

	}

}
