package com.reshui.common.log.service;

import com.reshui.common.core.constant.SecurityConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步调用日志服务
 * 
 * @author ruoyi
 */
@Service
public class AsyncLogService
{
//    @Autowired
//    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录 SysOperLog sysOperLog
     */
    @Async
    public void saveSysLog()
    {
//        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}
