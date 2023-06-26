package com.peels.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.peels.entity.Admins;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@Mapper
public interface AdminsMapper extends BaseMapper<Admins> {

}
