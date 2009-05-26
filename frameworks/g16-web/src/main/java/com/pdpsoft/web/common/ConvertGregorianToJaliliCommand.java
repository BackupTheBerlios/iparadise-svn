package com.pdpsoft.web.common;

import com.pdpsoft.commons.G16JaliliDateValueObject;
import com.pdpsoft.commons.persiancalendar.DateConvertor;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * Created by IntelliJ IDEA.
 * User: a_amini & s_sadjedy
 * Date: Apr 8, 2008
 * Time: 11:30:17 AM
 */
public class ConvertGregorianToJaliliCommand implements G16WebCommand {
    public boolean execute(Context context) throws Exception {

        G16ChainContext g16ChainContext = (G16ChainContext) context;

        DateConvertor dateConvertor = new DateConvertor();

        if (g16ChainContext.getGregorianDates() != null) {
            g16ChainContext.setG16JaliliDateValueObjects(new G16JaliliDateValueObject[g16ChainContext.getGregorianDates().length]);
//            g16ChainContext.setJaliliDateEntities(new JaliliDateEntity[g16ChainContext.getGregorianDates().length]);
            for (int counter = 0; counter < g16ChainContext.getGregorianDates().length; counter++) {
                g16ChainContext.getG16JaliliDateValueObjects()[counter] =
                        new G16JaliliDateValueObject(dateConvertor.convertDate(g16ChainContext.getGregorianDates()[counter]));
/*
                g16ChainContext.getJaliliDateEntities()[counter] =
                        new JaliliDateEntity(dateConvertor.convertDate(g16ChainContext.getGregorianDates()[counter]));
*/
            }
        }

        return Command.CONTINUE_PROCESSING;

    }
}
