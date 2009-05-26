/**
 * 
 */
package com.pdpsoft.sample;

import org.springframework.batch.item.file.mapping.FieldSet;
import org.springframework.batch.item.file.mapping.FieldSetMapper;

/**
 * @author shayan
 *
 */
public class BankFieldSetMapper implements FieldSetMapper {

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.file.mapping.FieldSetMapper#mapLine(org.springframework.batch.item.file.mapping.FieldSet)
	 */
	public Object mapLine(FieldSet fieldSet) {
		if(fieldSet == null)
			return null;
		
		BankEntity entity = new BankEntity();
		entity.setBranchCode(fieldSet.readString("BranchCode"));
		entity.setChannelType(fieldSet.readString("ChannelType"));
		entity.setPaymentDate(fieldSet.readString("PaymentDate"));
		entity.setBillId(fieldSet.readString("BillId"));
		entity.setPaymentId(fieldSet.readString("PaymentId"));
		entity.setReferenceCode(fieldSet.readString("ReferenceCode"));
		
		return entity;
	}

}
