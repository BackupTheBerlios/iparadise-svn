package com.pdpsoft.web.common;

import com.pdpsoft.commons.G16JaliliDateValueObject;
import com.pdpsoft.commons.G16ValueObject;
import org.apache.commons.chain.impl.ContextBase;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: a_amini, h_shayan
 * Date: Jan 16, 2008
 * Time: 4:51:43 PM
 */
public class G16ChainContext extends ContextBase {

    private G16ValueObject g16ValueObject;
    private WebBeanContext webBeanContext;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private ActionMapping actionMapping;

    //change name of this from gregorianDates to gregorianDates as version 0.5.3
    private Date[] gregorianDates;
    /*
    As version 0.5.3 jaliliDateEntities replaced by four old attributes jaliliDay, jalilMonth, jaliliYear, and jaliliDates
     */
    /*
    use g16JaliliDateValueObjects instead of it, from version 0.5.4
     */
    @Deprecated
    private JaliliDateEntity[] jaliliDateEntities;

    private G16JaliliDateValueObject[] g16JaliliDateValueObjects;


    public G16ChainContext(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public Date[] getGregorianDates() {
        return gregorianDates;
    }

    public void setGregorianDates(Date[] gregorianDates) {
        this.gregorianDates = gregorianDates;
    }

    @Deprecated
    public JaliliDateEntity[] getJaliliDateEntities() {
        if (g16JaliliDateValueObjects != null) {
            jaliliDateEntities = new JaliliDateEntity[g16JaliliDateValueObjects.length];
            for (int counter = 0; counter < g16JaliliDateValueObjects.length; counter++) {
                jaliliDateEntities[counter] = new JaliliDateEntity(g16JaliliDateValueObjects[counter].getDay(),
                        g16JaliliDateValueObjects[counter].getMonth(),
                        g16JaliliDateValueObjects[counter].getYear());
            }
        }
        return jaliliDateEntities;
    }

    @Deprecated
    public void setJaliliDateEntities(JaliliDateEntity[] jaliliDateEntities) {
        if (jaliliDateEntities != null) {
            g16JaliliDateValueObjects = new G16JaliliDateValueObject[jaliliDateEntities.length];
            for (int counter = 0; counter < jaliliDateEntities.length; counter++) {
                g16JaliliDateValueObjects[counter] = new G16JaliliDateValueObject(jaliliDateEntities[counter].getDay(),
                        jaliliDateEntities[counter].getMonth(),
                        jaliliDateEntities[counter].getYear());
            }
        }
        this.jaliliDateEntities = jaliliDateEntities;
    }

    public G16JaliliDateValueObject[] getG16JaliliDateValueObjects() {
        return g16JaliliDateValueObjects;
    }

    public void setG16JaliliDateValueObjects(G16JaliliDateValueObject[] g16JaliliDateValueObjects) {
        this.g16JaliliDateValueObjects = g16JaliliDateValueObjects;
    }

    public WebBeanContext getWebBeanContext() {
        return webBeanContext;
    }

    public void setWebBeanContext(WebBeanContext webBeanContext) {
        this.webBeanContext = webBeanContext;
    }

    public G16ValueObject getG16ValueObject() {
        return g16ValueObject;
    }

    public void setG16ValueObject(G16ValueObject valueObject) {
        g16ValueObject = valueObject;
    }

    public Object getBean() {
        if (getWebBeanContext() == null && getG16ValueObject() == null)
            throw new RuntimeException("There's not any WebBeanContext or G16ValueObject");
        if (getG16ValueObject() != null)
            return getG16ValueObject();
        if (getWebBeanContext() != null)
            return getWebBeanContext();
        return null;
    }

    public ActionMapping getActionMapping() {
        return actionMapping;
    }

    public void setActionMapping(ActionMapping actionMapping) {
        this.actionMapping = actionMapping;
    }
}
