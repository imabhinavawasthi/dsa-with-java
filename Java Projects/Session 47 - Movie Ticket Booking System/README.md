# Session 47: Java Mini Project 1 — Movie Ticket Booking System

A modular, production-ready console implementation of a **Movie Ticket Booking System** (inspired by BookMyShow) demonstrating enterprise Java architecture, Object-Oriented Design, layered decomposition, and Machine-Coding / LLD best practices.

---

## 1. Architectural Highlights

### Layered Architecture
```
┌────────────────────────────────────────────────────────┐
│                   Controller / View                    │
│                        Main.java                       │
│     (Scanner CLI, Display Menus, Exception Handling)   │
└──────────────────────────┬─────────────────────────────┘
                           │ delegates calls
┌──────────────────────────▼─────────────────────────────┐
│                     Service Layer                      │
│   MovieService   TheatreService   ShowService          │
│   UserService    BookingService                        │
│   (Business Rules, Atomic Operations, Concurrency)     │
└──────────────────────────┬─────────────────────────────┘
                           │ queries/updates
┌──────────────────────────▼─────────────────────────────┐
│                      Model Layer                       │
│   Movie   Theatre   Screen   Seat   Show   Booking     │
│   SeatType   SeatStatus   BookingStatus   User         │
└────────────────────────────────────────────────────────┘
```

### Key Engineering Principles
1. **Seat Availability Decoupling (`Seat ≠ Seat Availability`):**
   A physical seat (`A1`) exists in a screen, but its booked/available status depends strictly on the **Show**. Each `Show` maintains its own isolated `Map<String, Seat>`.
2. **Atomicity (All-or-Nothing Booking):**
   When a user requests `[A1, A2, A3]`, all seats are validated first. If `A3` is unavailable, none of the seats are booked, preventing partial/corrupt state.
3. **Data Structure Justification:**
   - `HashMap<Integer, Movie>`: $O(1)$ lookup by ID.
   - `HashMap<Integer, Show>`: $O(1)$ lookup by ID.
   - `Map<String, Seat>` per show: $O(1)$ direct access by seat code (`A1`, `B2`), eliminating $O(N)$ linear scans.
4. **Strategy Pattern for Payments:**
   `PaymentProcessor` interface implemented by `UPIPaymentProcessor`, `CardPaymentProcessor`, and `CashPaymentProcessor`.
5. **Thread Safety & Race Condition Prevention:**
   Critical sections in `BookingService` (`bookSeats`, `cancelBooking`) are `synchronized` to eliminate race conditions if two threads book the same seat at the same millisecond.
6. **Persistence Bridge:**
   Auto-saves and loads confirmed bookings from `bookings.txt` (CSV-based persistence).

---

## 2. Project Directory Layout

```
Session 47 - Movie Ticket Booking System/
├── src/
│   ├── model/
│   │   ├── Booking.java
│   │   ├── BookingStatus.java
│   │   ├── Movie.java
│   │   ├── Screen.java
│   │   ├── Seat.java
│   │   ├── SeatStatus.java
│   │   ├── SeatType.java
│   │   ├── Show.java
│   │   ├── Theatre.java
│   │   └── User.java
│   ├── exception/
│   │   ├── BookingNotFoundException.java
│   │   ├── InvalidSeatException.java
│   │   ├── MovieNotFoundException.java
│   │   ├── SeatNotAvailableException.java
│   │   └── ShowNotFoundException.java
│   ├── payment/
│   │   ├── CardPaymentProcessor.java
│   │   ├── CashPaymentProcessor.java
│   │   ├── PaymentProcessor.java
│   │   └── UPIPaymentProcessor.java
│   ├── service/
│   │   ├── BookingService.java
│   │   ├── MovieService.java
│   │   ├── ShowService.java
│   │   ├── TheatreService.java
│   │   └── UserService.java
│   └── Main.java
└── README.md
```

---

## 3. How to Compile and Run

From the project root:
```bash
cd "Mini Projects/Session 47 - Movie Ticket Booking System/src"
javac Main.java model/*.java exception/*.java payment/*.java service/*.java
java Main
```
