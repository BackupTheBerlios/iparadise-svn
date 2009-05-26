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
public class PlayerMapper implements FieldSetMapper {

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.file.mapping.FieldSetMapper#mapLine(org.springframework.batch.item.file.mapping.FieldSet)
	 */
	public Object mapLine(FieldSet fieldSet) {
		if(fieldSet == null)
			return null;
		
		PlayerEntity entity = new PlayerEntity();
		entity.setId(fieldSet.readString(0));
		entity.setLastName(fieldSet.readString(1));
		entity.setFirstName(fieldSet.readString(2));
		entity.setPosition(fieldSet.readString(3));
		entity.setBirthYear(fieldSet.readInt(4));
		entity.setDebutYear(fieldSet.readInt(5));
		
		return entity;
	}

}
