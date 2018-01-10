bootstrap-tab
==================
![bootstrap-tab](http://img.blog.csdn.net/20170725090302714?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanJuMTAxMg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

bootstrap-tab组件是对原生的bootstrap-tab组件的封装，方便开发者更方便地使用，主要包含以下功能：
 
 - tab页初始化
 - 关闭tab页
 - 新增tab
 - 显示tab页
 - 获取tab页ID

使用
==================
**Step1 ：引入样式**

```
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
<!--bootstrap-tab样式-->
<link rel="stylesheet" href="../css/bootstrap-tab.css">
```

**Step2：引入脚本**

```
<script src="jquery/jquery-1.8.3.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="../js/bootstrap-tab.js"></script>
```

**Step3：使用**

```javascript
<div id="tabContainer"></div>

<script>
    $("#tabContainer").tabs({
        data: [{
            id: 'home',
            text: '百度一下',
            url: "tab_first.html",
            closeable: true
        }, {
            id: 'admineap',
            text: 'AdminEAP',
            url: "tab_second.html"
        }, {
            id: 'edit',
            text: '编辑人员',
            url: "tab_content.html",
            closeable: true
        }],
        showIndex: 1,
        loadAll: false
    })

    $("#tabContainer").data("tabs").addTab({id: 'test', text: 'addTab', closeable: true, url: 'tab_content.html'})
</script>
```

参数和方法说明
=============
**参数说明**

参数名称|默认值|可选值|说明
----|----|-----|-------------
data|||tab页数据来源（对象列表），包含id,text,url,closeable属性
id|||必须，单个tab的id
text|||必须，单个tab页的标题
url|||必须，单个tab页的内容url
closeable|false|true,false|单个tab页是否可关闭
showIndex| 0||默认显示页的索引
loadAll|true|true,false|true=一次全部加在页面,false=只加在showIndex指定的页面，其他点击时加载，提高响应速度

**方法说明**

方法名称|参数|参数说明|方法说明
--------|-------|-------|---------
init|||tab组件初始化入口方法
builder|data|tab数据|构建tab页的主方法
loadData|||加载tab页的内容，根据loadAll即时加载或者点击时加载
addTab|obj|单个tab的数据|增加一个tab页
showTab|tabId|tab的id|根据id显示tab页
getCurrentTabId|||获取当前活动tab页的ID


相关链接
===============
[如何写一个前端组件(以bootstrap-tab为例)](http://code.admineap.com/blog/bc4d163c5d45ac86015d747cb6ea0019)





