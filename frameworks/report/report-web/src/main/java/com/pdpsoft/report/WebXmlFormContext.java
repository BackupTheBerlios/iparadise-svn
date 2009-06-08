package com.pdpsoft.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.pdpsoft.commons.ResourceLocator;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignReportFont;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jun 6, 2009
 * Time: 3:19:25 PM
 */
public abstract class WebXmlFormContext {
    private final static Log LOG = LogFactory.getLog(WebXmlFormContext.class);

    private static Map<String, JasperReport> jasperReportCache = new ConcurrentHashMap<String, JasperReport>();

    /**
     * this is the method that will handle the export of the form
     * @param formId form identifier as specified in forms.xml
     * @param response the HttpServletResponse
     * @param dtoClass the transfer object that should be map to the jasper xml file.
     */
    public static void defaultStream(final String formId, HttpServletResponse response, Object dtoClass) {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            final XmlFormCustomEntity customEntity = XmlFormContextHolder.getInstance().getXmlFormCustomEntity(formId);
            final Map<String,Object> map = XmlFormAdaptor.transform(customEntity, dtoClass);
            if(LOG.isDebugEnabled())
                debug(map);

            InputStream stream = ResourceLocator.getResourceAsStream(customEntity.getFilePath());

            JasperReport report = null;
            final String id = customEntity.getId();
            if(jasperReportCache.containsKey(id)) {
                report = jasperReportCache.get(id);
            } else {
                report = JasperCompileManager.compileReport(stream);
                jasperReportCache.put(id, report);
            }

            JasperPrint print = JasperFillManager.fillReport(
                    report,
                    map,
                    new JREmptyDataSource()
            );

            JasperExportManager.exportReportToPdfStream(print, outputStream);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=".concat(id));
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * debug mode only
     * @param map
     */
    private static void debug(Map<String, Object> map) {
        final Set<Map.Entry<String,Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            LOG.info("entry.getKey =" + entry.getKey());
            LOG.info("entry.getValue =" + entry.getValue());
        }
    }

    public static void main(String[] args) throws JRException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ORDER_TYPE", "ORDER_TYPE");
        map.put("ORDER_PK", "ORDER_PK");
        map.put("ORDER_DATE", "ORDER_DATE");
        
/*
        final String file = JasperRunManager.runReportToPdfFile(
                "C:\\Java\\pdp-svn2\\billyard\\electricity\\khuzestan\\khuzestan-electricity-web\\src\\main\\resources\\jasperforms\\resideSabteDarkhast\\Untitled_report_1.jasper",
                null
        );
*/
        JasperReport report = JasperCompileManager.compileReport("C:\\Java\\tools\\jasper\\resideSabteDarkhast\\resdeSbteDarkhast.jrxml");
        JasperPrint print = JasperFillManager.fillReport(
                report,
                map,
                new JREmptyDataSource()
        );
        JasperExportManager.exportReportToPdfFile(print, "C:\\Java\\tools\\jasper\\resideSabteDarkhast\\ad.pdf");
    }
}
