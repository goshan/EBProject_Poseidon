
function option_config_page(){
	option_config_edit_click();
	option_config_cancel_edit();
	option_config_back();
	option_config_scroll();
	option_config_save();
}


function option_config_edit_click(){
	$(".option_edit").click(function(e){
		var $this = $(this).parent();
		var $freeze = $this.children(".option_input_content_freeze");
		var $input = $this.children(".option_input_content");
		var val = $freeze.text();
		$input.val(val);
		
		$freeze.css("display", "none");
		$input.css("display", "inline-block");
		$input.focus();
		
		setTimeout(function(){
			$this.addClass("option_input_active");
		}, 10);
		
		e.preventDefault();
	});
}

function option_config_cancel_edit(){
	$("html").click(function(e){		
		var $this = $(".option_input_active");
		
		if ($this.length != 0){
			var top = $this.offset().top;
			var left = $this.offset().left;
			var width = $this.width();
			var height = $this.height();
			var mouseX = e.pageX;
			var mouseY = e.pageY;
			
			if (!(mouseX > left && mouseX < left+width && mouseY > top && mouseY < top+height)){
				var $freeze = $this.children(".option_input_content_freeze");
				var $input = $this.children(".option_input_content");
				var val = $input.val();
				$freeze.text(val);
				
				$input.css("display", "none");
				$freeze.css("display", "inline-block");
				$this.removeClass("option_input_active");
			}
		}
		
		e.preventDefault();
	});
}

function option_config_back(){
	$(".option_back").click(function(e){
		var $this = $(this).parent();
		var $freeze = $this.children(".option_input_content_freeze");
		var $input = $this.children(".option_input_content");
		var val = $freeze.text();
		$input.val(val);
		
		e.preventDefault();
	});
}

function option_config_scroll(){
	console.log($("#option_config_scroll"));
	$("#option_config_scroll").goshanScroll();
}

function option_config_save(){
	$("#option_save").click(function(e){
		var url = $(this).attr("href");
		
		setTimeout(function(){
			toggle_loading_tip();
			
			var acquireTimer = $("#option_acquire_timer_freeze").text();
			var acquireNum = $("#option_acquire_num_freeze").text();
			var weiboNum = $("#option_weibo_num_freeze").text();
			var cacheDir = $("#option_cache_path_freeze").text();
			var toolsDir = $("#option_tools_path_freeze").text();
			var chineseFilter = $("#option_chinese_filter_freeze").text();
			var communitiesNum = $("#option_communities_num_freeze").text();
			var accessToken = $("#option_access_token_freeze").text();
			var weiboAccount = $("#option_weibo_name_freeze").text();
			var sourceCountNum = $("#option_source_num_freeze").text();
			var communityUuid = $("#option_community_uuid_freeze").text();
			var connectionAccount = $("#option_connection_name_freeze").text();
			var connectionPwd = $("#option_connection_password_freeze").text();
			var connectionForumUuid = $("#option_forum_uuid_freeze").text();
			
			$.ajax({
				method: "post", 
				url: url, 
				data: {
					"acquire_timer": acquireTimer, 
					"acquire_num": acquireNum, 
					"weibo_num": weiboNum, 
					"cache_dir": cacheDir, 
					"tools_dir": toolsDir, 
					"chinese_filter": chineseFilter, 
					"communities_num": communitiesNum, 
					"access_token": accessToken, 
					"weibo_account": weiboAccount, 
					"source_count_num": sourceCountNum, 
					"community_uuid": communityUuid, 
					"connection_account": connectionAccount, 
					"connection_password": connectionPwd, 
					"connection_forum_uuid": connectionForumUuid
				}, 
				success: function(json){
					var res = json["flag"];
					toggle_loading_tip();
					show_alert(res);
					$("#option_config").click();
				}
			});
		}, 10);
		
		e.preventDefault();
	});
}
