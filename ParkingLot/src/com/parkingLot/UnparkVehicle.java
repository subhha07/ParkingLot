package com.parkingLot;

import java.util.Map;

public class UnparkVehicle {
	
	ParkingLot parkingLot = ParkingLot.getInstance();
	public void unpark(String vehicleNumber) throws Exception 
	{
		int vehicleChoice = parkingLot.userVehicleChoice;
		int slot = -1;   	
		for (Map.Entry<Integer, String> entry : parkingLot.getVehicleNumberMap(vehicleChoice).entrySet()) 
		{
		    if (entry.getValue().equals(vehicleNumber)) 
		    {
		        slot = entry.getKey();
		        break;
		    }
		}
		if (!parkingLot.getVehicleNumberMap(vehicleChoice).get(slot).equals(vehicleNumber)) 
		{
		    throw new Exception("Vehicle not found in parking lot");
		}
		if (!parkingLot.getSlotMap(vehicleChoice).get(slot)) 
		{
		    throw new Exception("Slot is already empty");
		}	
		parkingLot.getSlotMap(vehicleChoice).put(slot, false);
		System.out.println("Vehicle unparked from slot " +  slot);
		parkingLot.getSlotUsernameMap(vehicleChoice).remove(slot);
		parkingLot.getVehicleNumberMap(vehicleChoice).remove(slot);
		parkingLot.getVehicleUsernameMap(vehicleChoice).remove(parkingLot.username);
		parkingLot.decrementOccupiedSlots(vehicleChoice);
	}
}
