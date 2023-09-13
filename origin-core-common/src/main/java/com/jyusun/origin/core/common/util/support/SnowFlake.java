package com.jyusun.origin.core.common.util.support;

import com.jyusun.origin.core.common.util.DateUtil;

/**
 * 作用描述：
 * <p>
 * - ID生成
 *
 * @author JyuSun at 2019/1/3 11:26
 * @version 1.0.0
 */
public class SnowFlake {

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;

    /**
     * 占用的位数：数据中心
     */
    private final static long DATA_CENTER_BIT = 5;

    /**
     * 占用的位数：机器标识
     */
    private final static long MACHINE_BIT = 5;

    /**
     * 占用的位数：序列号
     */
    private final static long SEQUENCE_BIT = 12;

    /**
     * 最大值：数据中心
     */
    private final static long MAX_DATACENTER_NUM = ~(-1L << DATA_CENTER_BIT);

    /**
     * 最大值：机器标识
     */
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);

    /**
     * 最大值：序列号
     */
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 向左的位移：数据中心
     */
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    /**
     * 向左的位移：时间戳
     */
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATA_CENTER_BIT;

    /**
     * 向左的位移：机器标识
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;

    /**
     * 数据中心
     */
    private long dataCenterId;

    /**
     * 机器标识
     */
    private long machineId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上一次时间戳
     */
    private long lastStmp = -1L;

    public SnowFlake(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATACENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     */
    public long nextId() {
        synchronized (this) {
            long currStmp = DateUtil.timeStamp();
            if (currStmp < lastStmp) {
                throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
            }

            if (currStmp == lastStmp) {
                // if条件里表示当前调用和上一次调用落在了相同毫秒内，只能通过第三部分，序列号自增来判断为唯一，所以+1.
                sequence = (sequence + 1) & MAX_SEQUENCE;
                // 同一毫秒的序列数已经达到最大，只能等待下一个毫秒
                if (sequence == 0L) {
                    currStmp = getNextMill();
                }
            }
            else {
                // 不同毫秒内，序列号置为0
                // 执行到这个分支的前提是currTimestamp >
                // lastTimestamp，说明本次调用跟上次调用对比，已经不再同一个毫秒内了，这个时候序号可以重新回置0了。
                sequence = 0L;
            }

            lastStmp = currStmp;
            /* 就是用相对毫秒数、机器ID和自增序号拼接 */
            /* 时间戳部分 */
            return (currStmp - START_STMP) << TIMESTMP_LEFT
                    /* 数据中心部分 */
                    | dataCenterId << DATACENTER_LEFT
                    /* 机器标识部分 */
                    | machineId << MACHINE_LEFT
                    /* 序列号部分 */
                    | sequence;
        }
    }

    private long getNextMill() {
        long mill = DateUtil.timeStamp();
        while (mill <= lastStmp) {
            mill = DateUtil.timeStamp();
        }
        return mill;
    }

}
