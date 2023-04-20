package com.zshop.helper.utils.cache;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

	private Map<K, V> cacheMap = new HashMap<>();

	public V get(K key) {
		return getCacheMap().get(key);
	}

	public V put(K key, V value) {
		return getCacheMap().put(key, value);
	}

	public V remove(K key) {
		return getCacheMap().remove(key);
	}

	public abstract int init();

	public Map<K, V> getCacheMap() {
		return cacheMap;
	}

	public boolean containsKey(K key) {

		return getCacheMap().containsKey(key);
	}
}
