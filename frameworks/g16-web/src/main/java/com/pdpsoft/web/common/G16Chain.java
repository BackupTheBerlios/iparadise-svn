package com.pdpsoft.web.common;

import org.apache.commons.chain.impl.ChainBase;

/**
 * Created by IntelliJ IDEA.
 * User: a_amini
 * Date: Jan 16, 2008
 * Time: 4:49:19 PM
 */
public class G16Chain extends ChainBase {

    public G16Chain() {
        this.addCommand(new G16ChainExceptionHandler());
    }
}
