package org.example;

import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;
import org.hyperledger.fabric.shim.ChaincodeBase;
import org.hyperledger.fabric.shim.ChaincodeStub;

import java.util.ArrayList;
import java.util.List;

public class EMRSharingChaincode extends ChaincodeBase {
    @Override
    public Response init(ChaincodeStub chaincodeStub) {
        return newSuccessResponse();
    }

    @Override
    public Response invoke(ChaincodeStub chaincodeStub) {
        try {
            String func = chaincodeStub.getFunction();
            List<String> params = chaincodeStub.getParameters();
            if (func.equals("invoke")) {
                return invoke(chaincodeStub, params);
            }
            else if (func.equals("query")) {
                return query(chaincodeStub, params);
            }

            return newErrorResponse("Invalid invoke function name. Expecting one of: [\"invoke\", \"query\"]");
        } catch (Throwable e) {
            return newErrorResponse(e);
        }
    }

    private Response invoke(ChaincodeStub chaincodeStub, List<String> args) {
        long startTime = System.currentTimeMillis();
        if (args.size() != 2) {
            return newErrorResponse("Incorrect number of arguments. Expecting 2");
        }
        String key = args.get(0);
        String json = args.get(1);
        if(StringUtil.isNullOrEmpty(key) || StringUtil.isNullOrEmpty(json)){
            return newErrorResponse("arguments error");
        }else{
            List<EMRSharing> list = JSONObject.parseArray(chaincodeStub.getStringState(key), EMRSharing.class);
            if(list == null){
                list = new ArrayList<>();
            }
            list.add(JSONObject.parseObject(json, EMRSharing.class));
            chaincodeStub.putStringState(key, JSONObject.toJSONString(list));
            long costTime = System.currentTimeMillis() - startTime;
            return newSuccessResponse("Add EMRSharing success! costTime:" + costTime + "ms");
        }
    }

    private Response query(ChaincodeStub chaincodeStub, List<String> args) {
        long startTime = System.currentTimeMillis();
        if (args.size() != 1) {
            return newErrorResponse("Incorrect number of arguments. Expecting 1");
        }
        String key = args.get(0);
        if (StringUtil.isNullOrEmpty(key)) {
            return newErrorResponse("arguments error");
        } else {
            String res = chaincodeStub.getStringState(key);
            long costTime = System.currentTimeMillis() - startTime;
            System.out.println("res:" + res);
            return newSuccessResponse("query EMRSharing success! costTime:" + costTime + "ms");
        }
    }

    public static void main(String[] args) {
        new EMRSharingChaincode().start(args);
    }
}
