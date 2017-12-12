package com.work.util;
//分页辅助类
public class PageUtil {
	public static Page createPage(int everyPage,int currentPage,int totalCount){
		int everypage=getEveryPage(everyPage);
		int currentpage=getCurrentPaage(currentPage);
		int totalpage=getTotalPage(everyPage,totalCount);
		int index=getIndex(everyPage,currentPage);
		boolean prepage=getPrePage(currentPage);
		boolean nextpage=getNextPage(totalpage,currentPage);
		return new Page(everypage,totalCount,totalpage,currentpage,index,prepage,nextpage);
	}
	//获取每页记录
	public static int getEveryPage(int everyPage){
		return everyPage==0?10:everyPage;
	}
	//获取当前页
	public static int getCurrentPaage(int currentPage){
		return currentPage==0?1:currentPage;
	}
	//获取总页数
	public static int getTotalPage(int everyPage,int totalCount){
		int totalpage=0;
		if(totalpage!=0 && totalCount%everyPage==0){
			totalpage=totalCount/everyPage;
		}else{
			totalpage=totalCount/everyPage+1;
		}
		
		return totalpage;
	}
	//获取起始位置
	public static int getIndex(int everyPage,int currentPage){
		return (currentPage-1)*everyPage;
	}
	//是否有上一页
	public static boolean getPrePage(int currentPage){
		return currentPage==1?false:true;
	}
	//是否有下一页
	public static boolean getNextPage(int totalPage,int currentPage){
		return currentPage==totalPage||totalPage==0?false:true;
	}
}
