package com.test.server;

import java.io.Serializable;

public class Weather implements Serializable {
    /**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 3937801107498433681L;
	public int humidity;
    public int temperature;
    public int wind;

    @Override
    public String toString() {
        return "current weather is : humidity = " + humidity + " temperature = " + temperature + " wind= " + wind;
    }
}