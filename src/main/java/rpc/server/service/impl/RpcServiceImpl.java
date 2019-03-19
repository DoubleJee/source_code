package rpc.server.service.impl;

import rpc.server.service.RpcService;

import java.util.HashMap;
import java.util.Map;


public class RpcServiceImpl implements RpcService {
    //接口实现
    @Override
    public String rpcMethod(String key, String value) {
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put(key,value);
        return hashMap.toString();
    }
}
