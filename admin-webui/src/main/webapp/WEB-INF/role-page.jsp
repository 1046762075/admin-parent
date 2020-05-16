<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
<%@include file="/WEB-INF/includex/include-head.jsp" %>
<link rel="stylesheet" href="css/pagination_fireNay.css">
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="jquery/pagination_fireNay.js"></script>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">

    $(function(){
        // 初始化数据
        window.pageNum = 1;
        window.pageSize = 5;
        window.keyword = "";
        // 进来默认刷新页面
        generatePage();
        $("#searchBtn").click(function () {
            // 获取所有关键字赋值变量
            window.keyword = $("#keywordInput").val();
            // 按照用户再输入框中输入的值取SQL、ES查询
            generatePage();
        });

        // 新增按钮的id 点击打开模态框
        $("#showAddModalBtn").click(function () {
            $("#addModal").modal("show");
        });

        $("#saveRoleBtn").click(function () {
            // 找id为 addModal的对象中后代元素name为roleName的值
            var roleName = $.trim($("#addModal [name=roleName]").val());
            $.ajax({
                "url": "role/save.json",
                "type": "post",
                "data": {
                    "name": roleName
                },
                "dataType": "json",
                "success": function (response) {
                    var result = response.result;
                    if(result == "SUCCESS"){
                        layer.msg(response.message)
                        // 重新加载分页
                        window.pageNum = 666666;
                        generatePage();
                    }
                    if(result == "FAILED"){
                        layer.msg("操作失败！请检查添加的角色是否已经存在")
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }
            });
            // 关闭并清理模态框
            $("#addModal").modal("hide");
            $("#addModal [name=roleName]").val("");
        });

        // 先找到所有动态生成的元素所附着的静态元素
        // 第一个参数是事件类型 第二个参数是真正要绑定的事件类型
        $("#rolePageBody").on("click",".pencilBtn",function () {
            $("#editModal").modal("show");
            // 获取当前按钮所在的td的上一个td的值
            var roleName = $(this).parent().prev().text();
            // 这个id 在fillTableBody()中赋值了 将roleId的值放在全局变量来完成更新
            window.roleId = this.id;
            // 回显模态框中的文本值
            $("#editModal [name=roleName]").val(roleName);
        });

        // 发送更新请求
        $("#updateRoleBtn").click(function () {
            // 这里值获取错了会导致内存泄漏
            var roleName = $("#editModal [name=roleName]").val();
            $.ajax({
                "url": "role/update.json",
                "type": "post",
                "data": {
                    "id": window.roleId,
                    "name": roleName
                },
                "dataType": "json",
                "success": function (response) {
                    var result = response.result;
                    if(result == "SUCCESS"){
                        layer.msg(response.message)
                        // 重新加载分页
                        generatePage();
                    }
                    if(result == "FAILED"){
                        layer.msg("更新失败！")
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }
            });
            // 更新请求发完关闭模态框
            $("#editModal").modal("hide");
        });

        // 当用户点击了确认模态框中删除的
        $("#removeRoleBtn").click(function () {
            // 将数组转成JSON
            var requestBody = JSON.stringify(window.roleIdArry);
            $.ajax({
               "url": "role/remove/role/by/id/array.json",
                "type": "post",
                "data": requestBody,
                "contentType" : "application/json;charset=UTF-8",
                "dataType": "json",
                "success": function (response) {
                    var result = response.result;
                    if(result == "SUCCESS"){
                        layer.msg(response.message)
                        // 重新加载分页
                        generatePage();
                    }
                    if(result == "FAILED"){
                        layer.msg("删除失败！" + response.message)
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }
            });

            $("#confirmModal").modal("hide");
        });

        // 当用户点击删除按钮时
        $("#rolePageBody").on("click",".removeBtn",function () {
            // 获取当前按钮所在的td的上一个td的值
            var roleName = $(this).parent().prev().text();
            // 收集各个属性
            var roleArray = [{
                roleId: this.id,
                roleName: roleName,
            }]
            // 收集完各个属性之后弹出模态框
            showConfirmModal(roleArray);
        });

        $("#summaryBox").click(function () {

            var currentStatus = this.checked;
            if(currentStatus == true){
                $("#batchRemoveBtn").prop("disabled","");
            }else{
                $("#batchRemoveBtn").prop("disabled","disabled");
            }
            $(".itemBox").prop("checked",currentStatus);
        })

        // 全选、全不选的反向操作
        $("#rolePageBody").on("click",".itemBox",function(){
            // 获取已经选中的 .itemBox 的数量
            var checkedBoxCount = $(".itemBox:checked").length;
            if(checkedBoxCount > 0){
                $("#batchRemoveBtn").prop("disabled","");
            } else {
                $("#batchRemoveBtn").prop("disabled","disabled");
            }
            // 获取全部.itemBox的数量
            var totalBoxCount = $(".itemBox").length;
            $("#summaryBox").prop("checked", checkedBoxCount >= totalBoxCount && checkedBoxCount > 0);
        });

        // 批量删除
        $("#batchRemoveBtn").click(function () {
            // 存放选中的角色属性 用于批量删除
            var roles = [];
            // 遍历已经选中的按钮
            $(".itemBox:checked").each(function () {
                // 循环获取之前设置的id
                var roleId = this.id;
                // 通过DOM获取角色名称
                var roleName = $(this).parent().next().text();
                roles.push({
                    "roleId": roleId,
                    "roleName": roleName
                });
            });
            if(roles.length < 1){
                layer.msg("技术不错呀,不过你没选中是不能删除的！");
                return;
            }
            // 用户选中 1 一个以上的用户并点击'删除'按钮
            showConfirmModal(roles);
        })

        // 给分配权限按钮绑定单机响应函数
        $("#rolePageBody").on("click",".checkBtn",function(){
            // 当前角色的id存入全局变量 [用于后面查询角色权限]
            window.roleId = this.id;
            $("#assignModal").modal("show");
            // 再模态框中装配树Auth结构的数据
            fillAuthTree();
        });

        // 给分配权限按钮绑定单机响应函数
        $("#assignBtn").click(function () {
            // 准备给服务器发送更改权限id的集合
            var authIds = [];
            // 获取zTreeObj
            var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
            var checkedNodes = zTreeObj.getCheckedNodes();
            for (var i = 0;i < checkedNodes.length;i++){
                // 将每个节点的id放入集合
               authIds.push(checkedNodes[i].id);
            }
            var requestBody = {
                "authIds": authIds,
                // 196行给 windows.roleId 赋值了
                "roleId": [window.roleId]
            }
            requestBody = JSON.stringify(requestBody);
            $.ajax({
                "url": "assign/do/role/assign/auth.json",
                "type": "post",
                "data": requestBody,
                "contentType": "application/json;charset=UTF-8",
                "success": function (response) {
                    var result = response.result;
                    if(result == "SUCCESS"){
                        layer.msg(response.message)
                    }else{
                        layer.msg("请检查您角色的权限！")
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }
            });
            // 最后关闭模态框
            $("#assignModal").modal("hide");
        });
    });

    // ========================================================================================
    // 在模态框中显示Auth树形结构的数据
    function fillAuthTree() {
        var ajaxReturn = $.ajax({
            "url": "assign/get/all/auth.json",
            "type": "post",
            "dataType": "json",
            "async": false
        });
        if(ajaxReturn.status != 200){
            layer.msg("请求出错! 响应状态码：" + ajaxReturn.status + "说明: " + ajaxReturn.statusText);
            return ;
        }
        // 获取服务器返回的list
        var auths = ajaxReturn.responseJSON.data;
        // 让 zTree 帮我们组装数据
        var setting = {
            "data": {
                "simpleData": {
                    // 开启简单json的功能
                    "enable": true,
                    // 使用 categoryId 去关联父节点 不用pId
                    "pIdKey": "categoryId"
                },
                "key": {
                    // 使用tile属性作为节点名称 不用默认的name作为节点属性
                    "name": "title"
                }
            },
            "check": {
                "enable": true
            }
        };
        // 生成树形结构
        $.fn.zTree.init($("#authTreeDemo"), setting, auths);
        // 立即调用zTreeObj对象的方法 让节点默认展开
        var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
        zTreeObj.expandAll(true);
        // 查询已分配的Auth的id组成的数组
        ajaxReturn = $.ajax({
            "url": "assign/get/assigned/auth/id/by/role/id.json",
            "type": "post",
            "data": {
                "roleId": window.roleId,
            },
            "dataType": "json",
            "async": false
        });

        if(ajaxReturn.status != 200){
            layer.msg("请求出错! 响应状态码：" + ajaxReturn.status + "说明: " + ajaxReturn.statusText);
            return ;
        }
        // 根据查出来的数组对应的树形结构的节点进行勾选  从响应结果中获取 authIds
        var authIds = ajaxReturn.responseJSON.data;
        for(var i = 0;i < authIds.length;i++){
            var authId = authIds[i];

            var treeNode = zTreeObj.getNodeByParam("id",authId);
            // 要勾选的节点、true：表示节点需要勾选 false：表示节点不联动[为了避免把不该勾选的勾选上]
            zTreeObj.checkNode(treeNode, true, false);
        }
    }

    function generatePage() {

        // 获取页面数据
        var pageInfo = getPageInfoRemote();
        // 每当刷新的时候取消总的复选框并且禁用删除按钮
        $("#summaryBox").prop("checked", false);
        $("#batchRemoveBtn").prop("disabled","disabled");
        // 填充表格
        fillTableBody(pageInfo);
    }

    // 声明专门的函数 显示确认的模态框 [删除]
    function showConfirmModal(roles) {
        // 清除模态框中的数据
        $("#roleNameDiv").empty();
        $("#confirmModal").modal("show");
        window.roleIdArry = [];
        for(var i = 0;i < roles.length; i++){
            var role = roles[i];
            var roleName = roles[i].roleName;
            $("#roleNameDiv").append(roleName + "<br/>");

            // 往全局变量里面存放要删除的id
            var roleId = role.roleId;
            window.roleIdArry.push(roleId);
        }
    }

    // 远程服务端程序获取 pageInfo 数据
    function getPageInfoRemote() {

        var ajaxResult = $.ajax({
            "url": "role/get/page/info.json",
            "type": "post",
            "data": {
                "pageNum": window.pageNum,
                "pageSize": window.pageSize,
                "keyword": window.keyword
            },
            "dataType": "json",
            "async": false,
        });
        // 判断当前请求响应码是否为200
        var status = ajaxResult.status;
        if (status != 200) {
            layer.msg("Server-side program call failed! Response code: " + status)
            return null;
        }
        // 如果响应状态码是 200, 说明请求处理成功 获取pageInfo
        var resultEntity = ajaxResult.responseJSON;
        var result = resultEntity.result;
        if (result == "FAILED") {
            layer.msg(resultEntity.message);
            return null;
        }
        var pageInfo = resultEntity.data;
        return pageInfo;
    }

    // 填充表格
    function fillTableBody(pageInfo) {

        $("#rolePageBody").empty();
        // 这里清空是为了让没有搜索结果时不显示页面
        $("#Pagination").empty();

        if (pageInfo == null || pageInfo == undefined || pageInfo.list.length == 0) {
            // 向role-page.jsp 中的 rolePageBody ID中追加数据
            $("#rolePageBody").append("<tr><td colspan='4'>Sorry! you do not have permission or the data you are looking for</td></tr>")
            return;
        }

        for (var i = 0; i < pageInfo.list.length; i++) {
            var role = pageInfo.list[i];
            var roleId = role.id;
            var roleName = role.name;

            var numberTd = "<td>" + (i + 1) + "</td>";
            // 这里的id 后面遍历循环删除时用到
            var checkboxTd = "<td><input id='" + roleId + "' class='itemBox' type='checkbox'></td>";
            var roleNameTd = "<td>" + roleName + "</td>";
            // Role页面的第一个按钮 [权限分配]
            var checkBtn = "<button id= '" + roleId + "' type='button' class='btn btn-success btn-xs checkBtn'><i class=' glyphicon glyphicon-check'></i></button>";
            // 把roleId值传递到button按钮的单击响应函数中，在单击响应函数中使用this.id
            var pencilBtn = "<button id='" + roleId + "' type='button' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";
            var removeBtn = "<button id='" + roleId + "' type='button' class='btn btn-danger btn-xs removeBtn'><i class=' glyphicon glyphicon-remove'></i></button>";

            var buttonTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";

            // 组装表格
            var tr = "<tr>" + numberTd + checkboxTd + roleNameTd + buttonTd + "</tr>";

            $("#rolePageBody").append(tr);
        }
        // 生成分页导航条
        generateNavigator(pageInfo);
    }

    // 生成分特页码导航条
    function generateNavigator(pageInfo) {
// 获取总记录数
        var totalRecord = pageInfo.total;

        // 声明相关属性
        var properties = {
            "num_edge_entries": 3,
            "num_display_entries": 4,
            "callback": paginationCallBack,
            "items_per_page": pageInfo.pageSize,
            "current_page": pageInfo.pageNum - 1,
            "prev_text": "上一页",
            "next_text": "下一页"
        }

        // 调用pagination()函数
        $("#Pagination").pagination(totalRecord, properties);
    }

    function paginationCallBack(pageIndex, jQuery) {
        // 修改window对象的pageNum属性
        window.pageNum = pageIndex + 1;

        // 调用分页函数
        generatePage();
        return false;
    }
</script>
<body>
<%@include file="/WEB-INF/includex/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/includex/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button id="batchRemoveBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;" disabled="disabled"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button id="showAddModalBtn" type="button" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="summaryBox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <%-- 所有信息都显示在这 --%>
                            <tbody id="rolePageBody">
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <div id="Pagination" class="pagination"></div>
                                    </ul>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <%@include file="/WEB-INF/modal/role/modal-role-add.jsp" %>
    <%@include file="/WEB-INF/modal/role/modal-role-edit.jsp" %>
    <%@include file="/WEB-INF/modal/role/modal-role-confirm.jsp" %>
    <%@include file="/WEB-INF/modal/role/modal-role-assign-auth.jsp" %>
</body>
</html>
