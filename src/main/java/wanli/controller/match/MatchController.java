package wanli.controller.match;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wanli.pojo.Matchtype;
import wanli.service.MatchService;
import wanli.vo.ServerResponse;

import java.util.List;

/**
 * @Description:
 * @Author: zxh
 * @Date: Created in 2018/6/9
 */
@RestController
@RequestMapping("/match")
public class MatchController {

	@Autowired
	MatchService matchService;

	@RequestMapping("/typeList")
	@ResponseBody
	public ServerResponse<List<Matchtype>> typeList() {
		return matchService.getMatchType();
	}

	@RequestMapping("/list/{type}")
	@ResponseBody
	public ServerResponse<PageInfo> matchList(@PathVariable("type") Integer type,
											  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
											  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		if (type == null) {
			return ServerResponse.createByError();
		}

		return matchService.getMatchListByType(type, pageNum, pageSize);
	}

}
