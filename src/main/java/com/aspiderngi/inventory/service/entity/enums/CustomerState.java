package com.aspiderngi.inventory.service.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum CustomerState {
	
	PREUSE 		(1),
	ACTIVE		(2);
	
	private int id;
	
	private CustomerState(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static CustomerState GetValue(int id){
		return (CustomerState)buildMap().get(id);
	}
	
	public static String GetStringValue(int id){
		return ((CustomerState)buildMap().get(id)).toString();
	}

	public static Map<Integer, CustomerState> buildMap() {
        Map<Integer, CustomerState> map = new HashMap<Integer, CustomerState>();
        for (CustomerState value : CustomerState.values()) {
            map.put(value.getId(), value);
        }

        return map;
    }
}