
function membership_page(){
	membership_select_tag_hover();
	membership_select_tag_switch();
	membership_load_user_data();
	membership_table_scroll();
	membership_close_edit_panel();
	membership_reset_edit_panel();
	membership_save_edit_panel();
	$("#membership_verify").click();
}

function membership_option_hover(){
	$(".membership_verify_button").hover(function(){
		if ($(this).hasClass("membership_tip_hover")){
			$(this).removeClass("membership_tip_hover");
			$(this).parent().children(".membership_verify_tip").addClass("hidden");
		}
		else {
			$(this).addClass("membership_tip_hover");
			$(this).parent().children(".membership_verify_tip").removeClass("hidden");
		}
	});
	$(".membership_edit_button").hover(function(){
		if ($(this).hasClass("membership_tip_hover")){
			$(this).removeClass("membership_tip_hover");
			$(this).parent().children(".membership_edit_tip").addClass("hidden");
		}
		else {
			$(this).addClass("membership_tip_hover");
			$(this).parent().children(".membership_edit_tip").removeClass("hidden");
		}
	});
	$(".membership_delete_button").hover(function(){
		if ($(this).hasClass("membership_tip_hover")){
			$(this).removeClass("membership_tip_hover");
			$(this).parent().children(".membership_delete_tip").addClass("hidden");
		}
		else {
			$(this).addClass("membership_tip_hover");
			$(this).parent().children(".membership_delete_tip").removeClass("hidden");
		}
	});
}

function membership_toggle_cover(){
	if ($("#membership_cover").hasClass("hidden")){
		$("#membership_cover").removeClass("hidden");
	}
	else {
		$("#membership_cover").addClass("hidden");
	}
}

function membership_option_click(){
	$(".membership_verify_button").click(function(e){
		toggle_loading_tip();
		var user_id = $(this).attr("value");
		$.ajax({
			method: "post", 
			url: "/EBP1/userAction_verifyUser.action", 
			data: {
				"user_id": user_id
			}, 
			success: function(json){
				toggle_loading_tip();
				var res = json["flag"];
				show_alert(res);
				if (res == "verify_success"){
					$(".membership_tag_actived").click();
				}
			}
		});
		
		e.preventDefault();
	});
	$(".membership_edit_button").click(function(e){
		var user_id = $(this).attr("value");
		var real_name = $(this).parent().parent().children(".membership_realname_column").text();
		var email = $(this).parent().parent().children(".membership_email_column").text();
		var remarks = $(this).parent().parent().children(".membership_remarks_column").text();
		
		$("#membership_edit_userid").text(user_id);
		$("#membership_edit_realname").val(real_name).attr("init", real_name);
		$("#membership_edit_email").val(email).attr("init", email);
		$("#membership_edit_remarks").val(remarks).attr("init", remarks);
		
		membership_toggle_cover();
		$("#membership_edit_panel").removeClass("hidden");
		
		e.preventDefault();
	});
	$(".membership_delete_button").click(function(e){
		toggle_loading_tip();
		var user_id = $(this).attr("value");
		$.ajax({
			method: "post", 
			url: "/EBP1/userAction_deleteUser.action", 
			data: {
				"user_id": user_id
			}, 
			success: function(json){
				toggle_loading_tip();
				var res = json["flag"];
				show_alert(res);
				if (res == "delete_success"){
					$(".membership_tag_actived").click();
				}
			}
		});
		
		e.preventDefault();
	});
}

function membership_close_edit_panel(){
	$("#membership_edit_close").click(function(e){
		$("#membership_edit_userid").text("");
		$("#membership_edit_realname").val("");
		$("#membership_edit_oldpassword").val("");
		$("#membership_edit_newpassword").val("");
		$("#membership_edit_confirmpassword").val("");
		$("#membership_edit_email").val("");
		$("#membership_edit_remarks").val("");
		
		$("#membership_edit_panel").addClass("hidden");
		membership_toggle_cover();
		
		e.preventDefault();
	});
}

function membership_reset_edit_panel(){
	$("#membership_edit_reset").click(function(e){
		var real_name = $("#membership_edit_realname").attr("init");
		var email = $("#membership_edit_email").attr("init");
		var remarks = $("#membership_edit_remarks").attr("init");
		
		$("#membership_edit_realname").val(real_name);
		$("#membership_edit_oldpassword").val("");
		$("#membership_edit_newpassword").val("");
		$("#membership_edit_confirmpassword").val("");
		$("#membership_edit_email").val(email);
		$("#membership_edit_remarks").val(remarks);
		
		e.preventDefault();
	});
}

