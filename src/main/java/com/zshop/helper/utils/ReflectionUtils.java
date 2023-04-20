package com.zshop.helper.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ReflectionUtils {

	public static void invokeSetter(Object obj, String propertyName, Object variableValue) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(propertyName, obj.getClass());
			Method setter = pd.getWriteMethod();
			try {
				setter.invoke(obj, variableValue);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

	}

	public static Object invokeGetter(Object obj, String variableName) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(variableName, obj.getClass());
			Method getter = pd.getReadMethod();
			return getter.invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| IntrospectionException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean isValidDataType(Class<?> inClazz, String propertyName, Class<?> dataTypeClazz) {
		try {
			PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(inClazz, propertyName);
			if (Objects.nonNull(propertyDescriptor)) {
				Class<?> propertyTypeInClass = propertyDescriptor.getPropertyType();
				if (Objects.nonNull(propertyTypeInClass) && propertyTypeInClass.equals(dataTypeClazz)) {
					return true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// TODO : Test and remove invokeSetterV2 or invokeSetterV3

	/**
	 * Setter in case of nested object type
	 * 
	 * @param object
	 * @param fieldName
	 * @param fieldValue
	 */
	public static void invokeSetterV2(Object object, String fieldName, Object fieldValue) {
		try {
			if (fieldName.contains(".")) {
				int firstDotLocation = fieldName.indexOf('.');
				String childFieldName = fieldName.substring(0, firstDotLocation);
				Class<? extends Object> class1 = object.getClass();
				Method getter = class1.getDeclaredMethod(fieldToGetterName(childFieldName));
				Object childFieldInstance = getter.invoke(object);
				if (childFieldInstance == null) {
					Class<?> type = getter.getReturnType();
					childFieldInstance = type.getConstructor().newInstance();
					Method setter = object.getClass().getDeclaredMethod(fieldToSetterName(childFieldName), type);
					setter.invoke(object, childFieldInstance);
				}
				invokeSetterV2(childFieldInstance, fieldName.substring(firstDotLocation + 1), fieldValue);
			} else {
				String fieldToSetterName = fieldToSetterName(fieldName);
				Class<? extends Object> class1;
				if (fieldValue == null)
					class1 = String.class;
				else
					class1 = fieldValue.getClass();
				Method setter = object.getClass().getDeclaredMethod(fieldToSetterName, class1);
				setter.invoke(object, fieldValue);
			}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
				| InstantiationException e) {
			e.printStackTrace();
		}

	}

	private static String getClassName(Type type) {
		String fullName = type.toString();
		if (fullName.startsWith("class"))
			return fullName.substring("class".length());
		return fullName;
	}

	private static String fieldToGetterName(String fieldName) {
		return "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
	}

	private static String fieldToSetterName(String fieldName) {
		return "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
	}

	// TODO : Test and remove invokeSetterV2 or invokeSetterV3
	/**
	 * Setter in case of nested object type with list type params
	 * @param object
	 * @param fieldName
	 * @param fieldValue
	 */
	public static void invokeSetterV3(Object object, String fieldName, Object fieldValue) {
		try {
			if (fieldName.contains(".")) {
				int firstDotLocation = fieldName.indexOf('.');
				String childFieldName = fieldName.substring(0, firstDotLocation);
				Class<? extends Object> class1 = object.getClass();
				Method getter = class1.getDeclaredMethod(fieldToGetterName(childFieldName));
				Object childFieldInstance = getter.invoke(object);
				if (childFieldInstance == null) {
					Class<?> type = getter.getReturnType();

					if (type.equals(List.class)) {
						for (Field field : class1.getDeclaredFields()) {
							final Type t = field.getGenericType();
							if (t instanceof ParameterizedType) {

								ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
								Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
								childFieldInstance = stringListClass.getConstructor().newInstance();
								Method setter = object.getClass().getDeclaredMethod(fieldToSetterName(childFieldName),
										stringListClass); // should be List<stringListClass>
								setter.invoke(object, childFieldInstance);
							}
						}
					} else {
						childFieldInstance = type.getConstructor().newInstance();
						Method setter = object.getClass().getDeclaredMethod(fieldToSetterName(childFieldName), type);
						setter.invoke(object, childFieldInstance);
					}
				}
				invokeSetterV2(childFieldInstance, fieldName.substring(firstDotLocation + 1), fieldValue);
			} else {
				String fieldToSetterName = fieldToSetterName(fieldName);
				Class<? extends Object> class1 = fieldValue.getClass();
				Method setter = object.getClass().getDeclaredMethod(fieldToSetterName, class1);
				setter.invoke(object, fieldValue);
			}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
				| InstantiationException e) {
			e.printStackTrace();
		}

	}

}
