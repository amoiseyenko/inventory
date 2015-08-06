package com.aspiderngi.inventory.db.utils;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.aspiderngi.common.service.entity.enums.SubscriptionSuspendType;

@SuppressWarnings("rawtypes")
@Converter
public class SubscriptionSuspendTypeConverter implements AttributeConverter<EnumSet, Integer> {

	@SuppressWarnings("unchecked")
	@Override
	public Integer convertToDatabaseColumn(EnumSet attribute) {

		Integer result = 0;
		for(Iterator<SubscriptionSuspendType> it = attribute.iterator(); it.hasNext();){
		
			SubscriptionSuspendType suspendType = it.next();

			result |= suspendType.getId();			
		}

		return result;

	}

	@SuppressWarnings("unchecked")
	@Override
	public EnumSet convertToEntityAttribute(Integer dbData) {

		// http://dhruba.name/2008/12/31/effective-java-item-32-use-enumset-instead-of-bit-fields/
		
		EnumSet result = EnumSet.of(SubscriptionSuspendType.NONE);
		
		for (Map.Entry<Integer, SubscriptionSuspendType> entry : SubscriptionSuspendType.buildMap().entrySet()){
			if((dbData & entry.getKey()) == entry.getKey()){
				result.add(entry.getValue());
			}
		}

		return result;
	}

}