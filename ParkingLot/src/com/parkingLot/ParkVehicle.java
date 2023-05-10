package com.parkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkVehicle {
	ParkingLot parkingLot = ParkingLot.getInstance();
    private int gates = 3;
    private Map<Integer, Boolean> slots = new HashMap<Integer, Boolean>();

    private int getFirstAvailableSlot(int gateNumber, int floorNumber, int vehicleChoice) 
    {
        int slotsPerGate;

        switch (vehicleChoice) 
        {
            case 1:
                slotsPerGate = 166;
                break;
            case 2:
                slotsPerGate = 416;
                break;
            case 3:
                slotsPerGate = 50;
                break;
            case 4:
                slotsPerGate = 20;
                break;
            default:
                return -1;
        }

        int totalSlotsPerFloor = slotsPerGate * gates;
        int startSlot = (gateNumber - 1) * slotsPerGate + 1 + floorNumber * totalSlotsPerFloor;
        int endSlot = gateNumber * slotsPerGate + floorNumber * totalSlotsPerFloor;

        for (int i = startSlot; i <= endSlot; i++) 
        {
            if (!parkingLot.getSlotMap(vehicleChoice).get(i)) 
            {
                return i;
            }
        }
        

        return -1;
    }

    public int park(int gateNumber) throws Exception 
    {
        int vehicleChoice = parkingLot.userVehicleChoice;
        String vehicleNumber = parkingLot.vehicleNumber;

        if (vehicleNumber.isEmpty()) 
        {
            throw new Exception("Vehicle number cannot be empty!");
        }

        for (Map.Entry<Integer, String> entry : parkingLot.getVehicleNumberMap(vehicleChoice).entrySet()) 
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
            slot = getFirstAvailableSlot(gateNumber, floorNumber, vehicleChoice);

            if (slot == -1) 
            {
                gateNumber = (gateNumber == 3) ? 1 : gateNumber + 1;

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

        parkingLot.getSlotMap(vehicleChoice).put(slot, true);
//        System.out.println(parkingLot.getSlotMap(vehicleChoice));

        if (initialGateNumber != gateNumber) 
        {
            System.out.println("Parking lot is full at gate number " + initialGateNumber);
        }

        parkingLot.getSlotUsernameMap(vehicleChoice).put(slot, parkingLot.username);
        parkingLot.getVehicleNumberMap(vehicleChoice).put(slot, vehicleNumber);
        parkingLot.getVehicleUsernameMap(vehicleChoice).put(parkingLot.username, vehicleNumber);
        parkingLot.incrementOccupiedSlots(vehicleChoice);
        

        if(floorNumber>=1 && floorNumber<=12) {
        	System.out.println("Parking vehicle at gate " + gateNumber + " in slot " + slot + " on floor " + floorNumber);
        }
        System.out.println("Parking vehicle at gate " + gateNumber + " in slot " + slot + " on Groundfloor");
        

        return slot;
    }
}
