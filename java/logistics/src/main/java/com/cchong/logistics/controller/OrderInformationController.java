package com.cchong.logistics.controller;
import com.cchong.logistics.entity.OrderInformation;
import com.cchong.logistics.service.OrderInformationService;
import com.cchong.logistics.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import com.github.pagehelper.PageHelper;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/orderInformation")
public class OrderInformationController {
    @Autowired
    private OrderInformationService orderInformationService;

    /**
     * 根据主键删除
     * 要求转入 aId
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(String id) {
        try {
            return orderInformationService.deleteByPrimaryKey(id) > 0 ? new Result().successMessage("删除成功") : Result.error("删除失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 添加对象orderInformation
     *
     * @param orderInformation
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody OrderInformation orderInformation) {
        try {
            orderInformation.setoId(String.valueOf(System.currentTimeMillis()));
            return orderInformationService.insert(orderInformation) > 0 ? new Result().successMessage("添加成功！") : Result.error("添加失败！");
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
            OrderInformation orderInformation1 = orderInformationService.selectByPrimaryKey(id);
            if (orderInformation1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(orderInformation1);
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
            List<OrderInformation> list = orderInformationService.selectAll();
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
     * @param orderInformation
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody OrderInformation orderInformation) {
        try {
            return orderInformationService.updateByPrimaryKey(orderInformation) > 0 ? new Result().successMessage("修改成功") : Result.error("修改失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
    
    
     /* 查询所有数据分页
     *
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        try {
            PageHelper.startPage(page, limit);
            List<OrderInformation> list = orderInformationService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result(0, "ok", list, orderInformationService.count());
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /* 根据订单状态进行查询
     *
     * @return
     */
    @GetMapping("/selectStart")
    public Result selectPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit,String oStart,String oId,String contacts) {
        try {
            PageHelper.startPage(page, limit);
            List<OrderInformation> list = orderInformationService.selectStart(oStart,oId,contacts);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result(0, "ok", list, orderInformationService.countStart(oStart,oId,contacts));
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
    /* 根据订单状态进行查询
     *
     * @return
     */
    @GetMapping("/getCount")
    public Result getCount(@RequestParam(defaultValue = "0")int sId, @RequestParam(defaultValue = "0")int dId) {
        try {
            int wyc = orderInformationService.countType("已完成",sId,dId);
            int ysz = orderInformationService.countType("运输中",sId,dId);
            int dys = orderInformationService.countType("待运输",sId,dId);
            Map<String,String> map = new HashMap<>();
            map.put("ywc",String.valueOf(wyc));
            map.put("ysz",String.valueOf(ysz));
            map.put("dys",String.valueOf(dys));
            return new Result(0, "ok", map);
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
}
