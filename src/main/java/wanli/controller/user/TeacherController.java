package wanli.controller.user;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wanli.common.Const;
import wanli.pojo.StuMatch;
import wanli.pojo.User;
import wanli.pojo.WanliMatch;
import wanli.service.TeacherService;
import wanli.vo.ServerResponse;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 添加比赛信息
     * @param wanliMatch
     * @return
     */
    @RequestMapping("/addMatch")
    @ResponseBody
    public ServerResponse addMatch(WanliMatch wanliMatch,HttpSession session){
       User user = (User) session.getAttribute(Const.CURRENT_USER);
       /*  if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(user.getIdentity()!=2){
            return  ServerResponse.createByErrorMessage("你没有权限对此操作");
        }*/
        if(teacherService.matchExist(wanliMatch.getMatchName(),wanliMatch.getPlace(),wanliMatch.getMatchType())){
            return ServerResponse.createByErrorMessage("比赛已存在");
        }
        ServerResponse serverResponse=teacherService.addMatcheInformation(wanliMatch);
        WanliMatch wanliMatch1 =teacherService.selectMatch(wanliMatch.getMatchName(),wanliMatch.getPlace(),wanliMatch.getMatchType());
        teacherService.insertTeacherMatch(wanliMatch1.getTeacherId(),wanliMatch1.getId());
        return serverResponse;
    }

    /**
     * 根据id删除比赛
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/deleteMatch")
    @ResponseBody
    public ServerResponse deleteMatch(Integer id, HttpSession session){
      /*  User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(user.getIdentity()!=2){
            return  ServerResponse.createByErrorMessage("你没有权限对此操作");
        }*/
        if(teacherService.deleteMatch(id)){
            return  ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 更新比赛信息
     * @param wanliMatch
     * @param session
     * @return
     */
    @RequestMapping(value = "/updateMatch")
    @ResponseBody
    public ServerResponse updateMatch(WanliMatch wanliMatch,HttpSession session){
         /* User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(user.getIdentity()!=2){
            return  ServerResponse.createByErrorMessage("你没有权限对此操作");
        }*/
        System.out.println(wanliMatch);
        if(teacherService.updateMatchInformation(wanliMatch)){
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    /**
     * 查看一个比赛的报名情况
     * @param matchId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/ViewGroup")
    @ResponseBody
    public ServerResponse<PageInfo> ViewGroup(@RequestParam("matchId") Integer matchId,
                                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,HttpSession session){
         /* User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(user.getIdentity()!=2){
            return  ServerResponse.createByErrorMessage("你没有权限对此操作");
        }*/
        if(matchId==null){
            return ServerResponse.createByError();
        }
        return teacherService.getGroupList(matchId,pageNum,pageSize);
    }

    /**
     * 查看一组的成员
     * @param groupId
     * @param session
     * @return
     */
    @RequestMapping("/ViewMember")
    @ResponseBody
    public ServerResponse<List<StuMatch>> ViewMember(Integer groupId,HttpSession session){
         /* User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(user.getIdentity()!=2){
            return  ServerResponse.createByErrorMessage("你没有权限对此操作");
        }*/
         if(groupId==null){
             return  ServerResponse.createByError();
         }
         return teacherService.selectMemberByGroupId(groupId);
    }
    /**
     * 删除小组
     * @param groupId
     * @param session
     * @return
     */
    @RequestMapping("/deleteGroup")
    @ResponseBody
    public ServerResponse deleteGroup(Integer groupId,HttpSession session){
         /* User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(user.getIdentity()!=2){
            return  ServerResponse.createByErrorMessage("你没有权限对此操作");
        }*/
         if(teacherService.deleteGroupAllInformation(groupId)){
             return ServerResponse.createBySuccessMessage("删除成功");
         }
         return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 删除个别成员
     * @param id
     * @return
     */
    @RequestMapping("/deleteGroupMember")
    @ResponseBody
    public ServerResponse deleteGroupMember(Integer id,HttpSession session){
         /* User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(user.getIdentity()!=2){
            return  ServerResponse.createByErrorMessage("你没有权限对此操作");
        }*/
        if(id==null){
            return  ServerResponse.createByError();
        }
        return teacherService.deleteGroupMember(id);
    }

    @RequestMapping("/updateGroupMember")
    @ResponseBody
    public ServerResponse updateGroupMember(StuMatch stuMatch,HttpSession session){
        /* User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(user.getIdentity()!=2){
            return  ServerResponse.createByErrorMessage("你没有权限对此操作");
        }*/
        if(stuMatch==null){
            return  ServerResponse.createByError();
        }
        return teacherService.updateGroupMember(stuMatch.getId(),stuMatch.getStuId(),stuMatch.getStuName());
    }
}
