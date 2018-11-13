package cn.exrick.xboot.dao;

import cn.exrick.xboot.base.XbootBaseDao;
import cn.exrick.xboot.entity.User;
import cn.exrick.xboot.entity.node0.Member;

import java.util.List;

/**
 * @author liangxin
 * @since 02/09/2018
 */
public interface MemberDao extends XbootBaseDao<Member,String> {



    List<Member> findBySex(Integer sex);

}
