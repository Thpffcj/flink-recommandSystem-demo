package com.demo.dao;

import com.demo.domain.ProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ProductDao {

    /**
     * 获取指定商品的基本属性
     * @param id
     * @return
     */
    ProductEntity selectById(@Param("id") int id);

    /**
     * 获取指定商品集合的基本属性
     * @param ids
     * @return
     */
    List<ProductEntity> selectByIds(@Param("ids") List<String> ids);

    /**
     * 获取默认数量的商品id
     * @param size
     * @return
     */
	List<String> selectInitPro(@Param("size") int size);
}
