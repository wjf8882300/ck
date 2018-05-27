/**
 * 
 */
define(["jquery", "ligerui.ligerDialog"], function($){

		/**
		 * 弹窗
		 * @param type 弹窗类型
		 * @param str  弹窗内容
		 * @param callback 回调函数
		 */
	    var showMsg = function(type, str, callback)
	    {
	        switch (type)
	        {
	            case "success":
	                $.ligerDialog.success(str);
	                break;
	            case "warn":
	                $.ligerDialog.warn(str);
	                break;
	            case "question":
	                $.ligerDialog.question(str);
	                break;
	            case "error":
	                $.ligerDialog.error(str);
	                break;
	            case "confirm":
	                $.ligerDialog.confirm(str, callback);
	                break;
	            case "warning":
	                $.ligerDialog.warning(str, callback);
	                break;
	            case "prompt":
	                $.ligerDialog.prompt(str, callback); 
	                break;
	            case "waitting":
	                $.ligerDialog.waitting(str);
	                setTimeout(function ()
	                {
	                    $.ligerDialog.closeWaitting();
	                }, 2000);
	        }
	    };

	    /**
	     * 弹出错误提示框
	     * @param str  弹窗内容
	     */
	    var errorMsg = function(str) {
	    	showMsg("error", str);
		};
	    
	    /**
	     * 弹出警告提示框
	     * @param str  弹窗内容
	     */
	    var warnMsg = function(str) {
	    	showMsg("warn", str);
		};
	    
	    /**
	     * 弹出成功提示框
	     * @param str  弹窗内容
	     */
	    var successMsg = function(str) {
	    	showMsg("waitting", str);
	    	setTimeout(function () { $.ligerDialog.closeWaitting(); }, 2000);
		}; 
	    
	    /**
	     * 查询数据列表
	     * @param url   地址
	     * @param param 参数
	     * @param callback 回调
	     */
	    var queryList = function(url, param, callback) {
	    	// 加载用户数据
	    	$.ajax({ 
	    	  type:"post", 
	    	  url:url, 
	    	  contentType:"application/json",              
	    	  dataType:"json",  
	    	  data:JSON.stringify(param), 
	    	  cache:false,  
	    	  success:function(data){ 
	    	    //console.log("data=",data.result.data);
	    	  	if(data.result.hasOwnProperty("error")) {
	    	  		errorMsg(data.result.message);
	    	  	}
	    	  	else {
	    	  		if(callback) {
	    	  			callback(data);
	    	  		}
	    	  	}        
	    	  },
	    	  error:function(e) {
	    		  errorMsg("获取服务器数据异常!" + e);
	    	  }
	    	}); 
	    	$("#pageloading").hide(); 
	    }; /*End queryList*/
	    
	    /**
	     * 保存数据
	     * @param url   地址
	     * @param param 参数
	     * @param callback 回调
	     */
	    var saveData = function(url, param, callback) {
	    	// 加载用户数据
	    	$.ajax({ 
	    	  type:"post", 
	    	  url:url, 
	    	  contentType:"application/json",              
	    	  dataType:"json",  
	    	  data:JSON.stringify(param), 
	    	  cache:false,  
	    	  success:function(data){ 
	    	    //console.log("data=",data.result);
	    	  	if(data.result.hasOwnProperty("error")) {
	    	  		errorMsg(data.result.message);
	    	  	}
	    	  	else {
	    	  		if(callback) {
	    	  			callback(data);
	    	  		}
	    	  	}        
	    	  },
	    	  error:function(e) {
	    		  errorMsg("获取服务器数据异常!" + e);
	    	  }
	    	}); 
	    }; /*End saveData*/ 
	    
	    /**
	     * 通过ID查询数据
	     * @param url   地址
	     * @param param 参数
	     * @param callback 回调
	     */
	    var queryById = function(url, param, callback) {
	    	// 加载用户数据
	    	$.ajax({ 
	    	  type:"post", 
	    	  url:url, 
	    	  contentType:"application/json",              
	    	  dataType:"json",  
	    	  data:JSON.stringify(param), 
	    	  cache:false,  
	    	  success:function(data){ 
	    	   //console.log("data=",data.result);
	    	  	if(data.result.hasOwnProperty("error")) {
	    	  		errorMsg(data.result.message);
	    	  	}
	    	  	else {
	    	  		if(callback) {
	    	  			callback(data);
	    	  		}
	    	  	}        
	    	  },
	    	  error:function(e) {
	    		  errorMsg("获取服务器数据异常!" + e);
	    	  }
	    	}); 
	    }; /*End queryById*/
	    
	    var gridCheck = {
	    		/**
	    		 * 存放主键
	    		 */
	    		checkedCustomer:[],

	    		/**
	    		 * 选择全部行
	    		 */
	    		f_onCheckAllRow: function(checked)
	    		{
	    		    for (var rowid in this.records)
	    		    {
	    		        if(checked)
	    		        	gridCheck.addCheckedCustomer(this.records[rowid]['id']);
	    		        else
	    		        	gridCheck.removeCheckedCustomer(this.records[rowid]['id']);
	    		    }
	    		},

	    		/**
	    		 * 选中一行
	    		 */
	    		f_onCheckRow:function(checked, data)
	    		{
	    		    if (checked) gridCheck.addCheckedCustomer(data.id);
	    		    else gridCheck.removeCheckedCustomer(data.id);
	    		},

	    		/**
	    		 * 判断容器中是否已经保存过该主键
	    		 */
	    		findCheckedCustomer:function(id)
	    		{
	    		    for(var i =0;i< gridCheck.checkedCustomer.length;i++)
	    		    {
	    		        if(gridCheck.checkedCustomer[i] == id) return i;
	    		    }
	    		    return -1;
	    		},

	    		/**
	    		 * 像容器中新增主键
	    		 */
	    		addCheckedCustomer:function(id)
	    		{
	    		    if(gridCheck.findCheckedCustomer(id) == -1)
	    		    	gridCheck.checkedCustomer.push(id);
	    		},

	    		/**
	    		 * 从容器中移除主键
	    		 */
	    		removeCheckedCustomer:function(id)
	    		{
	    		    var i = gridCheck.findCheckedCustomer(id);
	    		    if(i==-1) return;
	    		    gridCheck.checkedCustomer.splice(i,1);
	    		}
	    };
	    
	    return {
	    	showMsg:showMsg,
	    	errorMsg:errorMsg,
	    	warnMsg:warnMsg,
	    	successMsg:successMsg,
	    	queryList:queryList,
	    	saveData:saveData,
	    	queryById:queryById,
	    	gridCheck:gridCheck
	    };
});
