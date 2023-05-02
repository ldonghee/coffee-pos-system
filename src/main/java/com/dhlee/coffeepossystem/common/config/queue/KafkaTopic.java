package com.dhlee.coffeepossystem.common.config.queue;

public enum KafkaTopic {
	ORDER("order");

	private String topicName;

	KafkaTopic(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicName() {
		return topicName;
	}
}
