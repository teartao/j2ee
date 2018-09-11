package com.tao.service;

import java.io.File;
import java.io.IOException;

/**
 * @Author neotao
 * @Date 2018/9/4
 * @Version V0.0.1
 * @Desc
 */
public interface OrderFactory {
    File getTodayOrderFile() throws IOException;
    void saveOrder(String content)throws IOException;
}
