package com.ibm.p1.service.impl;

import java.util.TimerTask;

class WakeUp extends TimerTask {

	private AcquireWeibo weibo = null;

	public WakeUp(AcquireWeibo weibo) {
		this.weibo = weibo;
	}

	@Override
	public void run() {
		System.out.println("启动定时器线程！");
		if (weibo == null) {
			return;
		}
		synchronized (weibo) {
			weibo.setSynFlag(true);
			weibo.notify();
		}

	}
}
