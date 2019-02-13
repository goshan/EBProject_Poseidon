<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>







<div id="reply_cover" class="hidden">
</div>
<div id="reply_detail" class="hidden">
	<div id="reply_detail_head">
		<label>Details</label>
		<a id="reply_detail_close" href="#"></a>
	</div>
	<div id="reply_detail_weibo">
		<div id="reply_detail_weibo_title">Weibo</div>
		<div id="reply_detail_weibo_content">
			<span id="reply_detail_weibo_fans"></span>
			<span id="reply_detail_weibo_text"></span>
		</div>
		<img id="reply_detail_seperate" src="/EBP1/image/replyDetailShadow.png">
	</div>
	<div id="reply_detail_replies">
		<div id="reply_detail_replies_title">Replies</div>
		<div id="reply_detail_replies_content">
		</div>
	</div>
</div>
<div id="reply_panel">
	<div id="reply_content_panel">
		<div id="reply_left_panel">
			<div id="reply_all_replies">
				<div id="reply_all_replies_title">Weibo Replies</div>
				<img id="reply_corner" src="/EBP1/image/replyCorner.png">
				<div id="reply_all_replies_content">
					<s:iterator id="comment" value="myComments">
						<div class="reply_all_replies_line">
							<span class="reply_all_replies_status_id hidden">${comment.statusId}</span>
							<span class="reply_all_replies_comment_id hidden">${comment.commentId}</span>
							<span class="reply_all_replies_fans">${comment.commentUser}</span>
							<span class="reply_all_replies_text">${comment.commentText}</span>
							<span class="reply_all_replies_time">${comment.datetime}</span>
							<span class="reply_all_replies_operate">
								<a class="reply_all_replies_match reply_all_replies_button" href="#"></a>
								<a class="reply_all_replies_ignore reply_all_replies_button" href="#"></a>
								<a class="reply_all_replies_post reply_all_replies_button" href="#"></a>
								<a class="reply_all_replies_detail reply_all_replies_button" href="#"></a>
							</span>
						</div>
					</s:iterator>
				</div>
			</div>
			<div id="reply_edit">
				<div id="reply_edit_title">Edit</div>
				<div id="reply_edit_word_cnt">
					<span class="word_cnt_prefix">Enter only</span>
					<span class="word_cnt_value">140</span>
					words
				</div>
				<div id="reply_edit_status_id" class="hidden"></div>
				<div id="reply_edit_comment_id" class="hidden"></div>
				<textarea id="reply_edit_content"></textarea>
				<div id="reply_edit_button">
					<a id="reply_edit_clear" href="#"></a>
					<a id="reply_edit_submit" href="#"></a>
				</div>
			</div>
			<img id="reply_shadow" src="/EBP1/image/replyShadow.png"/>
		</div>
		<div id="reply_right_panel">
			<div id="reply_match_title">Match</div>
			<div id="reply_search">
				<input id="reply_search_input" type="text"/>
				<a id="reply_search_button" href="#"></a>
			</div>
			<div id="reply_match">
				<div id="reply_candidates_title">Connection</div>
				<div id="reply_candidates_content">
				</div>
			</div>
		</div>
	</div>
</div>


<script>
	$(document).ready(function(e){
		reply_weibo_page();
	});
</script>