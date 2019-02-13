<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>







<div id="reply_connection_cover" class="hidden">
</div>
<div id="reply_connection_detail" class="hidden">
	<div id="reply_connection_detail_head">
		<label>Details</label>
		<a id="reply_connection_detail_close" href="#"></a>
	</div>
	<div id="reply_detail_connection">
		<div id="reply_detail_connection_title">Topic</div>
		<div id="reply_detail_connection_content">
			<span id="reply_detail_connection_fans"></span>
			<span id="reply_detail_connection_text"></span>
		</div>
		<img id="reply_detail_seperate" src="/EBP1/image/replyDetailShadow.png">
	</div>
	<div id="reply_connection_detail_replies">
		<div id="reply_connection_detail_replies_title">Replies</div>
		<div id="reply_connection_detail_replies_content">
		</div>
	</div>
</div>
<div id="reply_panel">
	<div id="reply_content_panel">
		<div id="reply_left_panel">
			<div id="reply_connection_replies">
				<div id="reply_connection_replies_title">Connection Replies</div>
				<img id="reply_corner" src="/EBP1/image/replyCorner.png">
				<div id="reply_connection_replies_content">
					<s:iterator id="reply" value="myReplies">
						<div class="reply_connection_replies_line">
							<span class="reply_connection_replies_reply_id hidden">${reply.replyId}</span>
							<span class="reply_connection_replies_fans">${reply.author}</span>
							<span class="reply_connection_replies_text">${reply.text}</span>
							<span class="reply_connection_replies_time">${reply.dateTime}</span>
							<span class="reply_connection_replies_operate">
								<a class="reply_connection_replies_edit reply_connection_replies_button" href="#"></a>
								<a class="reply_connection_replies_ignore reply_connection_replies_button" href="#"></a>
								<a class="reply_connection_replies_detail reply_connection_replies_button" href="#"></a>
							</span>
						</div>
					</s:iterator>
				</div>
			</div>
			<img id="reply_shadow" src="/EBP1/image/replyShadow.png"/>
		</div>
		<div id="reply_right_panel">
			<div id="reply_connection_edit">
				<div id="reply_connection_edit_title">Edit</div>
				<div id="reply_connection_edit_word_cnt">
					<span class="word_cnt_prefix">Enter only</span>
					<span class="word_cnt_value">140</span>
					words
				</div>
				<div id="reply_connection_edit_reply_id" class="hidden"></div>
				<textarea id="reply_connection_edit_content"></textarea>
				<div id="reply_connection_edit_button">
					<a id="reply_connection_edit_clear" href="#"></a>
					<a id="reply_connection_edit_submit" href="#"></a>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
	$(document).ready(function(e){
		reply_connection_page();
	});
</script>