package com.cs.springbootcrud.controller.base;

import com.cs.springbootcrud.constant.ResultEnum;
import com.cs.springbootcrud.model.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/error")
@Slf4j
public class ErrorController {

    @RequestMapping(value = "/notLogin")
    public Result loginError(HttpServletRequest request) {
        log.error(ResultEnum.NOT_LOGIN.getDisplay());
        return new Result(ResultEnum.NOT_LOGIN.getValue(),ResultEnum.NOT_LOGIN.getDisplay());
    }

    @RequestMapping(value = "/noAuth")
    public Result noAvatarError(HttpServletRequest request) {
        log.error("账号不存在,或错误");
        return new Result(ResultEnum.NOT_AUTH.getValue(),ResultEnum.NOT_AUTH.getDisplay());
    }



}
