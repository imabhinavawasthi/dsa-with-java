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
cd src
javac Main.java WebServer.java model/*.java exception/*.java payment/*.java service/*.java
java Main
```

The console application remains the default. To run the browser UI locally, use `java Main --web` instead of `java Main`, then open http://localhost:8080.

## 4. Deploy to Render

The project includes a `Dockerfile` for Render's Docker runtime.

1. Push this project to a GitHub repository.
2. In Render, choose **New +** → **Web Service**, connect the repository, and select **Docker** as the runtime.
3. Leave the Docker build and start commands at their defaults. The Dockerfile compiles the Java sources and starts the web server.
4. Deploy. Render provides the public URL after the build succeeds.

The server binds to `0.0.0.0` and reads Render's `PORT` environment variable. For persistent bookings, attach a Render persistent disk mounted at `/var/data` and set `BOOKINGS_FILE` to `/var/data/bookings.txt` in the service's environment variables. Without a persistent disk, bookings are stored in the container's filesystem and may be lost when the service restarts or redeploys.

This is an educational demo: the active user is shared by all visitors and the services keep data in memory, so it is not configured for real multi-user production use.
