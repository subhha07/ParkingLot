package com.parkingLot;

import java.util.Map;

public class ParkVehicle {
	ParkingLot parkingLot = new ParkingLot();
	private int gates = 3;
	
	   
	    private int getFirstAvailableSlot(int gateNumber, int floorNumber) 
	    {
	        int slotsPerGate = 166;
	        int totalSlotsPerFloor = slotsPerGate * gates;
	        int startSlot = (gateNumber - 1) * slotsPerGate + 1 + floorNumber * totalSlotsPerFloor;
	        int endSlot = gateNumber * slotsPerGate + floorNumber * totalSlotsPerFloor;

	        for (int i = startSlot; i <= endSlot; i++) 
	        {
	            if (!parkingLot.slots.get(i)) 
	            {
	                return i;
	            }
	        }
	        return -1;
	    }




    public int park(int gateNumber) throws Exception
    {
    	int selectedGateNumber = gateNumber;
        if (parkingLot.vehicleNumber.isEmpty()) 
        {
            throw new Exception("Vehicle number cannot be empty!");
        }
        for (Map.Entry<Integer, String> entry : parkingLot.slotVsVehicleNumberMap.entrySet()) 
        {
            if (entry.getValue().equals(parkingLot.vehicleNumber))
            {
                throw new Exception("Vehicle number " + parkingLot.vehicleNumber + " is already parked at slot " + entry.getKey());
            }
        }

        int floorNumber = 0;
        int slot = -1;

        int initialGateNumber = gateNumber;
        while (floorNumber <= 12 && slot == -1) 
        {
            slot = getFirstAvailableSlot(gateNumber, floorNumber);

            if (slot == -1) {
                if (gateNumber == 3) 
                {
                    gateNumber = 1;
                } 
                else 
                {
                    gateNumber++;
                }

                if (gateNumber == initialGateNumber) 
                {
                    floorNumber++;
                }
            }
        }

        if (slot == -1) 
        {
            throw new Exception("Sorry, parking lot is full");
        }

        parkingLot.slots.put(slot, true);
        if(selectedGateNumber!=gateNumber) 
        {
        	System.out.println("Parking lot is full at gate number " + selectedGateNumber );
        }
        System.out.println("Parking vehicle at gate " + gateNumber + " in slot " + slot + " on floor " + floorNumber);
        parkingLot.slotVsUsernameMap.put(slot, parkingLot.username);
        parkingLot.slotVsVehicleNumberMap.put(slot, parkingLot.vehicleNumber);
        parkingLot.vehicleNumberVsUserNameMap.put(parkingLot.username, parkingLot.vehicleNumber);
        parkingLot.occupied++;
        return slot;
    }
}
