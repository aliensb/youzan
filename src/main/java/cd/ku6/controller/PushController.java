package cd.ku6.controller;

import cd.ku6.model.MsgPushEntity;
import cd.ku6.service.ClientService;
import com.alibaba.fastjson.JSONObject;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanTradesSoldGet;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradesSoldGetParams;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2018/1/15.
 */
@RestController
public class PushController {
    @Autowired
    private ClientService clientService;
    @RequestMapping("/getOrders")
    public String index(){
        YZClient client= clientService.getClient();
        YouzanTradesSoldGetParams params=new YouzanTradesSoldGetParams();
        YouzanTradesSoldGet api=new YouzanTradesSoldGet(params);
        return client.execute(api);
    }
    @RequestMapping(value = "/push", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object test(@RequestBody MsgPushEntity entity) {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", "success");
        /**
         *  判断是否为心跳检查消息
         *  1.是则直接返回
         */
        if (entity.isTest()) {
            return res;
        }

        /**
         * 解析消息推送的模式  这步判断可以省略
         * 0-商家自由消息推送 1-服务商消息推送
         * 以服务商 举例
         * 判断是否为服务商类型的消息
         * 否则直接返回
         */
        if (entity.getMode() != 1 ){
            return res;
        }

        /**
         * 判断消息是否合法
         * 解析sign
         * MD5 工具类开发者可以自行引入
         */
//        String sign= RSASignature.MD5.digest(clientId+entity.getMsg()+clientSecret);
//        if (!sign.equals(entity.getSign())){
//            return res;
//        }

        /**
         * 对于msg 先进行URI解码
         */
        String msg="";
        try {
            msg= URLDecoder.decode(entity.getMsg(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         *  ..........
         *  接下来是一些业务处理
         *  判断当前消息的类型 比如交易
         *
         */

        if ("TRADE".equals(entity.getType())) {


            //TODO: 参考文档对应的交易对象 进行JSON解码  业务处理等

        }


        /**
         * 返回结果
         */
        return res;
    }
}
