package com.parkingLot;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ParkingLot {
    public Map<Integer, Boolean> slots = new HashMap<Integer, Boolean>();
    public Map<Integer, String> slotVsUsernameMap = new HashMap<>();
    public Map<Integer, String> slotVsVehicleNumberMap = new HashMap<Integer, String>();
    public Map<String, String> vehicleNumberVsUserNameMap = new HashMap<>();
    private static int slot;
    private int gateNumber;
    protected String adminPassword = "password";
    static String username;
    static String vehicleNumber;
    private boolean isAdmin = false;
    private boolean isUser = false; 
    public int occupied = 0;
    private int regularFloorCapacity = 498;
    private int capacity = 12 * 498; // 12 floors with 498 slots each
    private int floors = 12;
    
    Scanner scanner = new Scanner(System.in);

    public ParkingLot() 
    {
        for (int i = 1; i <= capacity; i++)
        {
            slots.put(i, false);
        }
    }

    private boolean isVehicleNumberPresent(String vehicleNumber) 
    {
        return (vehicleNumberVsUserNameMap.containsValue(vehicleNumber));
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
                System.out.print("Enter password:");
                String password = scanner.nextLine();
                if (password.equals(adminPassword)) 
                {
                    isAdmin = true;
                    System.out.println("Admin login successful!");
                    while (isAdmin) 
                    {
                    	System.out.println();
                    	System.out.print("\n"+"Enter 1 to view parking lot status, 2 to view user details or 3 to exit: ");
    	                int adminChoice;
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
    	                if (adminChoice == 1) {
    	                    System.out.println("\n"+"Total slots: " + capacity);
    	                    System.out.println("Available slots: " + (capacity-occupied));
    	                    System.out.println("Occupied slots: " + occupied);

    	                    for (int i = 0; i < floors; i++) {
    	                        int floorCapacity = regularFloorCapacity;
    	                        int floorStartingSlot = (i * regularFloorCapacity) + 1;
    	                        int floorEndingSlot = floorStartingSlot + floorCapacity - 1;

    	                        int floorOccupied = 0;
    	                        for (int j = floorStartingSlot; j <= floorEndingSlot; j++) {
    	                            Boolean isSlotOccupied = slots.get(j);
    	                            if (isSlotOccupied != null && isSlotOccupied.booleanValue()) {
    	                                floorOccupied++;
    	                            }
    	                        }

    	                        System.out.println("\nFloor " + (i+1) + ":");
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
    	                    
    	                    if (slot < 1 || slot > capacity) 
    	                    {
    	                    	System.out.println("Invalid slot number!");
    	                    } 
    	                    else 
    	                    {
    	                    	boolean isOccupied = slots.get(slot);
    	                    	
    	                    	if (isOccupied) 
    	                    	{
    	                    		System.out.println("Username: " + slotVsUsernameMap.get(slot));
    	                    		System.out.println("Vehicle Number: " + slotVsVehicleNumberMap.get(slot));
    	                    	} else {
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
                }
                else 
                {
                    System.out.println("Invalid password.  Try Again!");
                }
            }
            else if (choice == 2)
            {
            	System.out.print("\n"+"Enter username:");
            	username = scanner.nextLine();
            	if (username.isEmpty()) 
            	{
            		System.out.println("Username cannot be empty! Try again with a valid username.");
            		continue;
        		}
            	System.out.print("Enter vehicle number:");
	    		vehicleNumber = scanner.nextLine();
	    		 if (vehicleNumber.isEmpty())
	    		 {
	                 System.out.println("Vehicle number cannot be empty! Try again with a valid vehicle number.");
	                 continue;
	             }
	    		 if(!isVehicleNumberPresent(vehicleNumber)) 
	    		 {
    		         System.out.println("User login successful!");
    		         vehicleNumberVsUserNameMap.put(vehicleNumber, username);
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
    		        		        	 System.out.println("Invalid gate number! Please enter a number between 1 and 12.");
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
	    		 else if (vehicleNumberVsUserNameMap.containsValue(vehicleNumber) && (vehicleNumberVsUserNameMap.get(vehicleNumber).equals(username)))
	    		 {
	    			 System.out.println("User login successful!");
	    			 isUser = true;
    		         while (isUser) {
    		        	 System.out.print("\n"+"Enter 1 to unpark or 2 to exit: ");
    		        	 int userchoice = 0;
    		        	 try {
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
    		        	 catch (InputMismatchException e) {
    		        		 System.out.println("Invalid input! Please enter a valid number!");
    		                 scanner.nextLine();
    		                 continue;
						}
    		         }
				 }
	    		 else if (isVehicleNumberPresent(vehicleNumber))
	    		 {
					 System.out.println("Vehicle number already present! Please enter a different vehicle number!");
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
