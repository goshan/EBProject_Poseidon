function publish_page(){
	publish_setPoiElem();
	publish_showPoi();
	publish_candidate_scroll();
	publishEditClick();
	publishWordCnt();
	publishClearAndSubmit();
}

function publish_setPoiElem(){
	var colors = ["#076d9b", "#0079bc", "#0282cd", "#048ade", "#0693ee", "#089bff", "#12b4f8", "#1dcdf0", "#27e6e9", "#31ffe1"];
	var z_index = ["-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"];
	$("#publish_poi_content").children(".publish_poi_elem").each(function(index){
		$(this).css("background-color", colors[index]);
		$(this).css("z-index", -1*(index+1));
		$(this).css("left", (index-1)*101+"px");
	});
}

function publish_showPoi(){
	var interval = 50;
	$("#publish_showpoi").hover(function(){
		if (!$(this).hasClass("publish_poiExpand")){
			if ($(this).hasClass("publish_poiHover")){
				$(this).removeClass("publish_poiHover");
				$(this).css("background-position-y", "0");
			}
			else {
				$(this).addClass("publish_poiHover");
				$(this).css("background-position-y", "-40px");
			}
		}
	});
	$("#publish_showpoi").click(function(e){
		if ($(this).hasClass("publish_poiExpand")){
			$(this).removeClass("publish_poiExpand");
			$(this).css("background-position-y", "-40px");
			$(this).parent().children("#publish_poi_content").children(".publish_poi_elem").each(function(index){
				var $this = $(this);
				setTimeout(function(){
					$this.animate({
						"left": (index-1)*101+"px"
					}, interval, "linear");
					setTimeout(function(){
						$this.addClass("hidden");
					}, interval);
				}, (9-index)*interval);
			});
		}
		else {
			$(this).addClass("publish_poiExpand");
			$(this).css("background-position-y", "-80px");
			$(this).parent().children("#publish_poi_content").children(".publish_poi_elem").each(function(index){
				var $this = $(this);
				setTimeout(function(){
					$this.removeClass("hidden");
					$this.animate({
						"left": index*101+"px"
					}, interval, "linear");
				}, interval*index);
			});
		}
		
		e.preventDefault();
	});
}

function publish_candidate_scroll(){
	$("#publish_connection_panel_content").goshanScroll();
	$("#publish_libary_panel_content").goshanScroll();
}

function publishEditClick(){
	$(".publish_candidate_edit").live("click", function(e){
		var title = $(this).parent().children(".publish_candidate_title").attr("value");
		var content = $(this).parent().parent().children(".publish_candidate_content").text();
		$("#publish_title_panel").val(title);
		$("#publish_content_panel").val(content);
		$("#publish_content_panel").goshanUpdateWordCnt();
		
		e.preventDefault();
	});
}

function publishWordCnt(){
	$("#publish_content_panel").goshanWordCnt({
		"word_limit": 140, 
		"normal_color": "#089bff", 
		"warning_color": "red", 
		"word_show_div": $("#publish_edit_word_cnt"), 
		"submit_button": $("#publishEditSubmit")
	});
}

function publishClearAndSubmit(){
	$("#publishEditClear").click(function(e){
		$("#publish_title_panel").val("");
		$("#publish_content_panel").val("");
		$("#publish_content_panel").goshanUpdateWordCnt();
		
		e.preventDefault();
	});
	$("#publishEditSubmit").click(function(e){
		if ($(this).attr("enable") == "true"){
			toggle_loading_tip();
			
			var title = $("#publish_title_panel").val();
			var content = $("#publish_content_panel").val();
			$.ajax({
				method: "post", 
				url: "/EBP1/releaseAction_releaseSinaWeibo.action", 
				data: {
					"title": title, 
					"content": content,
					"request_type":"web"
					
				}, 
				success: function(json){
					toggle_loading_tip();
					var res = json["flag"];
					show_alert(res);
					if (res == "release_success"){
						$("#publishEditClear").click();
					}
				}
			});
		}
		else {
			show_alert("words_num_error");
		}
		
		
		e.preventDefault();
	});
}