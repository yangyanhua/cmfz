package com.xuxinhui.dao;

import com.xuxinhui.entity.Cities;

import java.util.List;

public interface CitiesDao {
	//查省
	List<Cities> findprovince();
	//查市
	List<Cities> findcity(Integer parentid);
}
