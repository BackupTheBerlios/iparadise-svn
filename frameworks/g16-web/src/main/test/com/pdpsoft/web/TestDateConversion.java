package com.pdpsoft.web;

import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jul 4, 2009
 * Time: 10:41:21 AM
 */
public class TestDateConversion extends TestCase {
    private final static Log LOG = LogFactory.getLog(TestDateConversion.class);

    public TestDateConversion(String s) {
        super(s);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    public void testDate() throws Exception {
        final String format = "dd/mm/yyyy";
/*
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setCalendar(new com.pdpsoft.commons.persiancalendar.JalaliCalendar());
        final String s = dateFormat.format(new Date());
        LOG.info(s);
*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setCalendar(new JalaliCalendar());
        final String source = "1388/04/13";
        final Date date = simpleDateFormat.parse(source);
        LOG.info(date);

        final String[] strings = StringUtils.split(source, '/');
        Calendar jalaliCalendar = new JalaliCalendar(Integer.valueOf(strings[0]), Integer.valueOf(strings[1]) - 1, Integer.valueOf(strings[2]));
        LOG.info(jalaliCalendar.getTime());
    }
}
