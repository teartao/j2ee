<%
	/*
	功能：设置支付宝所需有关参数
	接口名称：标准实物双接口
	版本：2.0
	日期：2008-12-25
	作者：支付宝公司销售部技术支持团队
	联系：0571-26888888
	版权：支付宝公司
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>支付宝支付</title>
</head>
<%
	UtilDate date = new UtilDate();//当前系统时间
	String paygateway = "https://www.alipay.com/cooperate/gateway.do?"; //支付接口(不可修改)
	String service = "create_partner_trade_by_buyer";//标准实物接口服务参数(不可修改)
	String sign_type = "MD5";//编码机制，目前采用MD5加密
	String out_trade_no = date.getOrderNum(); //商户网站订单,客户可以自己定义，目前采用时间作为订单号
	String input_charset = "utf-8";//页面编码，此时默认GBK
	
	/*查询合作者ID和安全校验码：登陆签约支付宝账户--->点击”商家服务“，可以看到我的商家服务【如果没有看到，说明合同还没有生效
		  ,需要确认合同生效时间】
	*/
	String partner = ""; //支付宝合作伙伴id (账户内提取)
	String key = ""; //支付宝安全校验码(账户内提取)
	String seller_email = ""; //卖家支付宝帐户，例如：gwl25@126.com

	/******以上是账户信息，以下是商品信息**************************/
	String body = ""; //商品描述，推荐格式：商品名称（订单编号：订单编号）,例如：支付宝（订单号：2008122500120）
	String subject = "AAA" + out_trade_no; //商品名称，如果想显示多个商品名称，可以将多个商品叠加传入。
	String price = "0.01"; //订单总价，范围：0.01～100000000.00（小数点后面最多两位）例如：23.80  
	String quantity = "1";//一般情况可以默认为1，具体可以参看开发文档
	String show_url = "";//商品展示地址，例如：http://www.alipay.com；如果锁定具体商品页面，可以传入（http://www.alipay.com?pid=变量）
	String payment_type = "1";//支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
	String discount = "0"; //折扣 参数范围：-10000000.00<i<10000000.00

	//******物流信息(支付宝目前仅支持三种物流信息)和支付宝通知，一般商城不需要通知，请删除此参数，
	//并且在payment.java里面相应删除参数，同时如果增加物流信息，请在payment.java里面相应增加参数********
	String logistics_type = "EMS";  //物流配送方式：POST(平邮)、EMS(EMS)、EXPRESS(其他快递)
	String logistics_fee = "0.00";  //物流配送费用
	String logistics_payment = "BUYER_PAY";  //物流配送费用付款方式：SELLER_PAY(卖家支付)、BUYER_PAY(买家支付)、BUYER_PAY_AFTER_RECEIVE(货到付款)

	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort();// "http://190.10.2.33:7001/";

	String notify_url = path + "/jsp_shiwu_utf/alipay_notify.jsp";	//付完款后服务器通知的页面(不能在本地测试)
	String return_url = path + "/jsp_shiwu_utf/alipay_return.jsp"; //支付完成后跳转返回的网址URL
	//注意以上两个地址 要用 http://格式的完整路径

	String ItemUrl = Payment.CreateUrl(paygateway, service, sign_type,
			out_trade_no, input_charset, partner, key, seller_email,
			body, subject, price, quantity, show_url, payment_type,
			discount, logistics_type, logistics_fee, logistics_payment,
			return_url);
	//notify_url需要的话请把这个参数加上到上面createurl
%>
<form name="alipaysubmit" method="post" action="https://www.alipay.com/cooperate/gateway.do?_input_charset=utf-8">
	<input type=hidden name="body" value="<%=body%>">

	<input type=text name="logistics_type" value="<%=logistics_type%>"><br/>
	<input type=text name="logistics_fee" value="<%=logistics_fee%>"><br/>
	<input type=text name="logistics_payment" value="<%=logistics_payment%>"><br/>
	<input type=text name="out_trade_no" value="<%=out_trade_no%>"><br/>
	<input type=text name="partner" value="<%=partner%>"><br/>
	<input type=text name="payment_type" value="<%=payment_type%>"><br/>
	<input type=text name="seller_email" value="<%=seller_email%>"><br/>
	<input type=text name="service" value="<%=service%>"><br/>
	<input type=text name="sign" value="<%=ItemUrl%>"><br/>
	<input type=text name="sign_type" value="MD5"><br/>
	<input type=text name="subject" value="<%=subject%>"><br/>
	<input type=text name="price" value="<%=price%>"><br/>
	<input type=text name="quantity" value="<%=quantity%>"><br/>
	<input type=text name="discount" value="<%=discount%>"><br/>
	<input type=text name="show_url" value="<%=show_url%>"><br/>
	<input type=text name="return_url" value="<%=return_url%>"><br/>
	<table>
		<tr>
			<td>
				<input type='button' name='v_action' value='支付宝网上安全即时支付平台' onClick='document.alipaysubmit.submit()'>
			</td>
		</tr>
	</table>
</form>
<body>

</body>
</html>