# Hotel Management System - OOP Project

## Project Overview
A complete, modular, console-based Hotel Management System built in Java using Object-Oriented Programming principles and a 3-layer architecture pattern.

## Project Structure

### Architecture Layers

#### 1. **Entity Layer** (Data Models)
- **Guest.java**: Represents a guest with personal information
  - Fields: name, phone, guestId
  - Methods: Getters, setters, toString()

- **Room.java**: Represents a hotel room with status information
  - Fields: roomNumber, isAvailable (boolean), price, guest (Guest reference)
  - Methods: Getters, setters, toString()

#### 2. **Service/Manager Layer** (Business Logic)
- **HotelManager.java**: Core business logic for hotel operations
  - Manages an array of 10 Room objects
  - Key Methods:
    - `displayAvailableRooms()`: Shows all vacant rooms
    - `bookRoom(int roomNo, Guest guest)`: Books a room (throws RoomUnavailableException if occupied)
    - `checkOut(int roomNo)`: Processes guest checkout
    - `displayAllRooms()`: Shows status of all rooms
    - `getAvailableRoomsCount()`: Returns count of available rooms

#### 3. **UI Layer** (Presentation & User Interaction)
- **HotelApplication.java**: Main application with menu-driven interface
  - Implements while(true) loop with switch-case menu
  - Menu Options:
    1. View Available Rooms
    2. Book a Room
    3. Checkout
    4. View All Rooms Status
    5. Exit
  - Robust error handling with try-catch for InputMismatchException
  - Never crashes due to invalid input

#### 4. **Custom Exception**
- **RoomUnavailableException.java**: Thrown when room booking fails
  - Extends Exception class
  - Provides custom error messages for room-related issues

## Files and File Paths

```
HotelManagementOOPSProject/
├── Guest.java                      # Entity class
├── Room.java                        # Entity class
├── RoomUnavailableException.java   # Custom exception
├── HotelManager.java               # Manager/Service class
├── HotelApplication.java           # Main UI application
└── README.md                        # This file
```

## How to Compile and Run

### Compilation
```bash
cd HotelManagementOOPSProject
javac *.java
```

### Execution
```bash
java HotelApplication
```

## Features Implemented

✅ **3-Layer Architecture**: Clean separation of concerns
✅ **Encapsulation**: Private fields with getter/setter methods
✅ **Exception Handling**: Custom exception for room booking errors
✅ **Input Validation**: Try-catch blocks handle InputMismatchException
✅ **User-Friendly Menu**: Switch-case menu system
✅ **Dynamic Room Management**: 10 rooms with availability tracking
✅ **Guest Management**: Guest tracking with phone and ID
✅ **No Application Crash**: Robust error handling throughout

## Usage Example

1. Start the application
2. View available rooms to see which rooms are free
3. Book a room by providing room number and guest details
4. View all rooms to see current booking status
5. Checkout a guest from a room when they leave
6. Exit the application

## Key Design Principles

- **Single Responsibility**: Each class has one clear purpose
- **Encapsulation**: Private data with public accessors
- **Exception Handling**: Checked exceptions for expected errors
- **Code Documentation**: Comprehensive JavaDoc comments
- **User Experience**: Clear, formatted output with visual cues

## Default Room Configuration

- **Number of Rooms**: 10
- **Room Numbers**: 101 to 110
- **Initial Status**: All available
- **Price**: ₹5000 per night (configurable)

## Notes

- Guest IDs are auto-generated starting from 1000
- All room prices are set to ₹5000 by default
- The system supports booking, checkout, and room status viewing
- Input validation prevents the application from crashing on invalid input

