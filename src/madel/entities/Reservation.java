package madel.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private int roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(int roomNumber, Date chackIn, Date checkOut) {
		if(!checkOut.after(checkIn)) { 
			throw new DomainException(" Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = chackIn;
		this.checkOut = checkOut;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {		
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkIn, Date checkOut){
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
		 throw new DomainException(" Reservaton dates for update must be future dates ");
		}
		if(!checkOut.after(checkIn)) { 
			throw new DomainException(" Check-out date must be after check-in date");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		}
	
	@Override
	public String toString() {
		return "Room"
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+", "
				+duration()
				+" nights";
	}

		
	
	
	
}
