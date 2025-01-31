package com.cryolytix.backend.enums;

/**
 * Defines the type of threshold checking:
 * - HIGH: Alert when value exceeds max.
 * - LOW: Alert when value drops below min.
 * - RANGE: Alert when value is outside a defined range.
 */
public enum ThresholdType {
    HIGH,    // Only max value matters (e.g., over-temperature alert)
    LOW,     // Only min value matters (e.g., under-voltage alert)
    RANGE    // Both min and max values are checked
}
