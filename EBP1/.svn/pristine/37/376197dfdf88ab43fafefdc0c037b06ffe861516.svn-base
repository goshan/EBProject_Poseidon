function reply_connection_page(){
	reply_connection_detail_close();
	reply_connection_scroll();
	reply_connection_word_count();
	reply_connection_clear_and_submit_click();
	reply_connection_replies_click();
}


function reply_connection_scroll(){
	$("#reply_connection_replies_content").goshanScroll();
	$("#reply_connection_detail_replies_content").goshanScroll();
}

function reply_connection_word_count(){
	$("#reply_connection_edit_content").goshanWordCnt({
		"word_limit": 140, 
		"normal_color": "#1abc9c", 
		"warning_color": "red", 
		"word_show_div": $("#reply_connection_edit_word_cnt"), 
		"submit_button": $("#reply_connection_edit_submit")
	});
}

function reply_connection_clear_and_submit_click(){
	$("#reply_connection_edit_clear").click(function(e){
		$("#reply_connection_edit_content").val("");
		$("#reply_connection_edit_content").goshanUpdateWordCnt();
		
		e.preventDefault();
	});
	$("#reply_connection_edit_submit").click(function(e){
		toggle_loading_tip();
		
		var reply_id = $("#reply_connection_edit_reply_id").text();
		var content = $("#reply_connection_edit_content").val();
		$.ajax({
			method: "post", 
			url: "/EBP1/releaseAction_releaseConnectionReplyToWeibo.action", 
			data: {
				"reply_id": reply_id, 
				"content": content
			}, 
			success: function(json){
				toggle_loading_tip();
				
				var res = json["flag"];
				show_alert(res);
				if (res == "reply_success"){
					$("#workbench_reply_connection").click();
				}
			}
		});
		
		e.preventDefault();
	});
}

function reply_connection_detail_close(){
	$("#reply_connection_detail_close").click(function(e){
		$("#reply_connection_detail").addClass("hidden");
		$("#reply_connection_cover").addClass("hidden");
		
		e.preventDefault();
	});
}

function reply_connection_show_detail(reply_id, json){
	$("#reply_connection_cover").removeClass("hidden");
	
	var topic = json["topic"];
	var replies = json["replies"];
	$("#reply_detail_connection_fans").text(topic.title);
	$("#reply_detail_connection_text").text(topic.content);
	
	var html = "";
	$(replies).each(function(index, elem){
		html += "<div class='"+(elem.replyId == reply_id ? "reply_connection_detail_replies_item_self" : "reply_connection_detail_replies_item_other")+"'>"
		+"<span class='reply_connection_detail_replies_item_fans'>"+elem.author+"</span>"
		+"<span class='reply_connection_detail_replies_item_text'>"+elem.text+"</span>"
		+"</div>";
	});
	$("#reply_connection_detail").removeClass("hidden");
	
	$("#reply_connection_detail_replies_content").goshanScrollUpdate(html);
}

function reply_connection_replies_click(){
	$(".reply_connection_replies_edit").click(function(e){
		var reply_id = $(this).parent().parent().children(".reply_connection_replies_reply_id").text();
		var content = $(this).parent().parent().children(".reply_connection_replies_text").text();
		$("#reply_connection_edit_reply_id").text(reply_id);
		$("#reply_connection_edit_content").val(content);
		$("#reply_connection_edit_content").goshanUpdateWordCnt();
		
		e.preventDefault();
	});
	$(".reply_connection_replies_ignore").click(function(e){
		toggle_loading_tip();
		var reply_id = $(this).parent().parent().children(".reply_connection_replies_reply_id").text();
		$.ajax({
			method: "post", 
			url: "/EBP1/replyAction_makeConnectionReplyRead.action", 
			data: {
				"reply_id": reply_id
			}, 
			success: function(json){
				toggle_loading_tip();
				
				var res = json["flag"];
				show_alert(res);
				if (res == "make_read_success"){
					$("#workbench_reply_connection").click();
				}
			}
		});
		
		e.preventDefault();
	});
	$(".reply_connection_replies_detail").click(function(e){
		toggle_loading_tip();
		var reply_id = $(this).parent().parent().children(".reply_connection_replies_reply_id").text();
		$.ajax({
			method: "post", 
			url: "/EBP1/replyAction_showConnectionReplyDetails.action", 
			data: {
				"reply_id": reply_id
			}, 
			success: function(json){
				toggle_loading_tip();
				
				var res = json["flag"];
				if (res == "show_detail_success"){
					reply_connection_show_detail(reply_id, json);
				}
				else {
					show_alert(res);
				}
			}
		});
		
		e.preventDefault();
	});
}