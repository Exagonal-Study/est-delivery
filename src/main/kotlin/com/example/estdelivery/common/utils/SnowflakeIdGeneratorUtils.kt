package com.example.estdelivery.common.utils

import com.example.estdelivery.common.Constant.MAX_MACHINE_ID_LENGTH
import java.time.Instant

object SnowflakeIdGeneratorUtils {
    private const val sequenceBits = 12
    private const val maxMachineId = (-1L).xor(-1L shl MAX_MACHINE_ID_LENGTH)
    private const val sequenceMask = (-1L).xor(-1L shl sequenceBits)

    private var lastTimestamp = -1L
    private var sequence = 0L

    fun generateId(machineId: Long, epoch: Long = 1627862400000L): String {
        require(machineId in 0..maxMachineId) { "Machine ID must be between 0 and $maxMachineId" }

        var timestamp = timeGen()

        if (timestamp < lastTimestamp) {
            throw RuntimeException("Clock moved backwards. Refusing to generate id for ${lastTimestamp - timestamp} milliseconds")
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) and sequenceMask
            if (sequence == 0L) {
                timestamp = tilNextMillis(lastTimestamp)
            }
        } else {
            sequence = 0
        }

        lastTimestamp = timestamp

        return (((timestamp - epoch) shl (MAX_MACHINE_ID_LENGTH + sequenceBits)) or (machineId shl sequenceBits) or sequence).toString()
    }

    private fun tilNextMillis(lastTimestamp: Long): Long {
        var timestamp = timeGen()
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen()
        }
        return timestamp
    }

    private fun timeGen(): Long {
        return Instant.now().toEpochMilli()
    }
}