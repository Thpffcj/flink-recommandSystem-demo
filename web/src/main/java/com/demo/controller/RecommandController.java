package com.demo.controller;

import com.demo.dto.ProductDto;
import com.demo.service.KafkaService;
import com.demo.service.RecommendService;
import com.demo.util.Result;
import com.demo.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class RecommandController {

    @Autowired
    RecommendService recommendService;

    @Autowired
    KafkaService kafkaService;

    /**
     * 返回推荐页面
     * 根据用户特征，重新排序热度榜，之后根据两种推荐算法计算得到的产品相关度评分，为每个热度榜中的产品推荐几个关联的产品
     * @param userId
     * @return
     * @throws IOException
     */
    @GetMapping("/recommend")
    public String recommendByUserId(@RequestParam("userId") String userId,
                                    Model model) throws IOException {

        // 拿到不同推荐方案的结果
        // 热门商品推荐
        List<ProductDto> hotList = recommendService.recommendByHotList();
        // 协同过滤推荐
        List<ProductDto> itemCoeffList = recommendService.recommendByItemCoeff();
        // 产品画像推荐
        List<ProductDto> productCoeffList = recommendService.recommendByProductCoeff();

        // 将结果返回给前端
        model.addAttribute("userId", userId);
        model.addAttribute("hotList",hotList);
        model.addAttribute("itemCfCoeffList", itemCoeffList);
        model.addAttribute("productCoeffList", productCoeffList);

        return "user";
    }

    /**
     * 记录用户的行为
     * @param userId
     * @param productId
     * @param action
     * @return
     */
    @GetMapping("/log")
    @ResponseBody
    public Result logToKafka(@RequestParam("id") String userId,
                             @RequestParam("prod") String productId,
                             @RequestParam("action") String action){

        String log = kafkaService.makeLog(userId, productId, action);
        kafkaService.send(null, log);
        return ResultUtils.success();
    }
}
