package dream.api.dmf.cn.dreaming.bean;

import java.util.List;

/**
 * Created by SongNing on 2019/6/26.
 * email: 836883891@qq.com
 */
public class TuiBean {

    /**
     * status : 200
     * message : ok
     * data : [{"realname":"1","level":"银卡","number":"1","total_rewqrd":"588.60","createtime":"2019-06-25 11:40:24"},{"realname":"","level":"银卡","number":"2","total_rewqrd":"1465.20","createtime":"2019-06-25 14:21:55"}]
     */

    public int status;
    public String message;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * realname : 1
         * level : 银卡
         * number : 1
         * total_rewqrd : 588.60
         * createtime : 2019-06-25 11:40:24
         */

        public String realname;
        public String level;
        public String number;
        public String total_rewqrd;
        public String createtime;
    }
}
