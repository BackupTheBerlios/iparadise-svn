package com.pdpsoft.web.common.commands;

import com.pdpsoft.web.common.G16ChainContext;
import com.pdpsoft.web.common.G16WebCommand;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Command;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: h_shayan & a_heydari
 * Date: Sep 6, 2008
 * Time: 9:12:41 AM
 */
public class MSExcelWebCommand extends G16GenericWebCommand implements G16WebCommand {
    public boolean execute(Context context) throws Exception {
        G16ChainContext g16ChainContext = (G16ChainContext) context;
        Object obj = g16ChainContext.get(PARAMETER);
        final String formPropName = (String) g16ChainContext.get(ACTIONFORM_PROP_NAME);

        if (!(obj instanceof byte[]))
            throw new RuntimeException("MSExcelWebCommand: Input parameter should be byte[] but it is " + obj);
        byte[] fileData = (byte[]) obj;
        List<String> fileValues = new ArrayList<String>();
        try {
            InputStream inputStream = new ByteArrayInputStream(fileData);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            for (Iterator rit = sheet.rowIterator(); rit.hasNext();) {
                HSSFRow row = (HSSFRow) rit.next();
                for (Iterator cit = row.cellIterator(); cit.hasNext();) {
                    HSSFCell cell = (HSSFCell) cit.next();
                    System.out.println("cell.toString() = " + cell.toString());
                    fileValues.add(cell.toString());
                }
            }
            BeanUtils.setProperty(g16ChainContext.getBean(), formPropName, fileValues);
        } catch (IOException e) {
            throw new RuntimeException("MSExcelWebCommand:" + e.getMessage());
        } catch (IllegalAccessException e) {
            throw e;
        } catch (InvocationTargetException e) {
            throw e;
        }
        return Command.CONTINUE_PROCESSING;
    }
}
