define(["jquery", "Common", "Constant", "ligerui.ligerTab", "ligerui.ligerLayout", "ligerui.ligerMenu", "bootstrap.min", "matrix"], function($, Common, Constant){
	
	var tab = null;
	var accordion = null;
	var tree = null;
	var tabItems = [];

	var init = function() {
				
		$("[to-url]").each(function () {			
			$(this).bind("click",function(){
					var nav = $(this).attr("nav-n");
					var sn = nav.split(",");
					f_addTab(sn[1], sn[0], $(this).attr("to-url"));
					$("[to-url]").each(function () {
						if($(this).hasClass("onsubmenu")) {
							$(this).removeClass("onsubmenu"); 
						}
					});
					$(this).addClass("onsubmenu"); 					
			});
		});
		
	    //布局
	    $("#layout1").ligerLayout({height: '100%',space:4, onHeightChanged: f_heightChanged });

	    var height = $(".l-layout-center").height();
	    
	    //menu
	    $("#sidebar").height(height);

	    //Tab
	    tab = $("#framecenter").ligerTab({
	        height: height
	    });

	    var lf = $('#submenu');
		if (lf.length != 0) {
			var submenu = lf.eq(0).find('li').eq(0).find('ul');
			if (submenu.length != 0) {
				submenu.find('li').filter(':first').children('a')
				.click();
			} else {
				lf.eq(0).find('li').removeClass('active').filter(':first')
						.addClass('active').children('a')
						.click();
			}
		}
	}

	function f_heightChanged(options)
	{  
	    if (tab)
	        tab.addHeight(options.diff);
	    if (accordion && options.middleHeight - 24 > 0)
	        accordion.setHeight(options.middleHeight - 24);
	}
	
	function f_addTab(tabid, text, url)
	{
	    tab.addTabItem({
	        tabid: tabid,
	        text: text,
	        url: url
	    });
	}
	
	return {
		init:init
	};
//	require(['domReady!'], function (document) {
//		init();
//    });
});