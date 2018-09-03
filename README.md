项目简介:
====

![Image text](https://img.shields.io/badge/%E7%8A%B6%E6%80%81-%E7%BB%B4%E6%8A%A4%E4%B8%AD-green.svg)   ![Image text](https://img.shields.io/badge/%E7%89%88%E6%9C%AC-2.0.0-green.svg)   ![Image text](https://img.shields.io/badge/%E6%9C%80%E8%BF%91%E6%8F%90%E4%BA%A4-2018%2F09%2F03-brightgreen.svg)
![Image text](https://img.shields.io/badge/%E7%BC%96%E8%AF%91%E6%83%85%E5%86%B5-%E6%AD%A3%E5%B8%B8%E7%BC%96%E8%AF%91-green.svg)  ![Image text](https://img.shields.io/badge/as%E7%89%88%E6%9C%AC-3.1.4-brightgreen.svg)




轻松购.是一款购物型Android端app.项目主要分为主页、热卖、分类、购物车、我的五大板块.

该app基本上覆盖市面上商业级商城类app的功能,包括购物车、微信及支付宝支付、热门及历史搜索、登录注册、收货地址管理等.

该项目主要功能已经完成,细节部分正在持续添加与优化中.

还有两个重点:(1)项目代码使用的都是常用但入门门槛较低的方式,比较适合初级android开发者;(2)项目在持续优化中


使用的开源技术(蓝色字体含链接):
====

1.[轮播图](https://github.com/youth5201314/banner)

2.[下拉刷新、加载更多](https://github.com/android-cjj/Android-MaterialRefreshLayout)

3.Gson解析

4.[butterknife](https://github.com/JakeWharton/butterknife)

5.[批量处理权限](https://github.com/mylhyl/AndroidAcp)

6.[自定义样式的dialog](https://github.com/d-max/spots-dialog)

7.[三级联动](https://github.com/Bigkoo/Android-PickerView)

8.[eventBus](https://github.com/greenrobot/EventBus)

9.[沉浸式状态栏](https://github.com/jgilfelt/SystemBarTint)

10.[greendao](https://github.com/greenrobot/greenDAO)

11.ShareSDK

12.[okhttpUtils](https://github.com/hongyangAndroid/okhttputils)

13.[baseAdapter](https://github.com/hongyangAndroid/baseAdapter)

14.[Glide](https://github.com/bumptech/glide)

15.百度地图定位及Mob天气查询


基本封装与自定义:
====

1.ToolBar的封装

2.FragmentTabHost的自定义

3.一键清除的EditText

4.购物车的加减控件

5.短信验证倒计时的定时器

6.Glide的封装

7.自定义圆形头像(可自定义是否有边框、边框宽度及颜色)

8.购物车本地缓存

9.对称加密DESUtil

10.读取Json文件的工具类GetJsonDataUtil

11.日志、sp、屏幕、toast工具类封装

主要界面截图:
====
![Image text](https://github.com/gaolhjy/enjoyshop/blob/master/screenshots/pic11.png)
![Image text](https://github.com/gaolhjy/enjoyshop/blob/master/screenshots/pic12.png)
![Image text](https://github.com/gaolhjy/enjoyshop/blob/master/screenshots/pic13.png)

修改完善记录:
====
版本:1.0.0  基本功能完成

版本:1.0.1  splash基本低版本沉浸式状态栏bug修复

版本:1.0.2  (1)splash界面倒计时UI改变.倒计时功能优化;(2)自定义社会化分享UI界面;(3)签名文件放在as中,避免读者再次下载

版本:1.0.3  修复搜索/历史搜索中当搜索或点击已经存在的条目时,数据顺序错乱的bug.将greendao数据库换成集合进行操作

版本:1.0.4  (1)引导页bug修复(2)网络请求成功后不需要使用handle进行发送消息的bug修复(3)将依赖库中最低版本、目标版本与主项目保持一致,减少项目的体积(4)将项目中使用的第三方库添加链接,方便读者查阅

版本:1.1.0  (1)对toast进行封装;(2)将社会化分享进行封装,可以直接在所有项目项目的所有涉及分享的界面直接调用;(3)对toolbar进行进一步封装,并修复toolbar的相关bug;(4)优化代码

版本:1.1.1  (1)对购物车fragment加载更多下一个索引越界bug进行修复;(2)对分类Fragment获取天气数据异常时闪退的bug进行修复;(3)更换、替换部分资源文件

版本:1.1.2  (1)对购物车fragment跑马灯效果在息屏、开屏出现数据重叠的bug修复;(2)删除ping支付相关废弃代码

版本:1.1.3  (1)自定义圆角头像控件;(2)重新封装Fragment基类;(3)对Glide进行升级,并重新封装,并分离出是普通头像还是用户头像;(4)修改工程名、部分文件名,便于阅读

版本:1.2.0  (1)重新设计注册业务逻辑,使其更符合实际开发. (2)修复项目在as 3.1.3及以上版本无法安装的bug

版本:2.0.0  (1)静态逻辑编写注册、登录、收货地址、添加(修改、删除地址)等业务逻辑,使项目成为完整项目. (2)启动页、引导页主题修改. (3)修复 新建收货地址界面 键盘遮挡的bug.

特别说明:
====
由于注册、登录、收货地址接口问题,以上三部分采取的方式都是模拟登录.
为了学习greendao数据库的操作,这三部分的逻辑均采用greendao的增删改查.整个流程与商业项目一致(在具体的代码中也有强调).这也是大家借此学习greendao的好机会.

项目谈论群:
====

说明:之所以没有使用微信群还是QQ群,主要基于两个原因:
(1)QQ群可以屏蔽,这样大家根据情况进行选择,以便更高效率工作、学习.
(2)QQ群上传资料后,便于保存与查找,也可以临时性和群里其他人私聊.

QQ群号:156250233 (添加请注明 轻松购 )

[![Wercker](https://img.shields.io/badge/QQ%E7%BE%A4-%E7%82%B9%E5%87%BB%E6%B7%BB%E5%8A%A0-green.svg)](<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=142fcd233ceb676fa8d0b0c97f21f23473f9af60b75675cc54aa4d96967b9c40"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="轻松购商场学习群" title="轻松购商场学习群"></a>)


![Image text](https://github.com/gaolhjy/enjoyshop/blob/master/screenshots/QQ%E7%BE%A4%E4%BA%8C%E7%BB%B4%E7%A0%81.png)

关于我:
====

[![Wercker](https://img.shields.io/badge/%E5%85%B3%E4%BA%8E%E6%88%91-CSDN-brightgreen.svg)](http://blog.csdn.net/gaolh89?viewmode=list)


致谢:
====
   
  如果您觉得我的此项目对您有些帮助,您的star就是对我最大的鼓励!


LICENSE
=======

    Copyright 2017 gaoleihua.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



