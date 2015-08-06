package com.aspiderngi.inventory.service.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum SimSwapState {

	INIT			(1),
	COMPLETED		(2),
	CANCELED		(3),
	ERROR			(4);
	
	private int id;
	
	private SimSwapState(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static SimResourceState GetValue(int id){
		return (SimResourceState)buildMap().get(id);
	}
	
	public static String GetStringValue(int id){
		return ((SimResourceState)buildMap().get(id)).toString();
	}

	public static Map<Integer, SimResourceState> buildMap() {
        Map<Integer, SimResourceState> map = new HashMap<Integer, SimResourceState>();
        for (SimResourceState value : SimResourceState.values()) {
            map.put(value.getId(), value);
        }

        return map;
    }
}