package com.fct.core;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.fct.core.dao.UmsRolePermissionRelationDAO;
import com.fct.core.domain.UmsPermissionDO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoCoreApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoCoreApplicationTests.class);

	@Resource
	private UmsRolePermissionRelationDAO umsRolePermissionRelationDAO;

	@Test
	public void getPermissionList() {
		List<UmsPermissionDO> list = umsRolePermissionRelationDAO.getPermissionList(1L);
		LOGGER.info("permissionList = {}", JSON.toJSONString(list));

	}

}

