package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.io.IOException;
import java.util.Map;

public interface IOrderService {

    ServerResponse pay(Long orderNo, Integer userId, String path) throws IOException;

    ServerResponse aliCallback(Map<String,String> params);

    ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);
}
