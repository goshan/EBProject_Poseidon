<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>


<div id="publish">
	<div id="publish_poi">
		<a id="publish_showpoi" href="#"></a>
		<div id="publish_poi_content">
			<s:iterator id="hotword" value="hotWords">
				<span class="publish_poi_elem hidden">${hotword.word}</span>
			</s:iterator>
		</div>
	</div>
	<div id="publish_operate">
		<div id="publish_candidate_panel">
			<div id="publish_candidate_panel_title">Candidate Posts</div>
			<img id="publish_candidate_panel_corner" src="/EBP1/image/publishCorner.png"/>
			<s:if test="shownPageType == 'connection'">
				<div id="publish_connection_panel_content">
					<s:iterator id="conn" value="conns">
						<div class="publish_candidate_topic">
							<div class="publish_candidate_title_line">
								<div class="publish_candidate_title_prefix"></div>
								<a class="publish_candidate_title" href="https://w3-connections.ibm.com/forums/html/topic?id=${conn.uuid}" target="_blank", value="${conn.title}">${conn.titleShow}${conn.titleShow == conn.title ? "" : "......"}</a>
								<a class="publish_candidate_edit" href="#"></a>
							</div>
							<div class="publish_candidate_content">${conn.content}</div>
						</div>
					</s:iterator>
				</div>
				<a id="publish_connection_panel_content_scroll_return" class="hidden" href="#"></a>
			</s:if>
			<s:else>
				<div id="publish_libary_panel_content">
					<s:iterator id="source" value="allSource">
						<div class="publish_candidate_topic">
							<div class="publish_candidate_title_line">
								<div class="publish_candidate_title_prefix"></div>
								<span class="publish_candidate_title", value="">${source.user.username}</span>
								<a class="publish_candidate_edit" href="#"></a>
							</div>
							<div class="publish_candidate_content">${source.content}</div>
						</div>
					</s:iterator>
				</div>
				<a id="publish_libary_panel_content_scroll_return" class="hidden" href="#"></a>
			</s:else>
		</div>
		<div id="publish_edit_panel">
			<div id="publish_edit_panel_title">Edit</div>
			<div id="publish_edit_panel_content">
				<div id="publish_edit_panel_shadow"></div>
				<div id="publish_edit_form">
					<div>
						<div class="publish_input_title">Title</div>
						<div>
							<input id="publish_title_panel" class="input_content" type="text"></input>
						</div>
					</div>
					<div>
						<div class="publish_input_title">
							Content
							<span id="publish_edit_word_cnt">
								<span class="word_cnt_prefix">Enter only</span>
								<span class="word_cnt_value">140</span>
								words
							</span>
						</div>
						<div>
							<textarea id="publish_content_panel" class="input_content"></textarea>
						</div>
					</div>
					<div id="publishEditButton">
						<a id="publishEditClear" href="#"></a>
						<a id="publishEditSubmit" href="#"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
  
<script>
	$(document).ready(function(){
		publish_page();
	});
</script>
