package struts2.common.basic;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface BaseService {


   Logger logger = LogManager.getLogger(BaseService.class);


}
