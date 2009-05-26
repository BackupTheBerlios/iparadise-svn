package com.pdpsoft.web;

import org.apache.strutsel.taglib.utils.EvalHelper;

import javax.servlet.jsp.JspException;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 16, 2009
 * Time: 9:36:34 PM
 * the code has been moved from KAFA project, but unfortunately
 * there was not any author specified, but i think it has been
 * developed by Mohammad Shams. I've changed some naming conventions
 * and the packaging.
 *
 * @author M. H. Shamsi
 * @version 1.0.0
 * @date Jun 19, 2005
 */
public class ELSolarDateTag extends SolarDateTag {
    private String nameExpr;

    public String getNameExpr() {
        return nameExpr;
    }

    public void setNameExpr(String nameExpr) {
        this.nameExpr = nameExpr;
    }

    public void release() {
        super.release();
        nameExpr = null;
    }

    public int doStartTag() throws JspException {

        System.out.println("*********************************************");
        System.out.println("*********************************************");

        evaluateExpressions();
        return super.doStartTag();
    }

    private void evaluateExpressions() throws JspException {
        String string;
        if ((string = EvalHelper.evalString("name", getNameExpr(), this, pageContext)) != null)
            setName(string);
    }
}