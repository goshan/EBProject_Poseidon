
function login_page(){
	panel_switch();
	login_submit_check();
	login_input_focus();
	signup_submit_check();
	signup_input_focus();
	showTime();
	setInterval("showTime()", 10000);
	flipLogo();
	changeBg();
	
}

var timer;
function loading(on){
	var interval = 1500;
	var length = 432-82;
	var full_len = 3;
	var slide = function(){
		$("#loading").css("opacity", "0.1").removeClass("hidden").animate({
			"top": 82+length/full_len+"px", 
			"opacity": "1"
		}, interval/full_len, "linear").animate({
			"top": 82+(full_len-1)*length/full_len+"px"
		}, (full_len-2)*interval/full_len, "linear").animate({
			"top": "432px", 
			"opacity": "0.1"
		}, interval/full_len, "linear").animate({
			"top": "82px"
		}, 0);
	};
	if (on){
		slide();
		timer = setInterval(function(){
			slide();
		}, interval);
	}
	else {
		clearInterval(timer);
		$("#loading").addClass("hidden").css("top", "96");
	}
}

function login_submit_check(){
	$("#login_submit").click(function(e){
		$("#login_username_empty").addClass("hidden");
		$("#login_password_empty").addClass("hidden");
		$("#login_username_error").addClass("hidden");
		$("#login_password_error").addClass("hidden");
		
		var username = $("#login_username_input").val();
		var password = $("#login_password_input").val();
		if (!username){
			$("#login_error_tip").text("you must fill...").removeClass("opacity");
			$("#login_username_empty").removeClass("hidden");
		}
		else if (!password){
			$("#login_error_tip").text("you must fill...").removeClass("opacity");
			$("#login_password_empty").removeClass("hidden");
		}
		else {
			loading(true);
			$.ajax({
				type: "POST", 
				url: "/EBP1/userAction_login.action", 
				data: {"user.username": username, "user.password": password,"request_type":"web"}, 
				success: function(json_response){
					var flag = json_response["flag"];
					if (flag == "not_exist"){
						$("#login_error_tip").text("user does not existed").removeClass("opacity");
						$("#login_username_error").removeClass("hidden");
						$("#login_username_input").val("");
						$("#login_password_input").val("");
					}
					else if (flag == "password_wrong"){
						$("#login_error_tip").text("password is not correct").removeClass("opacity");
						$("#login_password_error").removeClass("hidden");
						$("#login_password_input").val("");
					}
					else if (flag == "false_to_create_access_token"){
						$("#login_error_tip").text("false_to_create_access_token").removeClass("opacity");
						$("#login_password_error").removeClass("hidden");
						$("#login_password_input").val("");
					}
					else if (flag == "unknown_request_type"){
						$("#login_error_tip").text("unknown_request_type").removeClass("opacity");
						$("#login_password_error").removeClass("hidden");
						$("#login_password_input").val("");
					}
					else if (flag == "login_success"){
						location.href = "/EBP1/communicationAction_afterLogin.action"; 
					}
					loading(false);
				}
			});
		}
		
		e.preventDefault();
	});
}

function login_input_focus(){
	$("#login_username_input").focus(function(){
		if (!$("#login_username_empty").hasClass("hidden")){
			$("#login_error_tip").addClass("opacity");
			$("#login_username_empty").addClass("hidden");
		}
		if (!$("#login_username_error").hasClass("hidden")){
			$("#login_error_tip").addClass("opacity");
			$("#login_username_error").addClass("hidden");
		}
	});
	$("#login_password_input").focus(function(){
		if (!$("#login_password_empty").hasClass("hidden")){
			$("#login_error_tip").addClass("opacity");
			$("#login_password_empty").addClass("hidden");
		}
		if (!$("#login_password_error").hasClass("hidden")){
			$("#login_error_tip").addClass("opacity");
			$("#login_password_error").addClass("hidden");
		}
	});
}

