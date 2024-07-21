package com.oj.zengojbackendserviceclient.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oj.zengojbackendcommon.common.ErrorCode;
import com.oj.zengojbackendcommon.exception.BusinessException;
import com.oj.zengojbackendmodel.model.dto.user.UserQueryRequest;
import com.oj.zengojbackendmodel.model.entity.User;
import com.oj.zengojbackendmodel.model.enums.UserRoleEnum;
import com.oj.zengojbackendmodel.model.vo.LoginUserVO;
import com.oj.zengojbackendmodel.model.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

import static com.oj.zengojbackendcommon.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务
 *

 */
@FeignClient(name = "zengoj-backend-user-service", path = "/api/user/inner")
public interface UserFeignClient {

    /**
     * 所需要的调用服务的接口：
     *  userService.getById(userId)
     *  userService.getUserVO(user)
     *  userService.listByIds(userIdSet)
     *  userService.isAdmin(loginUser)
     *  userService.getLoginUser(request)
     *  judgeService.doJudge(questionSubmitId)
     */

    /**
     * 根据 id 获取用户
     * @param userId
     * @return
     */
    @GetMapping("/get/id")
    User getById(@RequestParam("userId") long userId);

    /**
     * 根据 id 获取用户列表
     * @param idList
     * @return
     */
    @GetMapping("/get/ids")
    List<User> listByIds(@RequestParam("idList") Collection<Long> idList);

    // 下面三个方法传递的内容如果在 Feign 的场景下，就是远程调用，会走 http，那么不能保证传输的对象一定能够
    // 序列化然后作为参数传递，且方法本身就不需要操作数据库，实现较为简单，那么可以直接用默认方法，无需远程调用

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    default User getLoginUser(HttpServletRequest request){
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        /*// 从数据库查询（追求性能的话可以注释，直接走缓存）
        long userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }*/
        return currentUser;
    }

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    default boolean isAdmin(User user){
        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getUserRole());
    }

    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    default UserVO getUserVO(User user){
        if(user == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

}
