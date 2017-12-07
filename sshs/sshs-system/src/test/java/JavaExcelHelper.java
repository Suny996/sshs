import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

/**
 * excel公式解析工具类
 * 
 * @author Suny
 *
 */
public class JavaExcelHelper {
	/**
	 * 默认长度32
	 */
	private static final int DEFAULT_LENGTH_32 = 32;
	/**
	 * 默认长度36
	 */
	private static final int DEFAULT_LENGTH_36 = 36;

	public static String execute(String exp, String json) {
		if (exp == null) {
			return null;
		}
		Double value = null;
		HSSFWorkbook workbook = null;
		try {
			if (json != null && !json.trim().equals("")) {
				json = json.replaceAll("\\{", "").replaceAll("\\}", "");
				String[] eles = json.split(",");
				for (String e : eles) {
					String[] tmp = e.split(":");
					String name = tmp[0];
					String value1 = "";
					if (tmp[1] != null) {
						value1 = tmp[1].trim();
					}
					exp = exp.replaceAll(name.replace("[", "\\[").replace("]", "\\]").trim(), value1);
				}
			}
			workbook = new HSSFWorkbook();

			// 也可以指定工作表的名字。
			HSSFSheet sheet = workbook.createSheet();

			HSSFRow row = sheet.createRow((short) 0);
			// 单元格
			HSSFCell cell = row.createCell((int) 0);

			cell.setCellFormula(exp);

			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			CellValue cellValue = evaluator.evaluate(cell);
			switch (cellValue.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				value = cellValue.getNumberValue();
				break;
			case Cell.CELL_TYPE_STRING:
				value = Double.valueOf(cellValue.getStringValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			case Cell.CELL_TYPE_ERROR:
				break;
			default:
				break;
			}
			if (value != null) {
				return String.valueOf(value);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					workbook = null;
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 替换字符串，支持正则表达式
	 * 
	 * @param str
	 * @param regex
	 * @param replacement
	 * @return
	 */
	public static String replaceAll(String str, String regex, String replacement) {
		if (str == null) {
			return null;
		}
		if (regex == null) {
			return str;
		}
		if (replacement == null) {
			replacement = "";
		}

		return str.replaceAll(regex, replacement);
	}

	/**
	 * 生成UUID方法
	 * 
	 * @param length
	 * @return
	 */
	public static String getUUID(int length) {
		if (length < DEFAULT_LENGTH_32) {
			length = DEFAULT_LENGTH_32;
		}
		String uuid = java.util.UUID.randomUUID().toString();
		if (length > DEFAULT_LENGTH_36) {
			while (uuid.length() < length) {
				uuid += "0";
			}
		} else if (length < DEFAULT_LENGTH_36) {
			uuid = uuid.replaceAll("-", "");
			while (uuid.length() < length) {
				uuid += "0";
			}
		}
		return uuid;
	}

	public static void main(String[] args) {
		// System.out.println("[adc]".replaceAll("\\[(*)\\]\\d+", "x"));
		// System.out.println(execute("if(a>b) parseInt(a/b) else c ","{a:5,b:3,c:4}"));
		System.out.println(execute("[P20033]*[V0000021]", "{[P20033]:304.43,[V0000021]:0.004  }"));
		// String teststr = "UAPPROJECT_ID=', \r\n select |";
		// System.out.println(teststr.replaceAll(",[^0-9a-zA-Z]*(${START_DATE})",""));
		// System.out.println(getUUID(32));
	}
}