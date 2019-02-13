function monitor_page(){
	monitor_scroll();
	monitor_all_replies_click()
}

function monitor_scroll(){
	console.log($("#monitor_scroll"));
	$("#monitor_scroll").goshanScroll();
}
function monitor_show_detail(comment_id, json){
	$("#reply_cover").removeClass("hidden");
	
	var status = json["status"];
	var allComments = json["all_comments"];
	$("#reply_detail_weibo_fans").text(status.userName);
	$("#reply_detail_weibo_text").text(status.text);
	
	var html = "";
	$(allComments).each(function(index, elem){
		html += "<div class='"+(elem.commentId == comment_id ? "reply_detail_replies_item_self" : "reply_detail_replies_item_other")+"'>"
		+"<span class='reply_detail_replies_item_fans'>"+elem.commentUser+"</span>"
		+"<span class='reply_detail_replies_item_text'>"+elem.commentText+"</span>"
		+"</div>";
	});
	$("#monitor_detail").removeClass("hidden");
	
	$("#monitor_detail_monitor_content").goshanScrollUpdate(html);
}

function monitor_all_monitor_click(){
	$(".monitor_all_monitor_detail").click(function(e){
		toggle_loading_tip();
		var comment_id = $(this).parent().parent().children(".reply_all_replies_comment_id").text();
		$.ajax({
			method: "post", 
			url: "/EBP1/replyAction_showWeiboCommentDetails.action", 
			data: {
				"comment_id": comment_id
			}, 
			success: function(json){
				toggle_loading_tip();
				
				var res = json["flag"];
				if (res == "show_detail_success"){
					monitor_show_detail(comment_id, json);
				}
				else {
					show_alert(res);
				}
			}
		});
		
		e.preventDefault();
	});
	$(".monitor_all_monitor_ignore").click(function(e){
		toggle_loading_tip();
		var comment_id = $(this).parent().parent().children(".reply_all_replies_comment_id").text();
		$.ajax({
			method: "post", 
			url: "/EBP1/replyAction_makeWeiboCommentRead.action", 
			data: {
				"comment_id": comment_id
			}, 
			success: function(json){
				toggle_loading_tip();
				
				var res = json["flag"];
				show_alert(res);
				if (res == "make_read_success"){
					$(".workbench_menu_selected").click();
				}
			}
		});
		
		e.preventDefault();
	});
	$(".monitor_all_monitor_detail").click(function(e){
		toggle_loading_tip();
		var comment_id = $(this).parent().parent().children(".reply_all_replies_comment_id").text();
		$.ajax({
			method: "post", 
			url: "/EBP1/replyAction_showWeiboCommentDetails.action", 
			data: {
				"comment_id": comment_id
			}, 
			success: function(json){
				toggle_loading_tip();
				
				var res = json["flag"];
				if (res == "show_detail_success"){
					monitor_show_detail(comment_id, json);
				}
				else {
					show_alert(res);
				}
			}
		});
		
		e.preventDefault();
	});
}
