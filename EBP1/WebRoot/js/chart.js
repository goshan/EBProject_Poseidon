function chart_page(){
	chart_scroll();
	dynamic_chart_create();
	zombie_chart_create();
	line_chart_create();
	column_chart_create();
}

function chart_scroll(){
	console.log($("#chart_scroll"));
	$("#chart_scroll").goshanScroll();
}
function dynamic_chart_create(){
	//create dynamic chart
	Highcharts.setOptions({ 
		colors: ['#F9E073','#999999','#CCCCCC','#A3F279','#FFD833','#FF9C39'],
		global: {
            useUTC: false
        }
	}); 
    $('#container1').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
    
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function(){
                        	$.ajax({
                        		method: "get", 
                        		url: "/EBP1/dataManageAjaxAction_dynamicChart.action",
                        		data: {
                        		}, 
                        		success: function(json){
                        			var nums = json["newSource"];
                        			var x = (new Date()).getTime(), // current time
                                    y = nums;                  
                        			series.addPoint([x, y], true, true);
                        			
                        		}
                        	});
                         }, 5000);
                    }
                },
                backgroundColor: 'rgba(255, 255, 255, 0)',  
            	plotBorderColor : null,  
            	plotBackgroundColor: null,  
            	plotBackgroundImage:null,  
            	plotBorderWidth: null,  
            	plotShadow: false
            },
            title: {
                text: '实时素材库内容添加'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
           		min:0,
                title: {
                    text: 'Num Of New Source'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 0);
                }
            },
            /*
            plotOptions: {
                series: {
                    marker: {      
                        radius: 4,  //曲线点半径，默认是4
                        symbol: 'circle' //曲线点类型："circle", "square", "diamond", "triangle","triangle-down"，默认是"circle"
                    }
                }
            },
            */
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'New source',
                data: 
                	(function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
    
                    for (i = -20; i <= 0; i++) {
                        data.push({
                            x: time + i * 5000,
                            y: 0
                        });
                    }
                    return data;
                })()
            }]
     });
}
function zombie_chart_create(){
	$.ajax({
		method: "get", 
		url: "/EBP1/dataManageAjaxAction_zombieChart.action",
		data: {
		}, 
		success: function(json){
			var nums = new Array();
			nums[0] = json["NumOfNormal"];
			nums[1] = json["NumOfZombie"];
			chart_create(nums);
		}
	});
}

