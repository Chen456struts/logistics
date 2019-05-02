package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.AdminMapper;
import com.cchong.logistics.dao.DriverInformationMapper;
import com.cchong.logistics.dao.ShipperInformationMapper;
import com.cchong.logistics.dao.VerificationCodeMapper;
import com.cchong.logistics.entity.Admin;
import com.cchong.logistics.entity.DriverInformation;
import com.cchong.logistics.entity.ShipperInformation;
import com.cchong.logistics.entity.VerificationCode;
import com.cchong.logistics.service.LoginService;
import com.cchong.logistics.service.SmsPhone;
import com.cchong.logistics.shiro.JWTUtil;
import com.cchong.logistics.util.LoginOk;
import com.cchong.logistics.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private VerificationCodeMapper verificationCodeMapper;
    @Autowired
    private SmsPhone smsPhone;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private DriverInformationMapper driverInformationMapper;
    @Autowired
    private ShipperInformationMapper shipperInformationMapper;
    /**
     * 使用司机手机号码 密码 验证条件 登录
     * @param dPhone
     * @param Password
     * @return
     */
    @Override
    public Result loginDriverInformation(String dPhone, String Password) {
            DriverInformation driverInformation = driverInformationMapper.yzm(dPhone);
            if(driverInformation == null){
                return Result.error("账号无效");
            }
            if(driverInformation.getdPassword().equals(Password)){
                return Result.successMessage(1028,"登录成功", loginOkDriverInformation(driverInformation));
            }
            return Result.error("用户名密码错误");

    }
    /**
     * 货主根据手机号码 密码 验证条件 获取信息
     * @return
     */
    @Override
    public Result loginShipperInformation(String dPhone, String Password) {

            ShipperInformation shipperInformation = shipperInformationMapper.yzm(dPhone);
            if(shipperInformation == null){
                return Result.error("账号无效");
            }
            if(shipperInformation.getsPassword().equals(Password)){
                return Result.successMessage(1028,"登录成功", loginOkShipperInformation(shipperInformation));
            }
            return Result.error("用户名密码错误");

    }
    /**
     * 管理员根据手机号码或者账号 密码 验证条件
     * @param dPhone
     * @return
     */
    @Override
    public Result loginAdmin(String dPhone, String Password) {

            Admin admin = adminMapper.yzms(dPhone);
            if(admin == null){
                return Result.error("账号无效");
            }
            if(admin.getaPassword().equals(Password)){
                return Result.successMessage(1028,"登录成功", loginOkAdmin(admin));
            }
            return Result.error("用户名密码错误");
    }

    @Override
    public Result yzm(String phone, int sf, int yzm) {
        //管理员
        if(sf == 0){
            VerificationCode verificationCode = verificationCodeMapper.selectLogin(phone,yzm);
            if(verificationCode == null){
                return  Result.error("验证码错误");
            }
            //获取用户
            Admin admin = adminMapper.yzm(phone);
            return Result.successMessage(1028,"登录成功", loginOkAdmin(admin));
        }
        //客户
        else if(sf == 1){
            VerificationCode verificationCode = verificationCodeMapper.selectLogin(phone,yzm);
            if(verificationCode == null){
                return  Result.error("验证码错误");
            }
            //获取用户
            ShipperInformation shipperInformation = shipperInformationMapper.yzm(phone);
            return Result.successMessage(1028,"登录成功", loginOkShipperInformation(shipperInformation));
        }
        //司机
        else{
            VerificationCode verificationCode = verificationCodeMapper.selectLogin(phone,yzm);
            if(verificationCode == null){
                return  Result.error("验证码错误");
            }
            //获取用户
            DriverInformation driverInformation = driverInformationMapper.yzm(phone);
            return Result.successMessage(1028,"登录成功", loginOkDriverInformation(driverInformation));
        }
    }

    /**sf
     * 0管理员
     * 1客户
     * 否则 司机
     * @param phone
     * @param sf
     * @return
     */
    @Override
    public Result getYzm(String phone, int sf) {
        //管理员
        if(sf == 0){
            //获取用户
            Admin admin = adminMapper.yzm(phone);
            if(admin == null){
                return new Result().error(1203,"手机号码无效！");
            }
            return new Result().success(smsPhone.send(phone));
        }
        //客户
        else if(sf == 1){
            //获取用户
            ShipperInformation shipperInformation = shipperInformationMapper.yzm(phone);
            if(shipperInformation == null){
                return new Result().error(1203,"手机号码无效！");
            }
            return new Result().success(smsPhone.send(phone));
        }
        //司机
        else{
            //获取用户
            DriverInformation driverInformation = driverInformationMapper.yzm(phone);
            if(driverInformation == null){
                return new Result().error(1203,"手机号码无效！");
            }
            return new Result().success(smsPhone.send(phone));
        }
    }
    //司机
    private static LoginOk loginOkDriverInformation(DriverInformation driverInformation){
        LoginOk loginOk = new LoginOk();
        loginOk.setmName(driverInformation.getdName());
        loginOk.setmUser(driverInformation.getdId().toString());
        loginOk.setSign(JWTUtil.sign("D"+driverInformation.getdId().toString(), driverInformation.getdPassword()));
        return loginOk;
    }
    //客户
    private static LoginOk loginOkShipperInformation(ShipperInformation shipperInformation){
        LoginOk loginOk = new LoginOk();
        loginOk.setmName(shipperInformation.getsName());
        loginOk.setmUser(shipperInformation.getsId().toString());
        loginOk.setSign(JWTUtil.sign("S"+shipperInformation.getsId().toString(), shipperInformation.getsPassword()));
        return loginOk;
    }
    //管理员
    private static LoginOk loginOkAdmin(Admin admin){
        LoginOk loginOk = new LoginOk();
        loginOk.setmName(admin.getaAccount());
        loginOk.setmUser(admin.getaId().toString());
        loginOk.setSign(JWTUtil.sign("A"+admin.getaId().toString(), admin.getaPassword()));
        return loginOk;
    }
    @Override
    public Result logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
            return Result.successMessage("退出成功");
        }
        return Result.successMessage("退出成功");
    }
}
