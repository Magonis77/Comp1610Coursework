package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.HolidaysDTO;

@Remote
public interface HolidayDTORemote {

	List<HolidaysDTO> allHolidays();
	
	void acceptholiday(int iD);

	void rejectholiday(int iD);
	
	void requestholiday(int id, Date startdate, Date enddate, int Lenght, String status);
}
