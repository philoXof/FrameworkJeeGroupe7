package com.esgi.framework_JEE.Infrastructure.logger;

import com.esgi.framework_JEE.kernel.logger.Logger;
import com.esgi.framework_JEE.kernel.logger.LoggerFactory;


import java.util.Objects;

public class DefaultLoggerFactory implements LoggerFactory {

    @Override
    public Logger getLogger(Object object) {
        return new DefaultLogger(Objects.requireNonNull(object).getClass().getName());
    }
}
