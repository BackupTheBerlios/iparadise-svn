package com.pdpsoft.web.common;

import org.apache.commons.chain.web.ChainListener;

/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Feb 28, 2009
 * Time: 5:57:31 PM
 * 
 * In web.xml following code should include.
 * 
 * <context-param>
 * 		<param-name>org.apache.commons.chain.CONFIG_CLASS_RESOURCE</param-name>
 * 		<param-value>resources/catalog.xml</param-value>
 * </context-param>
 * <listener>
 *    <listener-class>org.apache.commons.chain.web.ChainListener</listener-class>
 * </listener>
 * 
 * The mentioned XML file can be in following format:
 * 
 *	<catalog>
 *	  <chain name="LocaleChange">
 *	    <command 
 *	      className="org.apache.commons.chain.mailreader.commands.ProfileCheck"/>
 *	    <command 
 *	      className="org.apache.commons.chain.mailreader.commands.LocaleChange"/>
 *	  </chain>
 *	  <command 
 *	    name="LogonUser" 
 *	    className="org.apache.commons.chain.mailreader.commands.LogonUser"/>
 *	</catalog>
 */
public class GenericChainListener extends ChainListener {

}
