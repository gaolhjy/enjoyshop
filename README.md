项目简介:
====

轻松购.是一款购物性Android端app.项目主要分为主页、热卖、分类、购物车、我的五大板块.

该app基本上覆盖市面上商机级商城类app的功能,包括购物车、微信及支付宝支付、热门及历史搜索、登录注册、收货地址管理等.

由于接口原因,涉及用户登录后信息获取的,未完成.其他功能均已完成.

还有两个重点:(1)项目代码使用的都是常用但入门门槛较低的方式,比较适合初级android开发者;(2)项目在持续优化中


使用的开源技术:
====

[链接文字](链接地址)
    例子： [Markdown](http://blog.csdn.net/zhaokaiqiang1992)

1.[轮播图](https://github.com/youth5201314/banner)

2.[下拉刷新、加载更多]

3.Gson解析:

4.butterknife

5.批量处理权限:

6.自定义样式的dialog

7.三级联动

8.eventBus

9.沉浸式状态栏

10.greendao

11.ShareSDK

12.okhttpUtils

13.commonAdapter

14.Glide

15.pingpp

16.百度地图定位及Mob天气查询


基本封装与自定义:
====

1.ToolBar的封装

2.FragmentTabHost的自定义

3.一键清除的EditText

4.购物车的加减控件

5.短信验证倒计时的定时器

6.Glide的封装

7.购物车本地缓存

8.对称加密DESUtil

9.读取Json文件的工具类GetJsonDataUtil

10.日志、sp、屏幕、toast工具类封装

主要界面截图:
====
![Image text](https://github.com/gaolh89/cniao5/blob/master/screenshots/pic11.png)
![Image text](https://github.com/gaolh89/cniao5/blob/master/screenshots/pic12.png)
![Image text](https://github.com/gaolh89/cniao5/blob/master/screenshots/pic13.png)

修改完善记录:
====
版本:1.0.0  基本功能完成

版本:1.0.1  splash基本低版本沉浸式状态栏bug修复

版本:1.0.2  (1)splash界面倒计时UI改变.倒计时功能优化;(2)自定义社会化分享UI界面;(3)签名文件放在as中,避免读者再次下载

版本:1.0.3  修复搜索/历史搜索中当搜索或点击已经存在的条目时,数据顺序错乱的bug.将greendao数据库换成集合进行操作

版本:1.0.4  (1)引导页bug修复(2)网络请求成功后不需要使用handle进行发送消息的bug修复(3)将依赖库中最低版本、目标版本与主项目保持一致,减少项目的体积.





