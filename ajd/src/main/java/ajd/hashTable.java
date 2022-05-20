package ajd;

import java.util.Hashtable;

public class hashTable extends bas{
	
	public static Object[][] driven(String file,String testName){
		read readdata = new read(file);
		String sheetName = "Sheet3";
		
		int strtRow=0;
		
		while(!readdata.getCellData(sheetName, 0, strtRow).equalsIgnoreCase(testName)){
			strtRow++;
		}
		System.out.println(strtRow);
		
		int strtCol = strtRow + 1;
		int strtData = strtRow + 2;
		
		// how many rows present in test
		int rows = 0;
		while(!readdata.getCellData(sheetName, 0, strtData+rows).equals("")){
			rows++;
		}
		System.out.println(rows);
		
		// how many columns presents in test
		int cols = 0;
		while(!readdata.getCellData(sheetName, cols, strtCol).equals("")){
			cols++;
		}
		System.out.println(cols);
		
		Object[][] dataSet = new Object[rows][1];
		Hashtable<String, String> dataTable = null;
		int a =0;
		for(int row=strtData; row<=strtCol+rows;row++){	
			dataTable = new Hashtable<String, String>();
			for(int col=0;col<cols;col++){
				String key = readdata.getCellData(sheetName, col+1, strtCol);
				String value = readdata.getCellData(sheetName, col+1, row);
				System.out.println(key+ " " +value);
				dataTable.put(key, value);
//				dataSet[a][col]=readdata.getCellData(sheetName, col, row);
//				System.out.println(dataSet[a][col]);
			}
			
			dataSet[a][0]=dataTable;
			a++;
		}
		return dataSet;
	}

}