function signup_submit_check(){
	$("#signup_submit").click(function(e){
		$("#signup_username_empty").addClass("hidden");
		$("#signup_password_empty").addClass("hidden");
		$("#signup_password_error").addClass("hidden");
		$("#signup_confirm_password_empty").addClass("hidden");
		$("#signup_realname_empty").addClass("hidden");
		$("#signup_email_empty").addClass("hidden");
		
		var userid = "";
		var username = $("#signup_username_input").val();
		var password = $("#signup_password_input").val();
		var confirm_password = $("#signup_confirm_password_input").val();
		var realname = $("#signup_realname_input").val();
		var email = $("#signup_email_input").val();
		var comment = $("#signup_info_input").val();
		
		if (!username){
			$("#signup_error_tip").text("you must fill...").removeClass("opacity");
			$("#signup_username_empty").removeClass("hidden");
		}
		else if (!password){
			$("#signup_error_tip").text("you must fill...").removeClass("opacity");
			$("#signup_password_empty").removeClass("hidden");
			$("#signup_password_input").val("");
			$("#signup_confirm_password_input").val("");
		}
		else if (!confirm_password){
			$("#signup_error_tip").text("you must fill...").removeClass("opacity");
			$("#signup_confirm_password_empty").removeClass("hidden");
		}
		else if (!realname){
			$("#signup_error_tip").text("you must fill...").removeClass("opacity");
			$("#signup_realname_empty").removeClass("hidden");
		}
		else if (!email){
			$("#signup_error_tip").text("you must fill...").removeClass("opacity");
			$("#signup_email_empty").removeClass("hidden");
		}
		else {
			loading(true);
			$.ajax({
				type: "POST", 
				url: "/EBP1/userAction_signup.action", 
				data: {
					"user.username": username, 
					"user.password": password, 
					"user.confirmPassword": confirm_password, 
					"user.realname": realname, 
					"user.email": email, 
					"user.user_info": comment
				}, 
				success: function(json_response){
					var flag = json_response["flag"];
					if (flag == "username_conflict"){
						$("#signup_error_tip").text("user has existed").removeClass("opacity");
						$("#signup_username_error").removeClass("hidden");
						$("#signup_username_input").val("");
						$("#signup_password_input").val("");
						$("#signup_confirm_password_input").val("");
					}
					else if (flag == "password_not_match"){
						$("#signup_error_tip").text("passwords not match").removeClass("opacity");
						$("#signup_password_error").removeClass("hidden");
						$("#signup_password_input").val("");
						$("#signup_confirm_password_input").val("");
					}
					else if (flag == "signup_success"){
						location.href = "/EBP1/communicationAction_afterLogin.action";
					}
					loading(false);
				}
			});
		}
		
		e.preventDefault();
	});
}

function signup_input_focus(){
	$("#signup_form").children(".signup_form_line").each(function(index){
		var $line = $(this);
		$line.children("input").focus(function(){
			if (index < 5 && !$line.children(".empty").hasClass("hidden")){   //index < 5 means: not check momment
				$("#signup_error_tip").addClass("opacity");
				$line.children(".empty").addClass("hidden");
			}
			if (index < 2 && !$line.children(".error").hasClass("hidden")){    //index < 2 means: only check username and password 
				$("#signup_error_tip").addClass("opacity");
				$line.children(".error").addClass("hidden");
			}
		});
	});
}

function panel_switch(){
	var status = 0;
	var interval = 500;
	$(".switch_button").live("click", function(e){
		if (status == 0){
			$("#login_form").animate({
				"opacity": "0",
				"right": "-=50px"
			}, interval).css("z-index", "1");
			$("#signup_form").delay(interval).animate({
				"opacity": "1", 
				"right": "+=50px", 
				"zindex": "10"
			}, interval).css("z-index", "10");
		}
		else {
			$("#signup_form").animate({
				"opacity": "0", 
				"right": "-=50px"
			}, interval).css("z-index", "1");
			$("#login_form").delay(interval).animate({
				"opacity": "1", 
				"right": "+=50px"
			}, interval).css("z-index", "10");
		}
		status = 1-status;
		
		e.preventDefault();
	});
}

function showTime(){
	var dateTime = new Date();
	var year = dateTime.getFullYear();
	var month = dateTime.getMonth()+1;
	var day = dateTime.getDate();
	var hour = dateTime.getHours();
	var minute = dateTime.getMinutes();
	if (month < 10){
		month = "0" + month;
	}
	if (day < 10){
		day = "0" + day;
	}
	if (hour < 10){
		hour = "0" + hour;
	}
	if (minute < 10){
		minute = "0" + minute;
	}
	var date = month+"/"+day+"/"+year;
	var time = hour + ":" + minute;
	
	$("#date").text(date);
	$("#time").text(time);
}

function flipLogo() {
	var interval = 225;
	// 在前面显示的元素，隐藏在后面的元素
	var eleBack = null, eleFront = null,
	    // 纸牌元素们 
	    eleList = $(".list");

	// 确定前面与后面元素
	var funBackOrFront = function() {
	    eleList.each(function() {
	        if ($(this).hasClass("out")) {
	            eleBack = $(this);
	        } else {
	            eleFront = $(this);
	        }
	    });
	};

	$("#ibm_logo").hover(function() {
	    // 切换的顺序如下
	    // 1. 当前在前显示的元素翻转90度隐藏, 动画时间225毫秒
	    // 2. 结束后，之前显示在后面的元素逆向90度翻转显示在前
	    // 3. 完成翻面效果
		funBackOrFront();
	    eleFront.addClass("out").removeClass("in");
	    setTimeout(function() {
	        eleBack.addClass("in").removeClass("out");    
	    }, interval);
	});
}

function changeBg(){
	var interval = 1000;
	var index = 1;
	$("#ibm_logo").click(function(e){
		$("#bg_"+index).fadeOut();
		if (index == 5){
			index = 1;
		}
		else {
			index ++;
		}
		$("#bg_"+index).fadeIn();
		e.preventDefault();
	});
}


