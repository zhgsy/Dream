package dream.api.dmf.cn.dreaming.bean;

import java.util.List;

/**
 * Created by SongNing on 2019/6/27.
 * email: 836883891@qq.com
 */
public class SellBean {
    /**
     * data : []
     * error : 0
     */

    public int error;
    public String msg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 24
         * sale_uid : 5
         * sale_username : 15738827686
         * sale_mobile : 15738827686
         * buy_uid : null
         * buy_username : null
         * buy_mobile : null
         * amount : 10.0000
         * realpay : 16.7000
         * price : 1.6700
         * paytype : 0
         * bank_name : null
         * bank_card : null
         * bank_username : null
         * bank_address : null
         * addtime : 2019-06-25 20:08
         * buytime : -
         * paytime : -
         * donetime : 1561601116
         * status : 4
         * pzimages :
         * alipayfile :
         * wechatfile :
         * fe : 10%
         * tradetype : 2
         * uptime : 1561601116
         * checked : 已完成
         * payname : null
         */

        public String id;
        public String sale_uid;
        public String sale_username;
        public String sale_mobile;
        public Object buy_uid;
        public Object buy_username;
        public Object buy_mobile;
        public String amount;
        public String realpay;
        public String price;
        public String paytype;
        public Object bank_name;
        public Object bank_card;
        public Object bank_username;
        public Object bank_address;
        public String addtime;
        public String buytime;
        public String paytime;
        public String donetime;
        public String status;
        public String pzimages;
        public String alipayfile;
        public String wechatfile;
        public String fe;
        public String tradetype;
        public String uptime;
        public String checked;
        public Object payname;
    }
}
