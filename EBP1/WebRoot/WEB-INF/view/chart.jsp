<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>

  	<div id="chart_panel">
		<div id="chart_head">
			
		</div>
		<img id="chart_corner" src="/EBP1/image/chartCorner.png">
   	</div>		 
  	<div id="chart_scroll">
  		<div id="chart_head_3d">
  		</div>
  		<div id="chart_main_body"> 
	  					<div id="chart1" >
	  						<div id="name_font">Dynamic Chart</div>
			 				<div id="container1" style="min-width: 310px; max-width:1060px; height: 400px; margin: 0 auto"></div>
	  	  				</div>  	
	  	  				<div id="chart2" >
	  	  					<div id="name_font">Pie Chart </div>
	  	  	 				<div id="container2" style="min-width: 310px; max-width:1060px; height: 400px; margin: 0 auto"></div>		
	  	  				</div>
	  	  				
	  	  				<div id="chart3" >
	  	  					<div id="name_font">Line Chart</div>
	  	  	 				<div id="container3" style="min-width: 310px; max-width:1060px; height: 400px; margin: 0 auto"></div>
	  	  				</div>
	  	  				<div id ="lineChartMonth">
	  	  						<div id="lineChartName">Month Select:
		  							<SELECT id="lineMonths" SIZE="1" onchange="line_chart_create();">
										<OPTION VALUE="1">Jan</OPTION>
										<OPTION VALUE="2">Feb</OPTION>
										<OPTION VALUE="3">Mar</OPTION>
										<OPTION VALUE="4">Apr</OPTION>
										<OPTION VALUE="5">May</OPTION>
										<OPTION VALUE="6">June</OPTION>
										<OPTION VALUE="7">July</OPTION>
										<OPTION VALUE="8" selected="selected" >Aug</OPTION>
										<OPTION VALUE="9">Sept</OPTION>
										<OPTION VALUE="10">Oct</OPTION>
										<OPTION VALUE="11">Nov</OPTION>
										<OPTION VALUE="12">Dec</OPTION>
									</SELECT>
								</div>
  						</div>
	  	  				
	  	  				<div id="chart4" >
	  	  					<div id="name_font">Column Chart </div>
	  	  	 				<div id="container4" style="min-width: 310px; max-width:1060px; height: 400px; margin: 0 auto"></div>
	  	  				</div>	
	  	  				<div id ="columnChartMonth">
	  	  					<div id="columnChartName">Month Select:
	  							<SELECT id="columnMonths" SIZE="1" onchange="column_chart_create();">
									<OPTION VALUE="1">Jan</OPTION>
									<OPTION VALUE="2">Feb</OPTION>
									<OPTION VALUE="3">Mar</OPTION>
									<OPTION VALUE="4">Apr</OPTION>
									<OPTION VALUE="5">May</OPTION>
									<OPTION VALUE="6">June</OPTION>
									<OPTION VALUE="7">July</OPTION>
									<OPTION VALUE="8" selected="selected">Aug</OPTION>
									<OPTION VALUE="9">Sept</OPTION>
									<OPTION VALUE="10">Oct</OPTION>
									<OPTION VALUE="11">Nov</OPTION>
									<OPTION VALUE="12">Dec</OPTION>
								</SELECT>
							</div>
  						</div>  				
  		</div>
  		
  	</div>	
   <script>
   	$(document).ready(function(){
			chart_page();
		});
		
	</script>


