package com.firststringboot.repository.mapper;

import com.firststringboot.repository.domain.User;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper{

    int deleteByPrimaryKey(Long pkUserId);

    int insert(User record);

    int batchInsert(@Param("records") List<User> records);

    int insertSelective(User record);

    User selectByPrimaryKey(Long pkUserId);

    PageList<User> selectObjectListByWhere(User record, PageBounds pageBounds);

    PageList<User> selectByBillId(@Param("selective") User selective, @Param("ids") List<Long> ids, PageBounds pageBounds);

    int selectCountByWhere(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}