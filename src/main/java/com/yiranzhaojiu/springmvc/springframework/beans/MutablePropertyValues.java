package com.yiranzhaojiu.springmvc.springframework.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MutablePropertyValues implements PropertyValues, Serializable {

    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValues() {
        propertyValueList = new ArrayList<>(0);
    }

    public MutablePropertyValues addPropertyValue(PropertyValue pv) {
        for (int i = 0; i < this.propertyValueList.size(); i++) {
            PropertyValue currentPv = this.propertyValueList.get(i);
            if (currentPv.getName().equals(pv.getName())) {
                setPropertyValueAt(pv, i);
                return this;
            }
        }
        this.propertyValueList.add(pv);
        return this;
    }


    private void setPropertyValueAt(PropertyValue pv, int i) {
        this.propertyValueList.set(i, pv);
    }

    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }

    /**
     * ArrayList 转换为 PropertyValue[] 数组
     */
    @Override
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[this.propertyValueList.size()]);
    }

    @Override
    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }
}
