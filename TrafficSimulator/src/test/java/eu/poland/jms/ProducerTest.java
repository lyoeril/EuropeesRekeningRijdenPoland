/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.poland.jms;

import javax.jms.JMSException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Bart
 */
public class ProducerTest {
    
    private Producer msgQueueSender;

	@Before
	public void setup() {
		msgQueueSender = new Producer("tcp://localhost:61616", "admin", "admin");
	}

	@After
	public void cleanup() throws JMSException {
		msgQueueSender.close();
	}

	@Test
	public void send_msg_to_no_transaction_Queue() throws JMSException {
		msgQueueSender.setup(false, false, DataUtil.TEST_GROUP1_QUEUE_1);
		msgQueueSender.sendMessage("JCG");
	}

	@Test
	public void send_msg_to_Group2_Queue1() throws JMSException {
		msgQueueSender.setup(false, false, DataUtil.TEST_GROUP2_QUEUE_1);
		msgQueueSender.sendMessage("JCG");
	}

	@Test
	public void send_msg_to_transaction_Group1_Queue2() throws JMSException {
		msgQueueSender.setup(true, false, DataUtil.TEST_GROUP1_QUEUE_2);
		msgQueueSender.sendMessage("DEMO");
		msgQueueSender.commit(true);
	}

	@Test
	public void send_msg_to_no_transaction_Group1_Topic() throws JMSException {
		msgQueueSender.setup(false, true, DataUtil.TEST_GROUP1_TOPIC);
		msgQueueSender.sendMessage("MZHENG");
	}

	@Test
	public void send_msg_to_Virtual_Topic() throws JMSException {
		msgQueueSender.setup(false, true, DataUtil.CUSTOMER_VTC_TOPIC);
		msgQueueSender.sendMessage("MZHENG");
	}

	@Test
	public void send_msg_to_Virtual_Topic_WithSelector() throws JMSException {
		msgQueueSender.setup(false, true, DataUtil.TEST_VTC_TOPIC_SELECTOR);
		msgQueueSender.sendMessage("DZONE");
	}
    
}
