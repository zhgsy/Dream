package dream.api.dmf.cn.dreaming.bean;

import java.util.List;

/**
 * Created by SongNing on 2019/6/24.
 * email: 836883891@qq.com
 */
public class BigBean {


    /**
     * data : [{"id":"3","sale_uid":null,"sale_username":null,"sale_mobile":null,"buy_uid":"4","buy_username":"15515828109","buy_mobile":"15515828109","amount":"1000.0000","realpay":"1670.0000","price":"1.6700","paytype":"0","bank_name":null,"bank_card":null,"bank_username":null,"bank_address":null,"addtime":"2019-06-24 09:26","buytime":"-","paytime":"-","donetime":null,"status":"0","pzimages":"","alipayfile":"","wechatfile":"","fe":"10%","tradetype":"1","uptime":null,"checked":"待匹配","payname":null,"paymount":"1100.00"},{"id":"2","sale_uid":null,"sale_username":null,"sale_mobile":null,"buy_uid":"4","buy_username":"15515828109","buy_mobile":"15515828109","amount":"100.0000","realpay":"167.0000","price":"1.6700","paytype":"0","bank_name":null,"bank_card":null,"bank_username":null,"bank_address":null,"addtime":"2019-06-24 09:26","buytime":"-","paytime":"-","donetime":null,"status":"0","pzimages":"","alipayfile":"","wechatfile":"","fe":"10%","tradetype":"1","uptime":null,"checked":"待匹配","payname":null,"paymount":"110.00"}]
     * error : 0
     */

    public int error;
    public List<DataBean> data;
    public String msg;

    public static class DataBean {
        /**
         * id : 3
         * sale_uid : null
         * sale_username : null
         * sale_mobile : null
         * buy_uid : 4
         * buy_username : 15515828109
         * buy_mobile : 15515828109
         * amount : 1000.0000
         * realpay : 1670.0000
         * price : 1.6700
         * paytype : 0
         * bank_name : null
         * bank_card : null
         * bank_username : null
         * bank_address : null
         * addtime : 2019-06-24 09:26
         * buytime : -
         * paytime : -
         * donetime : null
         * status : 0
         * pzimages :
         * alipayfile :
         * wechatfile :
         * fe : 10%
         * tradetype : 1
         * uptime : null
         * checked : 待匹配
         * payname : null
         * paymount : 1100.00
         */

        public String id;
        public Object sale_uid;
        public Object sale_username;
        public Object sale_mobile;
        public String buy_uid;
        public String buy_username;
        public String buy_mobile;
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
        public Object donetime;
        public String status;
        public String pzimages;
        public String alipayfile;
        public String wechatfile;
        public String fe;
        public String tradetype;
        public Object uptime;
        public String checked;
        public Object payname;
        public String paymount;
    }
}
