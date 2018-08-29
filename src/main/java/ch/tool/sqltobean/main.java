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


				String sql1 = "CREATE TABLE `trm_gp_fruit` (\n" +
						"  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
						"  `addr` varchar(150) DEFAULT NULL,\n" +
						"  `admin` varchar(2) DEFAULT NULL,\n" +
						"  `checkup` varchar(200) DEFAULT NULL,\n" +
						"  `comTime` varchar(200) DEFAULT NULL,\n" +
						"  `companyName` varchar(200) DEFAULT NULL,\n" +
						"  `contacts` varchar(200) DEFAULT NULL,\n" +
						"  `createTime` datetime DEFAULT NULL,\n" +
						"  `creator` varchar(255) DEFAULT NULL,\n" +
						"  `deputy` varchar(200) DEFAULT NULL,\n" +
						"  `domain` varchar(200) DEFAULT NULL,\n" +
						"  `fruitForm` varchar(200) DEFAULT NULL,\n" +
						"  `fruitState` varchar(200) DEFAULT NULL,\n" +
						"  `industry` varchar(200) DEFAULT NULL,\n" +
						"  `introduce` longtext,\n" +
						"  `isActive` varchar(10) NOT NULL,\n" +
						"  `loginId` varchar(255) NOT NULL,\n" +
						"  `manId` varchar(200) DEFAULT NULL,\n" +
						"  `manIntroduce` longtext,\n" +
						"  `manPhone` varchar(200) DEFAULT NULL,\n" +
						"  `manTyep` varchar(200) DEFAULT NULL,\n" +
						"  `manager` varchar(200) DEFAULT NULL,\n" +
						"  `money` varchar(200) DEFAULT NULL,\n" +
						"  `name` varchar(200) NOT NULL,\n" +
						"  `nature` varchar(200) DEFAULT NULL,\n" +
						"  `oneTime` datetime DEFAULT NULL,\n" +
						"  `opinion` varchar(200) DEFAULT NULL,\n" +
						"  `org` varchar(200) DEFAULT NULL,\n" +
						"  `owner` varchar(200) DEFAULT NULL,\n" +
						"  `ownerPhone` varchar(200) DEFAULT NULL,\n" +
						"  `paddr` varchar(150) DEFAULT NULL,\n" +
						"  `phone` varchar(200) DEFAULT NULL,\n" +
						"  `postcode` varchar(200) DEFAULT NULL,\n" +
						"  `priceStage` varchar(10) DEFAULT NULL,\n" +
						"  `proType` varchar(200) DEFAULT NULL,\n" +
						"  `stage` varchar(200) DEFAULT NULL,\n" +
						"  `state` varchar(10) DEFAULT NULL,\n" +
						"  `threeTime` datetime DEFAULT NULL,\n" +
						"  `twoTime` datetime DEFAULT NULL,\n" +
						"  `updateTime` datetime DEFAULT NULL,\n" +
						"  `updater` varchar(255) DEFAULT NULL,\n" +
						"  `views` int(11) DEFAULT NULL,\n" +
						"  `way` varchar(200) DEFAULT NULL,\n" +
						"  `province` varchar(200) DEFAULT NULL,\n" +
						"  `city` varchar(200) DEFAULT NULL,\n" +
						"  `area` varchar(200) DEFAULT NULL,\n" +
						"  `dother` varchar(200) DEFAULT NULL,\n" +
						"  `fother` varchar(200) DEFAULT NULL,\n" +
						"  `ipother` varchar(200) DEFAULT NULL,\n" +
						"  `ffother` varchar(200) DEFAULT NULL,\n" +
						"  PRIMARY KEY (`id`)\n" +
						") ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;";

				SqlToBean.sqlToBean(sql);
				System.out.println("end");

	}
	
}
