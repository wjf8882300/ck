
/**
 * 常量
 */
define(function() {
	return {
		
		/**
		 * 公用
		 */
		constants: {
			
		},
		
		/**
		 * 用户管理
		 */
		user: {
			list:"/html/user/list.html", //列表页面
			detail:"/html/user/detail.html", //详细页面
			queryAll:"/ck/user/queryAllUser", //列表页面表格数据
			save:"/ck/user/saveUser",
			del:"/ck/user/deleteUser",
			queryById:"/ck/user/queryById"
		},
		
		/**
		 * 角色管理
		 */
		role: {
			list:"/html/role/list.html",
			detail:"/html/role/detail.html",
			queryAll:"/ck/role/queryAll",
			save:"/ck/role/saveRole",
			del:"/ck/role/deleteRole",
			queryById:"/ck/role/queryById",
			grant:"/html/role/grant.html"			
		},
		
		/**
		 * 菜单管理
		 */
		menu: {
			list:"/html/menu/list.html",
			detail:"/html/menu/detail.html",
			queryAll:"/ck/menu/queryAll",
			save:"/ck/menu/saveMenu",
			del:"/ck/menu/deleteMenu",
			queryById:"/ck/menu/queryById",
			queryByRoleId:"/ck/menu/queryByRoleId"
		}
	};
});