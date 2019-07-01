package dream.api.dmf.cn.dreaming.bean;

/**
 * Created by SongNing on 2019/6/29.
 * email: 836883891@qq.com
 */
public class MouthBean {
    /**
     * status : 200
     * message : ok
     * data : {"commend":320,"form":32,"share":0,"share_commission":40,"share_jifen":0,"real_reward":356}
     */

    public int status;
    public String message;
    public DataBean data;

    public static class DataBean {
        /**
         * commend : 320
         * form : 32
         * share : 0
         * share_commission : 40
         * share_jifen : 0
         * real_reward : 356
         */

        public int commend;
        public int form;
        public int share;
        public int share_commission;
        public int share_jifen;
        public int real_reward;
    }
}
