package com.peels.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.entity.GridCity;
import com.peels.entity.GridMember;
import com.peels.mapper.GridCityMapper;
import com.peels.mapper.GridMemberMapper;
import com.peels.service.IGridCityService;
import com.peels.service.IGridMemberService;
import org.springframework.stereotype.Service;

/**
 * @Author peelsannaw
 * @create 26/06/2023 14:33
 */
@Service
public class GridMemberService  extends ServiceImpl<GridMemberMapper, GridMember> implements IGridMemberService {
}
