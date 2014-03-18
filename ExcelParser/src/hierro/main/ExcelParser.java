package hierro.main;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.record.common.UnicodeString.ExtRst;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.xmlbeans.XmlException;

public class ExcelParser {
	private static ExcelExtractor extractor;
	
	
	public static void ExcelParser1(){
			try {
				extractor = (ExcelExtractor) ExtractorFactory.createExtractor(new File("ie_data.xls"));
				
				System.out.println(extractor.getText());
				
				System.out.println(extractor.getMetadataTextExtractor());
				
			} catch (Exception e) {
			}

	}
}
