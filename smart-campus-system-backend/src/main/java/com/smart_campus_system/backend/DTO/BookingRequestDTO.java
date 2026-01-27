package com.smart_campus_system.backend.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequestDTO {
    public int userId;
    public int resourceId;
    public LocalDate bookingDate;
    public LocalTime startTime;
    public LocalTime endTime;
}
