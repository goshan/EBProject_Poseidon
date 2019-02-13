
function option_tools_page(){
	option_tools_show_detail();
	option_tools_run();
	option_tools_mession();
}

function option_tools_show_detail(){
	$(".option_tools_detail").click(function(e){
		var $dialog = $(this).parent().children(".option_tools_detail_content");
		if ($dialog.hasClass("hidden")){
			$(".option_tools_detail_content").addClass("hidden");
			$dialog.removeClass("hidden");
		}
		else {
			$dialog.addClass("hidden");
		}
		
		e.preventDefault();
	});
}

function option_tools_run(){
	$(".option_tools_run").click(function(e){
		toggle_loading_tip();
		
		var url = $(this).attr("href");
		$.ajax({
			method: "post", 
			url: url, 
			success: function(json){
				toggle_loading_tip();
				
				var res = json["flag"];
				show_alert(res);
				if (res == "tools_success"){
					$("#option_tools").click();
				}
			}
		});
		
		e.preventDefault();
	});
	$(".option_tools_hold").click(function(e){
		show_alert("duplicate_running");
		
		e.preventDefault();
	});
}

function option_tools_mession(){
	$(".option_tools_open, .option_tools_close").click(function(e){
		toggle_loading_tip();
		
		var url=$(this).attr("href");
		$.ajax({
			method: "post", 
			url: url, 
			success: function(json){
				toggle_loading_tip();
				
				var res = json["flag"];
				show_alert(res);
				if (res == "tools_success"){
					$("#option_tools").click();
				}
			}
		});
		
		e.preventDefault();
	});
}