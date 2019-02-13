
function option_page(){
	option_select_tag_hover();
	option_select_tag_switch();
	option_load_data();
	$("#option_config").click();
}

function option_select_tag_hover(){
	var interval = 500;
	$(".option_select_tag").hover(function (e){
		if (!$(this).hasClass("option_tag_actived")){
			$(this).children(".option_select_icon").animate({
				"left": "0"
			}, interval);
		}
	}, function(e){
		var left = $(this).attr("id") == "option_config" ? "84px" : "79px";
		if (!$(this).hasClass("option_tag_actived")){
			$(this).children(".option_select_icon").animate({
				"left": left
			}, interval);
		}
	});
}

function option_select_tag_switch(){
	$(".option_select_tag").click(function(e){
		var $this = $(this);
		var $other;
		$(this).parent().children(".option_select_tag").each(function(index){
			if ($(this).attr("id") != $this.attr("id")){
				$other = $(this);
			}
		});
		
		$this.addClass("option_tag_actived");
		$other.removeClass("option_tag_actived");
		$this.css("background-color", "#9b59b6");
		$other.css("background-color", "#dbdbdb");
		$this.children(".option_select_icon").css("opacity", "1");
		$other.children(".option_select_icon").css("opacity", "0.5");
		$this.children(".option_select_title").css("color", "#fff");
		$other.children(".option_select_title").css("color", "#a09c97");
		$this.children(".option_select_arrow").css("opacity", "1");
		$other.children(".option_select_arrow").css("opacity", "0");
		
		var left = $other.attr("id") == "option_config" ? "84px" : "79px";
		$other.children(".option_select_icon").css("left", left);
				
		e.preventDefault();
	});
}

function option_load_data(){
	$(".option_select_tag").click(function(e){
		toggle_loading_tip();
		
		var url = $(this).attr("href");
		$.ajax({
			method: "get", 
			url: url, 
			success: function(data){
				toggle_loading_tip();
				
				$("#option_content").text("");
				$("#option_content").append(data);
			}
		});
		
		e.preventDefault();
	});
}