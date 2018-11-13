package cn.exrick.xboot.controller.node0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.exrick.xboot.common.utils.ResultUtil;
import cn.exrick.xboot.common.vo.Result;
import cn.exrick.xboot.common.vo2.PageUtil;
import cn.exrick.xboot.common.vo2.PageVo;
import cn.exrick.xboot.dao.MemberDao;
import cn.exrick.xboot.entity.node0.Member;
import cn.exrick.xboot.exception.RestResponse;
import cn.exrick.xboot.service.MemberService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author liangxin
 * @since 02/09/2018
 */
@Slf4j
@RestController
@Api(description = "用户接口")
@RequestMapping("/xboot/member")
@CacheConfig(cacheNames = "member")
public class MemberController {

    @Autowired
    MemberDao memberDao;
    @Autowired
    MemberService memberService;

    @RequestMapping(value ="/loadPage" , method = RequestMethod.POST)
//    @RequestMapping(value ="/getstu" , method = RequestMethod.POST)
    @ResponseBody
    public Result<Page<Member>> getTestuser(@RequestBody PageVo pageVo ) {
        Pageable pageable = PageUtil.initPage(pageVo);
        Page<Member> page = memberService.findByCondition(pageVo, pageable);

        return new ResultUtil<Page<Member>>().setData(page);
    }

    @RequestMapping(value ="/get/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public Member getUserById(@PathVariable("id")String id){
        Optional<Member> one = memberDao.findById(id);
        return one.get();
    }


    //List<Map<String, Object>> listmap, List<Member> lists
    @RequestMapping(value ="/save" , method = RequestMethod.POST)
//    @RequestMapping(value ="/update" , method = RequestMethod.POST)
    @ResponseBody
    public RestResponse update(
//          @RequestParam(value = "theEditId",required = true) String id,
            @RequestBody Map<String, Object> params ) {
//        if(StrUtil.isNotBlank(id)){ //update
//        }

        String theEditId = params.get("theEditId")+"";

        //BeanUtil JsonUtils ConvertUtil_diy
        Map<String, Object> m1 = (Map<String, Object>) params.get("bean");
////        List<Map<String, Object>> m2 = (List<Map<String, Object>>) params.get("bean");
//        BeanUtil.mapToBean(m1, Member.class);
//        JsonUtils.mapToBean(m1, Member.class);

        String s1 = JSON.toJSONString(m1);
        JSONObject jsonObject1 = JSON.parseObject(s1);
        Member theUser = JSON.toJavaObject(jsonObject1, Member.class);
        try {
            memberService.save(theUser);
        } catch (Exception e) {
            //new XbootException("");
            return new RestResponse(false, e.getMessage());
        }
        return RestResponse.SUCCESS_WITHOUT_MSG;
    }


    @RequestMapping(value ="/delete/po/list" , method = RequestMethod.POST)
    // @RequestMapping(value ="remove/{id}" , method = RequestMethod.POST)
    @ResponseBody
    public RestResponse deletePoList(@RequestBody List<Member> beans) {
        //@RequestBody List<Map<String, Object>> params
        //@RequestBody List<Member> params
        try {
            memberService.delete(beans);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResponse.SUCCESS_WITHOUT_MSG;
    }

    @RequestMapping(value ="remove/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public RestResponse deleteOneById(@PathVariable(value = "id",required = true) String id) {
        //@RequestBody List<Map<String, Object>> params
        //@RequestBody List<Member> params
        try {
            memberDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResponse.SUCCESS_WITHOUT_MSG;
    }


    @RequestMapping(value ="/add" , method = RequestMethod.POST)
    @ResponseBody
    public RestResponse add(@RequestBody Map<String, Object> params ) {
        Map<String, Object> m1 = (Map<String, Object>) params;

        String s1 = JSON.toJSONString(m1);
        JSONObject jsonObject1 = JSON.parseObject(s1);
        Member theUser = JSON.toJavaObject(jsonObject1, Member.class);
        try {
            memberService.save(theUser);
        } catch (Exception e) {
            //new XbootException("");
            return new RestResponse(false, e.getMessage());
        }
        return RestResponse.SUCCESS_WITHOUT_MSG;
    }



    //只为测试接收 前提提交复杂的 参数
    @RequestMapping(value ="/saveData_test_post" , method = RequestMethod.POST)
    @ResponseBody
    public void saveData(@RequestBody Map<String, Object> params) throws Exception {
        Map<String, Object> ChoisedOne_dim1Code_map = (Map<String, Object>) params.get("FKArrayOne");
        String targetExplain  = ChoisedOne_dim1Code_map.get("targetExplain")+"";
        String cat_three_code = ChoisedOne_dim1Code_map.get("cat_three_code")+"";
        List<Map<String, Object>> list = (List<Map<String, Object>>) params.get("dialogDatas");

    }






}
