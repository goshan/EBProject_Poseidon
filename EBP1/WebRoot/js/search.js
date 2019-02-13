
function search_page(){
	date_select();
	search();
	$(".search_type_tab").first().click();
}

function date_select(){
	$("#search_start_date_button").click(function(e){
		WdatePicker({
			el: 'search_start_date'
		});
		
		e.preventDefault();
	});
	$("#search_end_date_button").click(function(e){
		WdatePicker({
			el: 'search_end_date'
		});
		
		e.preventDefault();
	});
}

function search(){
	$(".search_type_tab").click(function(e){
		toggle_loading_tip();
		$(".search_type_tab_selected").removeClass("search_type_tab_selected");
		$(this).addClass("search_type_tab_selected");
		$(this).parent().children(".search_arrow").addClass("hidden");
		$(this).next().removeClass("hidden");
		if ($(this).hasClass("search_type_history")){
			$("#search_date_select").css("display", "inline");
		}
		else {
			$("#search_date_select").find("input").val("");
			$("#search_date_select").css("display", "none");
		}
		$("#search_result_panel").text("");
		var url = $(this).attr("href");
		var query = $("#search_query_input").val();
		var start = $("#search_start_date").val();
		var end = $("#search_end_date").val();
		$.ajax({
			method: "post", 
			url: url, 
			data: {
				"query": query, 
				"start": start, 
				"end": end
			}, 
			success: function(data){
				toggle_loading_tip();
				$("#search_result_panel").append(data);
			}
		});
		
		e.preventDefault();
	});
	$("#search_button").click(function(e){
		toggle_loading_tip();
		$("#search_result_panel").text("");
		var url = $(".search_type_tab_selected").attr("href");
		var query = $("#search_query_input").val();
		var start = $("#search_start_date").val();
		var end = $("#search_end_date").val();
		$.ajax({
			method: "post", 
			url: url, 
			data: {
				"query": query, 
				"start": start, 
				"end": end
			}, 
			success: function(data){
				toggle_loading_tip();
				$("#search_result_panel").append(data);
			}
		});
		
		e.preventDefault();
	});
}