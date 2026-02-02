package com.smart_campus_system.backend.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smart_campus_system.backend.Entity.BookingEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Integer>  {

	@Query(""" 
			Select b from BookingEntity b
			where b.resource.resourceId = :resourceId
			AND b.bookingDate =:date
			AND (:startTime < b.endTime AND :endTime>b.startTime)
			AND b.status IN('PENDING','APPROVED')
			""")
	List<BookingEntity> findConflictBookings(
			@Param("resourceId") int resourceId,
			@Param("date") LocalDate date,
			@Param("startTime") LocalTime startTime,
			@Param("endTime") LocalTime endTime
			);
}
