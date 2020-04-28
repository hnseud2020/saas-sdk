# 如何使用搜云SaaS公共服务平台
我们提供标准化的数字资产场景化服务以及其它关联服务（适合没有技术团队的企业）。<br>
如果您想在自己的应用程序中使用SaaS平台提供的服务接口，首先你需要申请应用授权，并提供一个可信任域名或Ip,申请审核通过后，将分配给您一个应用id和一个应用密钥，请妥善保存谨防泄漏。（[联系我们](http://www.seud.com.cn/h-col-102.html)）<br>
按以下步骤即使用SaaS平台提供的接口服务。<br>
1.在自己的程序中引入saas-sdk(maven)<br>
2.服务接口基于https协议,以下实例代码将展示如何构建一次请求信息：<br>
```
	Url url = new Url("https://testsaas.api.com");
	String requestUrlHost = url.getHost();//主机名称
	String methodName = "/search/company";//接口名称
	String version = "v1.0.0";//接口版本
	String jsonParameters = "{\"keyWord:\":\"搜云科技\"}";//放在request body中的参数
	boolean parametersEncrypt = false;//按接口规范的要求，是否需要对jsonParameters进行加密
	String appId = "";//应用id
	String appSecret = "";
  
	RequestBean bean = AuthorizationFactory.getInstance(requestUrlHost,methodName,jsonParameters,appId,appSecret);
	Map<String,String> requestHeaders = bean.getRequestHeaders();
	String reqeustBody = bean.getReqeustBody();

	//伪代码
	httpClient.setRequestHeaders(requestHeaders);//设置http reqeust-header请求头
	httpClient.setRequestBody();//设置请求内容
	Stirng responseBody = httpClient.post();//发送请求
	//{"retCode":"0000","retMsg":"","data":{...}}
```	
SaaS平台接口服务介绍请移步至[Wiki](https://github.com/hnseud2020/saas-sdk/wiki)
