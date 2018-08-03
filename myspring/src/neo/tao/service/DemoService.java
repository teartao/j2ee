package neo.tao.service;

import neo.tao.mvc.annotation.Service;

/**
 * @Author neotao
 * @Date 2018/8/3
 * @Version V0.0.1
 * @Desc
 */
@Service
public interface DemoService {
    String getByName(String name);
}
