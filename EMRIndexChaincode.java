package org.example;
import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;
import it.unisa.dia.gas.jpbc.Element;
import org.hyperledger.fabric.shim.ChaincodeBase;
import org.hyperledger.fabric.shim.ChaincodeStub;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class EMRIndexChaincode extends ChaincodeBase{

    public static int search_rBits = 160;
    public static int search_qBits = 512;
    public static nics.crypto.proxy.search.AFGHGlobalParameters search_global = new nics.crypto.proxy.search.AFGHGlobalParameters(search_rBits, search_qBits);
    public static byte[] search_sk_a = nics.crypto.proxy.search.AFGHProxyReEncryption.generateSecretKey(search_global).toBytes();
    public static byte[] search_pk_a = nics.crypto.proxy.search.AFGHProxyReEncryption.generatePublicKey(search_sk_a, search_global);
    public static Element search_sk = nics.crypto.proxy.search.AFGHProxyReEncryption.bytesToElement(search_sk_a, search_global.getZq()).getImmutable();
    public static Element search_pk = nics.crypto.proxy.search.AFGHProxyReEncryption.bytesToElement(search_pk_a, search_global.getG2()).getImmutable();
    public static String keyword1 = "ganmao";
    public static Element k1 = H(search_global, keyword1.getBytes()).getImmutable();
    public static Element r = search_global.getZq().newRandomElement().getImmutable();
    public static Element I0 = search_global.getH_ppp().powZn(r).getImmutable();
    public static Element I1 = search_global.getE().pairing(k1, search_pk.powZn(r)).getImmutable();


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
            } else if (func.equals("searchByPatientId")) {
                return searchByPatientId(chaincodeStub, params);
            } else if (func.equals("searchByKeyword")) {
                return searchByKeyword(chaincodeStub, params);
            }
            return newErrorResponse("Invalid invoke function name. ");
        } catch (Throwable e) {
            return newErrorResponse(e);
        }
    }

    /**
     * Add EMRIndex
     * @param chaincodeStub
     * @param args
     * @return
     */
    private Response invoke(ChaincodeStub chaincodeStub, List<String> args) {
        if (args.size() != 2) {
            return newErrorResponse("Incorrect number of arguments. Expecting 2");
        }
        String key = args.get(0);
        String json = args.get(1);
        if(StringUtil.isNullOrEmpty(key) || StringUtil.isNullOrEmpty(json)){
            return newErrorResponse("arguments error");
        }else{
            List<EMRIndex> list = JSONObject.parseArray(chaincodeStub.getStringState(key), EMRIndex.class);
            if(list == null){
                list = new ArrayList<>();
            }
            list.add(JSONObject.parseObject(json, EMRIndex.class));
            chaincodeStub.putStringState(key, JSONObject.toJSONString(list));
            return newSuccessResponse("Add EMRIndex success!");
        }
    }

    /**
     * 返回对应patientID的记录
     * @param chaincodeStub
     * @param args
     * @return
     */
    private Response searchByPatientId(ChaincodeStub chaincodeStub, List<String> args) {
        if (args.size() != 1) {
            return newErrorResponse("Incorrect number of arguments. Expecting 1");
        }
        String key = args.get(0);
        if(StringUtil.isNullOrEmpty(key)){
            return newErrorResponse("arguments error");
        }else{
            String res = chaincodeStub.getStringState(key);
            return newSuccessResponse(res);
        }
    }

    /**
     * 返回对应keyword对应的EMR编号
     * @param chaincodeStub
     * @param args
     * @return
     */
    private Response searchByKeyword(ChaincodeStub chaincodeStub, List<String> args) {
        if (args.size() != 1) {
            return newErrorResponse("Incorrect number of arguments. Expecting 1");
        }
        String key = args.get(0);
        if(StringUtil.isNullOrEmpty(key)){
            return newErrorResponse("arguments error");
        }else{
            String searchByPatientIdResult = chaincodeStub.getStringState(key);
            List<EMRIndex> list = JSONObject.parseArray(searchByPatientIdResult, EMRIndex.class);
            List<String> resList = new ArrayList<>();
            for(int i = 0;i < list.size();i++){
                Element T0 = (k1).powZn(search_sk).getImmutable();
                Element left = search_global.getE().pairing(T0, I0);
                Element right = I1;
                if(left.equals(right)){
                    resList.add(list.get(i).getEmrId());
                }
            }
            return newSuccessResponse(JSONObject.toJSONString(resList));
        }
    }

    public static Element H(nics.crypto.proxy.search.AFGHGlobalParameters global, byte[] bys){
        String bysHash = getSHA256StrJava(bys);
        Element resElement = global.getG1().newElement();
        resElement.setFromHash(bysHash.getBytes(), 0, bysHash.getBytes().length);
        resElement.getImmutable();
        return resElement;
    }

    public static String getSHA256StrJava(byte[] b){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(b);
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    public static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        new EMRIndexChaincode().start(args);
    }
}
