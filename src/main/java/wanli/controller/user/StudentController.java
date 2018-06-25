package wanli.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wanli.common.Const;
import wanli.pojo.Apply;
import wanli.pojo.MyMatch;
import wanli.pojo.User;
import wanli.pojo.WanliMatch;
import wanli.service.StudentService;
import wanli.vo.ServerResponse;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
    StudentService studentService;

	@RequestMapping("/apply")
    @ResponseBody
    public ServerResponse apply(Apply apply, HttpSession session){
	     /* User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
       */
        System.out.println(apply);
        String[] stuid =apply.getStuid();
        String[] name =apply.getName();
        for(int i=0;i<stuid.length;i++){
            if(stuid[i]==""||name[i]==""){
                return  ServerResponse.createByErrorMessage("参赛人员信息不完整");
            }
        }
        String groupname=apply.getGroupname();
        if(!studentService.addGroupInformation(groupname,apply.getMatchid())&&groupname!=null){
            return  ServerResponse.createByErrorMessage("队名已经存在");
        }
        //获取组id
        Integer groupId=studentService.selectGroupByName(groupname,apply.getMatchid()).getGroupId();
        //插入学生与比赛的相关信息
        for(int i=0;i<stuid.length;i++){
            if (i == 0) {
                studentService.addStuMatch(stuid[i],name[i],apply.getMatchid(),groupId,1);
            }else{
                studentService.addStuMatch(stuid[i],name[i],apply.getMatchid(),groupId,2);
            }
        }
        return  ServerResponse.createBySuccessMessage("报名成功");
    }
    @RequestMapping("/viewMyMatch")
    @ResponseBody
    public ServerResponse<List<MyMatch>> viewMyMatch(String stuid, HttpSession session){
        /*User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        Integer stuid=user.getUsername();*/
        System.out.println("“获取到的stuid");
        if(stuid==null){
            return ServerResponse.createByError();
        }
        return  studentService.listMyMatchByStuid(stuid);
    }

    @RequestMapping("/cancle")
    @ResponseBody
    public ServerResponse cancle(Integer groupid, HttpSession session){
        /*User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        Integer stuid=user.getUsername();*/
        System.out.println(groupid);
        if(groupid==null){
            return ServerResponse.createByError();
        }
        if (studentService.deleteMyMatch(groupid)){
            return ServerResponse.createBySuccessMessage("取消成功");
        }
        return  ServerResponse.createByErrorMessage("取消失败");
    }
}
