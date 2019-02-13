<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ibm.p1.entity.Parameter"%>




<div id="option_panel">
	<a id="option_config" class="option_select_tag" href="/EBP1/manageAction_getAllAcquireInfo.action">
		<img id="option_config_icon" class="option_select_icon" src="/EBP1/image/optionConfigLogo.png"/>
		<span id="option_config_title" class="option_select_title">Config</span>
		<img id="option_config_arrow" class="option_select_arrow" src="/EBP1/image/optionTableArrow.png"/>
	</a>
	<a id="option_tools" class="option_select_tag" href="/EBP1/manageAction_getAllToolsStatus.action">
		<img id="option_tools_icon" class="option_select_icon" src="/EBP1/image/optionToolsLogo.png"/>
		<span id="option_tools_title" class="option_select_title">Tools</span>
		<img id="option_tools_arrow" class="option_select_arrow" src="/EBP1/image/optionTableArrow.png"/>
	</a>
	<div id="option_content"></div>
</div>


<script>
	$(document).ready(function(){
		option_page();
	});
</script>