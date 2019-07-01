package dream.api.dmf.cn.dreaming.bean;

import java.util.List;

/**
 * Created by SongNing on 2019/6/21.
 * email: 836883891@qq.com
 */
public class TradingBean {

    /**
     * data : [{"date":"06-07","price":"2.9361","amount":"56030.0000"},{"date":"06-08","price":"2.9575","amount":"61440.0000"},{"date":"06-09","price":"2.9956","amount":"66250.0000"},{"date":"06-10","price":"3.0453","amount":"76540.0000"},{"date":"06-11","price":"3.0810","amount":"63940.0000"},{"date":"06-12","price":"3.1179","amount":"42830.0000"},{"date":"06-13","price":"3.1531","amount":"51280.0000"},{"date":"06-14","price":"3.1792","amount":"43190.0000"},{"date":"06-19","price":"3.1792","amount":"0.0000"},{"date":"06-21","price":"3.1792","amount":"0.0000"}]
     * error : 0
     */

    public String error;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * date : 06-07
         * price : 2.9361
         * amount : 56030.0000
         */

        public String date;
        public String price;
        public String amount;
    }
}
