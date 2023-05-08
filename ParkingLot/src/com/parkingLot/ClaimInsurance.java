package com.parkingLot;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClaimInsurance 
{
	
	public void insurance() 
	{
		Map<String, String> usernameVsVehicleNumberInsuranceMap = new HashMap<>();
		ParkingLot parkingLot = new ParkingLot();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n"+"Insurance can be claimed if your vehicle is lost in the time period of parking it in our parking lot.");
		System.out.print("\n"+"Enter a username to whom insurance is to be claimed: ");
		String usernameToClaimInsurance = scanner.next();
		System.out.print("Enter vehicle number which you parked: ");
		String vehicleNumberToClaimInsurance = scanner.next();
		usernameVsVehicleNumberInsuranceMap.put(usernameToClaimInsurance, vehicleNumberToClaimInsurance);
		if(parkingLot.vehicleNumberVsUserNameMap.containsValue(vehicleNumberToClaimInsurance) && (parkingLot.vehicleNumberVsUserNameMap.get(vehicleNumberToClaimInsurance).equals(usernameToClaimInsurance)))
		{
			int slotNumber = 0;
			for (Map.Entry<Integer, String> getSlot : parkingLot.slotVsVehicleNumberMap.entrySet()) 
			{
			    if (vehicleNumberToClaimInsurance.equals(getSlot.getValue())) 
			    {
				slotNumber = getSlot.getKey();
				break;
			    }
			}
			System.out.println("Your vehicle is safely parked in the slot number "+ slotNumber);
		}

		else if (parkingLot.vehicleNumberVsUserNameMap.containsValue(vehicleNumberToClaimInsurance) && (parkingLot.vehicleNumberVsUserNameMap.get(parkingLot.vehicleNumber)!=usernameToClaimInsurance))
		{
				System.out.println("This vehicle is not parked by you! Try again with you vehicle number.");
			}

		else if (!parkingLot.vehicleNumberVsUserNameMap.containsValue(vehicleNumberToClaimInsurance)) 
		{
			System.out.println("No vehicle with vehicle number "+ vehicleNumberToClaimInsurance + " is parked in the parking lot.");
		}

		else  
		{
			System.out.println("Insurance claimed!");         		
		}
	}
}
