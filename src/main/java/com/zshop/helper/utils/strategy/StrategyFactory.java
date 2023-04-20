package com.zshop.helper.utils.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class StrategyFactory {

    @Autowired
    private StrategyProcessEngineApplicationContext orderProcessEngineApplicationContext;

    public <T> T getBeanByTypeAndName(String type, String beanLName, Class<T> clazz) {
        Map<String, Object> beansMap = orderProcessEngineApplicationContext.getContext().getBeansWithAnnotation(Strategy.class);
        if (CollectionUtils.isEmpty(beansMap)) {
            throw new NoSuchBeanDefinitionException(type+beanLName);
        }

        List<Object> beansList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : beansMap.entrySet()) {
            Strategy strategy = entry.getValue().getClass().getAnnotation(Strategy.class);
            if (type.equals(strategy.type()) && beanLName.equals(strategy.beanLName()) && clazz.isAssignableFrom(entry.getValue().getClass())) {
                beansList.add(entry.getValue());
            }
        }
        if (CollectionUtils.isEmpty(beansList)) {
            throw new NoSuchBeanDefinitionException(clazz);
        } else if (beansList.size() > 1) {
            throw new NoUniqueBeanDefinitionException(clazz, beansList.stream().map(o -> o.getClass().getName()).collect(Collectors.toList()));
        } else {
            Object bean = beansList.get(0);
            if (Objects.nonNull(bean) && bean instanceof StrategyPattern)
                return clazz.cast(bean);
            else
                throw new NoSuchBeanDefinitionException(clazz);
        }
    }

}
