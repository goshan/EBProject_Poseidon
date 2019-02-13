<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'connectionsReply.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="js/jquery.js"></script>
		<style type="text/css">
body {
	font-family: Verdana;
	font-size: 14px;
	margin: 0;
}

#container {
	margin: 0 auto;
	width: 1000px;
}

#header {
	height: 100px;
	margin-bottom: 10px;
}

#mainContent {
	height: 500px;
	margin-bottom: 5px;
}

#left {
	float: left;
	width: 500px;
	height: 500px;
}

#right {
	float: right;
	width: 495px;
	height: 500px;
}

#bottom {
	width: 100%;
	height: 500px;
}
#topicLeft {
	float: left;
	width: 500px;
	height: 500px;
}

#topicRight {
	float: right;
	width: 495px;
	height: 500px;
}
</style>
		<script type="text/javascript">
	
	function ignoreReply(replyId){
		var params = {
			replyId : replyId
		};
		$.ajax( {
				url : 'connectionsReplyAction_ignoreReply.action',
				data : params,
				error : function() {
						alert("Wrong Request!!!");
				},
				success : function(data) {
					var result=data["result"];
					
					if(result == "success"){
						$.ajax( {
							url : 'connectionsReplyAction_getReplies.action',
							error : function() {
								alert("Wrong Request!!!");
							},
						success : function(data) {
							var replies=data["replies"];
							var content="";
							$(replies).each(function(index,item){
								var replyId=item.replyId;
								var topicId=item.topicId;
								content +="<tr><td>"+(index+1)+"."+"</td><td>"+item.author+"</td><td>"+item.text+"</td><td>"+item.dateTime+"</td><td><input type='button' value='忽略' onclick='ignoreReply("+replyId+");'/></td>	<td><input type='button' value='详情' onclick='displayDetails("+topicId+");'/></td><td><input type='button' value='发布' onclick='publishReply("+item.text+");'/></td></tr>";
							});
							$('#mytable').html(content);
						}
						});
						
					}
					else{
						alert("fail to ingore the reply!");
					}
					
				}
		});
		
	}
	function displayDetails(topicId){
		var details="<div><h3>主题：</h3></div><table frame='hsides' Rules='rows' id='details_topic' cellspacing='10px' cellpadding='10px'></table><div><h3>回复：</h3></div><table frame='hsides' rules='rows' id='details_replies' cellspacing='10px' cellpadding='10px'></table>";
		$('#right').html(details);
	
		var params = {
			topicId : topicId
		};
		$.ajax( {
				url : 'connectionsReplyAction_displayDetailsOfTopic.action',
				data : params,
				error : function() {
						alert("Wrong Request!!!");
				},
				success : function(data) {
					var topic=data["topic"];
					var item="<tr><td>"+topic.title+"</td><td>"+topic.dateTime+"</td></tr>";
					
					$('#details_topic').html(item);
				}
		});
		
		
		$.ajax( {
				url : 'connectionsReplyAction_displayDetailsOfReplies.action',
				data : params,
				error : function() {
						alert("Wrong Request!!!");
				},
				success : function(data) {
						var replies=data["replies"];
						var content="";
						
					$(replies).each(function(index,item){
						content +="<tr><td>"+(index+1)+"."+"</td><td>"+item.author+"</td><td>"+item.text+"</td><td>"+item.dateTime+"</td></tr>";
					});
					$('#details_replies').html(content);
					
				}
		});
		
	}
	function publishReply(){
	
		var text=$('#editReply').val();
		
		var params={text:encodeURI(text)};
		$.ajax( {
				url : 'connectionsReplyAction_publishReply.action',
				data : params,
				error : function() {
						alert("Wrong Request!!!");
				},
				success : function(data) {
					var result=data["result"];
					alert(result);					
				}
		});
	}
	function editReply(text){
		var area="<textarea id='editReply' rows='20' cols='50'>"+text+"</textarea><br/><input type='button' value='发布' onclick='publishReply();'/>";
		$('#right').html(area);
	}
	function removeTopic(topicId){
		var params={topicId:topicId};
		$.ajax( {
				url : 'connectionsReplyAction_removeTopic.action',
				data : params,
				error : function() {
						alert("Wrong Request!!!");
				},
				success : function(data) {
					var result=data["result"];
					
					if(result == "success"){
						$.ajax( {
							url : 'connectionsReplyAction_getTopics.action',
							error : function() {
								alert("Wrong Request!!!");
							},
						success : function(data) {
							var topics=data["topics"];
							var content="";
							$(topics).each(function(index,item){
								content +="<tr><td>"+(index+1)+"."+"</td><td>"+item.title+"</td><td>"+item.dateTime+"</td><td><input type='button' value='删除' onclick='removeTopic('"+item.topicId+");'/></td></tr>";
							});
							$('#topicTable').html(content);
						}
						});
						
					}
					else{
						alert("fail to ingore the reply!");
					}				
				}
		});
	}
	
	function createTopic(){
		var title=$('#topicTitle').val();
		var content=$('#topicContent').val();
		
		
		var params = {
				title : encodeURI(title),
				content:encodeURI(content)
		}
		$.post(
				'connectionsReplyAction_createTopic.action',
				params,
				function(data) {
					var result=data["result"];
						alert(result);
				}
		);
							
	}
	
</script>
	</head>
	<body>
		<div id="container">
			<div id="header">
				<h1>   
					Connections回复   
				</h1>
			</div>
			<div id="mainContent">
				<div><h2>最新回复</h2></div>
				<div id="left">
					<div id="replies_connections"
						style="overflow-x: auto; overflow-y: auto; height: 100%; width: 100%;">
						<table frame="hsides" Rules="rows" id="mytable" cellspacing="10px" cellpadding="10px">
							<c:forEach var="reply" items="${myReplies}" varStatus="status"> 
         						<tr>
         							<td>${status.count}.</td>
         							<td>${reply.author }</td>
         							<td>${reply.text }</td>
         							<td>${reply.dateTime }</td>
         							<td><input type="button" value="忽略" onclick="ignoreReply('${reply.replyId}');"/></td>
         							<td><input type="button" value="详情" onclick="displayDetails('${reply.topicId}');"/></td>
         							<td><input type="button" value="编辑" onclick="editReply('${reply.text}');"/></td>
         						</tr>
							</c:forEach> 							
						</table>
					</div>
				</div>
				<div id="right"
					style="overflow-x: auto; overflow-y: auto; height: 100%; width: 495px;">
				</div>
			</div>
  		<div id="bottom">
  			<div><h2>创建或撤销主题</h2></div>
  			<div id="topicLeft" style="overflow-x: auto; overflow-y: auto; height: 100%; width: 450px;">
  				<table frame="hsides" Rules="rows" id="topicTable" cellspacing="10px" cellpadding="10px">
					<c:forEach var="topic" items="${myTopics}" varStatus="status"> 
         				<tr>
         				<td>${status.count}.</td>
         				<td>${topic.title }</td>
         				<td>${topic.dateTime }</td>
         				<td><input type="button" value="删除" onclick="removeTopic('${topic.topicId}');"/></td>
         				</tr>
					</c:forEach> 							
				</table>
				
  			</div>
			<div id="topicRight" style="overflow-x: auto; overflow-y: auto; height: 100%; width: 450px;">
				<input type="text" id="topicTitle"/>(title)<br/>
				<textarea id="topicContent" rows="20" cols="50"></textarea><br/>
				<input type="button" value="创建主题" onclick="createTopic();" />
			</div>  			
  		</div>
	</div>
	</body>
</html>