function membership_save_edit_panel(){
	$("#membership_edit_save").click(function(e){
		toggle_loading_tip();
		
		var user_id = $("#membership_edit_userid").text();
		var real_name = $("#membership_edit_realname").val();
		var oldpassword = $("#membership_edit_oldpassword").val();
		var newpassword = $("#membership_edit_newpassword").val();
		var confirmpassword = $("#membership_edit_confirmpassword").val();
		var email = $("#membership_edit_email").val();
		var remarks = $("#membership_edit_remarks").val();
		
		$.ajax({
			method: "post", 
			url: "/EBP1/userAction_updateUser.action", 
			data: {
				"user_id": user_id, 
				"realname": real_name, 
				"oldpassword": oldpassword, 
				"newpassword": newpassword, 
				"confirmpassword": confirmpassword, 
				"email": email, 
				"remarks": remarks
			}, 
			success: function(json){
				toggle_loading_tip();
				
				var res = json["flag"];
				show_alert(res);
				if (res == "user_update_success"){
					$("#membership_edit_close").click();
					$(".membership_tag_actived").click();
				}
			}
		});
		
		e.preventDefault();
	});
}

function membership_select_tag_hover(){
	var interval = 500;
	$(".membership_select_tag").hover(function (e){
		if (!$(this).hasClass("membership_tag_actived")){
			$(this).children(".membership_select_num").animate({
				"opacity": "0"
			}, interval);
			$(this).children(".membership_select_icon").animate({
				"left": "0"
			}, interval);
		}
	}, function(e){
		var left = $(this).attr("id") == "membership_verify" ? "115" : "90";
		if (!$(this).hasClass("membership_tag_actived")){
			$(this).children(".membership_select_num").animate({
				"opacity": "1"
			}, interval);
			$(this).children(".membership_select_icon").animate({
				"left": left
			}, interval);
		}
	});
}

function membership_select_tag_switch(){
	$(".membership_select_tag").click(function(e){
		var $this = $(this);
		var $other;
		$(this).parent().children(".membership_select_tag").each(function(index){
			if ($(this).attr("id") != $this.attr("id")){
				$other = $(this);
			}
		});
		
		$this.addClass("membership_tag_actived");
		$other.removeClass("membership_tag_actived");
		$this.css("background-color", "#e67e22");
		$other.css("background-color", "#dbdbdb");
		$this.children(".membership_select_icon").css("opacity", "1");
		$other.children(".membership_select_icon").css("opacity", "0.5");
		$this.children(".membership_select_title").css("color", "#fff");
		$other.children(".membership_select_title").css("color", "#a09c97");
		$this.children(".membership_select_arrow").css("opacity", "1");
		$other.children(".membership_select_arrow").css("opacity", "0");
		
		$other.children(".membership_select_num").css("opacity", "1");
		var left = $other.attr("id") == "membership_verify" ? "115" : "90";
		$other.children(".membership_select_icon").css("left", left);
		
		$("#membership_table_title").text($this.attr("title"));
		
		e.preventDefault();
	});
}

function membership_load_user_data(){
	function make_html_row(index, elem){
		var html_line = "<div class='"+(index%2 == 0 ? "membership_table_evenrow" : "membership_table_oddrow")+" membership_table_tr'>"
		+"<span class='membership_username_column'>"+elem["username"]+"</span>"
		+"<span class='membership_realname_column'>"+elem["realname"]+"</span>"
		+"<span class='membership_usertype_column'>"+elem["type"]+"</span>"
		+"<span class='membership_email_column'>"+elem["email"]+"</span>"
		+"<span class='membership_remarks_column'>"+elem["user_info"]+"</span>"
		+"<span class='membership_option_column'>"
		+"<a class='"+(elem["type"] == "candidate" ? "membership_verify_button" : "membership_edit_button")+"' href='#' value='"+elem["user_id"]+"'></a>"
		+"<a class='membership_delete_button' href='#' value='"+elem["user_id"]+"'></a>"
		+"<img class='"+(elem["type"] == "candidate" ? "membership_verify_tip" : "membership_edit_tip")+" hidden' src='/EBP1/image/"+(elem["type"] == "candidate" ? "membershipVerifyTip" : "membershipEditTip")+".png'/>"
		+"<img class='membership_delete_tip hidden' src='/EBP1/image/membershipDeleteTip.png'/>"
		+"</span>"
		+"</div>";
		
		return html_line;
	}
	
	
	
	$("#membership_verify, #membership_manage").click(function(e){
		var user_type = $(this).attr("id") == "membership_verify" ? "candidates" : "users";
		$.ajax({
			method: "get", 
			url: "/EBP1/userAction_showUsers.action",
			data: {
				"user_type": user_type
			}, 
			success: function(json){
				var error = json["error"];
				var users = json[user_type];
				if (error && error == "not_login"){
					alart("you should login first!");
				}
				else if (error && error == "unknow_request"){
					alart("request error");
				}
				else if (users){
					$("#membership_verify_num").text(json["candidate_num"]+" candidates");
					$("#membership_manage_num").text(json["user_num"]+" users");
					
					var html = "";
					$.each(users, function(index, elem){
						html += make_html_row(index, elem);
					});
					$("#membership_table_body").goshanScrollUpdate(html);
					membership_option_hover();
					membership_option_click();
				}
			}
		});
		e.preventDefault();
	});
}

function membership_table_scroll(){
	$("#membership_table_body").goshanScroll();
}
