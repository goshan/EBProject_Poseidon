
//==================== word counter for textarea =================//
//need: jquery.js
//use:  $("textarea").goshanWorkCnt({   //make word count when typing
//            "word_limit": 140, 
//            "normal_color": "#089bff", 
//            "warning_color": "red", 
//            "word_show_div": $("wordShowDiv"), 
//            "submit_button": $("submitButton")
//      });
//      $("textarea").goshanUpdateWordCnt({  //update word count immediately
//            "word_limit": 140, 
//            "normal_color": "#089bff", 
//            "warning_color": "red", 
//            "word_show_div": $("wordShowDiv"), 
//            "submit_button": $("submitButton")
//      });



(function(jQuery) {
	var limit;
	var normalColor;
	var warningColor;
	var $wordShown;
	var $submit;
	function updateCounter(textArea){
		var len = textArea.value.length;
		var words = limit-len;
		if (words >= 0){
			$wordShown.children(".word_cnt_prefix").text("Enter only");
			$wordShown.children(".word_cnt_value").text(words);
			$wordShown.children(".word_cnt_value").css("color", normalColor);
			$submit.attr("enable", "true");
		}
		else {
			$wordShown.children(".word_cnt_prefix").text("Beyond");
			$wordShown.children(".word_cnt_value").text(0-words);
			$wordShown.children(".word_cnt_value").css("color", warningColor);
			$submit.attr("enable", "false");
		}
	};
	
	jQuery.fn.goshanWordCnt = function(data){
		var textArea = this[0];
		limit = data["word_limit"] || 140;
		normalColor = data["normal_color"] || "blue";
		warningColor = data["warning_color"] || "red";
		$wordShown = data["word_show_div"];
		$submit = data["submit_button"];
		
		updateCounter(textArea);
		
		this.keyup (function(e){
			updateCounter(textArea);
		});
	};
	
	jQuery.fn.goshanUpdateWordCnt = function(){
		var textArea = this[0];
		updateCounter(textArea);
	};
})(jQuery);










//======================= scroll tools for panel ===================//
// modify from jScrollPane
// need: jquery.js, 
//       jquery.jscrollpane.min.js, 
//       jquery.mousewheel.js, 
//       mwheelIntent.js, 
//       jquery.jscrollpane.css
// use:  $("scrollPanel").goshanScroll();   //make this panel scroll, and make .scroll_return button show 




(function(jQuery){
	var returnInterval = 500;
	var updateInterval = 500;
	var value = -100;
	function should_show_return($panel){
		var prefix = $panel.attr("id");
		var $returnButton = $("#"+prefix+"_scroll_return");
		if ($panel.find(".jspPane").length != 0 && $returnButton.length != 0){
			var top = $panel.find(".jspPane").css("top");
			top = top.substring(0, top.indexOf("px"));
			
			if (top < value){
				$returnButton.removeClass("hidden").animate({
					"top": "590px", 
					"opacity": "1"
				}, updateInterval/2);
			}
			else {
				$returnButton.animate({
					"top": "610px", 
					"opacity": "0"
				}, updateInterval/2);
				setTimeout(function() {
					$returnButton.addClass("hidden");
				}, updateInterval/2);
			}
		}
	}
	
	
	jQuery.fn.goshanScroll = function(){
		var $panel = this;
		var prefix = $panel.attr("id");
		var $returnButton = $("#"+prefix+"_scroll_return");
		
		$panel.jScrollPane();
		$returnButton.click(function(e){
			$panel.find(".jspPane").animate({
				"top": "0"
			}, returnInterval);
			$panel.find(".jspDrag").animate({
				"top": "0"
			}, returnInterval);
			
			e.preventDefault();
		});
		$panel.find(".jspTrack").live("hover", function(){
			if (!$panel.find(".jspDrag").hasClass("jspActive")){
				if ($panel.find(".jspTrack").hasClass("jspTrack_bg")){
					$panel.find(".jspTrack").removeClass("jspTrack_bg");
					$panel.find(".jspDrag").css("left", "5px").css("width", "5px");
				}
				else {
					$panel.find(".jspTrack").addClass("jspTrack_bg");
					$panel.find(".jspDrag").css("left", "0").css("width", "10px");
				}
			}
		});
		$("html").mouseup(function(e) {
			if ($panel.find(".jspTrack").length != 0){
				var top = $panel.find(".jspTrack").offset().top;
				var left = $panel.find(".jspTrack").offset().left;
				var width = $panel.find(".jspTrack").width();
				var height = $panel.find(".jspTrack").height();
				var mouseX = e.pageX;
				var mouseY = e.pageY;
				if (!(mouseX > left && mouseX < left+width && mouseY > top && mouseY < top+height)){
					$panel.find(".jspTrack").removeClass("jspTrack_bg");
					$panel.find(".jspDrag").css("left", "5px").css("width", "5px");
				}
			}
		});
		setInterval(function(){
			should_show_return($panel);
		}, updateInterval);
	};
	
	jQuery.fn.goshanScrollUpdate = function(html){
		var api = this.data('jsp');
		api.getContentPane().text("").append(html);
		api.reinitialise();
	};
})(jQuery);