function line_chart_create(){
    
	var obj=document.getElementById('lineMonths');
	var index=obj.selectedIndex; //序号，取当前选中选项的序号
	var val = obj.options[index].value;
	var param={month:val};
	//alert(val);
	$.ajax({
		method: "get",
		url: "/EBP1/dataManageAjaxAction_lineChart.action",
		data: param, 
		success: function(json){	
			//alert("返回成功");
			var nums = new Array();
			var days = new Array();
			nums[0] = json["0"];
			nums[1] = json["1"];
			nums[2] = json["2"];
			nums[3] = json["3"];
			nums[4] = json["4"];
			nums[5] = json["5"];
			days = json["daysOfMonth"];
			//alert("0:"+nums[0]+"1:"+nums[1]+"2:"+nums[2]+"3:"+nums[3]+"4:"+nums[4]+"5:"+nums[5]+"获得的天数是："+days);
			
			// Create the line chart
			$('#container3').highcharts({
		        chart: {	
		        	backgroundColor: 'rgba(255, 255, 255, 0)',  
		            plotBorderColor : null,  
		            plotBackgroundColor: null,  
		            plotBackgroundImage:null,  
		            plotBorderWidth: null,  
		            plotShadow: false
		    	},
		        title: {
		            text: '微博平均回复留言时间',
		            x: -20
		        },
		        subtitle: {
		            text: '数据来自: IBM软件技术支持微博数据',   
		            x: -20
		        },
		        xAxis: {
		            categories: days
		 
		        },
		        yAxis: {
		            title: {
		                text: 'Average Reply Time(min)'
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#F9E073'
		            }]
		        },
		        tooltip: {
		            valueSuffix: 'min'
		        },
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		        },
		        series: [{
		            name: 'TimeLag',
		            data: [nums[0],nums[1],nums[2], nums[3], nums[4], nums[5]]
		        }]
			});
		}
    });
}
function column_chart_create(){
	var obj=document.getElementById('columnMonths');
	var index=obj.selectedIndex; //序号，取当前选中选项的序号
	var val = obj.options[index].value;
	//alert(val);
	
	var param={month:val};
	$.ajax({
	method: "get",
	url: "/EBP1/dataManageAjaxAction_columnChart.action",
	data: param, 
	success: function(json){
		//alert("返回成功");
		var nums0 = new Array();
		var nums1 = new Array();
		var nums2 = new Array();
		var nums3 = new Array();
		var nums4 = new Array();
		var nums5 = new Array();
		var days = new Array();
		nums0 = json["0"];
		nums1 = json["1"];
		nums2 = json["2"];
		nums3 = json["3"];
		nums4 = json["4"];
		nums5 = json["5"];
		days = json["daysOfMonth"];
		//alert("数组是"+nums0.weiboClass);
		//alert("获得的天数是："+days);
		Highcharts.setOptions({ 
			colors: ['#F9E073','#999999','#CCCCCC','#A3F279','#FFD833','#FF9C39'],
			global: {
	            useUTC: false
	        }
		}); 
		var colors = Highcharts.getOptions().colors;
		
		
		var monitorData = [{
	        name: '微博分析',
	        data: [nums0.weiboClass, nums0.weiboClass,nums1.weiboClass,nums2.weiboClass, nums3.weiboClass, nums4.weiboClass, nums5.weiboClass],
	        color:colors[3]
	    }, {
	        name: '知识库分析',
	        data: [nums0.sourceClass, nums0.sourceClass,nums1.sourceClass, nums2.sourceClass, nums3.sourceClass, nums4.sourceClass, nums5.sourceClass],
	        color:colors[4]
	    }, {
	        name: '负面信息监控',
	        data: [nums0.negativeClass,1,nums1.negativeClass, 1, nums3.negativeClass, nums4.negativeClass,2],
	        color:colors[5]
	    }];
	    
		//create the column chart
		 $('#container4').highcharts({
		        chart: {
		            type: 'column',
		            backgroundColor: 'rgba(255, 255, 255, 0)',  
		            plotBorderColor : null,  
		            plotBackgroundColor: null,  
		            plotBackgroundImage:null,  
		            plotBorderWidth: null,  
		            plotShadow: false
		        },
		        title: {
		            text: '系统工具监控'
		        },
		        xAxis: {
		        	min: 1,
		            title: {
		                text: 'Day of Month'

		            },
		            categories: days
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: 'Number of Dispatch'

		            },
		            stackLabels: {
		                enabled: true,
		                style: {
		                    fontWeight: 'bold',
		                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
		                }
		            }
		        },
		        legend: {
		            align: 'right',
		            x: -70,
		            verticalAlign: 'top',
		            y: 20,
		            floating: true,
		            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
		            borderColor: '#CCC',
		            borderWidth: 1,
		            shadow: false
		        },
		        tooltip: {
		            formatter: function() {
		                return '<b>'+ this.x +'</b><br/>'+
		                    this.series.name +': '+ this.y +'<br/>'+
		                    'Total: '+ this.point.stackTotal;
		            }
		        },
		        plotOptions: {
		            column: {
		                stacking: 'normal',
		                dataLabels: {
		                    enabled: true,
		                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
		                }
		            }
		        },
		        series: monitorData
		    });
	}
	});	
}
function chart_create(nums){
	Highcharts.setOptions({ 
		colors: ['#F9E073','#999999','#CCCCCC','#A3F279','#FFD833','#FF9C39'],
		global: {
            useUTC: false
        }
	}); 
	var colors = Highcharts.getOptions().colors,
	// Build the data arrays	
	categories = ['正常粉丝', '僵尸粉'],
	data = [{
            y: nums[0],
            color:colors[0],
			sliced: true,
            selected: true
        }, {
            y: nums[1],
            color:colors[1]
        }];
	var zombieData = [];
    for (var i = 0; i < data.length; i++) {

        // add zombie data
        zombieData.push({
            name: categories[i],
            y: data[i].y,
            color: data[i].color,
			sliced: data[i].sliced,
			selected: data[i].selected
        });
     }
    
    // create the pie chart
    $('#container2').highcharts({
        chart: {  
            x: 400 ,
            backgroundColor: 'rgba(255, 255, 255, 0)',  
            plotBorderColor : null,  
            plotBackgroundColor: null,  
            plotBackgroundImage:null,  
            plotBorderWidth: null,  
            plotShadow: false
        },
        title: {
            text: '僵尸粉分析',
            x: -20
        },
        tooltip: {
        	 valueSuffix: '个'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#494949',
                    connectorColor: '#494949',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                },
				showInLegend: true

            }
        }, 
		legend: {  
            layout: 'vertical', 
            backgroundColor: '#FFFFFF', 
            align: 'right', 
            verticalAlign: 'top', 
            x: -160, 
            y: 60, 
            floating: true, 
            shadow: true
        },
        series: [{
			type: 'pie',
			name: '数量',
            data: zombieData,
			size: '80%',
			innerSize: '60%'
        }]
	});
}
