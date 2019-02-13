<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>






<s:if test="type == 'source'">
	<div id="search_result_content">
		<s:iterator id="source" value="allSource">
			<div class="search_result_source">
				<div class="search_result_source_author">${source.user.username } ${source.created_at }</div>
				<div class="search_result_source_content">${source.content }</div>
			</div>
		</s:iterator>
	</div>
</s:if>
<s:elseif test="type == 'connection'">
	<div id="search_result_content">
		<s:iterator id="conn" value="allSearchResult">
			<div class="search_result_connection">
				<div class="search_result_connection_head">
					<a class="search_result_connection_title" href="${conn.resultLink }" target="_blank">${conn.resultTitle }</a>
					<span class="search_result_connection_author">作者：${conn.resultAuthor } 更新：${conn.resultUpdated }</span>
				</div>
				<div class="search_result_connection_content">${conn.resultDescription }</div>
			</div>
		</s:iterator>
	</div>
</s:elseif>
<s:elseif test="type == 'user'">
	<div id="search_result_head">
		<span id="search_result_username">Username</span>
		<span id="search_result_usertype">Type</span>
		<span id="search_result_useremail">E-mail</span>
		<span id="search_result_userremarks">Remarks</span>
	</div>
	<div id="search_result_content" style="height: 370px">
		<%int index = 0; %>
		<s:iterator id="user" value="allUsers">
			<div class="search_result_<%=index%2 == 0 ? "even" : "odd"%>">
				<span class="search_result_username">${user.username }</span>
				<span class="search_result_usertype">${user.type }</span>
				<span class="search_result_useremail">${user.email }</span>
				<span class="search_result_userremarks">${user.user_info }</span>
			</div>
			<%index ++; %>
		</s:iterator>
	</div>
</s:elseif>
<s:elseif test="type == 'history'">
	<div id="search_cover" class="hidden"></div>
	<div id="search_history_detail" class="hidden">
		<div id="search_history_detail_head">
			Details
			<a id="search_history_detail_close" href="#"></a>
		</div>
		<div id="search_history_detail_title"></div>
		<div id="search_history_detail_content"></div>
	</div>
	<div id="search_result_head">
		<span id="search_result_hisTitle">Title</span>
		<span id="search_result_hisContent">Content</span>
		<span id="search_result_hisAuthor">Author</span>
		<span id="search_result_hisDate">Date</span>
		<span id="search_result_hisType">Type</span>
		<span id="search_result_hisOption">Option</span>
	</div>
	<div id="search_result_content" style="height: 370px">
		<%int index = 0; %>
		<s:iterator id="his" value="allReleaseHistorys">
			<div class="search_result_<%=index%2 == 0 ? "even" : "odd"%>">
				<span class="search_result_hisTitle">${his.title }</span>
				<span class="search_result_hisContent">${his.contentSub }</span>
				<span class="search_result_hisAuthor">${his.user.username }</span>
				<span class="search_result_hisDate">${his.date }</span>
				<span class="search_result_hisType">${his.isDraft == 0 ? "已发布" : "未发布"}</span>
				<a class="search_result_hisOption" href="${his.release_id }">查看</a>
			</div>
			<%index ++; %>
		</s:iterator>
	</div>
</s:elseif>
<s:else>
	<img id="search_result_bg_logo" src="/EBP1/image/searchPoseidon.png">
</s:else>


<script>
	$(document).ready(function(){
		search_result_page();
	});
</script>