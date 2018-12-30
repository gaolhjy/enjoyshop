项目简介:
====

![Image text](https://img.shields.io/badge/%E7%8A%B6%E6%80%81-%E7%BB%B4%E6%8A%A4%E4%B8%AD-green.svg)   ![Image text](https://img.shields.io/badge/%E7%89%88%E6%9C%AC-2.1.0-brightgreen.svg)   ![Image text](https://img.shields.io/badge/%E6%9C%80%E8%BF%91%E6%8F%90%E4%BA%A4%E6%97%B6%E9%97%B4-2018%2F12%2F30-brightgreen.svg)
![Image text](https://img.shields.io/badge/%E7%BC%96%E8%AF%91%E6%83%85%E5%86%B5-%E6%AD%A3%E5%B8%B8%E7%BC%96%E8%AF%91-green.svg)  ![Image text](https://img.shields.io/badge/as%E7%89%88%E6%9C%AC-3.2.1-brightgreen.svg)




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

13.[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

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
版本:2.1.0  (1)整个项目适配器的替换.(2) 首页轮播图进行优化. (3)更新as到官网最新稳定版本.

版本:2.0.1  (1)对购物车fragment代码进行重构. (2)其他代码优化.

版本:2.0.0  (1)静态逻辑编写注册、登录、收货地址、添加(修改、删除地址)等业务逻辑,使项目成为完整项目. (2)启动页、引导页主题修改. (3)修复 新建收货地址界面 键盘遮挡的bug.

版本:1.2.0  (1)重新设计注册业务逻辑,使其更符合实际开发. (2)修复项目在as 3.1.3及以上版本无法安装的bug

版本:1.1.3  (1)自定义圆角头像控件;(2)重新封装Fragment基类;(3)对Glide进行升级,并重新封装,并分离出是普通头像还是用户头像;(4)修改工程名、部分文件名,便于阅读

版本:1.1.2  (1)对购物车fragment跑马灯效果在息屏、开屏出现数据重叠的bug修复;(2)删除ping支付相关废弃代码

版本:1.1.1  (1)对购物车fragment加载更多下一个索引越界bug进行修复;(2)对分类Fragment获取天气数据异常时闪退的bug进行修复;(3)更换、替换部分资源文件

版本:1.1.0  (1)对toast进行封装;(2)将社会化分享进行封装,可以直接在所有项目项目的所有涉及分享的界面直接调用;(3)对toolbar进行进一步封装,并修复toolbar的相关bug;(4)优化代码

版本:1.0.4  (1)引导页bug修复(2)网络请求成功后不需要使用handle进行发送消息的bug修复(3)将依赖库中最低版本、目标版本与主项目保持一致,减少项目的体积(4)将项目中使用的第三方库添加链接,方便读者查阅

版本:1.0.3  修复搜索/历史搜索中当搜索或点击已经存在的条目时,数据顺序错乱的bug.将greendao数据库换成集合进行操作

版本:1.0.2  (1)splash界面倒计时UI改变.倒计时功能优化;(2)自定义社会化分享UI界面;(3)签名文件放在as中,避免读者再次下载

版本:1.0.1  splash基本低版本沉浸式状态栏bug修复

版本:1.0.0  基本功能完成



项目谈论群:
====

说明:之所以没有使用微信群还是QQ群,主要基于两个原因:
(1)QQ群可以屏蔽,这样大家根据情况进行选择,以便更高效率工作、学习.
(2)QQ群上传资料后,便于保存与查找,也可以临时性和群里其他人私聊.

QQ群号:156250233 (添加请注明 轻松购 )

[![Wercker](https://img.shields.io/badge/QQ%E7%BE%A4-%E7%82%B9%E5%87%BB%E6%B7%BB%E5%8A%A0-green.svg)](<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=142fcd233ceb676fa8d0b0c97f21f23473f9af60b75675cc54aa4d96967b9c40"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="轻松购商场学习群" title="轻松购商场学习群"></a>)


![Image text](https://github.com/gaolhjy/enjoyshop/blob/master/screenshots/QQ%E7%BE%A4%E4%BA%8C%E7%BB%B4%E7%A0%81.png)


帮助文档(请先看这里):
====
(一)基础性问题:

Q1.这个项目是什么语言写的?什么项目?

A1:这是一个android项目,商城类.采用java语言编写.目前android项目编写的语言主要有java、kotlin.而这个项目采用的是java语言

Q2.这个项目能运行吗?

A2:可以.这个项目目前还一直在维护中.并且在短时间内,都不会放弃维护.

Q3: 这个项目采用的编译器(IDE)是什么:

A3: 采用的是android studio(简称as).当前采用的是as的v3.1.4版本.

Q4:如果我的as版本不是3.1.4版本,能运行这个项目吗?

A4: 能.完全没问题,但需要修改部分内容.方法如下:

    (1)将build.gradle(project目录)下的classpath 'com.android.tools.build:gradle:3.1.4'中的 3.1.4改成你as一致的版本

    (2)将gradle-wrapper.properties下的distributionUrl=https\://services.gradle.org/distributions/gradle-4.4-all.zip  中4.4改成你as一致的版本.

   另外,需要说明的事,as的3.0.0版本之后,对gradle进行了大量的优化,可能你还需要修改以下内容:

   ①gradle版本必须在4.1或者4.4甚至更高的版本.

   ②build.gradle(project目录)的classpath 'com.novoda:bintray-release:0.8.0' 版本也需要修改.

   ③build.gradle(module目录)的 implementation 进行替换.

   ④如果你是以module的形式添加第三方库的(比如okhttputil的),由于部分第三方库已经放弃维护了,尤其在as 3.0.0版本后,会有很多冲突


关于Q4,涉及gradle的使用,上述修改步骤不仅仅适用 轻松购项目,其他项目也是同样的道理.遇到问题最好的方式就在在网上直接搜索报错信息.
注意注意: 方法(1)(2)一定要最先修改,因为国内大陆的环境需要科学上网才能下载部分资源,如果你一直傻傻的等待下载,有可能1天都下载不下来.

--------
总结:上述问题,其实都不是这个项目本身的问题.比如Q1、Q2、Q3.一个项目,肯定是需要你自己运行看一下的,不要什么都没看就张口问.这个习惯对于编程人员非常致命.
Q4问题涉及的因素特别多,需要你一点一点搜索,或者直接加入轻松购的QQ群问(群号? 上面已经提供了.)



(二)关于本项目的一些问题:

Q1.这个项目后台开源吗?

A1:不开源.现阶段只有android端代码.

Q2:这个项目的定位:

A2:这个项目的定位是0-2年的android编程人员,编码方式中规中矩.

   毕竟,对于0-2年的android编程人员而言,核心任务是功能的实现.如果你连接口回调、源码阅读都不熟悉或者不会,什么代码解耦、拓展性都是扯淡.还是希望0-2年的人务实一点.

   当然,2年以上的android编程人员不适合学习这个项目,因为对你们而言,解耦、封装、设计模式才是你们的核心任务.


Q3:部分接口的使用说明:

A3:这个项目由于涉及部分权限(不是android的权限.是vip权限),注册、登录、收货地址这3个接口无法按照正常的接口调用.

   无法调用这个问题之前困扰了我很久如何解决.后来我采用了greendao数据库的增删改查操作.

   一方面是弥补这3个接口无法正常调用的缺陷.另外一方面也是借此机会学习一下数据库.岂不是一举两得.

    虽然采用的是数据库操作,但整个流程与商业项目一致(在具体的代码中也有强调).大家千万不要纠结这个问题.因为其他地方(比如首页、热卖)的接口都是好的.并且,你学习其他开源项目,99%的项目也有这些问题--除非android端和后台都开源

Q4:我点击了某某地方怎么没反应?

A4: 2种可能,一是我的代码出现了问题.二是我忘记了开发.

    可能性2的概率很小,因为我一直在维护这个项目,但不排除的确有遗漏的可能性.

    至于是哪种可能,请使用编程的思维: 断点调试一下就ok撒.

    你确定了原因后,可能在轻松购QQ群直接@我,或者在github上以Issues的形式提交给我.我会在工作不是特别忙的时候第一时间修复.

--------
总结:关于轻松购项目本身的问题或者疑问,大家可以直接加入群问,或者以issues的形式提交.只有2个目的: (1)解除你的疑惑;(2)让这个项目更好


(三)其他问题:

Q1:如果进轻松购的群? 进群有什么好处?需要遵守什么规则?

A1: (1)如何加入:群号上面已经提供了,亲,再说一下,一定一定要有看文档的习惯.不看文档对于编程人员是非常致命的习惯.

    (2)好处:加群后,你可以直接在群里谈论这个项目,开发这个项目的人(也就是我)在群里,群信息也没有屏蔽.这样便于快速解答疑问.另外,平时我会在群里上传一些android或者其他方面的数据,或者好的资源.
    你加群后,也可以选择屏蔽或者退出,这个都没有关系.

    (3)需要遵守的规则: ①以昵称或者真实姓名的方式 -工作年限 修改备注.如果还是在校生,直接写0年.

    不要求直接写真实姓名哈,但工作年限一定要写.这样便于大家交流.你抛出一个问题,群主或者管理员可以根据你的工作年限具体的更有针对性的回答.
    如果你连工作年限都不想说,这个群你慎入.

    ②本群不要求过于严肃,但也不是注水群.以谈论本项目或者android、编程知识为主.也有一些生活方面的(但低于10%.因为我还没这么闲)

Q2: 关于对本项目的点赞?

A2: 真心的希望大家给我这个项目star.

    这,就是对我最大的鼓励了.不需要现金打赏哈.

Q3:如何star?为什么要star?

A3: (1)如果satr: 首先你需要有一个github账号.登录后,在项目网页的右上角,看到star按钮,点击一下,如果变成了 Unstar,说明star成功了.

    (2)为什么要star:

    ①对你有很大好处:你star后,可以直接在你个人主页查看star过哪些项目.此时star的功能类似于收藏.尤其是你后期要再看,或者查找,但你又不觉得项目名字,或者类似名字的项目很多时,你直接去你主页查看就行

    ②对我有好处: 鼓励我更有动力维护这个项目.此时satr类似于点赞.

    ③对其他人有好处:一般star越多的项目,说明这个项目被认可的程度越高.如果我这个项目star很多,就会有更多的人参与这个项目的谈论 维护中.其实这点,对你也有好处,你也可以一起成长,以及持续学习到更高质量的代码.毕竟:众人抬货火焰高.




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



