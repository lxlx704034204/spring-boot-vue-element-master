package cn.exrick.xboot.serviceimpl;

import cn.exrick.xboot.common.vo2.PageVo;
import cn.exrick.xboot.dao.MemberDao;
import cn.exrick.xboot.entity.node0.Member;
import cn.exrick.xboot.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author liangxin
 * @since 02/09/2018
 */
@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;
    
    
    @Override
    public Page<Member> findByCondition(PageVo pageVo, Pageable pageable) {

        LinkedHashMap searcher = (LinkedHashMap) pageVo.getQuery();

        Specification<Member> spec = (root, query, cb) -> {

            //https://www.cnblogs.com/dreamroute/p/5173896.html
//            Join abMap = root.join("classPlanSend", JoinType.INNER);
//            query.groupBy(root.get("pkg")).orderBy(cb.desc(root.get("createTime")));


            Path<String> usernameField = root.get("name");

            List<Predicate> list = new ArrayList<Predicate>();

            if(null !=searcher.get("name") && !"".equals(searcher.get("name"))){
                list.add(cb.like(usernameField,'%'+ searcher.get("name").toString() +'%'));
            }
//            if(null !=searcher.get("region") && !"".equals(searcher.get("region"))){
//                list.add(cb.equal(root.get("region"),searcher.get("region").toString()));
//            }
            Predicate[] arr = new Predicate[list.size()];
            query.where(list.toArray(arr));
            return null;
        };

        //        Specification<Member> spec = new Specification<Member>() {
////            @Nullable
//            @Override
//            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//
//                Path<String> usernameField = root.get("name");
////                Path<String> mobileField = root.get("regin");//页面seacher无此字段的时候不要写，否则报错！
//
//
//                List<Predicate> list = new ArrayList<Predicate>();
//
////                //模糊搜素
//                if(null !=searcher.get("name") && !"".equals(searcher.get("name"))){
//                    list.add(cb.like(usernameField,'%'+ searcher.get("name").toString() +'%'));
//                }
//
////                if(StrUtil.isNotBlank(user.getUsername())){
////                    list.add(cb.like(usernameField,'%'+user.getUsername()+'%'));
////                }
////                if(StrUtil.isNotBlank(user.getMobile())){
////                    list.add(cb.like(mobileField,'%'+user.getMobile()+'%'));
////                }
////                //性别
////                if(user.getSex()!=null){
////                    list.add(cb.equal(sexField, user.getSex()));
////                }
////                //类型
////                if(user.getType()!=null){
////                    list.add(cb.equal(typeField, user.getType()));
////                }
////                //状态
////                if(user.getStatus()!=null){
////                    list.add(cb.equal(statusField, user.getStatus()));
////                }
////                //创建时间
////                if(StrUtil.isNotBlank(searchVo.getStartDate())&&StrUtil.isNotBlank(searchVo.getEndDate())){
////                    Date start = DateUtil.parse(searchVo.getStartDate());
////                    Date end = DateUtil.parse(searchVo.getEndDate());
////                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
////                }
//
//                Predicate[] arr = new Predicate[list.size()];
//                query.where(list.toArray(arr));
//                return null;
//            }
//        };

        Page<Member> page = memberDao.findAll(spec, pageable);
        return page;
        
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Member po){
        memberDao.save(po);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Member> pos){
        memberDao.deleteAll(pos);
    }



}
