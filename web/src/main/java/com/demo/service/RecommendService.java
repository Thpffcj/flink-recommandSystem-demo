package com.demo.service;

import com.demo.domain.ProductScoreEntity;
import com.demo.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface RecommendService {


    /**
     * 弃用
     * 基于用户特征的热度表和产品标签关联表 -> 联合推荐
     * @param userId
     * @return
     * @throws IOException
     */
    @Deprecated
    List<ProductScoreEntity> userRecommand(String userId) throws IOException;

    /**
     * 热度榜数据
     */
    List<ProductDto> recommendByHotList();

    /**
     * 协同过滤推荐结果
     * @return
     */
    List<ProductDto> recommendByItemCoeff() throws IOException;

    /**
     * 产品画像推荐结果
     * @return
     */
    List<ProductDto> recommendByProductCoeff() throws IOException;
}
