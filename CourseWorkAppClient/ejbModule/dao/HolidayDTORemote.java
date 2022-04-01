package dao;

import java.util.List;

import javax.ejb.Remote;

import model.HolidaysDTO;

@Remote
public interface HolidayDTORemote {

	List<HolidaysDTO> allHolidays();

}
