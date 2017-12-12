package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.PhotoDao;
import com.work.entity.Photo;
import com.work.util.Page;

@Service
public class PhotoService {
	@Autowired PhotoDao photodao;
	  //查询照片
	public List<Photo> selectPhoto(Page page){
		return photodao.selectPhoto(page);
	}
	//查询照片
	public List<Photo> selectPhoto(int begin){
		return photodao.selectPhoto(begin);
	}
	//查询公告总数
		public int photoAllCount(){
			return photodao.photoAllCount();
	}
    //删除照片
	public void deletePhoto(int id){
		photodao.deletePhoto(id);
	}
	  //增加照片
		public void addPhoto(Photo photo){
			photodao.addPhoto(photo);
		}
		//修改照片
		public void updatePhoto(Photo photo){
			photodao.updatePhoto(photo);
		
		}
}
