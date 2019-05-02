package com.cchong.logistics.controller;
import com.cchong.logistics.dao.EvaluationInformationMapper;
import com.cchong.logistics.entity.EvaluationInformation;
import com.cchong.logistics.service.EvaluationInformationService;
import com.cchong.logistics.util.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/evaluationInformation")
public class EvaluationInformationController {
    @Autowired
    private EvaluationInformationService evaluationInformationService;
    @Autowired
    private EvaluationInformationMapper evaluationInformationMapper;

    /**
     * 根据主键删除
     * 要求转入 aId
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    @RequiresRoles("admin")
    public Result deleteByPrimaryKey(int id) {
        try {

            return evaluationInformationService.deleteByPrimaryKey(id) > 0 ? new Result().successMessage("删除成功") : Result.error("删除失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 添加对象evaluationInformation
     *
     * @param evaluationInformation
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody EvaluationInformation evaluationInformation) {
        try {
            return evaluationInformationService.insert(evaluationInformation) > 0 ? new Result().successMessage("添加成功！") : Result.error("添加失败！");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }

    }

    /**
     * 根据主键查找对象  最多只能返回一个对象
     *
     * @param id
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(int id) {
        try {
            EvaluationInformation evaluationInformation1 = evaluationInformationService.selectByPrimaryKey(id);
            if (evaluationInformation1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(evaluationInformation1);
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
    /**
     * 根据主键查找对象  最多只能返回一个对象
     *
     * @param oId
     * @return
     */
    @GetMapping("/selectoId")
    public Result selectoId(String oId) {
        try {
            EvaluationInformation evaluationInformation1 = evaluationInformationMapper.selectoId(oId);
            if (evaluationInformation1 == null) {
                return new Result().error("无数据");
            } else {
                return new Result().success(evaluationInformation1);
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
    /**
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        try {
            List<EvaluationInformation> list = evaluationInformationService.selectAll(0,"","");
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 根据主键修改全部字段
     *
     * @param evaluationInformation
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody EvaluationInformation evaluationInformation) {
        try {
            return evaluationInformationService.updateByPrimaryKey(evaluationInformation) > 0 ? new Result().successMessage("修改成功") : Result.error("修改失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
    
    
     /* 查询所有数据分页
     *
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit,int fsid, String sName, String dName) {
        try {
            PageHelper.startPage(page, limit);
            List<EvaluationInformation> list = evaluationInformationService.selectAll(fsid,sName,dName);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result(0, "ok", list, evaluationInformationService.count(fsid,sName,dName));
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
}
