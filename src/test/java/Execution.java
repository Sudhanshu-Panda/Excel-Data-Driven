import java.io.IOException;
import java.util.ArrayList;

public class Execution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		DataDrivenFromExcel ddfx = new DataDrivenFromExcel();
		ArrayList<String> values = ddfx.getDataFromExcel("OW execution","satellite");
		
		System.out.println(values);
	}

}
