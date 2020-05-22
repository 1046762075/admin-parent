package com.firenay.leave.test;

import com.firenay.leave.entity.po.MemberPO;
import com.firenay.leave.entity.vo.DetailProjectVO;
import com.firenay.leave.entity.vo.DetailReturnVO;
import com.firenay.leave.entity.vo.PortalTypeVO;
import com.firenay.leave.mapper.MemberPOMapper;
import com.firenay.leave.mapper.ProjectPOMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>Title: MySQLMain</p>
 * Description：各种SQL的测试
 * date：2020/5/18 11:15
 */
@SpringBootTest
public class MySQLMainTest {

	@Resource
	private DataSource dataSource;

	@Resource
	private MemberPOMapper memberPOMapper;

	@Resource
	private ProjectPOMapper projectPOMapper;

	private Logger logger = LoggerFactory.getLogger(MySQLMainTest.class);

	@Test
	public void loadDetailProjectTest() {
		DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(1);
		logger.info(detailProjectVO.getProjectId() + "");
		logger.info(detailProjectVO.getProjectName());
		logger.info(detailProjectVO.getProjectDesc());
		logger.info(detailProjectVO.getFollowerCount() + "");
		logger.info(detailProjectVO.getStatus() + "");
		logger.info(detailProjectVO.getMoney() + "");
		logger.info(detailProjectVO.getSupportMoney() + "");
		logger.info(detailProjectVO.getPercentage() + "");
		logger.info(detailProjectVO.getDeployDate() + "");
		logger.info(detailProjectVO.getSupporterCount() + "");
		logger.info(detailProjectVO.getHeaderPicturePath());

		List<String> detailPicturePathList = detailProjectVO.getDetailPicturePathList();
		for (String path : detailPicturePathList) {
			logger.info("detail path=" + path);
		}

		List<DetailReturnVO> detailReturnVOList = detailProjectVO.getDetailReturnVOList();
		for (DetailReturnVO detailReturnVO : detailReturnVOList) {
			logger.info(detailReturnVO.getReturnId() + "");
			logger.info(detailReturnVO.getSupportMoney() + "");
			logger.info(detailReturnVO.getSignalPurchase() + "");
			logger.info(detailReturnVO.getPurchase() + "");
			logger.info(detailReturnVO.getSupproterCount() + "");
			logger.info(detailReturnVO.getFreight() + "");
			logger.info(detailReturnVO.getReturnDate() + "");
			logger.info(detailReturnVO.getContent() + "");
			logger.info(detailReturnVO.getFreight() + "");
		}
	}


	@Test
	public void projectDataTest() {
		List<PortalTypeVO> portalTypeVOS = projectPOMapper.selectPortalTypeVOs();
		portalTypeVOS.forEach(System.out::println);
	}

	@Test
	public void testConnection() throws SQLException {
		Connection conn = dataSource.getConnection();
		logger.info("\n" + conn.toString());
	}

	@Test
	public void testMapper() {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String source = "123123";

		String encode = passwordEncoder.encode(source);

		MemberPO memberPO = new MemberPO(null, "leave", encode, "莉唔", "leave@qq.com", 1, 1, "莉唔", "123", 2);

		memberPOMapper.insert(memberPO);
	}

}
