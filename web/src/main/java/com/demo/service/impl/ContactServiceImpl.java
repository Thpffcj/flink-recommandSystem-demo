package com.demo.service.impl;

import com.demo.dao.ContactDao;
import com.demo.domain.ContactEntity;
import com.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactDao contactDao;

    /**
     * 根据id集合获取商品其他信息
     * @param ids
     * @return
     */
    @Override
    public List<ContactEntity> selectByIds(List<String> ids) {
        return contactDao.selectByIds(ids);
    }

    @Override
    public ContactEntity selectById(String id) {
        return contactDao.selectById(Integer.valueOf(id));
    }
}
