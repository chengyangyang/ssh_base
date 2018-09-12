package ch.tool.sqltobean;

public class main {

	public static void main(String[] args) {
		String sql = "CREATE TABLE  `trm_comPany`   (" +
	              "  `id` varchar(32) NOT NULL COMMENT '用户账号'," +
	              "  `user_no` varchar(50) DEFAULT NULL COMMENT '用户账号'," +
	              "  `company_name` varchar(100) DEFAULT NULL COMMENT '单位名称'," +
	              "  `company_code` varchar(100) DEFAULT NULL COMMENT '统一社会信用代码'," +
	              "  `link_name` varchar(50) DEFAULT NULL COMMENT '联系人名称'," +
	              "  `link_phone` varchar(11) DEFAULT NULL COMMENT '联系人电话'," +
	              "  `address` varchar(200) DEFAULT NULL COMMENT '通讯地址'," +
	              "  `prove_file` text COMMENT '证明材料'," +
	              "  `company_type` varchar(1) DEFAULT NULL COMMENT '用户类型，0：服务方；1：服务供应方'," +
	              "  `applay_status` varchar(5) DEFAULT NULL COMMENT '认证状态'," +
	              "  `applay_date` datetime DEFAULT NULL COMMENT '认证时间'," +
	              "  `audit_date` datetime DEFAULT NULL COMMENT '审核时间'," +
	              "  `audit_opinion` varchar(100) DEFAULT NULL COMMENT '审核意见'," +
	              "  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人'," +
	              "  `create_date` datetime DEFAULT NULL COMMENT '创建时间'," +
	              "  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新人'," +
	              "  `update_date` datetime DEFAULT NULL COMMENT '更新时间'," +
	              "  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0'," +
	              "  PRIMARY KEY (`id`)" +
	              ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业信息  Yn';";


				String sql1 = "CREATE TABLE `trm_sys_menu` (\n" +
						"  `id` varchar(64) NOT NULL COMMENT '编号',\n" +
						"  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',\n" +
						"  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',\n" +
						"  `name` varchar(100) NOT NULL COMMENT '名称',\n" +
						"  `sort` decimal(10,0) NOT NULL COMMENT '排序',\n" +
						"  `href` varchar(2000) DEFAULT NULL COMMENT '链接',\n" +
						"  `target` varchar(20) DEFAULT NULL COMMENT '目标',\n" +
						"  `icon` varchar(100) DEFAULT NULL COMMENT '图标',\n" +
						"  `is_show` char(1) NOT NULL COMMENT '是否在菜单中显示',\n" +
						"  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',\n" +
						"  `create_by` varchar(64) NOT NULL COMMENT '创建者',\n" +
						"  `create_date` datetime NOT NULL COMMENT '创建时间',\n" +
						"  `update_by` varchar(64) NOT NULL COMMENT '更新者',\n" +
						"  `update_date` datetime NOT NULL COMMENT '更新时间',\n" +
						"  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',\n" +
						"  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',\n" +
						"  PRIMARY KEY (`id`),\n" +
						"  KEY `sys_menu_parent_id` (`parent_id`),\n" +
						"  KEY `sys_menu_del_flag` (`del_flag`)\n" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';";

				SqlToBean.sqlToBean(sql1);
				System.out.println("end");

	}
	
}
