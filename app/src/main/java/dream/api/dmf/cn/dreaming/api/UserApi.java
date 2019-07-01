package dream.api.dmf.cn.dreaming.api;

public class UserApi
{
    public static final String BASE_URL="http://api.xg360.cc/";
    //登陆接口
    public static final String getLogin = "index.php?mod=mobile&act=login";
    //注册
    public static final String getRegst="http://api.xg360.cc/index.php?mod=member&act=register";
    //忘记
    public static final String getForget="http://api.xg360.cc/index.php?mod=member&act=forgot";
    //商城接口
    public static final String getShopList="https://shop.xg360.cc/app/index.php?i=2&c=entry&m=ewei_shopv2&do=mobile&r=news.login&mobile=";
    //钱包
    public static final String getMoney =BASE_URL+"index.php?mod=mobile&act=chart";
    //islogin
    public static final String getIsLogin=BASE_URL+"index.php?mod=mobile&act=islogin";
    //HYT额度买入
    public static final String getMRBUG="index.php?mod=mobile&act=trade_sell";
    //HYT额度卖出
    public static final String getMCSell="index.php?mod=mobile&act=trade_buy";
    //MDF额度买入
    public static final String getMDFBUG="index.php?mod=mobile&act=trade_sell_dmf";
    //MDF额度卖出
    public static final String getMDFSELL="index.php?mod=mobile&act=trade_buy_dmf";
    //完善收款信息
    public static final String getSKNew="index.php?mod=mobile&act=profile";
    //交易大厅
    public static final String getBigShow="index.php?mod=mobile&act=gettradebox";
    //交易大厅卖出
    public static final String  getBIGSell="index.php?mod=mobile&act=sell_dmf_trade2";
    //交易大厅买入
    public static final String getBIGBug="index.php?mod=mobile&act=buy_stock_trade2";
    //卖出HYTB
    public static final String getBigHSell="index.php?mod=mobile&act=trade_buy2";
    //买入HYTB
    public static final String getBIGHBug="index.php?mod=mobile&act=trade_sell2";
    //判断是否
    //积分转增
    public static final String getReZZ="https://rwd.xg360.cc/index/integral/givving";
    //积分申请
    public static final String getRESHEN="https://rwd.xg360.cc/index/integral/apply_form";
    //推荐清单
    public static final String getTUI="https://rwd.xg360.cc/index/users/recommend";
    //购物车
    public static final String getShop="https://shop.xg360.cc/app/index.php?i=2&c=entry&m=ewei_shopv2&do=mobile&r=member.cart";
    //收货地址
    public static final String getADDRESS="https://shop.xg360.cc/app/index.php?i=2&c=entry&m=ewei_shopv2&do=mobile&r=member.address";
    //网络图谱
    public static final String getPU="https://shop.xg360.cc/addons/ewei_shopv2/template/mobile/default/notice/member-info.html";
    //大厅卖出的HYTB
    public static final String getBIGHYTBSell="index.php?mod=mobile&act=trade_dmf_buy2";
    //大厅买入的HYTB
    public static final String getBIGHYTBBUY="index.php?mod=mobile&act=trade_dmf_sell2";
    //卖出记录
    public static final String getSelllist="index.php?mod=mobile&act=trade_sell_record";
    //买入记录
    public static final String getBUYLIST="index.php?mod=mobile&act=trade_buy_record";
    //DMF转换金元
    public static final String  getdMFMONEY="index.php?mod=mobile&act=changeDmf";
    //HYT转换金元
    public static final String getHYTMONEY="index.php?mod=mobile&act=changemoney";
    //奖励主页
    public static final String getRewardList="https://rwd.xg360.cc/index/form/reward_center";
    //积分转换DMF
    public static final String getZHUAN="https://rwd.xg360.cc/index/integral/integral_dmf";
    //修改密码
    public static final String getUpPass="index.php?mod=mobile&act=password";
    //修改安全密码
    public static final String getUPSafe="index.php?mod=mobile&act=sepwd";
    //推广
    public static final String getTuiShare="index.php?mod=mobile&act=sharedown";
    //收款信息
    public static final String getBACK="index.php?mod=mobile&act=profile";
    //在线客服
    public static final String getFUWU="http://api.xg360.cc/index.php?mod=mobile&act=contact";
    //奖励列表
    public static final String getREList="https://rwd.xg360.cc/index/reward/reward_list";
    //奖励列表当月统计
    public static final String getData="https://rwd.xg360.cc/index/reward/total_month";
    //大厅交易买入DMF
    public static final String getBUYid="index.php?mod=mobile&act=buy_dmf_trade2";
    //大厅交易卖出DMF
    public static final String getSELLid="index.php?mod=mobile&act=sell_dmf_trade2";
    //大厅交易买入HYT
    public static final String getHYTBYID="index.php?mod=mobile&act=buy_stock_trade2";
    //大厅交易卖出HYT
    public static final String getHYTBYIDSELL="index.php?mod=mobile&act=sell_stock_trade2";
    //奖励列表当月统计
    public static final String getMouth="https://rwd.xg360.cc/index/reward/total_month";
    //实名认证
    public static final String getMAN="index.php?mod=mobile&act=verification";
    //首页轮播图
    //上传认证图片
    public static final String getPhoneImage="index.php?mod=mobile&act=updatecode";
    public static final String SP = "SP";
    public static final String TrueName = "TrueName";
    public static final String Groupname_cn = "Groupname_cn";
    public static final String UserName = "UserName";
    public static final String Shell = "Shell";
    public static final String Uid = "Uid";
    public static final String _UUID = "_UUID";
    public static final String IDCard = "IDCard";
    public static final String dmf_day_price = "dmf_day_price";
    public static final String dmf_day_Today="today";
    public static final String updatemoney="updatemoney";
    public static final String DMFED="dmfed";
    public static final String HYTT_PRICE = "HYTT_PRICE";
    public static final String DMF_PRICE = "DMF_PRICE";
    public static final String PhoneCode = "PhoneCode";
    public static final String DmfNUm="dmf_num";
    public static final String tdmf_num="tdmf_num";
    public static final String STock_mdf="stock_mdf";
    public static final String INTENT_EXTRA_KEY_QR_SCAN = "qr_scan_result";
    public static final int REQ_QR_CODE = 11002; // // 打开扫描界面请求码
    public static final int REQ_PERM_CAMERA = 11003; // 打开摄像头
    public static final int REQ_PERM_EXTERNAL_STORAGE = 11004; // 读写文件
    public static final int REQUEST_CODE_SCAN_GALLERY = 1; // 读写文件
    public static final int DMF=1;
    public static final int HYT=2;
    public static final String idcard="idcard";
    public static final String STOCK="stock";
    public static final String balance="balance";
    public static final String regmoney="regmoney";
    public static final String credit3="credit3";
    public static final String STOCKDMF="stock";
    public static final String balanceDMF="balance";
    public static final String regmoneyDMF="regmoney";
    public static final String credit4="credit4";
    public static final String HYE="HYESTERDAY";
    public static final String HTODAY="HTODAY";
    public static final String HUPDATE="HUPDATE";
    public static final String Hnum="one";
    public static final String HYTED="HYTED";
    public static final String ac_status="ac_status";
    public static final int LOGIN = 101;
    public static final int Main = 102;
    public static final int Lock = 103;
    public static final String WECHAT="WECHAT";
    public static final String WECHATCode="WECHATCode";
    public static final String ALI="ALI";
    public static final String ALICode="ALICode";
    public static final String BACK="BACK";
    public static final String BACKCode="BACKCode";
    public static final String BACKADDRESS="BACKADDRESS";
    public static final String BACKNAME="BACKNAME";
    public static final String BUYNUM="BUYNUM";

}
