package dream.api.dmf.cn.dreaming.bean;

/**
 * Created by SongNing on 2019/6/28.
 * email: 836883891@qq.com
 */
public class RewardBean {
    /**
     * status : 200
     * message : ok
     * data : {"first":{"commend":0,"form":0,"share":0,"share_commission":0,"manger":0,"share_jifen":0,"real_reward":0},"last":{"commend":0,"form":0,"share":0,"share_commission":0,"manger":0,"share_jifen":0,"real_reward":0},"total":{"commend":0,"form":0,"share":0,"share_commission":0,"manger":0,"share_jifen":0,"real_reward":0},"number":"10000","fee":"10","share_ratio":0}
     */

    public String status;
    public String message;
    public DataBean data;

    public static class DataBean {
        /**
         * first : {"commend":0,"form":0,"share":0,"share_commission":0,"manger":0,"share_jifen":0,"real_reward":0}
         * last : {"commend":0,"form":0,"share":0,"share_commission":0,"manger":0,"share_jifen":0,"real_reward":0}
         * total : {"commend":0,"form":0,"share":0,"share_commission":0,"manger":0,"share_jifen":0,"real_reward":0}
         * number : 10000
         * fee : 10
         * share_ratio : 0
         */

        public FirstBean first;
        public LastBean last;
        public TotalBean total;
        public String number;
        public String fee;
        public String share_ratio;

        public static class FirstBean {
            /**
             * commend : 0
             * form : 0
             * share : 0
             * share_commission : 0
             * manger : 0
             * share_jifen : 0
             * real_reward : 0
             */

            public String commend;
            public String form;
            public String share;
            public String share_commission;
            public String manger;
            public String share_jifen;
            public String real_reward;
        }

        public static class LastBean {
            /**
             * commend : 0
             * form : 0
             * share : 0
             * share_commission : 0
             * manger : 0
             * share_jifen : 0
             * real_reward : 0
             */

            public String commend;
            public String form;
            public String share;
            public String share_commission;
            public String manger;
            public String share_jifen;
            public String real_reward;
        }

        public static class TotalBean {
            /**
             * commend : 0
             * form : 0
             * share : 0
             * share_commission : 0
             * manger : 0
             * share_jifen : 0
             * real_reward : 0
             */

            public String commend;
            public String form;
            public String share;
            public String share_commission;
            public String manger;
            public String share_jifen;
            public String real_reward;
        }
    }
}
