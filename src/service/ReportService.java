package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

public class ReportService {
	public int getDaySpend(Date d,List<Record> monthRawDate){
		int daySpend=0;
		for(Record r:monthRawDate){
			if(r.date.equals(d))
				daySpend+=r.getSpend();
		}
		return daySpend;
	}
	public List<Record> listThisMonthRecords(){
		RecordDAO dao=new RecordDAO();
		List<Record> monthRawDate=dao.listThisMonth();
		List<Record> result=new ArrayList<Record>();
		Date monthBegin=DateUtil.monthBegin();
		int monthTotalDay=DateUtil.thisMonthTotalDay();
		Calendar c=Calendar.getInstance();
		for(int i=0;i<monthTotalDay;i++){
			Record r=new Record();
			c.setTime(monthBegin);
			c.add(Calendar.DATE, i);
			Date eachDayOfThisMonth=c.getTime();
			int dayspend=getDaySpend(eachDayOfThisMonth, monthRawDate);
			r.spend=dayspend;
			result.add(r);
		}
		return result;
	}
}
