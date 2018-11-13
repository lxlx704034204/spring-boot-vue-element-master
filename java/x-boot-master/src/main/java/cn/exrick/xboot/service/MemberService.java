package cn.exrick.xboot.service;

import cn.exrick.xboot.common.vo2.PageVo;
import cn.exrick.xboot.entity.node0.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liangxin
 * @since 02/09/2018
 */
public interface MemberService {
    Page<Member> findByCondition(PageVo pageVo, Pageable pageable);

    @Transactional(rollbackFor = Exception.class)
    void save(Member po);

    @Transactional(rollbackFor = Exception.class)
    void delete(List<Member> pos);
}
