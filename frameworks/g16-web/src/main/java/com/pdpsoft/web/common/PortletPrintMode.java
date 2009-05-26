package com.pdpsoft.web.common;

import org.apache.portals.bridges.struts.StrutsPortlet;

import javax.portlet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Chris Shayan
 * Date: Jul 29, 2008
 * Time: 2:33:49 PM
 */
public class PortletPrintMode extends StrutsPortlet {
    private String g16PortletCustumModes;
    private String defaultCustomPage;
    private Map<String, String> mapModePath = new HashMap<String, String>();

    /**
     *
     * @param renderRequest
     * @param renderResponse
     * @throws PortletException
     * @throws java.io.IOException
     */
    protected void doDispatch(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        if(renderRequest.getPortletMode().toString().equals("print")) {
            defaultCustomPage = mapModePath.get("PrintPage");
            doCustom(renderRequest, renderResponse);
        } else {
            super.doDispatch(renderRequest, renderResponse);
        }
    }


    /**
     *
     * @param renderRequest
     * @param renderResponse
     * @throws PortletException
     * @throws java.io.IOException
     */
    public void doCustom(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        processRequest(renderRequest, renderResponse, defaultCustomPage, StrutsPortlet.CUSTOM_REQUEST);
    }

    /**
     *
     * @param portletConfig
     * @return
     */
    protected String getCustomPageParameter(PortletConfig portletConfig) {
        g16PortletCustumModes = portletConfig.getInitParameter("g16-portlet-custom-modes");
        String[] modePages = g16PortletCustumModes.split(",");
        for (String modePage : modePages) {
            mapModePath.put(modePage, portletConfig.getInitParameter(modePage));
        }
        return g16PortletCustumModes;
    }
}
