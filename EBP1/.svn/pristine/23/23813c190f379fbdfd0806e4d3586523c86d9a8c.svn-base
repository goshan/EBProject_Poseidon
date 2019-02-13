
function search_result_page(){
	search_show_history_detail();
	search_close_detail();
	$("#search_result_content").goshanScroll();
}

function search_show_history_detail(){
	$(".search_result_hisOption").click(function(e){
		toggle_loading_tip();
		
		var id = $(this).attr("href");
		$.ajax({
			method: "get", 
			url: "/EBP1/searchAction_searchReleaseHistoryById.action", 
			data: {
				"id": id
			}, 
			success: function(json){
				toggle_loading_tip();
				
				var res = json["flag"];
				if (res == "search_success"){
					$("#search_cover").removeClass("hidden");
					$("#search_history_detail_title").text(json.release_history.title);
					$("#search_history_detail_content").text(json.release_history.content);
					$("#search_history_detail").removeClass("hidden");
				}
				else {
					show_alert(res);
				}
			}
		});
		
		e.preventDefault();
	});
}

function search_close_detail(){
	$("#search_history_detail_close").click(function(e){
		$("#search_history_detail_title").text("");
		$("#search_history_detail_content").text("");
		$("#search_cover").addClass("hidden");
		$("#search_history_detail").addClass("hidden");
		
		e.preventDefault();
	});
}