package info.xiaomo.core.untils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author : xiaomo
 */
public class ExcelUtil {
//
//    public static void main(String[] args) throws Exception {
//        List<List<String>> listData = getListData("E:\\thinkpage_cities.xls", 1);
//        List<CountryModel> list = new ArrayList<>();
//        for (List<String> listDatum : listData) {
//            CountryModel model = new CountryModel();
//            model.setCityName(listDatum.get(0));
//            model.setCityNameEN(listDatum.get(1));
//            list.add(model);
//        }
//        System.out.println(list);
//    }

    public static List<List<String>> getListData(String path, int ignoreRows) throws IOException {
        File file = new File(path);
        String[][] result = getData(file, ignoreRows);
        List<List<String>> lists = new ArrayList<>();
        for (String[] aResult : result) {
            List<String> list = new ArrayList<>();
            for (String anAResult : aResult) {
                if (!"".equals(anAResult)) {
                    list.add(anAResult);
                }
            }
            lists.add(list);
        }
        return lists;
    }

    /**
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     *
     * @param file       读取数据的源Excel
     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
     * @return 读出的Excel中数据的内容
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static String[][] getData(File file, int ignoreRows)
            throws IOException {
        List<String[]> result = new ArrayList<>();
        int rowSize = 0;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                file));
        // 打开HSSFWorkbook
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，不取
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    if (date != null) {
                                        value = new SimpleDateFormat("yyyy-MM-dd")
                                                .format(date);
                                    } else {
                                        value = "";
                                    }
                                } else {
                                    value = new DecimalFormat("0").format(cell
                                            .getNumericCellValue());
                                }
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA:
                                // 导入时如果为公式生成的数据则无值
                                if (!"".equals(cell.getStringCellValue())) {
                                    value = cell.getStringCellValue();
                                } else {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                value = "";
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                value = (cell.getBooleanCellValue() ? "Y"
                                        : "N");
                                break;
                            default:
                                value = "";
                        }
                    }
                    if (columnIndex == 0 && "".equals(value.trim())) {
                        break;
                    }
                    values[columnIndex] = rightTrim(value);
                    hasValue = true;
                }

                if (hasValue) {
                    result.add(values);
                }
            }
        }
        in.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = result.get(i);
        }
        return returnArray;
    }

    /**
     * 去掉字符串右边的空格
     *
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }
}