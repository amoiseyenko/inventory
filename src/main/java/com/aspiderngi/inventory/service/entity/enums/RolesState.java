package com.aspiderngi.inventory.service.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum RolesState {
	
	USER 		(1),
	AGENT		(2);
	
	private int id;
	
	private RolesState(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static RolesState getValue(int id){
		return (RolesState)buildMap().get(id);
	}
	
	public static String getStringValue(int id){
		return ((RolesState)buildMap().get(id)).toString();
	}

	public static Map<Integer, RolesState> buildMap() {
        Map<Integer, RolesState> map = new HashMap<Integer, RolesState>();
        for (RolesState value : RolesState.values()) {
            map.put(value.getId(), value);
        }

        return map;
    }
}