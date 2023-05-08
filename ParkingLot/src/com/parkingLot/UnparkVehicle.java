package com.parkingLot;

import java.util.Map;

public class UnparkVehicle {
	
    ParkingLot parkingLot = new ParkingLot();
	public void unpark(String vehicleNumber) throws Exception 
	{
		int slot = -1;   	
		for (Map.Entry<Integer, String> entry : parkingLot.slotVsVehicleNumberMap.entrySet()) 
		{
		    if (entry.getValue().equals(vehicleNumber)) 
		    {
		        slot = entry.getKey();
		        break;
		    }
		}
		if (!parkingLot.slotVsVehicleNumberMap.get(slot).equals(vehicleNumber)) 
		{
		    throw new Exception("Vehicle not found in parking lot");
		}
		if (!parkingLot.slots.get(slot)) 
		{
		    throw new Exception("Slot is already empty");
		}	
		parkingLot.slots.put(slot, false);
		System.out.println("Vehicle unparked from slot " +  slot);
		parkingLot.slotVsUsernameMap.remove(slot);
		parkingLot.slotVsVehicleNumberMap.remove(slot);
		parkingLot.vehicleNumberVsUserNameMap.remove(parkingLot.username);
		parkingLot.occupied--;
	}
}
