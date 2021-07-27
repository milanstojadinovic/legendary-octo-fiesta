package com.linkedinlearning.demo.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkedinlearning.demo.business.domain.RoomReservation;
import com.linkedinlearning.demo.data.entity.Guest;
import com.linkedinlearning.demo.data.entity.Reservation;
import com.linkedinlearning.demo.data.entity.Room;
import com.linkedinlearning.demo.data.repository.GuestRepository;
import com.linkedinlearning.demo.data.repository.ReservationRepository;
import com.linkedinlearning.demo.data.repository.RoomRepository;

@Service
public class ReservationService {

	private final RoomRepository roomRepository;
	private final ReservationRepository reservationRepository;
	private final GuestRepository guestRepository;

	@Autowired
	public ReservationService(RoomRepository roomRepository, ReservationRepository reservationRepository,
			GuestRepository guestRepository) {
		this.roomRepository = roomRepository;
		this.reservationRepository = reservationRepository;
		this.guestRepository = guestRepository;
	}

	public List<RoomReservation> getRoomReservationsForDate(Date date) {
		Iterable<Room> rooms = this.roomRepository.findAll();
		Map<Long, RoomReservation> roomReservationsMap = new HashMap<Long, RoomReservation>();
		rooms.forEach(room -> {
			RoomReservation roomReservation = new RoomReservation();
			roomReservation.setRoomId(room.getRoomId());
			roomReservation.setRoomName(room.getRoomName());
			roomReservation.setRoomNumber(room.getRoomNumber());

			roomReservationsMap.put(room.getRoomId(), roomReservation);
		});
		Iterable<Reservation> reservations = this.reservationRepository
				.findReservationByReservationDate(new java.sql.Date(date.getTime()));
		reservations.forEach(reservation -> {
			RoomReservation roomReservation = roomReservationsMap.get(reservation.getRoomId());
			roomReservation.setDate(date);
			Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
			roomReservation.setFirstName(guest.getFirstName());
			roomReservation.setLastName(guest.getLastName());
			roomReservation.setGuestId(guest.getGuestId());
		});
		List<RoomReservation> roomReservations = new ArrayList<RoomReservation>();
		for(Long id : roomReservationsMap.keySet() ) {
			roomReservations.add(roomReservationsMap.get(id));
		}
		return roomReservations;
	}

}
