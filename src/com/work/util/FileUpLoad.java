package com.work.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.work.dao.EmployeeDao;
import com.work.dao.TimeTableDao;
import com.work.entity.Employee;
import com.work.entity.TimeTable;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FileUpLoad {
/*
 * 输入上传文件路径进行解析
 * 通过特定格式将数据存入数据库	
 */
	public static void readImage(String path){
		//读取用户头像文件
	}

	public static void readExcel(String path){
		
		//创建list存取读取信息

		TimeTableDao timetabledao=new TimeTableDao();
		TimeTable timetable=new TimeTable();
		//Employee employee=new Employee();
		
		List<Object> list = new ArrayList<Object>();
	    Workbook rwb = null;
	    Cell cell = null;
		File file=new File(path);
		try {
			InputStream input=new FileInputStream(file);
			    //获取excel对象
				rwb=Workbook.getWorkbook(input);
				//获取第一个工作表
			Sheet sheet=rwb.getSheet(0);
			for(int i=0;i<sheet.getRows();i++){
				String[] str=new String[sheet.getColumns()];
				for(int j=0;j<sheet.getColumns();j++){
				cell=sheet.getCell(j, i);
			    str[j]=cell.getContents();
				}
				list.add(str);
			}
			
			for(int i=0;i<list.size();i++){
			     String[] str = (String[])list.get(i);
			     //由于文件被修改，日期变为非date类型导致数据截取出错，故而做此判断
			     str[1]=str[1].replace("-","/");
			     if(str[1].indexOf("/")<=2){
			    	 str[1]="20"+str[1]; 
			     }

			     timetable.setUsername(str[0]);
			     timetable.setDate(str[1]);
			     timetable.setStarttime(str[2]);
			     timetable.setEndtime(str[3]);
			     timetable.setWeekend(str[4]);
			     timetable.setHoliday(str[5]);
			     timetable.setExplain(str[6]);
			     timetabledao.addTimeTable(timetable);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(file.exists()){
			file.delete();
		}
	}
	//创建excel
	public static void writeExcel(String path,String month){
		
		String title[]={"姓名","日期","上班打卡时间","下班打卡时间","周末加班","假日加班","说明"};		
		File file=new File(path,"work.xls");
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TimeTableDao timetabledao=new TimeTableDao();
		EmployeeDao employeedao=new EmployeeDao();
			try {
				
				WritableWorkbook workbook=Workbook.createWorkbook(file);
				//WritableWorkbook workbook=Workbook.createWorkbook(file, ws)
				WritableSheet sheet=workbook.createSheet("sheet1", 0);
				sheet.setColumnView(0, 10);
				sheet.setColumnView(1, 15);
				sheet.setColumnView(2, 15);
				sheet.setColumnView(3, 15);
				sheet.setColumnView(4, 15);
				sheet.setColumnView(5, 15);
				sheet.setColumnView(6, 18);
				WritableFont wf=new WritableFont(WritableFont.ARIAL,11);
			WritableFont wf1=new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
				WritableCellFormat wcf = new WritableCellFormat(wf);
				WritableCellFormat wcf1 = new WritableCellFormat(wf);
				WritableCellFormat wcf2 = new WritableCellFormat(wf1);
				wcf1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				wcf2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				wcf2.setBackground(jxl.format.Colour.YELLOW);
				wcf.setBackground(jxl.format.Colour.YELLOW); // 设置单元格的背景颜色
				Label label=null;
				for(int i=0;i<title.length;i++){
					label=new Label(i,0,title[i],wcf2);
					sheet.addCell(label);
				}
			List<Employee> list1=employeedao.queryUserStatusIsUse();
  			//List<TimeTable> list=timetabledao.queryTimeTableByDate(month);
			List<TimeTable> list=null;
				//开始追加数据
			int k=1;
			for(int j=0;j<list1.size();j++){
				
				list=timetabledao.queryTimeTableByUserName(list1.get(j).getUsername());
				for(int i=0;i<list.size();i++){
				//	System.out.println(k);
					if(!(list.get(i).getWeekend()==null) && list.get(i).getWeekend().length()>0){
						label=new Label(0,k,list.get(i).getUsername(),wcf);
						sheet.addCell(label);
						label=new Label(1,k,list.get(i).getDate(),wcf);
						sheet.addCell(label);
						label=new Label(2,k,list.get(i).getStarttime(),wcf);
						sheet.addCell(label);
						label=new Label(3,k,list.get(i).getEndtime(),wcf);
						sheet.addCell(label);
						label=new Label(4,k,list.get(i).getWeekend(),wcf);
						sheet.addCell(label);
						label=new Label(5,k,list.get(i).getHoliday(),wcf);
						sheet.addCell(label);
						label=new Label(6,k,list.get(i).getExplain(),wcf);
						sheet.addCell(label);
						k++;
					}else{
						label=new Label(0,k,list.get(i).getUsername(),wcf1);
						sheet.addCell(label);
						label=new Label(1,k,list.get(i).getDate(),wcf1);
						sheet.addCell(label);
						label=new Label(2,k,list.get(i).getStarttime(),wcf1);
						sheet.addCell(label);
						label=new Label(3,k,list.get(i).getEndtime(),wcf1);
						sheet.addCell(label);
						label=new Label(4,k,list.get(i).getWeekend(),wcf1);
						sheet.addCell(label);
						label=new Label(5,k,list.get(i).getHoliday(),wcf1);
						sheet.addCell(label);
						label=new Label(6,k,list.get(i).getExplain(),wcf1);
						sheet.addCell(label);
					k++;
					}
					
				}
				
			}
			  
				workbook.write();//写入文件
				workbook.close();//关闭流
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	//创建excel
		public static void writeExcelByAuto(String path,String month){
			
			String title[]={"姓名","日期","上班打卡时间","下班打卡时间","周末加班","假日加班","说明"};	
			String filename="甘肃考勤"+month+"月份.xls";
			File file=new File(path,filename);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			TimeTableDao timetabledao=new TimeTableDao();
			EmployeeDao employeedao=new EmployeeDao();
				try {
					
					WritableWorkbook workbook=Workbook.createWorkbook(file);
					//WritableWorkbook workbook=Workbook.createWorkbook(file, ws)
					WritableSheet sheet=workbook.createSheet("sheet1", 0);
					sheet.setColumnView(0, 10);
					sheet.setColumnView(1, 15);
					sheet.setColumnView(2, 15);
					sheet.setColumnView(3, 15);
					sheet.setColumnView(4, 15);
					sheet.setColumnView(5, 15);
					sheet.setColumnView(6, 18);
					WritableFont wf=new WritableFont(WritableFont.ARIAL,11);
				WritableFont wf1=new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
					WritableCellFormat wcf = new WritableCellFormat(wf);
					WritableCellFormat wcf1 = new WritableCellFormat(wf);
					WritableCellFormat wcf2 = new WritableCellFormat(wf1);
					wcf1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
					wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
					wcf2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
					wcf2.setBackground(jxl.format.Colour.YELLOW);
					wcf.setBackground(jxl.format.Colour.YELLOW); // 设置单元格的背景颜色
					Label label=null;
					for(int i=0;i<title.length;i++){
						label=new Label(i,0,title[i],wcf2);
						sheet.addCell(label);
					}
				List<Employee> list1=employeedao.queryUserStatusIsUse();
	  			//List<TimeTable> list=timetabledao.queryTimeTableByDate(month);
				List<TimeTable> list=null;
					//开始追加数据
				int k=1;
				for(int j=0;j<list1.size();j++){
					
					list=timetabledao.queryTimeTableByUserName(list1.get(j).getUsername());
					for(int i=0;i<list.size();i++){
					//	System.out.println(k);
						if(!(list.get(i).getWeekend()==null) && list.get(i).getWeekend().length()>0){
							label=new Label(0,k,list.get(i).getUsername(),wcf);
							sheet.addCell(label);
							label=new Label(1,k,list.get(i).getDate(),wcf);
							sheet.addCell(label);
							label=new Label(2,k,list.get(i).getStarttime(),wcf);
							sheet.addCell(label);
							label=new Label(3,k,list.get(i).getEndtime(),wcf);
							sheet.addCell(label);
							label=new Label(4,k,list.get(i).getWeekend(),wcf);
							sheet.addCell(label);
							label=new Label(5,k,list.get(i).getHoliday(),wcf);
							sheet.addCell(label);
							label=new Label(6,k,list.get(i).getExplain(),wcf);
							sheet.addCell(label);
							k++;
						}else{
							label=new Label(0,k,list.get(i).getUsername(),wcf1);
							sheet.addCell(label);
							label=new Label(1,k,list.get(i).getDate(),wcf1);
							sheet.addCell(label);
							label=new Label(2,k,list.get(i).getStarttime(),wcf1);
							sheet.addCell(label);
							label=new Label(3,k,list.get(i).getEndtime(),wcf1);
							sheet.addCell(label);
							label=new Label(4,k,list.get(i).getWeekend(),wcf1);
							sheet.addCell(label);
							label=new Label(5,k,list.get(i).getHoliday(),wcf1);
							sheet.addCell(label);
							label=new Label(6,k,list.get(i).getExplain(),wcf1);
							sheet.addCell(label);
						k++;
						}
						
					}
					
				}
				  
					workbook.write();//写入文件
					workbook.close();//关闭流
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
	
}
