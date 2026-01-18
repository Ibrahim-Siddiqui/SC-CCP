package test.java.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {

    private final LocalDate reservationDate;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer number;

    public Reservation(LocalDate reservationDate,
                       LocalDate startDate,
                       LocalDate endDate,
                       Integer number) {

        if (reservationDate == null) {
            throw new IllegalArgumentException("Reservation date cannot be null");
        }
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start and end dates cannot be null");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        if (number == null || number <= 0) {
            throw new IllegalArgumentException("Reservation number must be positive");
        }

        this.reservationDate = reservationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.number = number;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getNumber() {
        return number;
    }

    public int getNumberOfNights() {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
}
