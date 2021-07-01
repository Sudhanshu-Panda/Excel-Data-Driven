import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenFromExcel {

	
	public ArrayList<String> getDataFromExcel(String SheetName, String SPname) throws IOException
	{
		FileInputStream filePath = new FileInputStream("C:\\Users\\sudha\\Desktop\\inputCodes.xlsx");
		XSSFWorkbook excelWorkBook = new XSSFWorkbook(filePath);
		ArrayList<String> valuesAre = new ArrayList<String>();
		
		int noOfSheets = excelWorkBook.getNumberOfSheets();
		
		for(int i=0;i<noOfSheets;i++)
		{
			if(excelWorkBook.getSheetName(i).equalsIgnoreCase(SheetName))
			{
				//get control of the sheet
				XSSFSheet sheetControl = excelWorkBook.getSheetAt(i);
				
				//Read the first column
				Iterator<Row> rows = sheetControl.iterator();  //it will iterate through rows
				
				Row firstRow = rows.next(); // access is in first row
				
				Iterator<Cell> cells = firstRow.cellIterator();  // it will iterate through cells of each row
				
				int k= 0 ; // Flag the get column index for the desired row
				int column = 0; // to save the index of the desired coulmn value
				while(cells.hasNext())
				{
					Cell columnValue = cells.next();
					if(columnValue.getStringCellValue().equalsIgnoreCase("TestData"))
					{
						column=k;
					}
					k++;
				}
				
				//Once column is identified scan each row to go to particular row where data is there
				while(rows.hasNext())
				{
					Row desiredRow= rows.next();
					if(desiredRow.getCell(column).getStringCellValue().equalsIgnoreCase(SPname))  //TO check the row name is :Telstra: for that particular colum
					{
						Iterator<Cell> getValuesFromRow = desiredRow.cellIterator();
						while(getValuesFromRow.hasNext())
						{
							Cell cellValue = getValuesFromRow.next();
							
							if(cellValue.getCellType()==CellType.STRING)
							{
								valuesAre.add(cellValue.getStringCellValue());
							}
							else
							{
								// NumberToTextConverter.toText();  - It will convert number to string
								// NumberToTextConverter.toText(cellValue.getNumericCellValue());  - It will give string value
								valuesAre.add(NumberToTextConverter.toText(cellValue.getNumericCellValue()));
							}
						}
							
						}
					}
					
				}
			
			}
		return valuesAre;
		}
		
		
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
