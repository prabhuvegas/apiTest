package util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class readDataFromExcel {
    @DataProvider(name = "readTestData")
    public Object[][] readTestData(Method m)throws Exception
    {
        Map<String, Object[]> map = new HashMap<String, Object[]>();
        String fileName ="";
        String tempStr  = null;
        String quoteColumns = "";

        FileInputStream ioFileName = new FileInputStream(new File("src\\test\\resources\\adjusterFile.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(ioFileName);

        //Get first/desired sheet from the workbook
        XSSFSheet sh = workbook.getSheet("APITest");

        Iterator<Row> rowIterator = sh.iterator();
        Iterator<Row> tempIterator;
        Row header_row,row;

        header_row = rowIterator.next();

        String[]   testDataKeysArray = new String[header_row.getLastCellNum()];
        int columnCnt = header_row.getLastCellNum()-1;
        //System.out.println("Num of Columns in the testdata :"+ columnCnt);
        for(int c =0;c < columnCnt;c++)
            testDataKeysArray[c] = header_row.getCell(c).getStringCellValue();

        int numOfRows =0;
        int testcaseEndRow =0;
        while(rowIterator.hasNext()){
            row = rowIterator.next();
            if(row.getCell(0).getStringCellValue().compareTo(m.getName())==0)
            {
                numOfRows ++;
                testcaseEndRow=row.getRowNum();
            }
        }
        int testcaseStartRow = testcaseEndRow+1 - numOfRows;
        //System.out.println("Num of Columns in the testdata :"+ columnCnt +" and Num of Rows in the testcase "+ testcase +"  are :"+ numOfRows +"  Start Row : "+ testcaseStartRow +"  and testcase End Row :"+ testcaseEndRow);

        int k = 0;
        for(int c =0;c < columnCnt;c++)
        {
            Object[] temp = new Object[numOfRows];
            int indx = 0;
            tempIterator = sh.iterator();
            for(int i=0;i<testcaseStartRow;i++)
                tempIterator.next();
            for(int r=testcaseStartRow;r<=testcaseEndRow;r++){
                row = tempIterator.next();
                switch(row.getCell(c, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK).getCellType())
                {
                    case 0:
                        Double dbl = row.getCell(c).getNumericCellValue();

                        temp[indx] = dbl.toString();
                        break;
                    case 1:
                        temp[indx] = new String(row.getCell(c).getStringCellValue());
                        break;
                    case 3:
                        temp[indx] = new String("");
                        break;
                    default:
                        temp[indx] = new String("");
                        break;
                }
                indx++;
            }
            map.put(testDataKeysArray[k].toString(), temp);
            temp = null;
            k++;
        }
        testDataKeysArray = null;
        System.out.println("Reading TestData for "+ m.getName() + " completed ");
        return new Object[][] {new Object[] {map}};
    }


}
