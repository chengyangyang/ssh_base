package ch.tool.sqltobean;

public class main {

	public static void main(String[] args) {
		String sql = "CREATE TABLE    `trm_comPany`   (" +
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


				SqlToBean.sqlToBean(sql);
				System.out.println("end");

	}
	
}
