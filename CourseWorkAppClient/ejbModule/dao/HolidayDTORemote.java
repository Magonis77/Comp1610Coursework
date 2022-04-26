package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.HolidaysDTO;
import model.UserDTO;

@Remote
public interface HolidayDTORemote {

	List<HolidaysDTO> allHolidays();
	
	void acceptholiday(int iD);

	void rejectholiday(int iD);
	
	void requestholiday(int id, Date startdate, Date enddate, int Lenght, String status);

	List<HolidaysDTO> allUserHolidays(int userID);

	List<HolidaysDTO> allOutstandingHolidays(String status);

	List<HolidaysDTO> countuserlenght(int user);


	List<HolidaysDTO> allOutstandingHolidayswcontrain(String status, int i);
}
