<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>





<div id="membership_cover" class="hidden">
</div>

<div id="membership_edit_panel" class="hidden">
	<div id="membership_edit_head">
		<img id="membership_edit_head_img" src="/EBP1/image/membershipEditLogo.png">
		<a id="membership_edit_close" href="#"></a>
	</div>
	<div id="membership_edit_body">
		<div id="membership_edit_userid" class="hidden"></div>
		<div id="membership_edit_realname_line" class="membership_edit_line">
			<span class="membership_edit_label">RealName</span>
			<input id="membership_edit_realname" class="membership_edit_input" init="" type="text"/>
		</div>
		<div id="membership_edit_oldpassword_line" class="membership_edit_line">
			<span class="membership_edit_label">OldPassword</span>
			<input id="membership_edit_oldpassword" class="membership_edit_input" init="" type="password"/>
		</div>
		<div id="membership_edit_newpassword_line" class="membership_edit_line">
			<span class="membership_edit_label">NewPassword</span>
			<input id="membership_edit_newpassword" class="membership_edit_input" init="" type="password"/>
		</div>
		<div id="membership_edit_confirmpassword_line" class="membership_edit_line">
			<span class="membership_edit_label">ConfirmPassword</span>
			<input id="membership_edit_confirmpassword" class="membership_edit_input" init="" type="password"/>
		</div>
		<div id="membership_edit_email_line" class="membership_edit_line">
			<span class="membership_edit_label">E-mail</span>
			<input id="membership_edit_email" class="membership_edit_input" init="" type="text"/>
		</div>
		<div id="membership_edit_remarks_line" class="membership_edit_line">
			<span class="membership_edit_label">Remarks</span>
			<input id="membership_edit_remarks" class="membership_edit_input" init="" type="text"/>
		</div>
		<div id="membership_edit_button_line">
			<a id="membership_edit_reset" href="#"></a>
			<a id="membership_edit_save" href="#"></a>
		</div>
	</div>
</div>

<div id="membership_panel">
	<a id="membership_verify" class="membership_select_tag" href="#" title="Candidate User Management">
		<span id="membership_verify_num" class="membership_select_num">99 candidates</span>
		<img id="membership_verify_icon" class="membership_select_icon" src="/EBP1/image/membershipVerifyLogo.png"/>
		<span id="membership_verify_title" class="membership_select_title">Verify</span>
		<img id="membership_verify_arrow" class="membership_select_arrow" src="/EBP1/image/membershipArrow.png"/>
	</a>
	<a id="membership_manage" class="membership_select_tag" href="#" title="All User Management">
		<span id="membership_manage_num" class="membership_select_num">99 users</span>
		<img id="membership_manage_icon" class="membership_select_icon" src="/EBP1/image/membershipManageLogo.png"/>
		<span id="membership_manage_title" class="membership_select_title">Manage</span>
		<img id="membership_manage_arrow" class="membership_select_arrow" src="/EBP1/image/membershipArrow.png"/>
	</a>
	<div id="membership_table_title">Candidate User Management</div>
	<img id="membership_table_corner" src="/EBP1/image/membershipCorner.png"/>
	<div id="membership_table_attrname" class="membership_table_tr">
		<span class="membership_username_column">UserName</span>
		<span class="membership_realname_column">RealName</span>
		<span class="membership_usertype_column">UserType</span>
		<span class="membership_email_column">E-mail</span>
		<span class="membership_remarks_column">Remarks</span>
		<span class="membership_option_column">Option</span>
	</div>
	<div id="membership_table_body">
	</div>
</div>

<script>
	$(document).ready(function(){
		membership_page();
	});
</script>