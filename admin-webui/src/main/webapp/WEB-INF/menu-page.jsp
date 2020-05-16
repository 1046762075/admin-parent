<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/includex/include-head.jsp" %>
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">

    $(function () {

        generateTree();
        // 添加-打开模态框
        $("#treeDemo").on("click",".addBtn",function () {
            $("#menuAddModal").modal("show");
            // 后面依据这个id进行赋值
            window.id = this.id;
            return false;
        });
        // 更新-打开模态框
        $("#treeDemo").on("click",".editBtn",function () {
            // 后面依据这个id进行赋值
            window.id = this.id;
            $("#menuEditModal").modal("show");
            // 获取zTreeObj
            var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
            // 用户来搜的属性名 属性值
            var key = "id";
            var value = window.id;
            // 利用zTreeAPI获取用户当前点击的节点值然后回显 [注意这里的 currentNode 值来自数据库]
            var currentNode = zTreeObj.getNodeByParam(key, value);
            $("#menuEditModal [name=name]").val(currentNode.name);
            $("#menuEditModal [name=url]").val(currentNode.url);
            // 找到icon这个name 根据currentNode的值就能进行回显
            $("#menuEditModal [name=icon]").val([currentNode.icon]);
            return false;
        });
        // 保存
        $("#menuSaveBtn").click(function () {
            // 手机表单中用户输入的数据
            var name = $.trim($("#menuAddModal [name=name]").val());
            var url = $.trim($("#menuAddModal [name=url]").val());
            // 定位到被选中的那个
            var icon = $.trim($("#menuAddModal [name=icon]:checked").val());

            $.ajax({
                "url": "menu/save.json",
                "type": "post",
                "data": {
                    "pid": window.id,
                    "name": name,
                    "url": url,
                    "icon": icon
                },
                "dataType": "json",
                "success": function (response) {
                    var result = response.result;
                    if(result == "SUCCESS"){
                        layer.msg(response.message);
                        generateTree();
                    }else{
                        layer.msg(response.message);
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }
            });
            $("#menuAddModal").modal("hide");
            // 不传任何参数 就相当于用户点击了一下
            $("#menuResetBtn").click();
        })


        // 更新
        $("#menuEditBtn").click(function () {
            var name = $("#menuEditModal [name=name]").val();
            var url = $("#menuEditModal [name=url]").val();
            // 定位到被选中的那个
            var icon = $("#menuEditModal [name=icon]:checked").val();

            $.ajax({
                "url": "menu/update.json",
                "type": "post",
                "data": {
                    "id": window.id,
                    "name": name,
                    "url": url,
                    "icon": icon
                },
                "dataType": "json",
                "success": function (response) {
                    var result = response.result;
                    if(result == "SUCCESS"){
                        layer.msg(response.message);
                        generateTree();
                    }else{
                        layer.msg(response.message);
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }
            });
            $("#menuEditModal").modal("hide");
        })

        // 给 x 绑定单机响应函数
        $("#treeDemo").on("click", ".removeBtn", function () {
            // 后面依据这个id进行赋值
            window.id = this.id;
            $("#menuConfirmModal").modal("show");
            // 获取zTreeObj
            var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
            // 用户来搜的属性名 属性值
            var key = "id";
            var value = window.id;
            // 利用zTreeAPI获取用户当前点击的节点值然后回显 [注意这里的 currentNode 值来自数据库]
            var currentNode = zTreeObj.getNodeByParam(key, value);
            $("#removeNodeSpan").html("【<i class='" + currentNode.icon + "'></i>" + currentNode.name + "】");
            return false;
        })
        // OK按钮的响应函数
        $("#confirmBtn").click(function () {
            $.ajax({
                "url": "menu/remove.json",
                "type": "post",
                "data": {
                    "id": window.id
                },
                "success": function (response) {
                    var result = response.result;
                    if(result == "SUCCESS"){
                        layer.msg(response.message);
                        generateTree();
                    }else{
                    	layer.msg("对不起！您无法对这个菜单进行更改！");
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }
            })
            $("#menuConfirmModal").modal("hide");
        })
    });

    // 生成树形结构
    function generateTree(){
        $.ajax({
            "url": "menu/get/whole/tree.json",
            "type": "post",
            "dataType": "json",
            "success": function (response) {
                var result = response.result;
                if(result == "SUCCESS"){
                    var setting = {
                        "view": {
                            "addDiyDom": myAddDiyDom,
                            "addHoverDom": myAddHoverDom,
                            "removeHoverDom": myRemoveHoverDom
                        },
                        // 他会用去zTreeNode中 firenay这个属性名
                        "data": {
                            "key":{
                                "url": "firenay"
                            }
                        }
                    };
                    // 获取返回的JSON数据
                    var zNodes = response.data;

                    $.fn.zTree.init($("#treeDemo"), setting, zNodes)
                }else{
                    layer.msg(response.message);
                }
            }
        });

        // 给节点换图标
        function myAddDiyDom(treeId, treeNode) {
            // zTree生成节点id的规则
            // 例子：treeDemo_7_ico
            // ul标签的id_前节点的序号_功能
            // ul标签的id_当前节点的序号  这个部分可以通过访问 zTreeNode的Id得到
            var spanId = treeNode.tId + "_ico";
            // 1.根据控制图标的span标签找到这个span标签
            // 2.删除Class
            // 3.修改class
            $("#" + spanId).removeClass().addClass(treeNode.icon);
        }

        // 鼠标移入节点范围时 添加按钮组
        function myAddHoverDom(treeId, treeNode) {
            // 按钮组的标签结构 <span><a><i></i></></span>
            // 按钮组出现的位置：节点中 treeDemo_n_a 超链接的后面

            var btnGroupId = treeNode.tId + "_btnGrp";
            var anchorId = treeNode.tId + "_a";
            // 要是以前加过了就不再追加了
            if($("#" + btnGroupId).length > 0){
                return;
            }

            var btnHTML = "";
            var addBtn = "<a id = '" + treeNode.id + "' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
            var editBtn = "<a id = '" + treeNode.id + "' class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='修改子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
            var removeBtn = "<a id = '" + treeNode.id + "' class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='删除子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
            // 根节点
            var level = treeNode.level;
            if(level == 0){
                btnHTML = btnHTML + addBtn;
            }
            // 分支节点
            if(level == 1){
                btnHTML = addBtn + " " + editBtn;
                var length = treeNode.children.length;
                // 如果当前节点没有子节点 则它自己也可以移除
                if(length == 0){
                    btnHTML = btnHTML + " " +  removeBtn;
                }
            }
            // 叶子节点
            if(level == 2){
                btnHTML = editBtn + " " + removeBtn;
            }
            // 在超链接后面追加元素
            $("#" + anchorId).after("<span id='" + btnGroupId + "'>" + btnHTML + "</span>");
        }
        // 鼠标离开节点范围时 添加按钮组
        function myRemoveHoverDom(treeId, treeNode) {
            // 这边移除这个元素
            var btnGroupId = treeNode.tId + "_btnGrp";
            $("#" + btnGroupId).remove();
        }
    }
</script>
<body>
<%@ include file="/WEB-INF/includex/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/includex/include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float: right; cursor: pointer;" data-toggle="modal"
                         data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <!-- 这个ul标签是zTree动态生成的节点所依附的静态节点 -->
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
    <%@include file="/WEB-INF/modal/menu/modal-menu-add.jsp" %>
    <%@include file="/WEB-INF/modal/menu/modal-menu-edit.jsp" %>
    <%@include file="/WEB-INF/modal/menu/modal-menu-confirm.jsp" %>
</body>
</html>