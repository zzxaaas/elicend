package cn.zzxcloud.elicend.api.controller;

import cn.zzxcloud.elicend.api.entity.BuildHistory;
import cn.zzxcloud.elicend.api.entity.Project;
import cn.zzxcloud.elicend.api.service.BuildHistoryService;
import cn.zzxcloud.elicend.common.dto.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/build")
public class BuildHistoryController {

    @Autowired
    private BuildHistoryService buildHistoryService;

    @GetMapping("/history")
    public BaseResult getBuildHistory(@RequestParam int projectId){
        List<BuildHistory> history = buildHistoryService.getByProjectId(projectId);
        return BaseResult.success("success",history);
    }
    @GetMapping("/state")
    public BaseResult updateState(@RequestParam int buildId,int state){
        buildHistoryService.updateStateById(buildId,state);
        return BaseResult.success();
    }
}
