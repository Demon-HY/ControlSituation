package com.control.situation.listener;

import com.control.situation.init.InitRole;
import com.control.situation.init.InitUser;
import com.demon.utils.exception.LogicalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听工程启动的监听事件
 *
 * Created by demon on 2018/2/7 0007.
 */
@Component
public class PostOnApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    public PostOnApplicationListener() {}

    @Autowired
    private InitUser initUser;
    @Autowired
    private InitRole initRole;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // 二次调用问题
        // 传统的application.xml和project-servlet.xml配置中会出现二次调用的问题。主要原因是初始化root容器之后，
        // 会初始化project-servlet.xml对应的子容器
        if(applicationReadyEvent.getApplicationContext().getParent() != null){
            return;
        }

        try {
            // 初始化角色
            initRole.initRole();

            // 初始化用户
            initUser.initUser();
        } catch (LogicalException e) {
            e.printStackTrace();
        }
    }
}
