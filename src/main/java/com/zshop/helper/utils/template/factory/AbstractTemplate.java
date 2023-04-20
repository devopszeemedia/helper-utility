package com.zshop.helper.utils.template.factory;

/**
 * implement this interface to build a template based on passed enum
 *
 * @param <T> - enum type to get the template
 */
public interface AbstractTemplate<T> {

	T templateFor();

}
