/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.jms;

import javax.jms.JMSException;

public class ConsumerMain {

	public static void main(String[] args) {

		consumeCustomerVTCQueue();
		consumerVTCQueueWithSelector();
		consumeGroup1Topic();
		consumeAllGroup2();
		consume_queue_with_prefetchsize();

	}

	private static void consumeCustomerVTCQueue() {
		// the message in the topic before this subscriber starts will not be
		// picked up.
		Consumer queueMsgListener = new Consumer("tcp://localhost:61616", "admin",
				"admin");
		queueMsgListener.setDestinationName("Consumer.zheng.VirtualTopic.Consumer.Topic");

		try {
			queueMsgListener.run();
		} catch (JMSException e) {

			e.printStackTrace();
		}
	}

	private static void consumerVTCQueueWithSelector() {
		Consumer queueMsgListener = new Consumer("tcp://localhost:61616", "admin",
				"admin");
		queueMsgListener.setDestinationName("VTC.DZONE.JCG.Mary.Topic");
		queueMsgListener.setSelector("action='DZONE'");
		try {
			queueMsgListener.run();
		} catch (JMSException e) {

			e.printStackTrace();
		}
	}

	private static void consumeGroup1Topic() {
		Consumer queueMsgListener = new Consumer("tcp://localhost:61616", "admin",
				"admin");
		queueMsgListener.setDestinationName("test.group1.topic");
		queueMsgListener.setDestinationTopic(true);

		try {
			queueMsgListener.run();
		} catch (JMSException e) {

			e.printStackTrace();
		}
	}

	private static void consumeAllGroup2() {
		Consumer queueMsgListener = new Consumer("tcp://localhost:61616", "admin",
				"admin");
		queueMsgListener.setDestinationName("*.group2.*");

		try {
			queueMsgListener.run();
		} catch (JMSException e) {

			e.printStackTrace();
		}
	}

	private static void exclusive_queue_Consumer() {
		Consumer queueMsgListener = new Consumer("tcp://localhost:61616", "admin",
				"admin");
		queueMsgListener.setDestinationName("test.group2.queue2?consumer.exclusive=true");

		try {
			queueMsgListener.run();
		} catch (JMSException e) {

			e.printStackTrace();
		}
	}

	private static void consume_queue_with_prefetchsize() {
		Consumer queueMsgListener = new Consumer("tcp://localhost:61616", "admin",
				"admin");
		queueMsgListener.setDestinationName("test.group1.queue2?consumer.prefetchSize=10");

		try {
			queueMsgListener.run();
		} catch (JMSException e) {

			e.printStackTrace();
		}
	}

}
