package com.cchong.logistics.controller;
import com.cchong.logistics.dao.ShipperInformationMapper;
import com.cchong.logistics.entity.Admin;
import com.cchong.logistics.entity.ShipperInformation;
import com.cchong.logistics.service.ShipperInformationService;
import com.cchong.logistics.util.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/shipperInformation")
public class ShipperInformationController {
    @Autowired
    private ShipperInformationService shipperInformationService;
    @Autowired
    private ShipperInformationMapper shipperInformationMapper;
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

            return shipperInformationService.deleteByPrimaryKey(id) > 0 ? new Result().successMessage("删除成功") : Result.error("删除失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 添加对象shipperInformation
     *
     * @param shipperInformation
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody ShipperInformation shipperInformation) {
        try {
            return shipperInformationService.insert(shipperInformation) > 0 ? new Result().successMessage("添加成功！") : Result.error("添加失败！");
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
            ShipperInformation shipperInformation1 = shipperInformationService.selectByPrimaryKey(id);
            if (shipperInformation1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(shipperInformation1);
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
            List<ShipperInformation> list = shipperInformationService.selectAll("","","");
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
     * @param shipperInformation
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody ShipperInformation shipperInformation) {
        try {
            return shipperInformationService.updateByPrimaryKey(shipperInformation) > 0 ? new Result().successMessage("修改成功") : Result.error("修改失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
    
    
     /* 查询所有数据分页
     *
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit,String sName,String sPhone,String sSex) {
        try {
            PageHelper.startPage(page, limit);
            List<ShipperInformation> list = shipperInformationService.selectAll(sName,sPhone,sSex);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result(0, "ok", list, shipperInformationService.count(sName,sPhone,sSex));
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }


    /* 修改密码
     *
     * @return
     */
    @GetMapping("/updatePassword")
    public Result updatePassword(String password,String old, int id) {
        try {
            ShipperInformation shipperInformation = shipperInformationService.selectByPrimaryKey(id);
            if(shipperInformation != null){
                if(shipperInformation.getsPassword().equals(old)){
                    if(shipperInformationMapper.updatePassword(password,id)>0)
                        return new Result(200,"修改成功！");
                }
            }
            return new Result().error("修改失败!");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
}
