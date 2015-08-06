package com.aspiderngi.inventory.service.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum SubscriptionState {
	
	PREUSE 		(1),
	ACTIVE		(2),
	TERMINATED	(3),  
	DELETED		(4); 
	
	private int id;
	
	private SubscriptionState(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static SubscriptionState getValue(int id){
		return (SubscriptionState)buildMap().get(id);
	}
	
	public static String getStringValue(int id){
		return ((SubscriptionState)buildMap().get(id)).toString();
	}

	public static Map<Integer, SubscriptionState> buildMap() {
        Map<Integer, SubscriptionState> map = new HashMap<Integer, SubscriptionState>();
        for (SubscriptionState value : SubscriptionState.values()) {
            map.put(value.getId(), value);
        }

        return map;
    }
}