<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>


<div id="search_panel">
	<div id="search_head">
		<input id="search_query_input" type="text">
		<a id="search_button" href="#"></a>
		<div id="search_date_select">
			<span>&nbsp;&nbsp;&nbsp;&nbsp;Start:</span>
			<input id="search_start_date">
			<a id="search_start_date_button" href="#"></a>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;End:</span>
			<input id="search_end_date">
			<a id="search_end_date_button" href="#"></a>
		</div>
	</div>
	<img id="search_corner" src="/EBP1/image/searchCorner.png">
	<div id="search_type_select">
		<a class="search_type_tab search_type_tab_selected" href="/EBP1/searchAction_searchQuery.action?type=source">Library Search</a>
		<img class="search_arrow" style="left: 342px"src="/EBP1/image/searchArrow.png">
		<a class="search_type_tab" href="/EBP1/searchAction_searchQuery.action?type=connection">Connection Search</a>
		<img class="search_arrow hidden" style="left: 628px"src="/EBP1/image/searchArrow.png">
		<a class="search_type_tab" href="/EBP1/searchAction_searchQuery.action?type=user">User Search</a>
		<img class="search_arrow hidden" style="left: 896px"src="/EBP1/image/searchArrow.png">
		<a class="search_type_tab search_type_history" href="/EBP1/searchAction_searchQuery.action?type=history">History Search</a>
		<img class="search_arrow hidden" style="left: 1176px"src="/EBP1/image/searchArrow.png">
	</div>
	<div id="search_result_panel"></div>
</div>

<script>
	$(document).ready(function(){
		search_page();
	});
</script>
