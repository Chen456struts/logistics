package com.cchong.logistics.controller;
import com.cchong.logistics.entity.CollectionFee;
import com.cchong.logistics.service.CollectionFeeService;
import com.cchong.logistics.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/collectionFee")
public class CollectionFeeController {
    @Autowired
    private CollectionFeeService collectionFeeService;

    /**
     * 根据主键删除
     * 要求转入 aId
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(int id) {
        try {

            return collectionFeeService.deleteByPrimaryKey(id) > 0 ? new Result().successMessage("删除成功") : Result.error("删除失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 添加对象collectionFee
     *
     * @param collectionFee
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody CollectionFee collectionFee) {
        try {
            return collectionFeeService.insert(collectionFee) > 0 ? new Result().successMessage("添加成功！") : Result.error("添加失败！");
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
            CollectionFee collectionFee1 = collectionFeeService.selectByPrimaryKey(id);
            if (collectionFee1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(collectionFee1);
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
            List<CollectionFee> list = collectionFeeService.selectAll("");
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
     * @param collectionFee
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody CollectionFee collectionFee) {
        try {
            return collectionFeeService.updateByPrimaryKey(collectionFee) > 0 ? new Result().successMessage("修改成功") : Result.error("修改失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
    
    
     /* 查询所有数据分页
     *
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit,String cType) {
        try {
            PageHelper.startPage(page, limit);
            List<CollectionFee> list = collectionFeeService.selectAll(cType);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result(0, "ok", list, collectionFeeService.count(cType));
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
}
