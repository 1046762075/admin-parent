<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">
  $(function () {
    $("#btn5").click(function () {
        layer.msg("哈哈哈")
    })

      $("#btn4").click(function () {
        // 准备要发送的数据
        var student = {
          "stuId": 5,
          "stuName": "firenay",
          "address": {
            "province": "湖南",
            "city": "长沙",
            "street": "后瑞"
          },
          "subjectList": [
            {
              "subjectName": "JavaSE",
              "subjectScore": 100
            }, {
              "subjectName": "SSM",
              "subjectScore": 520
            }
          ],
          "map": {
            "k1": "v1",
            "k2": "v2"
          }
        };
        // 将JSON对象转换为JSON字符串
        var requestBody = JSON.stringify(student);

        // 发送Ajax请求
        $.ajax({
          "url": "send/compose/object.json",
          "type": "post",
          "data": requestBody,
          "contentType": "application/json;charset=UTF-8",
          // 返回类型 ResultEntity<Student>
          "dataType": "json",
          //  关闭异步模式
          "async" : false,
          "success": function (result) {
            console.log(result);
          },
          "error": function (result) {
            console.log(result);
          }
        });
      });

      $("#btn3").click(function () {

        // 准备好要发送到服务器端的数组
        var array = [5, 2, 0];
        console.log('数组的长度为：' + array.length);
        // 将JSON数组转换为JSON字符串
        var requestBody = JSON.stringify(array);
        console.log('转换后[空格也会算上]：' + requestBody.length);
        $.ajax({
          "url": "send/array/three.html",
          "type": "post",
          "data": requestBody,
          "contentType": "application/json;charset=UTF-8",
          "dataType": "text",
          "success": function (result) {
            console.log(result);
          },
          "error": function (result) {
            console.log(result);
          }
        });
      });

      $("#btn2").click(function () {
        $.ajax({
          "url": "send/array/two.html",
          "type": "post",
          "data": {
            "array[0]": 5,
            "array[1]": 2,
            "array[2]": 0
          },
          "dataType": "text",
          "success": function (result) {
            console.log(result);
          },
          "error": function (result) {
            console.log(result);
          }
        });

      });

      $("#btn1").click(function () {
        $.ajax({
          "url": "send/array/one.html",
          "type": "post",
          "data": {
            "array": [5, 2, 0]
          },
          "dataType": "text",
          "success": function (result) {
            console.log(result)
          },
          "error": function (result) {
            console.log(result)
          }
        });
      })
    })
</script>
<body>
<center>
    <a href="testSSM.html">测试SSM整合环境</a><br/><br/>
    <button id="btn1">array 的方式传值</button><br/><br/>
    <button id="btn2">array[x] 的方式传值</button><br/><br/>
    <button id="btn3">JSON数组转换后</button><br/><br/>
    <button id="btn4">用泛型接收JSON数据</button><br/><br/>
    <button id="btn5">layer测试</button><br/><br/>
    <a href="admin/to/main/page.html">LEAVE</a><br/><br/>
</center>
</body>
</html>
