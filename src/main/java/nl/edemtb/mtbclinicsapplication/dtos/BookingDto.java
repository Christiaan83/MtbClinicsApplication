package nl.edemtb.mtbclinicsapplication.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookingDto {
    @NotNull(message = "bookingDate is required")
    private LocalDate bookingDate;

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
