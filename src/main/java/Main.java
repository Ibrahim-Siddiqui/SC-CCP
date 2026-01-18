import domain.*;

import java.time.LocalDate;

/**
 * Main class demonstrates the Hotel Reservation System.
 * 
 * This program shows:
 * 1. Creating a hotel chain with hotels
 * 2. Creating different room types and rooms
 * 3. Creating guests and payers
 * 4. Making reservations
 * 5. Checking in guests
 * 6. Checking out guests
 * 7. Canceling reservations
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("========== HOTEL RESERVATION SYSTEM ==========\n");
        
        // Step 1: Create Hotel Chain
        System.out.println("Step 1: Creating Hotel Chain...");
        HotelChain chain = new HotelChain("Luxury Hotels International");
        System.out.println("✓ Hotel Chain created: " + chain.getName() + "\n");
        
        // Step 2: Create Hotels
        System.out.println("Step 2: Creating Hotels...");
        Hotel hotelKarachi = new Hotel(new Name("Grand", "Hotel Karachi"));
        Hotel hotelLahore = new Hotel(new Name("Elite", "Hotel Lahore"));
        
        chain.addHotel(hotelKarachi);
        chain.addHotel(hotelLahore);
        System.out.println("✓ Hotel 1: " + hotelKarachi.getNameAsString());
        System.out.println("✓ Hotel 2: " + hotelLahore.getNameAsString());
        System.out.println("✓ Total hotels in chain: " + chain.getHotelCount() + "\n");
        
        // Step 3: Create Room Types
        System.out.println("Step 3: Creating Room Types...");
        RoomType singleRoom = new RoomType(RoomKind.SINGLE, new Money(50.0, "USD"));
        RoomType doubleRoom = new RoomType(RoomKind.DOUBLE, new Money(100.0, "USD"));
        RoomType suiteRoom = new RoomType(RoomKind.SUITE, new Money(200.0, "USD"));
        System.out.println("✓ Single Room: " + singleRoom);
        System.out.println("✓ Double Room: " + doubleRoom);
        System.out.println("✓ Suite Room: " + suiteRoom + "\n");
        
        // Step 4: Create Rooms and add to Hotel Karachi
        System.out.println("Step 4: Adding Rooms to Hotel Karachi...");
        Room room101 = new Room(101, singleRoom);
        Room room102 = new Room(102, doubleRoom);
        Room room103 = new Room(103, suiteRoom);
        Room room104 = new Room(104, singleRoom);
        
        hotelKarachi.addRoom(101, room101);
        hotelKarachi.addRoom(102, room102);
        hotelKarachi.addRoom(103, room103);
        hotelKarachi.addRoom(104, room104);
        System.out.println("✓ Room 101 (Single): Added");
        System.out.println("✓ Room 102 (Double): Added");
        System.out.println("✓ Room 103 (Suite): Added");
        System.out.println("✓ Room 104 (Single): Added");
        System.out.println("✓ Total rooms in Hotel Karachi: " + hotelKarachi.getRoomCount());
        System.out.println("✓ Available rooms: " + hotelKarachi.getAvailableRoomCount() + "\n");
        
        // Step 5: Create Guests
        System.out.println("Step 5: Creating Guests...");
        Guest guest1 = new Guest(
            new Name("Ahmed", "Khan"),
            new Address("123 Main Street", "Karachi", "Pakistan", "75000")
        );
        Guest guest2 = new Guest(
            new Name("Fatima", "Ali"),
            new Address("456 Park Avenue", "Lahore", "Pakistan", "54000")
        );
        Guest guest3 = new Guest(
            new Name("Hassan", "Hassan"),
            new Address("789 Business Road", "Karachi", "Pakistan", "75100")
        );
        System.out.println("✓ Guest 1: " + guest1.getNameAsString());
        System.out.println("✓ Guest 2: " + guest2.getNameAsString());
        System.out.println("✓ Guest 3: " + guest3.getNameAsString() + "\n");
        
        // Step 6: Create ReserverPayers
        System.out.println("Step 6: Creating ReserverPayers (with Credit Card & ID)...");
        ReserverPayer payer1 = new ReserverPayer(
            new CreditCard("4532123456789010", "Ahmed Khan", "12/25"),
            new Identity("12345-6789012-1", "CNIC")
        );
        ReserverPayer payer2 = new ReserverPayer(
            new CreditCard("5425233010103010", "Fatima Ali", "08/26"),
            new Identity("98765-4321098-9", "CNIC")
        );
        System.out.println("✓ Payer 1: " + payer1);
        System.out.println("✓ Payer 2: " + payer2 + "\n");
        
        // Step 7: Make Reservations
        System.out.println("Step 7: Making Reservations...");
        
        Reservation res1 = new Reservation(
            LocalDate.now(),
            LocalDate.now().plusDays(1),
            LocalDate.now().plusDays(4),
            new HowMany(1)
        );
        System.out.println("Making reservation for Guest 1 in Room 101...");
        chain.makeReservation("Grand Hotel Karachi", 101, guest1, payer1, res1);
        System.out.println("✓ Reservation made: " + res1);
        
        Reservation res2 = new Reservation(
            LocalDate.now(),
            LocalDate.now().plusDays(2),
            LocalDate.now().plusDays(5),
            new HowMany(2)
        );
        System.out.println("Making reservation for Guest 2 in Room 102...");
        chain.makeReservation("Grand Hotel Karachi", 102, guest2, payer2, res2);
        System.out.println("✓ Reservation made: " + res2);
        
        System.out.println("✓ Available rooms after reservations: " + hotelKarachi.getAvailableRoomCount() + "\n");
        
        // Step 8: Check In Guest
        System.out.println("Step 8: Checking In Guest 1...");
        chain.checkInGuest("Grand Hotel Karachi", 101, "Ahmed Khan");
        room101.setOccupant(guest1);
        System.out.println("✓ Guest '" + guest1.getNameAsString() + "' checked in to Room 101");
        System.out.println("✓ Room 101 occupied by: " + room101.getOccupant().getNameAsString() + "\n");
        
        // Step 9: Check In Another Guest
        System.out.println("Step 9: Checking In Guest 2...");
        chain.checkInGuest("Grand Hotel Karachi", 102, "Fatima Ali");
        room102.setOccupant(guest2);
        System.out.println("✓ Guest '" + guest2.getNameAsString() + "' checked in to Room 102");
        System.out.println("✓ Room 102 occupied by: " + room102.getOccupant().getNameAsString() + "\n");
        
        // Step 10: Check Room Status
        System.out.println("Step 10: Checking Room Status...");
        System.out.println("Room 101 - Occupied: " + room101.isOccupied() + ", Available: " + room101.isAvailable());
        System.out.println("Room 102 - Occupied: " + room102.isOccupied() + ", Available: " + room102.isAvailable());
        System.out.println("Room 103 - Occupied: " + room103.isOccupied() + ", Available: " + room103.isAvailable());
        System.out.println("✓ Available rooms in hotel: " + hotelKarachi.getAvailableRoomCount() + "\n");
        
        // Step 11: Check Out Guest
        System.out.println("Step 11: Checking Out Guest 1...");
        chain.checkOutGuest("Grand Hotel Karachi", 101, "Ahmed Khan");
        System.out.println("✓ Guest 'Ahmed Khan' checked out from Room 101");
        System.out.println("✓ Room 101 is now empty: " + !room101.isOccupied());
        System.out.println("✓ Available rooms in hotel: " + hotelKarachi.getAvailableRoomCount() + "\n");
        
        // Step 12: Make Another Reservation (for available room)
        System.out.println("Step 12: Making Reservation for Available Room...");
        Reservation res3 = new Reservation(
            LocalDate.now(),
            LocalDate.now().plusDays(1),
            LocalDate.now().plusDays(3),
            new HowMany(1)
        );
        System.out.println("Making reservation for Guest 3 in Room 104...");
        chain.makeReservation("Grand Hotel Karachi", 104, guest3, payer1, res3);
        System.out.println("✓ Reservation made: " + res3 + "\n");
        
        // Step 13: Cancel Reservation
        System.out.println("Step 13: Canceling Reservation...");
        System.out.println("Canceling reservation for Guest 3 in Room 104...");
        chain.cancelReservation("Grand Hotel Karachi", 104, "Hassan Hassan");
        System.out.println("✓ Reservation canceled for Room 104");
        System.out.println("✓ Room 104 is now available: " + room104.isAvailable() + "\n");
        
        // Step 14: Final Status
        System.out.println("Step 14: Final System Status...");
        System.out.println("========== FINAL REPORT ==========");
        System.out.println("Hotel Chain: " + chain.getName());
        System.out.println("Total Hotels: " + chain.getHotelCount());
        System.out.println("Hotel Karachi Rooms: " + hotelKarachi.getRoomCount());
        System.out.println("  - Room 101: " + (room101.isOccupied() ? "Occupied" : "Available"));
        System.out.println("  - Room 102: " + (room102.isOccupied() ? "Occupied" : "Available"));
        System.out.println("  - Room 103: " + (room103.isOccupied() ? "Occupied" : "Available"));
        System.out.println("  - Room 104: " + (room104.isOccupied() ? "Occupied" : "Available"));
        System.out.println("Total Available Rooms: " + hotelKarachi.getAvailableRoomCount());
        System.out.println("===================================\n");
        
        System.out.println("✓ System demonstration completed successfully!");
    }
}
