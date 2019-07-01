package dream.api.dmf.cn.dreaming.bean;

import java.util.List;

/**
 * Created by SongNing on 2019/6/23.
 * email: 836883891@qq.com
 */
public class IsLoginBean {

    /**
     * bankhm :
     * bankname : null
     * bankcard : null
     * bankadd : null
     * alipayhm :
     * alipay :
     * alipayfile :
     * wechathm :
     * wechat :
     * wechatfile :
     * idcard : null
     * ac_status : 1
     * stock : 0.0000
     * balance : 41000.0000
     * regmoney : 41000.00
     * stock_mdf : 41000.0000
     * balance_dmf : 41000.0000
     * regmoney_dmf : 41000.0000
     * buy_num : ["10","30","50","100","500","1000"]
     * jy4 : ["10","30","50","100","300","500","1000","2000","3000"]
     * dmf_num : ["1","5","10","50","100","300","1000"]
     * tdmf_num : ["10","50","100","300","500","1000"]
     * toshopmoney : 3.1792
     * jy2 : 1.67
     * dmfpmoney : 2.3
     * tdmfpmoney : 1.9
     * hyt_day_price : {"yestoday":"3.1792","today":"3.1792","updatemoney":"0.0000"}
     * dmf_day_price : {"yestoday":"2.3000","today":"2.3000","updatemoney":"0.0000"}
     * hyted : 0.04
     * hytdt : 0.1
     * dmfed : 0.05
     * dmfdt : 0.1
     */

    public String bankhm;
    public Object bankname;
    public Object bankcard;
    public Object bankadd;
    public String alipayhm;
    public String alipay;
    public String alipayfile;
    public String wechathm;
    public String wechat;
    public String wechatfile;
    public Object idcard;
    public String ac_status;
    public String stock;
    public String balance;
    public String regmoney;
    public String stock_mdf;
    public String balance_dmf;
    public String regmoney_dmf;
    public String toshopmoney;
    public String jy2;
    public String credit3;
    public String credit4;
    public String dmfpmoney;
    public String tdmfpmoney;
    public HytDayPriceBean hyt_day_price;
    public DmfDayPriceBean dmf_day_price;
    public double hyted;
    public double hytdt;
    public double dmfed;
    public double dmfdt;
    public List<String> buy_num;
    public List<String> jy4;
    public List<String> dmf_num;
    public List<String> tdmf_num;

    public static class HytDayPriceBean {
        /**
         * yestoday : 3.1792
         * today : 3.1792
         * updatemoney : 0.0000
         */

        public String yestoday;
        public String today;
        public String updatemoney;
    }

    public static class DmfDayPriceBean {
        /**
         * yestoday : 2.3000
         * today : 2.3000
         * updatemoney : 0.0000
         */

        public String yestoday;
        public String today;
        public String updatemoney;
    }
}
