package com.parkingLot;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ParkingLot {
	//hashmap's for CAR
	public static Map<Integer, Boolean> carSlots = new HashMap<Integer, Boolean>();
	public static Map<Integer, String> carSlotVsUsernameMap = new HashMap<>();
	public static Map<Integer, String> carSlotVsVehicleNumberMap = new HashMap<Integer, String>();
	public static Map<String, String> carVehicleNumberVsUserNameMap = new HashMap<>();
	
	//hashmap's for BIKE
	public static Map<Integer, Boolean> bikeSlots = new HashMap<Integer, Boolean>();
	public static Map<Integer, String> bikeSlotVsUsernameMap = new HashMap<>();
	public static Map<Integer, String> bikeSlotVsVehicleNumberMap = new HashMap<Integer, String>();
	public static Map<String, String> bikeVehicleNumberVsUserNameMap = new HashMap<>();
	
	//hashmap's for MINIBUS
	public static Map<Integer, Boolean> minibusSlots = new HashMap<Integer, Boolean>();
	public static Map<Integer, String> minibusSlotVsUsernameMap = new HashMap<>();
	public static Map<Integer, String> minibusSlotVsVehicleNumberMap = new HashMap<Integer, String>();
	public static Map<String, String> minibusVehicleNumberVsUserNameMap = new HashMap<>();
	
	//hashmap's for CARGO
	public static Map<Integer, Boolean> cargoSlots = new HashMap<Integer, Boolean>();
	public static Map<Integer, String> cargoSlotVsUsernameMap = new HashMap<>();
	public static Map<Integer, String> cargoSlotVsVehicleNumberMap = new HashMap<Integer, String>();
	public static Map<String, String> cargoVehicleNumberVsUserNameMap = new HashMap<>();
	
    private static int slot;
    private int gateNumber;
    
    protected String adminPassword = "password";
    public int adminVehicleChoice;
    
    public static int userVehicleChoice;
    static String username;
    static String vehicleNumber;
    
    private boolean isAdmin = false;
    private boolean isUser = false; 
    
    public static int carOccupiedSlots;
    public static int bikeOccupiedSlots;
    public static int minibusOccupiedSlots;
    public static int cargoOccupiedSlots;
    
    private int floors = 12;
    
    // 12 floors + ground floor
    private int carCapacity = 13 * 498; 
    private int bikeCapacity = 13 * 1248;
    private int minibusCapacity = 13 * 150;
    private int cargoCapacity = 13 * 60;
    
    Scanner scanner = new Scanner(System.in);

    public ParkingLot() 
    {
        for (int i = 1; i <= carCapacity; i++)
        {
            carSlots.put(i, false);
        }
        for (int i = 1; i <= bikeCapacity; i++)
        {
            bikeSlots.put(i, false);
        }
        for (int i = 1; i <= minibusCapacity; i++)
        {
            minibusSlots.put(i, false);
        }
        for (int i = 1; i <= cargoCapacity; i++)
        {
            cargoSlots.put(i, false);
        }
        
    }
    
    private static ParkingLot instance;
    public static synchronized ParkingLot getInstance() 
    {
        if (instance == null) 
        {
            instance = new ParkingLot();
        }
        return instance;
    }

    private boolean isCarVehicleNumberPresent(String vehicleNumber) 
    {
        return (carVehicleNumberVsUserNameMap.containsValue(vehicleNumber));
    }
    private boolean isBikeVehicleNumberPresent(String vehicleNumber) 
    {
        return (bikeVehicleNumberVsUserNameMap.containsValue(vehicleNumber));
    }
    private boolean isMinibusVehicleNumberPresent(String vehicleNumber) 
    {
        return (minibusVehicleNumberVsUserNameMap.containsValue(vehicleNumber));
    }
    private boolean isCargoVehicleNumberPresent(String vehicleNumber) 
    {
        return (cargoVehicleNumberVsUserNameMap.containsValue(vehicleNumber));
    }
    
    public int getVehicleCapacity(int vehicleChoice)
    {
        switch (vehicleChoice)
        {
            case 1:
                return carCapacity;
            case 2:
                return bikeCapacity;
            case 3:
                return minibusCapacity;
            case 4:
                return cargoCapacity;
            default:
                return -1;
        }
    }
    
    public Boolean getVehicleNumberPresent(int vehicleChoice) 
    {
        switch (vehicleChoice)
        {
            case 1:
                return isCarVehicleNumberPresent(vehicleNumber);
            case 2:
                return isBikeVehicleNumberPresent(vehicleNumber);
            case 3:
                return isMinibusVehicleNumberPresent(vehicleNumber);
            case 4:
                return isCargoVehicleNumberPresent(vehicleNumber);
            default:
                return null;
        }
    }
    
    public Map<Integer, Boolean> getSlotMap(int vehicleChoice)
    {
        switch (vehicleChoice)
        {
            case 1:
                return carSlots;
            case 2:
                return bikeSlots;
            case 3:
                return minibusSlots;
            case 4:
                return cargoSlots;
            default:
                return null;
        }
    }

    public Map<Integer, String> getVehicleNumberMap(int vehicleChoice) {
        switch (vehicleChoice) {
            case 1:
                return carSlotVsVehicleNumberMap;
            case 2:
                return bikeSlotVsVehicleNumberMap;
            case 3:
                return minibusSlotVsVehicleNumberMap;
            case 4:
                return cargoSlotVsVehicleNumberMap;
            default:
                return null;
        }
    }

    public Map<Integer, String> getSlotUsernameMap(int vehicleChoice) 
    {
        switch (vehicleChoice)
        {
            case 1:
                return carSlotVsUsernameMap;
            case 2:
                return bikeSlotVsUsernameMap;
            case 3:
                return minibusSlotVsUsernameMap;
            case 4:
                return cargoSlotVsUsernameMap;
            default:
                return null;
        }
    }

    public Map<String, String> getVehicleUsernameMap(int vehicleChoice) 
    {
        switch (vehicleChoice) 
        {
            case 1:
                return carVehicleNumberVsUserNameMap;
            case 2:
                return bikeVehicleNumberVsUserNameMap;
            case 3:
                return minibusVehicleNumberVsUserNameMap;
            case 4:
                return cargoVehicleNumberVsUserNameMap;
            default:
                return null;
        }
    }

    public void incrementOccupiedSlots(int vehicleChoice)
    {
        switch (vehicleChoice) 
        {
            case 1:
            	System.out.println("\n"+"Parking Details:"+"\n"+"Vehicle Parked in Builing - 1");
                carOccupiedSlots++;
                break;
            case 2:
            	System.out.println("\n"+"Parking Details:"+"\n"+"Vehicle Parked in Builing - 2");
                bikeOccupiedSlots++;
                break;
            case 3:
            	System.out.println("\n"+"Parking Details:"+"\n"+"Vehicle Parked in Builing - 3");
                minibusOccupiedSlots++;
                break;
            case 4:
            	System.out.println("\n"+"Parking Details:"+"\n"+"Vehicle Parked in Builing - 4");
                cargoOccupiedSlots++;
                break;
            default:
                break;
        }
    }
    
    public void decrementOccupiedSlots(int vehicleChoice)
    {
        switch (vehicleChoice) 
        {
            case 1:
                carOccupiedSlots--;
                break;
            case 2:
                bikeOccupiedSlots--;
                break;
            case 3:
                minibusOccupiedSlots--;
                break;
            case 4:
                cargoOccupiedSlots--;
                break;
            default:
                break;
        }
    }
    
    public int getOccupiedSlotsCount(int vehicleChoice)
    {
    	switch (vehicleChoice) 
    	{
			case 1:
				return carOccupiedSlots;
			case 2:
                 return bikeOccupiedSlots;
            case 3:
            	return minibusOccupiedSlots;
            case 4:
            	return cargoOccupiedSlots;
			default:
				throw new IllegalArgumentException("Unexpected value: " + vehicleChoice);
		}
    }
    
    public void run()
    {   
        while (true)
        {
            System.out.println("\n"+"Enter 1 for admin login, 2 for user login, 3 for insurance or 4 to exit");
            int choice = 0;
            try 
            {
                choice = scanner.nextInt();
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Invalid input! Please enter a valid number!");
                scanner.nextLine();
                continue; 
            }
            scanner.nextLine();
            if (choice == 1) 
            {
            	int adminChoice = 0;
                System.out.print("Enter password:");
                String password = scanner.nextLine();
                if (password.equals(adminPassword)) 
                {
                    isAdmin = true;
                    System.out.println("Admin login successful!");
                    while (isAdmin) 
                    {
                    	try
                    	{
                    		int vehicleFloorCapacity = 0;
                    		System.out.println("\n"+"Choose which vehicle type you are parking: "+"\n"+"1. Car"+"\n"+"2. Bike"+"\n"+"3. Minibus"+"\n"+"4. Cargo"+"\n"+"5. Exit");
                    		adminVehicleChoice = scanner.nextInt();
                    		scanner.nextLine();
                    		if(adminVehicleChoice>=1 && adminVehicleChoice<=4) 
                    		{
		                    	System.out.print("\n"+"Enter 1 to view parking lot status, 2 to view user details or 3 to exit: ");
		    	                try 
		    	                {
		    	                	adminChoice = scanner.nextInt();
		    	                } 
		    	                catch (InputMismatchException e) 
		    	                {
		    	                    System.out.println("Invalid input! Please enter a valid number!");
		    	                    scanner.nextLine();
		    	                    continue; 
		    	                }
		    	                scanner.nextLine();
		    	                if (adminChoice == 1)
		    	                {
		    	                	System.out.println("\n"+"Total slots: " + getVehicleCapacity(adminVehicleChoice));
		    	                    System.out.println("Available slots: " + (getVehicleCapacity(adminVehicleChoice)-getOccupiedSlotsCount(adminVehicleChoice)));
		    	                    System.out.println("Occupied slots: " + getOccupiedSlotsCount(adminVehicleChoice));
		    	                    if(adminVehicleChoice==1) 
		    	                    {
		    	                    	vehicleFloorCapacity = 498;
	    	                		}
	    	                		else if (adminVehicleChoice==2) 
	    	                		{
	    	                			vehicleFloorCapacity = 1248;
									}
    	                			else if (adminVehicleChoice==3) 
    	                			{
										vehicleFloorCapacity = 150;
									}
    	               			 	else if (adminVehicleChoice==4) 
    	               			 	{
										vehicleFloorCapacity = 60;
									}
		    	                    for (int i = 0; i <=floors; i++) 
		    	                    {
		    	                        int floorCapacity = vehicleFloorCapacity;
		    	                        int floorStartingSlot = (i * vehicleFloorCapacity) + 1;
		    	                        int floorEndingSlot = floorStartingSlot + floorCapacity - 1;
//		    	                        System.out.println("s -- "+floorStartingSlot);
//		    	                        System.out.println("e -- "+floorEndingSlot);

		    	                        int floorOccupied = 0;
		    	                        for (int j = floorStartingSlot; j <= floorEndingSlot; j++) {
		    	                            Boolean isSlotOccupied = getSlotMap(adminVehicleChoice).get(j);
		    	                            if (isSlotOccupied != null && isSlotOccupied.booleanValue()) {
		    	                                floorOccupied++;
		    	                            }
		    	                        }

		    	                        System.out.println("\nFloor " + (i) + ":");
		    	                        System.out.println("Total slots: " + floorCapacity);
		    	                        System.out.println("Available slots: " + (floorCapacity - floorOccupied));
		    	                        System.out.println("Occupied slots: " + floorOccupied);
		    	                    }
		    	                }
		    	                else if (adminChoice == 2) 
		    	                {
		    	                    System.out.print("Enter slot number:");
		    	                     
		    	                    int slot = scanner.nextInt();
		    	                    scanner.nextLine();
		    	                    
		    	                    if (slot < 1 || slot > getVehicleCapacity(adminVehicleChoice)) 
		    	                    {
		    	                    	System.out.println("Invalid slot number!");
		    	                    } 
		    	                    else 
		    	                    {
//		    	                    	System.out.println(getSlotMap(adminVehicleChoice).get(slot));
		    	                    	boolean isOccupied = getSlotMap(adminVehicleChoice).get(slot);
		    	                    	
		    	                    	if (isOccupied) 
		    	                    	{
		    	                    		System.out.println("Username: " + getSlotUsernameMap(adminVehicleChoice).get(slot));
		    	                    		System.out.println("Vehicle Number: " + getVehicleNumberMap(adminVehicleChoice).get(slot));
		    	                    	} 
		    	                    	else 
		    	                    	{
		    	                    		System.out.println("Slot is empty!");
		    	                    	}
		    	                    }
			                    }
		    	                else if (adminChoice == 3) 
		    	                {
			                    	isAdmin = false;
			                    	System.out.println("Logged out!");
			                    } 
		    	                else 
		    	                {
		    	                	System.out.println("Invalid choice!");
		    	                }
		    	            }
                    		else if(adminVehicleChoice==5)
                    		{
                    			System.out.println("Exiting program...");
   		        			 	break;
   		                 	}
                    		else 
                    		{
                   			 System.out.println("Invalid choice! Please enter a number between 1 and 5.");
           		        	 continue;
                    		}
                    	}
                    	catch (InputMismatchException e)
                    	{
                    		System.out.println("Invalid input! Please enter a valid number!");
                    		scanner.nextLine();
	   		                continue;
						}
		            }
                }
                else 
                {
                    System.out.println("Invalid password.  Try Again!");
                }
            }
            else if (choice == 2) 
            {
                System.out.println("\nChoose which vehicle type you are parking:\n1. Car\n2. Bike\n3. Minibus\n4.Cargo");
                try {
                    userVehicleChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (userVehicleChoice < 1 || userVehicleChoice > 4) 
                    {
                        System.out.println("Invalid choice! Please enter a number between 1 and 4.");
                        continue;
                    }

//                    System.out.println(userVehicleChoice);
                    System.out.print("\nEnter username:");
                    username = scanner.nextLine();

                    if (username.isEmpty()) {
                        System.out.println("Username cannot be empty! Try again with a valid username.");
                        continue;
                    }

                    System.out.print("Enter vehicle number:");
                    vehicleNumber = scanner.nextLine();

                    if (vehicleNumber.isEmpty()) {
                        System.out.println("Vehicle number cannot be empty! Try again with a valid vehicle number.");
                        continue;
                    }

                    if (!getVehicleNumberPresent(userVehicleChoice)) 
                    {
                        System.out.println("User login successful!");
                        getVehicleUsernameMap(userVehicleChoice).put(vehicleNumber, username);
                        isUser = true;
                        while (isUser)
                        {
                        	System.out.print("\n"+"Enter 1 to park or 2 to exit: ");
                        	int userchoice = 0;
                        	try
                        	{
                        		userchoice = scanner.nextInt();
                        		scanner.nextLine();
                        		if(userchoice==1)
                        		{
                        			try
                        			{
       		    	    		     	System.out.print("\n"+"Enter the gate number where you want to park (1-3): ");
       		    	    		     	gateNumber = scanner.nextInt();
       		    	    		     	scanner.nextLine();
       		    	    		     	if (gateNumber < 1 || gateNumber > 3) 
       		    	    		     	{
       		    	    		     		System.out.println("Invalid gate number! Please enter a number between 1 and 3.");
       		    	    		     		continue;
       		    	    		     	}
       		    	    		     	ParkVehicle parkVehicle = new ParkVehicle();
       		    	    		     	slot = parkVehicle.park(gateNumber);
       		    	    		     	isUser = false;
                        			}
                        			catch (Exception e)
       	    		        	 	{
                        				System.out.println(e.getMessage());
       	    		        	 	}
       		        		 	}
                        		else if(userchoice==2)
                        		{
                        			System.out.println("Exiting program...");
       		        			 	break;
       		                 	}
       		        	  }
                          catch (InputMismatchException e) 
       		        	  {
       		        		 System.out.println("Invalid input! Please enter a valid number!");
       		                 scanner.nextLine();
       		                 continue;
       		        	 }   		        	 
       		        }
   		         }
   	    		 else if (getVehicleUsernameMap(userVehicleChoice).containsValue(vehicleNumber) && (getVehicleUsernameMap(userVehicleChoice).get(vehicleNumber).equals(username)))
   	    		 {
   	    			 System.out.println("User login successful!");
   	    			 isUser = true;
       		         while (isUser) 
       		         {
       		        	 System.out.print("\n"+"Enter 1 to unpark or 2 to exit: ");
       		        	 int userchoice = 0;
       		        	 try 
       		        	 {
       		        		 userchoice = scanner.nextInt();
       		        		 scanner.nextLine();
       		        		 if(userchoice==1)
       		        		 {
       		        			 try
       			                	{
       		        				 	UnparkVehicle unparkVehicle = new UnparkVehicle();
       		        				 	unparkVehicle.unpark(vehicleNumber);
       			                        isUser = false;
       			                    } 
       			                	catch (Exception e)
       			                	{
       			                        System.out.println("No vehicle is parked.  First park vehicle to unpark it.");
       			                        continue;
       			                    }
       		        		 }
       		        		 else if (userchoice == 2)
       		        		 {
       		        			 System.out.println("Exiting program...");
       		        			 break;
       		        		 }
   						 }
       		        	 catch (InputMismatchException e)
       		        	 {
       		        		 System.out.println("Invalid input! Please enter a valid number!");
       		                 scanner.nextLine();
       		                 continue;
   						}
       		         }
   				 }
   	    		 else if (getVehicleNumberPresent(userVehicleChoice))
   	    		 {
   					 System.out.println("Vehicle number already present! Please enter a different vehicle number!");
   	    			 continue;
   	    		 }
               }
                catch (InputMismatchException e) {
                	System.out.println("Invalid input! Please enter a valid number!");
	                scanner.nextLine();
                    continue;
				}
            }
            else if (choice == 3)
            {
            	ClaimInsurance claimInsurance = new ClaimInsurance();
            	claimInsurance.insurance();
    		}
            else if (choice == 4) 
            {
            	System.out.println("Exiting program...");
        		break;
			}
            else
            {
    			System.out.println("Invalid input!");
    	    }
        }
    }

    public static void main(String[] args) 
    {
    	ParkingLot parkingLot = new ParkingLot();
        parkingLot.run();
    }
}
