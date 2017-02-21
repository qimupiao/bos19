package com.itheima.bos.service;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.utils.PageBean;

public interface StaffService {

	public void save(Staff model);

	public void pageQuery(PageBean pageBean);

	public void deleteBatch(String ids);
}
